# ECommerceOfflineFirst

A e-commerce demo app built with **Jetpack Compose**, showcasing clean architecture, offline-first
support, and adaptive UI for both phones and tablets.

## 📱 Features

- Product List and Detail Screens with mock API (FakeStoreAPI)
- Add to Cart with local storage using Room
- Offline-first architecture with Room as the single source of truth
- Cart management and checkout flow
- Delivery reminder using local notifications (AlarmManager)
- Dark mode toggle via settings
- Bottom navigation (Home, Profile)
- Responsive UI for phones and tablets

## 🧱 Tech Stack

- **Jetpack Compose** — declarative UI
- **MVVM** — architecture pattern
- **Koin** — dependency injection
- **Retrofit + Moshi** — network layer
- **Room** — local database
- **Kotlin Coroutines + Flow** — async/reactive streams
- **Navigation-Compose** — navigation with multiple nav graphs
- **AlarmManager** — local delivery notifications
- **DataStore** — local storage for small data
- **Material Design 3** — modern UI design

## 📦 API

- [FakeStoreAPI](https://fakestoreapi.com) — used for fetching product data

## 📂 Folder Structure

- data/
- domain/
- ui/
- di/
- core/

## 🚧 Status

This app is for demonstration purposes. Some functionality like payment and authentication is mocked
or not fully implemented.
