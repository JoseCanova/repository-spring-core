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
    file_location_awk = ""; # Variable to store fileLocation
    file_name_awk = "";     # Variable to store fileName
    if (target_block == "") {
        should_print_block = 1;
    } else {
        should_print_block = 0;
    }
}

/^[[:alnum:]_]+:/ {
    if (in_base_map == 1) {
        in_base_map = 0;
        if (should_print_block == 1 || target_block == "") {
            print "";
        }
    }

    current_block_name = substr($0, 1, index($0, ":") - 1);

    if (target_block == "" || current_block_name == target_block) {
        should_print_block = 1;
        print "Mapping for [" current_block_name "]:";
        # Reset fileLocation and fileName for the new block
        file_location_awk = "";
        file_name_awk = "";
    } else {
        should_print_block = 0;
    }
    next;
}

# Capture and print fileLocation
/^[[:space:]]{2,}fileLocation:/ {
    if (should_print_block == 1) {
        file_location_awk = substr($0, index($0, ":") + 2);
        sub(/^[[:space:]]*/, "", file_location_awk);
        print "  fileLocation: " file_location_awk;
    }
    next;
}

# Capture and print fileName
/^[[:space:]]{2,}fileName:/ {
    if (should_print_block == 1) {
        file_name_awk = substr($0, index($0, ":") + 2);
        sub(/^[[:space:]]*/, "", file_name_awk);
        print "  fileName: " file_name_awk;
    }
    next;
}

/^[[:space:]]{2,}baseMap:/ {
    if (should_print_block == 1) {
        in_base_map = 1;
        print "  baseMap:";
    }
    next;
}

{
    if (in_base_map == 1 && should_print_block == 1) {
        if ($0 ~ /^[[:space:]]{4,}[^[:space:]]/) {
            line = $0;
            sub(/^[[:space:]]+/, "", line);
            print "    " line;
        } else {
            in_base_map = 0;
        }
    } else if (in_base_map == 1 && should_print_block == 0) {
        if (!($0 ~ /^[[:space:]]{4,}[^[:space:]]/)) {
            in_base_map = 0;
        }
    }
}
' "$input_file"
