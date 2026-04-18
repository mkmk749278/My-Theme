# My Theme (Android)

A lightweight Android app project focused on reducing eye strain with a black-and-white / grayscale experience, targeting **per-app behavior** as far as Android constraints allow.

## Why this exists
- Long daily screen time can increase eye fatigue.
- A muted monochrome style can lower visual intensity.
- The product direction prioritizes low battery usage, minimal resource cost, and practical Android compatibility.

## Current status
This repository now contains:
- A minimal Kotlin + Jetpack Compose Android app shell
- Placeholder UI sections for:
  - selected-app list
  - grayscale intensity/mode settings
  - onboarding for required services/permissions
- GitHub Actions workflow that builds a debug APK and uploads it as an artifact
- Product/engineering roadmap in [`docs/ROADMAP.md`](docs/ROADMAP.md)

## Initial app shell preview
![App shell preview](docs/images/app-shell.png)

Alternative hosted preview: https://github.com/user-attachments/assets/feafdb0f-48e5-4f79-8c97-86c5391960c2

## Android constraints (important)
Android does not provide a simple public API for forcing true per-app grayscale rendering in arbitrary third-party apps.
Practical approaches involve trade-offs (accessibility service, overlay/filter behavior, quick settings + foreground control patterns, OEM/root/device-owner constraints).
See roadmap for feasibility details.

## Build APK with GitHub Actions (no PC workflow)
1. Push changes to GitHub.
2. Open the **Actions** tab.
3. Run or inspect **Android CI - Build Debug APK**.
4. Download the `app-debug-apk` artifact from the workflow run.
