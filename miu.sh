#!/usr/bin/env bash

echo "Welcome to the Marvelous Indexing Utility"

unset n
input="./recordings-test.txt"

while IFS= read -u 3 -r line
do
  filename=$(echo ${line} | sed 's/  */ /g' | cut -d' ' -f 11-)
  date=$(echo ${line} | sed 's/  */ /g' | awk -F' ' '{ print $10"-"$8"-"$9 }')
  echo "Current file: "${filename}
  read -p "Tempo?" tempo
  read -p "Beat?" beat
  read -p "Style?" style
  read -p "Comments?" comments

  http POST localhost:8080/recordings \
  filename="${filename}" date="${date}" tempo="${tempo}" \
  beat="${beat}" style="${style}" comments="${comments}"

  : $((n++))
done 3< "$input"

sed "1,$n d" ${input}
