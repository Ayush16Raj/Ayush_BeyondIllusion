# Ayush Beyond Illusion

An Android app to search GitHub repositories by username, fetch data from the GitHub API, and display repository details in a user-friendly interface. Built using **MVVM architecture** and **Dagger Hilt** for dependency injection.

---

## Features

- Search for repositories by entering a GitHub username.
- Display repository details including:
  - Repository name
  - Description
  - Language
  - Star count.
- Dark/Light theme support.
- Built with modern Android development practices.

---

## Setup Instructions

### Prerequisites
- Android Studio (latest version recommended).

### Steps to Run the Project
1. Clone the repository:
   ```bash
   git clone https://github.com/Ayush16Raj/Ayush_BeyondIllusion.git
2. Open the project in android studio.

   ## Architecture Overview

The project follows the **MVVM (Model-View-ViewModel)** architecture pattern for clean and maintainable code.

### Key Components
- **Model**: Handles data operations and interacts with the GitHub API.
- **ViewModel**: Manages UI-related data and interacts with the Model.
- **View**: Displays data and collects user interactions using Jetpack Compose.

---

## Libraries Used

| Library           | Purpose                                   |
|--------------------|-------------------------------------------|
| **Jetpack Compose**| Declarative UI development.              |
| **Dagger Hilt**    | Dependency injection for modularity.     |
| **Retrofit**       | Network calls to GitHub API.             |
| **Material3**      | Modern UI components and theming.        |
| **Coroutines**     | Asynchronous programming.                |
| **StateFlow**      | Managing UI state reactively.            |

---

## Assumptions and Decisions Made

1. **Error Handling**:  
   - Displays error messages for invalid usernames or network issues.

2. **Dark/Light Mode**:  
   - The app supports both themes based on system settings.

