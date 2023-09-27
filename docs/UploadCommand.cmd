rem customize the tag name and message here
set TAG_NAME=Level-1
set MESSAGE="Echo"

rem create a branch
git branch branch-%TAG_NAME%
git switch branch-%TAG_NAME%
rem commit all changes
git add .
git commit -m %MESSAGE%
git push origin branch-%TAG_NAME%

rem delete the tag if there exist
git tag -d %TAG_NAME%
git push origin --delete %TAG_NAME%
rem create the tag with certain branch
git tag %TAG_NAME% branch-%TAG_NAME%
git push origin %TAG_NAME%