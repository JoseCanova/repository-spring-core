#!/bin/bash

# This script reads a YAML-like configuration file,
# extracts the 'baseMap' sections for each defined block,
# and prints the mapping to the console.

# Check if a file argument is provided
if [ -z "$1" ]; then
    echo "Usage: $0 <your_yaml_config.yml>" >&2
    echo "Please provide the path to your YAML configuration file." >&2
    exit 1
fi

input_file="$1"

# Check if the input file exists
if [ ! -f "$input_file" ]; then
    echo "Error: File '$input_file' not found." >&2
    exit 1
fi

echo "--- Processing YAML mapping for CSV files ---"
echo "" # Add a newline for better readability

# Use awk to parse the YAML-like structure
awk '
BEGIN {
    in_base_map = 0; # Flag to indicate if we are currently inside a baseMap section
    current_block_name = ""; # Stores the name of the current top-level block (e.g., "artist")
}

# Rule 1: Identify top-level block names (e.g., "artist:", "area:", "areatype:")
# This matches lines that start at the beginning of the line (no leading spaces)
# and contain alphanumeric characters or underscores followed by a colon.
/^[[:alnum:]_]+:/ {
    # If we were previously in a baseMap section from a prior block,
    # reset the flag and add a separator for clarity.
    if (in_base_map == 1) {
        in_base_map = 0;
        print ""; # Add a newline for separation between different block mappings
    }
    # Extract the block name by taking the substring before the first colon.
    current_block_name = substr($0, 1, index($0, ":") - 1);
    print "Mapping for [" current_block_name "]:";
    next; # Move to the next line to continue parsing
}

# Rule 2: Identify the 'baseMap:' line within a block
# This matches lines that are indented by at least two spaces and contain "baseMap:".
/^[[:space:]]{2,}baseMap:/ {
    in_base_map = 1; # Set the flag to true, indicating we are now in a baseMap section
    next; # Move to the next line to process the baseMap entries
}

# Rule 3: Process lines when inside a 'baseMap' section
{
    if (in_base_map == 1) {
        # Check if the current line is an entry of the baseMap.
        # This is determined by checking if the line starts with at least four spaces,
        # followed by any non-space character (to exclude empty lines or comments).
        if ($0 ~ /^[[:space:]]{4,}[^[:space:]]/) {
            # Create a copy of the line to modify.
            line = $0;
            # Remove all leading spaces from the line.
            sub(/^[[:space:]]+/, "", line);
            # Print the cleaned mapping entry.
            print line;
        } else {
            # If the line is not indented by at least four spaces,
            # it means the baseMap section has ended (or a new top-level block started,
            # which is handled by Rule 1). So, reset the flag.
            in_base_map = 0;
        }
    }
}
' "$input_file"