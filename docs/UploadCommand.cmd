set TAG_NAME=Level-0
set MESSAGE="Rename, Greet, Exit"

git branch branch-%TAG_NAME%
git switch branch-%TAG_NAME%
git add .
git commit -m %MESSAGE%
git push origin branch-%TAG_NAME%
git tag %TAG_NAME% branch-%TAG_NAME%
git push origin %TAG_NAME%