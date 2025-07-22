#!/bin/bash

# This script reads a YAML-like configuration file,
# extracts the 'baseMap' sections for each defined block,
# and prints the mapping to the console.
# It can optionally filter to print only a specific block's mapping.

# Define the expected number of columns based on your layout (0-18 makes 19 columns)
expected_columns=19

# Define the column index for the artistName (0-indexed, so 2 for the 3rd column)
artist_name_column_index=2

# Check if a file argument is provided
if [ -z "$1" ]; then
    echo "Usage: $0 <your_yaml_config.yml> [block_name]" >&2
    echo "  <your_yaml_config.yml> : Path to your YAML configuration file." >&2
    echo "  [block_name]           : (Optional) The name of a specific block (e.g., 'artist', 'area') to print its baseMap." >&2
    exit 1
fi

input_file="$1"
target_block_name="$2" # Capture the optional second argument

# Check if the input file exists
if [ ! -f "$input_file" ]; then
    echo "Error: File '$input_file' not found." >&2
    exit 1
fi

echo "--- Processing YAML mapping for CSV files ---"
if [ -n "$target_block_name" ]; then
    echo "Filtering for block: [$target_block_name]"
fi
echo "" # Add a newline for better readability

# Use awk to parse the YAML-like structure
# Pass the target_block_name to awk using -v
awk -v target_block="$target_block_name" '
BEGIN {
    in_base_map = 0;
    current_block_name = "";
    file_location_awk = "";
    file_name_awk = "";
    base_map_buffer = ""; # To store baseMap entries before printing
    if (target_block == "") {
        should_print_block = 1;
    } else {
        should_print_block = 0;
    }
}

# Function to print all collected data for a block
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
            # Print the buffered baseMap entries
            printf "%s", base_map_buffer;
        }
        # Construct and print absoluteFileLocation
        if (file_location_awk != "" && file_name_awk != "") {
            absolute_file_location = file_location_awk "/" file_name_awk;
            print "absoluteFileLocation: " absolute_file_location;
        }
        print ""; # Separator after each block
    }
}

# Rule 1: Identify top-level block names
/^[[:alnum:]_]+:/ {
    # If a new block starts, print data for the previous block if applicable
    print_block_data();

    # Reset state for the new block
    in_base_map = 0;
    current_block_name = substr($0, 1, index($0, ":") - 1);
    file_location_awk = "";
    file_name_awk = "";
    base_map_buffer = "";

    # Determine if we should print this new block
    if (target_block == "" || current_block_name == target_block) {
        should_print_block = 1;
    } else {
        should_print_block = 0;
    }
    next;
}

# Rule 2: Capture fileLocation
/^[[:space:]]{2,}fileLocation:/ {
    if (should_print_block == 1) {
        file_location_awk = substr($0, index($0, ":") + 2);
        sub(/^[[:space:]]*/, "", file_location_awk);
    }
    next;
}

# Rule 3: Capture fileName
/^[[:space:]]{2,}fileName:/ {
    if (should_print_block == 1) {
        file_name_awk = substr($0, index($0, ":") + 2);
        sub(/^[[:space:]]*/, "", file_name_awk);
    }
    next;
}

# Rule 4: Identify the 'baseMap:' line
/^[[:space:]]{2,}baseMap:/ {
    if (should_print_block == 1) {
        in_base_map = 1;
    }
    next;
}

# Rule 5: Process lines when inside a 'baseMap' section
{
    if (in_base_map == 1 && should_print_block == 1) {
        if ($0 ~ /^[[:space:]]{4,}[^[:space:]]/) {
            line = $0;
            sub(/^[[:space:]]+/, "", line);
            base_map_buffer = base_map_buffer "    " line "\n"; # Buffer with indentation
        } else {
            in_base_map = 0; # End of baseMap section
        }
    }
}

# END block: Print data for the last block processed
END {
    print_block_data();
}
' "$input_file"
