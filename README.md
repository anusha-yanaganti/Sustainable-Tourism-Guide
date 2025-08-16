# 🌿 Sustainable Tourism Guide - Mobile App

A mobile application that promotes eco-friendly, responsible, and community-driven tourism. Designed to help travelers explore destinations sustainably while supporting local culture and minimizing environmental impact.

---

## ✅ Features

| Category | Capabilities |
|---|---|
| 🌍 Discover | Search **eco-friendly destinations**, **accommodations**, and **activities** by location; curated lists and descriptions. :contentReference[oaicite:2]{index=2} |
| 🧭 Ease of Use | Simple home & navigation; role-aware flows for tourists and managers. :contentReference[oaicite:3]{index=3} |
| 👥 Management | **Managers** can add eco-listings (type, location, name, description). :contentReference[oaicite:4]{index=4} |
| 🔔 Updates | Real-time updates / notifications (design supports push in future). :contentReference[oaicite:5]{index=5} |
| 🔐 Security | Role-based access; only verified managers can publish listings. :contentReference[oaicite:6]{index=6} |
| 📈 Scalability | Future: AI recommendations, eco-cert integrations, interactive maps. :contentReference[oaicite:7]{index=7} |

---

## 👥 User Roles & Flows

**Tourist**
- Register → Login → Search by location → View eco-activities & accommodations → Read details → Plan responsibly. :contentReference[oaicite:8]{index=8}

**Manager**
- Register → Login → Add eco-listing (Activity/Accommodation) → Provide location, eco-context, description → Save to database → Listing visible to tourists. :contentReference[oaicite:9]{index=9}

---

## 🧱 Architecture & Tech Stack

| Layer | Technology | Notes |
|---|---|---|
| App/UI | **Java (Android Studio)** + **XML** | Native performance, access to Android APIs; responsive layouts with ConstraintLayout/RecyclerView. :contentReference[oaicite:10]{index=10} |
| Data | **SQLite (local)** | Users & Listings tables; optional **Firebase** for Authentication/Firestore. :contentReference[oaicite:11]{index=11} |
| Optional Services | **Firebase Auth/Firestore** | Email/password or Google sign-in; scalable cloud data if needed. :contentReference[oaicite:12]{index=12} |

---

## 🗃️ Data Model

### Tables (SQLite)

| Table | Columns | Purpose |
|---|---|---|
| **Users** | `id` (PK), `username` (UNIQUE), `password` (**store hashed**), `role` (`Tourist`/`Manager`) | Auth & role-based navigation. :contentReference[oaicite:13]{index=13} |
| **Listings** | `id` (PK), `type` (`Activity`/`Accommodation`), `location`, `name`, `description` | Eco-listings searchable by location. :contentReference[oaicite:14]{index=14} |

> 📌 The report’s sample implementation uses raw SQLite with `Users` and `Listings`. This README upgrades security (password hashing), prepared statements, and query helpers. :contentReference[oaicite:15]{index=15}

---

## 🖼 Screens & Navigation

- **Home → Login/Register**: Entry point with “Enter” → login.  
- **Register/Login (Tourist/Manager)**: Role selection and auth.  
- **Manager – Add Listing**: Type, location, name, description → Save to DB.  
- **Tourist – Search**: Enter location → See activities & stays → Details list. :contentReference[oaicite:16]{index=16}

---

## 🚀 Getting Started

### Prerequisites
- **Android Studio** (latest stable)
- **JDK 17+**
- **Gradle** (managed by Android Studio)
- (Optional) **Firebase** project if using cloud auth/data

---

### Build & Run
1. Clone:
   ```bash
   git clone https://github.com/HARSHITAUPPALAPATI/Sustainable-Tourism-Guide.git
   cd Sustainable-Tourism-Guide
   ```
2. Open the project in Android Studio.
3. Sync Gradle → Build → Run on emulator or connected device.

---

## ⚙️ Configuration
- **Local (SQLite):** No .env needed. DB is created on first run.

- **Firebase (Optional):**
   1. Create a Firebase project → Add Android app
   2. Download google-services.json and place under app/
   3. Enable Email/Password sign-in (or Google) in Firebase Console
   4. Add Firestore rules/collections as needed

--- 

## 🗂 Folder Structure
   ```swift
      tourism-guide/
      ├─ app/
      │  ├─ manifests/
      │  │  └─ AndroidManifest.xml
      │  ├─ java/com/example/tourism_guide/
      │  │  ├─ DatabaseHelper.java
      │  │  ├─ LoginActivity.java
      │  │  ├─ MainActivity.java
      │  │  ├─ ManagerActivity.java
      │  │  ├─ RegisterActivity.java
      │  │  └─ TouristActivity.java
      │  └─ res/
      │     ├─ drawable/  (icons, image_mad.jpeg)
      │     ├─ layout/    (activity_main.xml, activity_login.xml, ...)
      │     ├─ mipmap/
      │     ├─ values/
      │     └─ xml/       (backup_rules.xml, data_extraction_rules.xml)
      ├─ Gradle Scripts/  (build.gradle.kts, proguard-rules.pro, settings.gradle.kts)
   ```

---

## 🗺 Roadmap

- 🤖 AI-Driven Recommendations (personalized eco-itineraries)
- 🗺 Interactive Map for eco-destinations & trails
- ⭐ User Reviews & Ratings for listings
- 📦 Offline Mode (cache saved listings)
- ✅ Eco-Certification Integration (verify listings

---

## 📄 License

MIT — free to use, modify, and distribute.


