#!/bin/bash

URL=$1
CONFIG_FILE="${CONFIG_FILE:-$(dirname $0)/../config/development.yml}"

if [ -z "$URL" ]; then
    echo "Usage: $0 <url>" 1>&2
    exit 1
fi

java \
    -jar $(dirname $0)/../target/ffl*.jar \
     index -u ${URL} \
     ${CONFIG_FILE} \
;
