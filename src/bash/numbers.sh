#!/bin/bash

# ./numbers.sh 8

NUMBERS=()

while [[ $# -gt 0 ]]; do
    case $1 in
        *[!0-9]*)
            echo "Error: input a valid number."
            exit 1
            ;;
        *)
            NUMBERS+=("$1")
            shift
            ;;
    esac
done

if [ ${#NUMBERS[@]} -ne 1 ]; then
    echo "Error: input one number parameter."
    exit 1
fi

NUMBER="${NUMBERS[0]}"
middle=$((NUMBER / 2))

mult_result=1
for ((i=1; i<=middle; i++)); do
    mult_result=$((mult_result * i))
done

sum_result=0
start=$((middle + 1))
if [ $((NUMBER % 2)) -eq 1 ]; then
    start=$((start + 1))
fi

for ((i=start; i<=NUMBER; i++)); do
    sum_result=$((sum_result + i))
done

echo "mult: $mult_result"
echo "sum: $sum_result"