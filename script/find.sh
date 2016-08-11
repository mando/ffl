#!/bin/bash

q=$1

#curl -s "http://localhost:8181/document/find?q=$q" | jq .
curl -s "http://ffl.mando.org:8181/document/find?q=$q" | jq .
