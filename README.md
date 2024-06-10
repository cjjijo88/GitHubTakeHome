GitHub App

Overview:

This Android application communicates with the GitHub API to display information about a specific user.

Architecture:

The app follows the MVVM (Model-View-ViewModel) architecture pattern:

- Model: Represents the data and business logic of the application. It includes classes responsible for data manipulation and retrieval from the GitHub API.

- View: Represents the UI components of the application. It includes Activities, Fragments, and XML layout files responsible for displaying the user interface to the user.

- ViewModel: Serves as an intermediary between the Model and the View. It retrieves data from the Model and prepares it for display in the View. It also handles user interactions and triggers actions in the Model.

Communication between Components:

- View -> ViewModel: Views (Activities or Fragments) observe changes in ViewModel's LiveData objects. When a user interacts with the UI, the View sends events to the ViewModel, which then triggers actions in the Model.

- ViewModel -> Model: ViewModel communicates with the Model to fetch data from the GitHub API. It uses Retrofit to make network requests and receives responses asynchronously.

- Model -> ViewModel: Once the Model retrieves data from the GitHub API, it updates LiveData objects in the ViewModel. These LiveData objects are observed by the View, which then updates the UI accordingly.

- ViewModel -> View: ViewModel exposes LiveData objects that hold the data to be displayed in the View. Views observe these LiveData objects and update their UI elements when the data changes.

Setup Instructions:

1. Clone the repository to your local machine.
2. Open the project in Android Studio.
3. Build and run the project on an Android device or emulator.
4. Enter a GitHub user ID in the input field and click the "Search" button to view the user's information and repositories.

Dependencies:

- Retrofit: Used for making HTTP requests to the GitHub API.
- ViewModel: Part of the Android Architecture Components, used for managing UI-related data in a lifecycle-conscious way.
- Data Binding: Used to bind UI components in layouts to data sources in the app.
- Dagger 2: Used for dependency injection to improve code scalability and maintainability.

Contributor:

- Jijo Chittaruvickal Joy - Developer

