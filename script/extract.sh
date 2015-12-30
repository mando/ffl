#!/bin/bash

URL=$1

if [ -z "$URL" ]; then
    echo "Usage: $0 <url>" 1>&2
    exit 1
fi

java \
    -jar $(dirname $0)/../target/ffl*.jar \
     extract -u ${URL} \
;
