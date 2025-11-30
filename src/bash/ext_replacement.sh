#!/bin/bash

# ./ext_replacement.sh --file document.pdf --file image.jpg --file picture.png --extension jpg --replacement png
# ./ext_replacement.sh --file test.txt --file test2.sh --extension txt --replacement sh

FILE=()
EXTENSION=""
REPLACEMENT=""

while [[ $# -gt 0 ]]; do
    case $1 in
        --file)
            FILE+=("$2")
            shift
            shift
            ;;
        --extension)
            EXTENSION="$2"
            shift
            shift
            ;;
        --replacement)
            REPLACEMENT="$2"
            shift
            shift
            ;;
        *)
            echo "Parameter $1 not supported."
            exit 1
    esac
done

if [ ${#FILE[@]} -eq 0 ]; then
    echo "Error: no file argument specified."
    exit 1
fi

if [ -z "$EXTENSION" ]; then
    echo "Error: no extension argument specified."
    exit 1
fi

if [ -z "$REPLACEMENT" ]; then
    echo "Error: no replacement argument specified."
    exit 1
fi

for file in "${FILE[@]}"; do
    filename=$(basename "$file")
    name="${filename%.*}"
    current_ext="${filename##*.}"

    if [ "$current_ext" = "$EXTENSION" ]; then
        echo "$name.$REPLACEMENT"
    else
        echo "$filename"
    fi
done