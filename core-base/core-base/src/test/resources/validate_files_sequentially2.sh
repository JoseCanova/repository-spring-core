#!/bin/bash

# This script scans a specified directory recursively for .yml files.
# For each .yml file found, it executes the 'validate_file_mappinglayout4.sh' script
# with the file's full path, its base name (without extension), and the 'validate' action.
# The script is designed to continue processing all files even if individual calls
# to 'validate_file_mappinglayout4.sh' result in errors.

# --- Configuration ---
# Path to the validate_file_mappinglayout4.sh script
# IMPORTANT: Update this path to where your validate_file_mappinglayout4.sh is located.
VALIDATE_SCRIPT="./validate_file_mappinglayout4.sh"

# Directory to scan for .yml files
# IMPORTANT: Update this path to the folder you want to scan.
SCAN_DIRECTORY="./split_config/files2" # Example: /path/to/your/yml_configs

# --- Script Logic ---

# Check if the validate script exists and is executable
if [ ! -f "$VALIDATE_SCRIPT" ]; then
    echo "Error: Validation script '$VALIDATE_SCRIPT' not found." >&2
    exit 1
fi
if [ ! -x "$VALIDATE_SCRIPT" ]; then
    echo "Error: Validation script '$VALIDATE_SCRIPT' is not executable. Please run 'chmod +x $VALIDATE_SCRIPT'." >&2
    exit 1
fi

# Check if the scan directory exists
if [ ! -d "$SCAN_DIRECTORY" ]; then
    echo "Error: Scan directory '$SCAN_DIRECTORY' not found." >&2
    exit 1
fi

echo "--- Starting recursive validation of YAML files in '$SCAN_DIRECTORY' ---"
echo "--- Using validation script: '$VALIDATE_SCRIPT' ---"
echo ""

# Initialize counters for the summary report
TOTAL_FILES=0
SUCCESSFUL_VALIDATIONS=0
FAILED_VALIDATIONS=0

# Use find to locate all .yml files recursively
# -type f: only regular files
# -name "*.yml": files ending with .yml
# -print0: prints filenames separated by a null character, robust for filenames with spaces/special chars
find "$SCAN_DIRECTORY" -type f -name "*.yml" -print0 | while IFS= read -r -d $'\0' yml_file; do
    TOTAL_FILES=$((TOTAL_FILES + 1))

    # Extract the full path of the .yml file
    FULL_PATH="$yml_file"

    # Extract the filename without extension (e.g., "area" from "/path/to/area.yml")
    # basename: extracts the filename from a path
    # sed 's/\.yml$//': removes the .yml extension
    BLOCK_NAME=$(basename "$yml_file" | sed 's/\.yml$//')

    echo "Processing file: '$FULL_PATH' (Block: '$BLOCK_NAME')"
    echo "-----------------------------------------------------"

    # Execute the validate_file_mappinglayout4.sh script and capture its output
    # We use '2>&1' to redirect stderr to stdout, so all output can be captured.
    # '|| true' still ensures the loop continues regardless of script's exit code.
    VALIDATION_OUTPUT=$("$VALIDATE_SCRIPT" "$FULL_PATH" "$BLOCK_NAME" "validate" 2>&1 || true)
    
    # Print the captured output to the console so the user can see the details
    echo "$VALIDATION_OUTPUT"

    # Determine validation status: if "Error:" is found, it's a failure; otherwise, it's a success.
    if echo "$VALIDATION_OUTPUT" | grep -q "Error:"; then
        echo "Validation for '$BLOCK_NAME' FAILED. (See errors above)"
        FAILED_VALIDATIONS=$((FAILED_VALIDATIONS + 1))
    else
        echo "Validation for '$BLOCK_NAME' SUCCEEDED."
        SUCCESSFUL_VALIDATIONS=$((SUCCESSFUL_VALIDATIONS + 1))
    fi
    echo "" # Add a blank line for readability between file processes

done

echo "--- Validation Summary Report ---"
echo "Total YAML files processed: $TOTAL_FILES"
echo "Successful validations:     $SUCCESSFUL_VALIDATIONS"
echo "Failed validations:         $FAILED_VALIDATIONS"
echo "--- Report End ---"

# Exit with a non-zero status if any validation failed
if [ "$FAILED_VALIDATIONS" -gt 0 ]; then
    exit 1
else
    exit 0
fi
