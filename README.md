# SceneStream

**SceneStream** is a Firebase-powered Android application that showcases a cinematic gallery of movies. It features authentication, dynamic content loading, genre filtering, search functionality, and customizable UI—all built using modular fragments and modern Android development practices.

---

##  Features

-  **Firebase Authentication** – Secure sign-in and registration
-  **Movie Grid** – Images and titles dynamically loaded from Firestore
-  **Genre Filtering** – Browse movies by category via GenreFragment
-  **Search Functionality** – Real-time search with dynamic filtering
-  **Navigation Drawer** – Profile details and seamless fragment navigation
-  **Settings Panel** – Toggle features with `PreferenceFragmentCompat`
-  **Custom UI Elements** – Styled splash screen, toolbar, and movie cards

---

## Architecture Overview

- `SplashActivity` – Launch screen that redirects to authentication
- `LoginActivity` – Firebase login with option to sign up
- `SignupActivity` – New user registration and profile setup
- `MainActivity` – Hosts NavigationDrawer and fragment transactions
- `Fragments` – Modular screens for Movies, Search, Genres, Account, Settings
- `Adapters` – Custom RecyclerView adapters for movies and genres
- `Firestore` – Stores and retrieves movie and genre entities

---

##  Installation

1. Clone the repo  
   ```bash
   git clone https://github.com/Chazdj0510/SceneStream.git
2. Open the project in Android Studio
3. Sync dependencies using libs.versions.toml (includes Firebase, Glide, Preference, RecyclerView, etc.)
4. Run on emulator or physical device (Min SDK: 21)

---

## Firebase Setup
To enable Firebase features:

- Add your google-services.json file to /app
- Enable Firebase Authentication (Email/Password)
- Configure Firestore with collections:
-   users → name, email, profilePicUrl
-   movies → title, posterUrl
-   genres → name, iconUrl
