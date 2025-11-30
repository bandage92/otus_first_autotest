#!/bin/bash

# ./selenoid.sh --browserName chrome --browserVersion 128
# ./selenoid.sh --browserName firefox --browserVersion 125

BROWSER_NAME=""
BROWSER_VERSION=""
SELENOID_URL="${SELENOID_URL:-http://188.130.251.59/wd/hub}"

while [[ $# -gt 0 ]]; do
    case $1 in
        --browserName)
            BROWSER_NAME="$2"
            shift
            shift
            ;;
        --browserVersion)
            BROWSER_VERSION="$2"
            shift
            shift
            ;;
        --url)
            SELENOID_URL="$2"
            shift
            shift
            ;;
        *)
            echo "Unknown parameter: $1."
            exit 1
    esac
done

if [ -z "$BROWSER_NAME" ] || [ -z "$BROWSER_VERSION" ]; then
    echo "Error: browser name and version are required."
    exit 1
fi

echo "Checking Selenoid connection..."
if ! curl -s "$SELENOID_URL/status" | grep "ready" > /dev/null; then
    echo "Error: Selenoid is not available at $SELENOID_URL."
    echo "Please make sure Selenoid is running in Docker."
    exit 1
fi

echo "✓ Selenoid is available."

echo "Starting Maven tests with $BROWSER_NAME $BROWSER_VERSION..."
echo "=========================================="

mvn test -DbrowserName="$BROWSER_NAME" \
         -DbrowserVersion="$BROWSER_VERSION" \
         -Dremote.url="$SELENOID_URL"

TEST_RESULT=$?

echo "=========================================="
if [ $TEST_RESULT -eq 0 ]; then
    echo "✓ Tests completed successfully."
else
    echo "✗ Tests failed with exit code: $TEST_RESULT."
fi

exit $TEST_RESULT