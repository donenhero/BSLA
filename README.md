README


Project Overview

This is an Android application developed using modern architectures and best practices to ensure maintainability and scalability. The application features a clean and efficient user interface designed with Jetpack Compose, facilitating a responsive and smooth user experience.



Architecture

The project follows a Single Activity architecture with Navigation Compose for seamless navigation between screens. It implements Clean Architecture principles through the following layered structure:

Data Layer: Contains configurations related to data storage and remote data fetching. This layer handles the data sources.
Domain Layer: Consists of Use Cases and data models. This layer encapsulates the business logic of the application.
Presentation Layer: Houses the ViewModels and screen views. It is responsible for the UI logic and data binding.
Dependency Injection

The project utilizes Hilt for dependency injection, making the code modular and easier to test.

Data Access and Caching

The application employs Retrofit for API access, using suspend functions within ViewModels to follow the principles of Clean Architecture and Kotlin coroutines.

For caching data, the app employs Room to fetch data when the user is offline, ensuring a smooth user experience even without an internet connection. Additionally, SharedPreferences is used for storing user preferences, such as favorites. (This can also be achieved using Room, but this demonstrates the ability to use SharedPreferences for managing user settings.)



Unit Testing

To ensure the reliability of the application, unit tests have been implemented. You can find an example in the MainListUnitTest class, which contains unit tests for retrieving data from the API.



Code Commenting

The code is documented with comments for better understanding and maintainability. For examples of code commenting, please refer to the MainListUnitTest and MainListScreen classes.



Contact Information

For any inquiries or feedback, please reach out to:



Ali Najafi
Email: Ali.najaphi@gmail.com
