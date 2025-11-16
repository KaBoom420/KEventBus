# ⚡ Kotlin Multiplatform EventBus
KEventBus is a lightweight, Kotlin Multiplatform (KMP) event bus built on top of coroutines and SharedFlow.  
It provides type-safe event delivery, optional TTL buffering, delayed event delivery, optional logging, and annotation-based subscribers powered by KSP.

>Works on **Android, JVM, iOS, Desktop, and Kotlin Multiplatform** out of the box.

[![](https://jitpack.io/v/KaBoomDev/KaBoomEventBus.svg)](https://jitpack.io/#KaBoomDev/KaBoomEventBus)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.24-blue.svg?logo=kotlin)](https://kotlinlang.org)
[![License](https://img.shields.io/badge/License-Apache_2.0-green.svg)](LICENSE)

---

### ✨ Features

- 🧠 **Multiplatform** — works on Android, Desktop, iOS, JVM.
- ⚡ **Coroutine-powered** — built entirely on `StateFlow` / `SharedFlow`.
- 🧩 **Type-safe** — events are strongly typed (`sealed class` recommended).
- ⏳ **TTL (time-to-live)** for delayed events.
- 🕒 **Queued delivery** — events posted before subscribers still arrive later.
- 🔍 **Optional logging** with custom logger function (`println`, `Timber`, `Log.d`, etc).
- 🧱 **DSL-style API** for posting and subscribing.
- 🧰 **KSP integration** — compile-time codegen for `@SubscribeEvent`.
- 🪄 **Zero reflection**, no magic, just Kotlin.

---

### 🚀 Installation

Add JitPack to your repositories:

```kotlin
repositories {
    maven { url = uri("https://jitpack.io") }
}

