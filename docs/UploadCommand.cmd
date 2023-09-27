rem customize the tag name and message here
set TAG_NAME=Level-3
set MESSAGE="Mark as Done"

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