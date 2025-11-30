#!/bin/bash

# ./search.sh --file test.txt --search "apple"

FILE=""
SEARCH=""

while [[ $# -gt 0 ]]; do
    case $1 in
        --file)
            FILE="$2"
            shift
            shift
            ;;
        --search)
            SEARCH="$2"
            shift
            shift
            ;;
        *)
            echo "Параметр $1 не поддерживается."
            exit 1
    esac
done

if [ -z "$FILE" ] || [ -z "$SEARCH" ]; then
    echo "Ошибка: file и search являются обязательными аргументами."
    exit 1
fi

if [ ! -f "$FILE" ]; then
    echo "Ошибка: файл '$FILE' не найден."
    exit 1
fi

ABSOLUTE_PATH=$(realpath "$FILE" 2>/dev/null || echo "$FILE")

MATCH_COUNT=$(grep -x -c -- "$SEARCH" "$FILE" 2>/dev/null)

if [ -z "$MATCH_COUNT" ]; then
    echo "Ошибка: поиск не удался."
    exit 1
elif [ "$MATCH_COUNT" -eq 0 ]; then
    echo "Не найдено ни одного совпадения в файле $ABSOLUTE_PATH."
else
    echo "$MATCH_COUNT"
fi