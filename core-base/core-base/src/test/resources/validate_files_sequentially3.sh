#!/bin/bash

# This script scans a specified directory recursively for .yml files.
# For each .yml file found, it executes the 'validate_file_mappinglayout4.sh' script
# with the file's full path, its base name (without extension), and the 'validate' action.
# It continues processing even if individual validations fail.

# --- Configuration ---
VALIDATE_SCRIPT="./validate_file_mappinglayout4.sh"     # Adjust this path as needed
SCAN_DIRECTORY="./split_config/files2"                   # Adjust this to your target directory

# --- Pre-checks ---
if [ ! -f "$VALIDATE_SCRIPT" ]; then
    echo "Error: Validation script '$VALIDATE_SCRIPT' not found." >&2
    exit 1
fi

if [ ! -x "$VALIDATE_SCRIPT" ]; then
    echo "Error: Validation script '$VALIDATE_SCRIPT' is not executable. Run: chmod +x $VALIDATE_SCRIPT" >&2
    exit 1
fi

if [ ! -d "$SCAN_DIRECTORY" ]; then
    echo "Error: Scan directory '$SCAN_DIRECTORY' not found." >&2
    exit 1
fi

echo "--- Starting recursive validation of YAML files in '$SCAN_DIRECTORY' ---"
echo "--- Using validation script: '$VALIDATE_SCRIPT' ---"
echo ""

# --- Counters ---
TOTAL_FILES=0
SUCCESSFUL_VALIDATIONS=0
FAILED_VALIDATIONS=0

# --- File Processing ---
while IFS= read -r -d $'\0' yml_file; do
    TOTAL_FILES=$((TOTAL_FILES + 1))

    FULL_PATH="$yml_file"
    BLOCK_NAME=$(basename "$yml_file" | sed 's/\.yml$//')

    echo "Processing file: '$FULL_PATH' (Block: '$BLOCK_NAME')"
    echo "-----------------------------------------------------"

    VALIDATION_OUTPUT=$("$VALIDATE_SCRIPT" "$FULL_PATH" "$BLOCK_NAME" "validate" 2>&1 || true)
    echo "$VALIDATION_OUTPUT"

    if echo "$VALIDATION_OUTPUT" | grep -q "Error:"; then
        echo "Validation for '$BLOCK_NAME' FAILED. (See errors above)"
        FAILED_VALIDATIONS=$((FAILED_VALIDATIONS + 1))
    else
        echo "Validation for '$BLOCK_NAME' SUCCEEDED."
        SUCCESSFUL_VALIDATIONS=$((SUCCESSFUL_VALIDATIONS + 1))
    fi

    echo ""
done < <(find "$SCAN_DIRECTORY" -type f -name "*.yml" -print0)

# --- Summary ---
echo "--- Validation Summary Report ---"
echo "Total YAML files processed: $TOTAL_FILES"
echo "Successful validations:     $SUCCESSFUL_VALIDATIONS"
echo "Failed validations:         $FAILED_VALIDATIONS"
echo "--- Report End ---"

# Exit code reflects success/failure
if [ "$FAILED_VALIDATIONS" -gt 0 ]; then
    exit 1
else
    exit 0
fi
