#!/bin/bash

uri=$1

curl -s -H 'Content-Type:application/json' -X POST "http://ffl.mando.org:8181/document" -d "{ \"uri\": \"$uri\" }" | jq .
