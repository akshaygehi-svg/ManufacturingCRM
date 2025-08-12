# Manufacturing CRM Android App

A comprehensive Customer Relationship Management (CRM) application designed specifically for small manufacturing units. This Android app helps manage leads, track customer interactions, and organize documents including quotations, drawing drafts, and technical specifications.

## Features

### 🏢 Lead Management
- **Add New Leads**: Comprehensive form to capture company information, contact details, and project specifications
- **Lead Tracking**: Track leads through different stages (New, Contacted, Quotation Sent, Negotiation, Won, Lost, On Hold)
- **Priority Management**: Set priority levels (Low, Medium, High, Urgent) for better task management
- **Search & Filter**: Search leads by company name or contact person, filter by status
- **Lead Details**: View complete lead information with all associated documents and activities

### 📊 Dashboard
- **Key Metrics**: View total leads, new leads, quotations sent, won leads, and total value
- **Quick Actions**: Navigate directly to filtered lead lists
- **Pending Activities**: Track pending follow-ups and tasks

### 📄 Document Management
- **PDF Support**: Attach and view PDF files for each lead
- **Document Types**: Organize documents by type:
  - Quotations
  - Drawing Drafts
  - Drawing Revisions
  - Final Quotations
  - Technical Specifications
  - Contracts
  - Other Documents
- **Version Control**: Track document versions and revisions
- **File Viewer**: Built-in PDF viewer for document preview

### 📅 Activity Tracking
- **Activity Types**: Log different types of interactions:
  - Calls
  - Emails
  - Meetings
  - Site Visits
  - Quotation Sent
  - Follow-ups
  - Technical Discussions
  - Negotiations
  - Closures
- **Scheduling**: Schedule future activities with reminders
- **Completion Tracking**: Mark activities as completed with timestamps

### 🎨 Modern UI/UX
- **Material Design**: Modern, intuitive interface following Material Design guidelines
- **Responsive Layout**: Optimized for different screen sizes
- **Color-coded Status**: Visual indicators for lead status and priority
- **Smooth Navigation**: Bottom navigation with easy access to main features

## Technical Specifications

### Architecture
- **MVVM Pattern**: Model-View-ViewModel architecture for clean separation of concerns
- **Room Database**: Local SQLite database with Room persistence library
- **LiveData**: Reactive data handling for real-time UI updates
- **Repository Pattern**: Centralized data access layer

### Technologies Used
- **Kotlin**: Modern Android development language
- **Android Jetpack Components**:
  - Room Database
  - LiveData
  - ViewModel
  - Navigation Component
- **Material Design Components**: Modern UI components
- **PDF Viewer**: Android PDF Viewer library
- **File Picker**: Custom file selection for document uploads

### Database Schema
- **Leads Table**: Company info, contact details, project specs, status, priority
- **Documents Table**: File metadata, document types, versions, lead associations
- **Activities Table**: Interaction logs, scheduling, completion tracking

## Setup Instructions

### Prerequisites
- Android Studio Arctic Fox or later
- Android SDK API level 24 (Android 7.0) or higher
- Kotlin 1.8.20 or later

### Installation

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd ManufacturingCRM
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Open the project folder
   - Wait for Gradle sync to complete

3. **Configure Dependencies**
   - The project uses Gradle for dependency management
   - All dependencies are defined in `app/build.gradle`
   - Sync project to download required libraries

4. **Build and Run**
   - Connect an Android device or start an emulator
   - Click "Run" in Android Studio
   - The app will install and launch automatically

### Permissions Required
The app requires the following permissions:
- **Storage Access**: To read and save PDF documents
- **Internet**: For potential future cloud sync features

## Usage Guide

### Adding a New Lead
1. Navigate to the "Leads" tab
2. Tap the floating action button (+)
3. Fill in the required information:
   - Company name and contact details
   - Project description and estimated value
   - Set status and priority
   - Add expected delivery date (optional)
   - Include lead source and notes
4. Tap "Save Lead"

### Managing Documents
1. Open a lead from the leads list
2. Navigate to the "Documents" section
3. Tap "Add Document" to upload a PDF
4. Select document type and add description
5. View documents using the built-in PDF viewer

### Tracking Activities
1. From lead details, go to "Activities" section
2. Tap "Add Activity" to log an interaction
3. Select activity type and add details
4. Set scheduled date for future activities
5. Mark activities as completed when done

### Dashboard Overview
- View key metrics at a glance
- Tap on metric cards to see filtered lead lists
- Monitor pending activities and follow-ups

## File Structure

```
app/src/main/java/com/manufacturing/crm/
├── data/                    # Data layer
│   ├── Lead.kt             # Lead entity
│   ├── Document.kt         # Document entity
│   ├── Activity.kt         # Activity entity
│   ├── LeadDao.kt          # Lead data access
│   ├── DocumentDao.kt      # Document data access
│   ├── ActivityDao.kt      # Activity data access
│   └── AppDatabase.kt      # Room database
├── repository/             # Repository layer
│   └── LeadRepository.kt   # Business logic
├── ui/                     # UI layer
│   ├── dashboard/          # Dashboard screens
│   ├── lead/               # Lead management screens
│   └── document/           # Document management screens
└── MainActivity.kt         # Main activity
```

## Customization

### Adding New Document Types
1. Update `DocumentType` enum in `Document.kt`
2. Add corresponding string resources in `strings.xml`
3. Update UI components to include new type

### Modifying Lead Status
1. Update `LeadStatus` enum in `Lead.kt`
2. Add status colors in `colors.xml`
3. Update filter chips in `fragment_leads.xml`

### Theme Customization
- Modify colors in `colors.xml`
- Update themes in `themes.xml`
- Customize styles for different UI components

## Future Enhancements

### Planned Features
- **Cloud Sync**: Backup and sync data across devices
- **Email Integration**: Send quotations directly from the app
- **Calendar Integration**: Sync activities with device calendar
- **Analytics Dashboard**: Advanced reporting and insights
- **Multi-language Support**: Support for multiple languages
- **Offline Mode**: Enhanced offline functionality

### Technical Improvements
- **Unit Testing**: Comprehensive test coverage
- **UI Testing**: Automated UI testing with Espresso
- **Performance Optimization**: Improved app performance
- **Security Enhancements**: Data encryption and security measures

## Support and Contributing

### Reporting Issues
- Use GitHub Issues to report bugs or request features
- Include device information and steps to reproduce

### Contributing
1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Contact

For support or questions, please contact the development team or create an issue in the repository.

---

**Note**: This app is designed for small manufacturing units and can be customized further based on specific business requirements. 