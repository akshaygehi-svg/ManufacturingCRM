# Manufacturing CRM - Setup Guide (No Android Studio Required)

## Quick Start Options

### Option 1: Use Online Services (Easiest)

#### A. Android Studio for Web
1. Go to: https://developer.android.com/studio
2. Click "Try Android Studio for Web"
3. Sign in with Google account
4. Upload the ManufacturingCRM folder
5. Build and run directly in browser

#### B. GitHub Actions (Automatic Build)
1. Create a GitHub account
2. Upload this folder to a new repository
3. The app will automatically build
4. Download the APK from Actions tab

### Option 2: Install Minimal Tools

#### Step 1: Install Java (Required)
1. Download OpenJDK 17 from: https://adoptium.net/
2. Run the installer
3. Add Java to PATH (installer usually does this)

#### Step 2: Install Android SDK (Command Line)
1. Download Android Command Line Tools from: https://developer.android.com/studio#command-tools
2. Extract to a folder (e.g., C:\Android\cmdline-tools)
3. Set ANDROID_HOME environment variable

#### Step 3: Build the App
```bash
# In the ManufacturingCRM folder
gradlew.bat assembleDebug
```

#### Step 4: Install on Device
1. Enable "Developer Options" on your Android phone
2. Enable "USB Debugging"
3. Connect phone via USB
4. Run: `gradlew.bat installDebug`

### Option 3: Use Pre-built APK

If you want to test immediately without building:

1. **Download APK from GitHub Actions** (after uploading to GitHub)
2. **Enable "Install from Unknown Sources"** on your Android device
3. **Install the APK** directly on your phone

## Testing the App

### On Your Android Phone:
1. Install the APK
2. Open "Manufacturing CRM" app
3. Start adding leads and documents

### Features to Test:
- ✅ Add new leads
- ✅ Upload PDF documents
- ✅ Track activities
- ✅ View dashboard metrics
- ✅ Search and filter leads

## Troubleshooting

### Common Issues:

#### "Java not found"
- Install Java 17 from https://adoptium.net/
- Restart command prompt after installation

#### "Android SDK not found"
- Download Android Command Line Tools
- Set ANDROID_HOME environment variable

#### "Permission denied"
- Enable USB Debugging on your phone
- Allow installation from unknown sources

#### "Build failed"
- Check internet connection (needed to download dependencies)
- Ensure you have enough disk space (2GB+)

## Alternative: Use Online Emulator

### Appetize.io (Free)
1. Go to: https://appetize.io/
2. Sign up for free account
3. Upload your APK
4. Test in web browser

### Firebase App Distribution
1. Go to: https://firebase.google.com/
2. Create project
3. Upload APK
4. Share with testers

## Next Steps

Once you have the app running:

1. **Add Sample Data**: Create a few test leads
2. **Upload Documents**: Add PDF quotations and drawings
3. **Customize**: Modify colors, add your company logo
4. **Deploy**: Share with your team

## Support

If you encounter issues:
1. Check the troubleshooting section above
2. Look at the README.md file for detailed documentation
3. Create an issue in the GitHub repository

---

**Note**: This app is designed to work offline and store all data locally on your device. No internet connection is required for normal operation. 