#!/bin/bash

# This script splits a large YAML configuration file into multiple smaller YAML files.
# Each new file is named after a top-level block (e.g., 'artist.yml')
# and contains the content of that specific block from the original file.
# It ensures that only content up to and including the 'baseMap' section is included,
# effectively removing any lines that appear after the baseMap.

# Check if a file argument is provided
if [ -z "$1" ]; then
    echo "Usage: $0 <input_yaml_file.yml> [output_directory]" >&2
    echo "  <input_yaml_file.yml> : The path to the YAML file to be split." >&2
    echo "  [output_directory]    : (Optional) The directory where the split YAML files will be saved." >&2
    echo "                          If not provided, files will be saved in the current directory." >&2
    exit 1
fi

input_file="$1"
output_dir="${2:-.}" # Use current directory as default if no output_directory is provided

# Check if the input file exists
if [ ! -f "$input_file" ]; then
    echo "Error: Input YAML file '$input_file' not found." >&2
    exit 1
fi

# Create the output directory if it doesn't exist
mkdir -p "$output_dir" || { echo "Error: Could not create directory '$output_dir'."; exit 1; }

echo "--- Splitting YAML file: '$input_file' into '$output_dir' ---"

# Use awk to process the file and split it
# The entire awk program is enclosed in single quotes for robust parsing by Bash.
awk -v output_dir="$output_dir" '
BEGIN {
    current_output_file = "";
    in_base_map_section = 0;
}

# Rule 1: Identify new top-level blocks
/^[[:alnum:]_]+:[[:blank:]]*$/ {
    if (current_output_file != "") {
        close(current_output_file);
    }
    
    block_name = substr($0, 1, index($0, ":") - 1);
    current_output_file = output_dir "/" block_name ".yml";
    
    printf "Creating file: %s\n", current_output_file > "/dev/stderr";
    
    in_base_map_section = 0; # Reset for new block
    
    print $0 > current_output_file; # Write the block header
    
    next;
}

# Rule 2: Identify the start of a 'baseMap:' section
/^[[:blank:]]{2,}baseMap:[[:blank:]]*$/ {
    if (current_output_file != "") {
        in_base_map_section = 1;
        print $0 >> current_output_file; # Write the "baseMap:" line
    }
    next;
}

# Rule 3: Process lines within a block (before or within baseMap)
{
    if (current_output_file != "") {
        # If we are in the baseMap section
        if (in_base_map_section == 1) {
            # Only write if the line is an indented baseMap entry
            if ($0 ~ /^[[:blank:]]{4,}[^[:blank:]]/) {
                print $0 >> current_output_file;
            } else if ($0 !~ /^[[:blank:]]*$/) { # If not an empty line, and not a baseMap entry, then baseMap section has ended.
                in_base_map_section = 0; # End baseMap collection
                close(current_output_file); # Close the file to stop writing
                current_output_file = ""; # Invalidate the file handle
            }
        } else { # If we are NOT yet in the baseMap section
            # Write lines that are not empty and are part of the block's initial metadata
            if ($0 ~ /^[[:blank:]]{2,}(fileLocation|fileName|immutable):/ || $0 ~ /^[[:blank:]]*$/) {
                print $0 >> current_output_file;
            }
        }
    }
}
' "$input_file"

echo "--- Splitting complete ---"
