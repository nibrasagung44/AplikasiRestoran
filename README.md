# Aplikasi Restoran - Warung Nusantara Bahagia

Aplikasi Android menu dan profil restoran fiktif, dibangun dengan **Jetpack Compose** dan **Material 3**.

## Screenshot

| Home                                | Menu                          | Detail Menu                       |
| ----------------------------------- | ----------------------------- | --------------------------------- |
| ![Home](screenshots/home_light.png) | ![Menu](screenshots/menu.png) | ![Detail](screenshots/detail.png) |

| Profil                             | Edit Profil                           | Dark Mode                          |
| ---------------------------------- | ------------------------------------- | ---------------------------------- |
| ![Profil](screenshots/profile.png) | ![Edit](screenshots/edit_profile.png) | ![Dark](screenshots/home_dark.png) |

## Fitur

- 5 layar dengan navigasi: Home, Menu, Detail Menu, Profil, Edit Profil
- Tema gelap/terang dengan toggle (disimpan di SharedPreferences)
- Animasi transisi antar layar (slide + fade)
- Rating bintang interaktif di Detail Menu
- Data profil restoran tersimpan di SharedPreferences

## Tech Stack

- **Bahasa:** Kotlin
- **UI:** Jetpack Compose
- **Design System:** Material 3
- **Navigasi:** Navigation Compose
- **Penyimpanan:** SharedPreferences

## Cara Menjalankan

1. Clone repository ini

```bash
   git clone https://github.com/USERNAME/aplikasi-restoran-android.git
```

2. Buka dengan **Android Studio**
3. Tunggu Gradle sync selesai
4. Jalankan di emulator atau device
