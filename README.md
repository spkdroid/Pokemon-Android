# Pokedex - Offline Pokémon Database

## Overview
Pokedex is an Android application that allows users to search for Pokémon, view their details, and store Pokémon data offline. The app ensures that previously viewed Pokémon details remain accessible even during internet disruptions. The UI is designed to resemble the classic Pokédex from the Pokémon anime.

## Features
- **Search Pokémon:** Fetch Pokémon details from an online API.
- **Offline Storage:** Pokémon details are saved locally for offline access.
- **Pokédex Dashboard:** A stylized dashboard displaying available modules.
- **Saved Pokémon:** View a list of previously searched Pokémon.
- **Settings Module:** Manage app preferences.

## Tech Stack
- **Programming Language:** Kotlin
- **Architecture:** MVVM (Model-View-ViewModel)
- **UI Framework:** Jetpack Compose
- **Networking:** Retrofit
- **Dependency Injection:** Hilt
- **Local Database:** Room
- **Testing:** JUnit, Mockito, Espresso

## Installation
### Prerequisites
Ensure you have the following installed:
- Android Studio (Latest version recommended)
- Kotlin 1.9+
- Gradle (KTS-based configuration)

### Clone the Repository
```sh
 git clone https://github.com/yourusername/pokedex.git
 cd pokedex
```

### Build & Run
1. Open the project in Android Studio.
2. Sync Gradle files.
3. Connect an Android device or use an emulator.
4. Run the app using:
   - **Command Line:**
     ```sh
     ./gradlew installDebug
     ```
   - **Android Studio:** Click ▶️ (Run).

## Usage
1. Launch the app to view the **Pokédex Dashboard**.
2. Navigate to **Search Pokémon** and enter a Pokémon’s name.
3. View detailed Pokémon stats.
4. Pokémon details are stored automatically for offline access.
5. Visit the **Saved Pokémon** section to browse offline Pokémon.
6. Use the **Settings** module to customize preferences.

## Project Structure
```
app/
├── data/
│   ├── local/   # Room Database & DAO
│   ├── remote/  # Retrofit API Service
│   ├── repository/  # Data Repository
├── ui/
│   ├── components/  # Reusable UI Components
│   ├── screens/  # App Screens (Dashboard, Search, Details, Saved)
├── di/  # Dependency Injection (Hilt)
├── utils/  # Helper Functions
```

## API Reference
- The app fetches Pokémon data from `https://spkdroid.com/pokemon/`
- Example API request:
  ```sh
  GET https://spkdroid.com/pokemon/{pokemon_name}
  ```

## Testing
Run unit and UI tests with:
```sh
./gradlew testDebugUnitTest
./gradlew connectedAndroidTest
```

## Contributing
Contributions are welcome! Follow these steps:
1. Fork the repository.
2. Create a feature branch: `git checkout -b feature-new-feature`.
3. Commit changes: `git commit -m 'Add new feature'`.
4. Push to the branch: `git push origin feature-new-feature`.
5. Open a pull request.

## License
This project is licensed under the MIT License.

---
**Author:** [Ramkumar Velmurugan](https://github.com/spkdroid)

