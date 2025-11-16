# ⚡ Kotlin EventBus
> A lightweight, coroutine-based **Multiplatform EventBus** with delayed delivery, TTL, logging, and compile-time generated subscribers via KSP.

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
