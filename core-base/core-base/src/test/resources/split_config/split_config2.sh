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

# Use awk to process the file and split it
awk -v output_dir="$output_dir" '
BEGIN {
    current_output_file = ""; # Stores the name of the current file being written to
}

# This rule matches lines that define a new top-level block (e.g., "artist:", "area:")
# It looks for lines starting at the beginning (after trimming whitespace)
# with alphanumeric characters or underscores, followed by a colon and optional whitespace.
/^[[:alnum:]_]+:[[:blank:]]*$/ {
    # Extract the block name by removing the trailing colon and any whitespace
    block_name = substr($0, 1, index($0, ":") - 1);
    
    # If we were writing to a previous file, close it to ensure data is flushed
    if (current_output_file != "") {
        close(current_output_file);
    }
    
    # Construct the full path for the new output file
    current_output_file = output_dir "/" block_name ".yml";
    print "Creating file: " current_output_file; # Print status to stderr for user feedback
}

# This rule applies to every line. It writes the line to the current output file.
# The redirection ">" creates/overwrites the file on the first write, and ">>" appends.
# Since we close the file and reopen it with ">" for each new block, this works correctly.
{
    if (current_output_file != "") {
        print $0 > current_output_file;
    }
}
' "$input_file"

echo "--- Splitting complete ---"
