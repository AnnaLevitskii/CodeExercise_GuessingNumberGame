#!/bin/bash

mkdir -p .github/workflows/reports
OUTPUT_FILE=".github/workflows/reports/index.html"
  if [ -f "$OUTPUT_FILE" ]; then
    rm "$OUTPUT_FILE"
  fi
echo '<!DOCTYPE html>
          <html>
            <head>
               <link href="css/base-style.css" rel="stylesheet" type="text/css"/>
               <link href="css/style.css" rel="stylesheet" type="text/css"/>
               <title>Test Reports</title>
               <script src="js/report.js" type="text/javascript"></script>
            </head>
            <body>
               <h1>Test Summary</h1>
                                 ' > "$OUTPUT_FILE"
find build/reports/tests/ -type f -name 'index.html' | while read -r file; do
  if xmllint --xpath '//div[@id="tab0"]/table' "$file" >/dev/null 2>&1; then
   echo "<div id='content' class='tab selected'>" >> "$OUTPUT_FILE"
   xmllint --xpath '//div[@id="tab0"]/table' "$file" >> "$OUTPUT_FILE"
   echo "</div>" >> "$OUTPUT_FILE"
  fi
done
echo '</body></html>' >> "$OUTPUT_FILE"

find build/reports/tests/ -type d -name 'packages' | while read -r dir; do
  cp -r "$dir/"* .github/workflows/reports/packages/
done

find build/reports/tests/ -type d -name 'classes' | while read -r dir; do
  cp -r "$dir/"* .github/workflows/reports/classes/
done
