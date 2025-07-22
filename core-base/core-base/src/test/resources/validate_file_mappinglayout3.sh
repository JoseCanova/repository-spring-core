#!/bin/bash

# This script reads a YAML-like configuration file,
# extracts the 'baseMap' sections for each defined block,
# and prints the mapping to the console.
# It can optionally filter to print only a specific block's mapping.
# With a third argument 'validate', it will check the column conformance
# of the associated CSV file based on the baseMap definition.

# Check if a file argument is provided
if [ -z "$1" ]; then
    echo "Usage: $0 <your_yaml_config.yml> [block_name] [action]" >&2
    echo "  <your_yaml_config.yml> : Path to your YAML configuration file." >&2
    echo "  [block_name]           : (Optional) The name of a specific block (e.g., 'artist', 'area') to print its baseMap or validate its CSV." >&2
    echo "  [action]               : (Optional) 'validate' to perform CSV column validation for the specified block. If omitted, prints baseMap." >&2
    exit 1
fi

input_file="$1"
target_block_name="$2" # Capture the optional second argument
action="$3"            # Capture the optional third argument (e.g., "validate")

# Check if the input file exists
if [ ! -f "$input_file" ]; then
    echo "Error: YAML configuration file '$input_file' not found." >&2
    exit 1
fi

# Function to print mapping details (original behavior when no action is specified)
print_mapping_details() {
    local yaml_file="$1"
    local block_name="$2"

    echo "--- Processing YAML mapping for CSV files ---"
    if [ -n "$block_name" ]; then
        echo "Filtering for block: [$block_name]"
    fi
    echo "" # Add a newline for better readability

    awk -v target_block="$block_name" '
    BEGIN {
        in_base_map = 0;
        current_block_name = "";
        file_location_awk = "";
        file_name_awk = "";
        base_map_buffer = "";
        if (target_block == "") {
            should_print_block = 1;
        } else {
            should_print_block = 0;
        }
    }

    function print_block_data() {
        if (should_print_block == 1 && current_block_name != "") {
            print "Mapping for [" current_block_name "]:";
            if (file_location_awk != "") {
                print "fileLocation: " file_location_awk;
            }
            if (file_name_awk != "") {
                print "fileName: " file_name_awk;
            }
            if (base_map_buffer != "") {
                print "  baseMap:";
                printf "%s", base_map_buffer;
            }
            if (file_location_awk != "" && file_name_awk != "") {
                absolute_file_location = file_location_awk "/" file_name_awk;
                print "absoluteFileLocation: " absolute_file_location;
            }
            print "";
        }
    }

    /^[[:alnum:]_]+:/ {
        print_block_data();
        in_base_map = 0;
        current_block_name = substr($0, 1, index($0, ":") - 1);
        file_location_awk = "";
        file_name_awk = "";
        base_map_buffer = "";
        if (target_block == "" || current_block_name == target_block) {
            should_print_block = 1;
        } else {
            should_print_block = 0;
        }
        next;
    }

    /^[[:space:]]{2,}fileLocation:/ {
        if (should_print_block == 1) {
            file_location_awk = substr($0, index($0, ":") + 2);
            sub(/^[[:space:]]*/, "", file_location_awk);
        }
        next;
    }

    /^[[:space:]]{2,}fileName:/ {
        if (should_print_block == 1) {
            file_name_awk = substr($0, index($0, ":") + 2);
            sub(/^[[:space:]]*/, "", file_name_awk);
        }
        next;
    }

    /^[[:space:]]{2,}baseMap:/ {
        if (should_print_block == 1) {
            in_base_map = 1;
        }
        next;
    }

    {
        if (in_base_map == 1 && should_print_block == 1) {
            if ($0 ~ /^[[:space:]]{4,}[^[:space:]]/) {
                line = $0;
                sub(/^[[:space:]]+/, "", line);
                base_map_buffer = base_map_buffer "    " line "\n";
            } else {
                in_base_map = 0;
            }
        } else if (in_base_map == 1 && should_print_block == 0) {
            if (!($0 ~ /^[[:space:]]{4,}[^[:space:]]/)) {
                in_base_map = 0;
            }
        }
    }

    END {
        print_block_data();
    }
    ' "$yaml_file"
}


# --- Main Logic based on 'action' argument ---

if [ -z "$action" ]; then
    # Default behavior: Print mapping
    print_mapping_details "$input_file" "$target_block_name"

elif [ "$action" == "validate" ]; then
    # First, print the mapping details as if no action was specified
    print_mapping_details "$input_file" "$target_block_name"

    # Then, proceed with validation logic
    if [ -z "$target_block_name" ]; then
        echo "Error: A block name must be provided for the 'validate' action." >&2
        exit 1
    fi

    echo "--- Initiating CSV validation for block: [$target_block_name] ---"

    # Use awk to extract fileLocation, fileName, and baseMap content (for column count)
    # This simplified awk assumes the input file is a single block, as split by the previous script.
    # Output format: fileLocation\nfileName\nbaseMap_line_count
    extracted_data=$(awk '
    BEGIN {
        in_base_map_section = 0;
        file_location_val = "";
        file_name_val = "";
        base_map_lines = 0;
    }

    # Capture fileLocation
    /^[[:blank:]]{2,}fileLocation:[[:blank:]]*(.*)$/ {
        file_location_val = substr($0, index($0, ":") + 2);
        sub(/^[[:blank:]]*/, "", file_location_val);
    }
    # Capture fileName
    /^[[:blank:]]{2,}fileName:[[:blank:]]*(.*)$/ {
        file_name_val = substr($0, index($0, ":") + 2);
        sub(/^[[:blank:]]*/, "", file_name_val);
    }
    # Identify baseMap start
    /^[[:blank:]]{2,}baseMap:$/ {
        in_base_map_section = 1;
    }
    # Process lines within baseMap
    {
        if (in_base_map_section == 1) {
            # Check if the line is still part of baseMap (indented by at least 4 blanks)
            if ($0 ~ /^[[:blank:]]{4,}[^[:blank:]]/) {
                base_map_lines++;
            } else {
                # End of baseMap section (indentation dropped or new section started)
                in_base_map_section = 0;
            }
        }
    }

    END {
        print file_location_val;
        print file_name_val;
        print base_map_lines;
    }
    ' "$input_file")

    # Read the extracted data into Bash variables
    readarray -t parsed_values <<< "$extracted_data"
    file_location="${parsed_values[0]}"
    file_name="${parsed_values[1]}"
    expected_columns="${parsed_values[2]}"

    if [ -z "$file_location" ] || [ -z "$file_name" ]; then
        echo "Error: fileLocation or fileName not found for block '$target_block_name' in '$input_file'." >&2
        exit 1
    fi

    if [ -z "$expected_columns" ] || [ "$expected_columns" -eq 0 ]; then
        echo "Error: baseMap not found or is empty for block '$target_block_name' in '$input_file'." >&2
        exit 1
    fi

    csv_file_path="${file_location}/${file_name}"

    if [ ! -f "$csv_file_path" ]; then
        echo "Error: CSV file '$csv_file_path' not found at '$csv_file_path'." >&2
        exit 1
    fi

    # Use awk to validate the CSV file
    awk -F'\t' -v expected_cols="$expected_columns" -v block_name="$target_block_name" '
    {
        if (NF != expected_cols) {
            print "Error: CSV file \047" FILENAME "\047, line " NR " has " NF " columns. Expected " expected_cols " for block \047" block_name "\047." > "/dev/stderr";
            exit 1;
        }
    }
    ' "$csv_file_path"

    if [ $? -eq 0 ]; then
        echo "Validation successful for CSV file: '$csv_file_path' (Block: '$target_block_name'). All lines conform to the expected column count."
    else
        echo "Validation failed for CSV file: '$csv_file_path' (Block: '$target_block_name'). Please check the error message above."
        exit 1
    fi

else
    echo "Error: Invalid action specified. Use 'validate' or omit for default behavior." >&2
    exit 1
fi
