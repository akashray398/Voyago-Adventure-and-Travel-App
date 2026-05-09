# ✈️ Voyago - Premium Adventure & Travel Discovery Engine

[![Kotlin](https://img.shields.io/badge/Kotlin-2.2.10-blue.svg?style=flat&logo=kotlin)](https://kotlinlang.org)
[![Compose](https://img.shields.io/badge/Jetpack%20Compose-2024.06.00-green.svg?style=flat&logo=android)](https://developer.android.com/jetpack/compose)
[![Material3](https://img.shields.io/badge/Material%203-Enabled-orange.svg?style=flat&logo=materialdesign)](https://m3.material.io)
[![License](https://img.shields.io/badge/License-MIT-brightgreen.svg)](https://opensource.org/licenses/MIT)

**Voyago** is a high-fidelity travel application designed for the modern explorer. Built with a "UI-First" philosophy, it combines fluid animations, professional-grade imagery, and a robust navigation architecture to deliver a premium user experience.

---

## 📸 Visual Showcase

| Onboarding & Splash | Discovery (Home) | Immersive Details |
|:---:|:---:|:---:|
| ![Splash](https://images.unsplash.com/photo-1464822759023-fed622ff2c3b?w=400) | ![Home](https://images.unsplash.com/photo-1527631746610-bca00a040d60?w=400) | ![Detail](https://images.unsplash.com/photo-1537996194471-e657df975ab4?w=400) |
| *Seamless entry* | *Dynamic filtering* | *20+ Curated Photos* |

---

## 🔥 Key Engineering Features

- **🏆 Dynamic Content Engine:** A custom-built repository logic that delivers unique, factually accurate descriptions and curated 20+ photo galleries for 15+ global destinations.
- **🗺️ Real-time Journey Tracking:** A professional "Route Tracking" prototype using Glassmorphism effects and state-based pulse animations to simulate active GPS engagement.
- **🎨 Advanced UI/UX:** 
    - **Custom Category Filtering:** Real-time state-based filtering for Beach, Mountain, Trek, Resort, and Adventure types.
    - **Professional Design Patterns:** Implementation of Gradient Overlays, Tonal Elevations, and Card-based architectures.
- **⚡ Reactive Architecture:** Fully implemented with **Jetpack Compose**, leveraging `remember`, `mutableStateOf`, and `StateFlow` principles for 60FPS UI performance.

---

## 🏗️ Project Architecture & Hierarchy

The project follows the **Modern Android Development (MAD)** standards with a clean, feature-based directory structure:

```text
root/
├── 📁 app/
│   ├── 📁 src/main/java/com/akash/voyagotravel/
│   │   ├── 📄 MainActivity.kt           # Edge-to-edge entry point
│   │   ├── 📁 ui/
│   │   │   ├── 📁 theme/               # Material 3 Color Schemes & Typography
│   │   │   ├── 📁 navigation/          # Navigation Controller & Bottom Bar
│   │   │   │   ├── 📄 NavGraph.kt      # Centralized Route Management
│   │   │   │   └── 📄 BottomNavigationBar.kt
│   │   │   └── 📁 screens/             # Modular Screen Implementations
│   │   │       ├── 📄 HomeScreen.kt    # Category-based discovery
│   │   │       ├── 📄 DestinationDetailScreen.kt # High-fidelity content
│   │   │       ├── 📄 FavoritesScreen.kt # Persistent Wishlist logic
│   │   │       ├── 📄 RouteTrackingScreen.kt # Glassmorphic map UI
│   │   │       ├── 📄 BookingScreen.kt  # Transaction & Summary UI
│   │   │       ├── 📄 OnboardingScreen.kt # Multi-page Pager implementation
│   │   │       └── 📄 SplashScreen.kt   # Brand entry experience
│   └── 📄 AndroidManifest.xml
├── 📄 build.gradle.kts (Project)        # Version Catalog integration
└── 📄 README.md                        # Documentation
```

---

## 🛠️ Tech Stack

- **Language:** Kotlin (Modern DSL)
- **UI:** Jetpack Compose (Declarative UI)
- **Navigation:** Navigation Compose (Type-safe routing)
- **Image Loading:** Coil (Coroutines Image Loader)
- **Design:** Material 3 (M3)
- **Build System:** Gradle Kotlin DSL

---

## 🚀 Getting Started

1. **Clone the Repo:**
   ```bash
   git clone https://github.com/akashray398/VoyagoAdventureApp.git
   ```
2. **Open in Android Studio:**
   Use the latest version of Android Studio (Koala or later).
3. **Sync Gradle:**
   Allow the IDE to download dependencies via the Version Catalog.
4. **Run:**
   Execute on any Emulator or Physical Device (API 26+).

---

## 🔮 Roadmap & Future Vision

- [ ] **Integration with Google Maps SDK:** Transition from static map imagery to interactive real-time mapping.
- [ ] **Room Database:** Local persistence for the user's Wishlist and Booking history.
- [ ] **Firebase Authentication:** Secure user profiles and social login integration.
- [ ] **AR Trails:** Implementing ARCore for trail path visualization on the Tracking screen.

---

### 👨‍💻 Developer
**Akash Yadav**  
*Passionate Android Developer focusing on High-End UI/UX and Scalable Architectures.*

"Exploring the world, one line of code at a time." 🌍
