@echo off
echo ========================================
echo Manufacturing CRM - GitHub Upload Helper
echo ========================================
echo.

echo Step 1: Initializing Git repository...
git init

echo.
echo Step 2: Adding all files to Git...
git add .

echo.
echo Step 3: Making first commit...
git commit -m "Initial commit: Manufacturing CRM Android app"

echo.
echo ========================================
echo SUCCESS! Your code is ready for GitHub.
echo ========================================
echo.
echo NEXT STEPS:
echo 1. Go to your GitHub repository page
echo 2. Copy the repository URL
echo 3. Run these commands (replace YOUR_USERNAME):
echo    git remote add origin https://github.com/YOUR_USERNAME/ManufacturingCRM.git
echo    git branch -M main
echo    git push -u origin main
echo.
echo After pushing, GitHub Actions will automatically build your app!
echo.
pause 