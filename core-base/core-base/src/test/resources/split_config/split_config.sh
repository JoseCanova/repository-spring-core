#!/bin/bash

# This script splits a large YAML configuration file into multiple smaller YAML files.
# Each new file is named after a top-level block (e.g., 'artist.yml')
# and contains the content of that specific block from the original file.

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

current_block_name=""
output_file=""

# Process the YAML file line by line
while IFS= read -r line || [[ -n "$line" ]]; do
    # Remove leading whitespace to check for block names
    trimmed_line=$(echo "$line" | sed -e 's/^[[:blank:]]*//')

    # Check if the line is a new top-level block (e.g., "artist:", "area:")
    # A block name starts at the beginning of the line (after trimming whitespace)
    # and ends with a colon, followed by optional whitespace.
    if [[ "$trimmed_line" =~ ^[[:alnum:]_]+:[[:blank:]]*$ ]]; then
        # Extract the block name (remove the trailing colon and any whitespace)
        new_block_name=$(echo "$trimmed_line" | sed -e 's/:[[:blank:]]*$//')

        # If an output file was open, close it
        if [ -n "$output_file" ]; then
            exec 1>&- # Close stdout (which was redirected to the previous file)
        fi

        # Set the new current block name and output file path
        current_block_name="$new_block_name"
        output_file="${output_dir}/${current_block_name}.yml"

        echo "Creating file: '$output_file'"
        # Redirect stdout to the new output file
        exec 1> "$output_file"

    fi

    # Write the current line to the active output file
    # This includes the block header itself
    echo "$line"

done < "$input_file"

# Close the last opened output file if any
if [ -n "$output_file" ]; then
    exec 1>&- # Close stdout
fi

echo "--- Splitting complete ---"
