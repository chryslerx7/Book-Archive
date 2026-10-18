# BookArchive 📚

**BookArchive** is an Android application built in Java that allows users to record and manage book entries using a **Stack (LIFO — Last-In, First-Out)** data structure. Users can input book details, publish dates, and categories, archive them in memory, view the archived records rendered in LIFO order within a table, and pop the latest entry from the stack.

---

## 🚀 Features

* ➕ **Add Book Form**: Enter book name, author, publication date, and category.
* 📅 **Interactive Date Picker**: Easily select publication dates using a clean `DatePickerDialog`.
* 🏷️ **Category Selection**: Choose book genres from a dropdown `Spinner` (Fiction, Non-Fiction, Science & Tech, History, Biography, Fantasy).
* ⚠️ **Input Validation**: Ensures required fields are filled out before archiving, providing friendly feedback toasts.
* 📊 **LIFO Archive Table**: Displays all archived books in a dynamic, scrollable table sorted with the newest book on top.
* 🔝 **Pop Operation**: Remove the most recently added book from the top of the stack with real-time UI updates.
* 🧭 **Simple Navigation**: Effortlessly switch between the **Home** (Add Book) and **Data** (Archive View) screens using bottom navigation buttons.

---

## 🛠️ Tech Stack & Dependencies

* **Language**: Java 11 (`JavaVersion.VERSION_11`)
* **Minimum SDK**: API Level 24 (Android 7.0 Nougat)
* **Target / Compile SDK**: API Level 37
* **Build System**: Gradle with Kotlin DSL (`build.gradle.kts`) & Version Catalogs (`libs.versions.toml`)
* **UI Framework**: Android XML Views (`ConstraintLayout`, `TableLayout`, `ScrollView`)
* **Theme**: Material3 (`Theme.Material3.DayNight.NoActionBar`)
* **Core Libraries**:
  * `androidx.appcompat:appcompat:1.6.1`
  * `com.google.android.material:material:1.10.0`
  * `androidx.constraintlayout:constraintlayout:2.1.4`
  * `androidx.activity:activity-ktx:1.8.0`

---

## 🏛️ Architecture & Data Structure

The application utilizes an in-memory **Singleton Stack** pattern:

```
[ MainActivity ]  --- (push Book) --->  [ BookStack (Singleton) ]
                                             |  (Stack<Book> LIFO)
[ DataActivity ]  <--- (pop / view) ---  [ BookStack (Singleton) ]
```

* **Singleton Pattern ([`BookStack`](app/src/main/java/com/example/bookarchive/BookStack.java))**: Thread-safe synchronized singleton managing the global in-memory `Stack<Book>`.
* **LIFO Ordering**: Newest books are pushed onto the top of the stack and rendered top-to-bottom in the archive view.

---

## 📁 Project Structure

```
BookArchive/
├── app/
│   ├── src/main/java/com/example/bookarchive/
│   │   ├── Book.java          # Data model class for a book entity
│   │   ├── BookStack.java     # Singleton stack manager (LIFO)
│   │   ├── MainActivity.java  # Home screen for adding books & validation
│   │   └── DataActivity.java  # Archive screen displaying table & pop action
│   └── src/main/res/
│       ├── layout/
│       │   ├── activity_main.xml # Form layout for adding books
│       │   └── activity_data.xml # Table layout for archive display
│       └── values/            # Colors, strings, and themes
├── build.gradle.kts
└── gradle/libs.versions.toml
```

---

## 💻 Getting Started

### Prerequisites
* **Android Studio** (Ladybug / 2024.2.1 or newer recommended)
* **JDK 11** or higher
* Android Emulator or physical device running **Android 7.0 (API 24)** or higher

### Installation & Execution
1. **Clone or Open the Repository** in Android Studio:
   ```bash
   git clone <repository-url>
   ```
2. **Sync Gradle**: Let Android Studio download and configure dependencies automatically.
3. **Run the Application**: Select an emulator or connected device and click **Run (Shift + F10)**.

---

## 🧪 Running Tests

To run local unit tests and instrumentation tests via Gradle command line:

* **Local Unit Tests**:
  ```bash
  ./gradlew test
  ```
* **Instrumented Android Tests**:
  ```bash
  ./gradlew connectedAndroidTest
  ```
