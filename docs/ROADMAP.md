# My Theme Roadmap

## 1) Problem Statement
Users with high daily phone usage want a less visually intense display mode that feels old-school black / grayscale to reduce eye strain, while avoiding full-system impact and preserving battery life.

## 2) Goals
- Deliver a lightweight Android app with low idle overhead.
- Prefer per-app grayscale behavior over global system grayscale whenever feasible.
- Keep UX simple: choose apps, tune intensity/mode, enable/disable quickly.
- Support no-PC development/iteration workflows via GitHub Actions APK builds.

## 3) Non-Goals (for now)
- Root-only implementations.
- Device-owner / enterprise-only mandatory setup.
- Perfect pixel-level grayscale enforcement across every third-party app on all OEM ROMs.
- Complex background processing that increases battery drain.

## 4) Android Feasibility Notes (Per-App Grayscale)
Per-app grayscale across arbitrary apps is constrained by platform security boundaries.

Potentially feasible options with trade-offs:
1. **Accessibility Service-assisted behavior**
   - Can observe app transitions and trigger app-defined behavior quickly.
   - Cannot universally rewrite another app’s rendering pipeline directly.
2. **Overlay/filter approach**
   - Draws a controlled monochrome tint/filter layer over target apps.
   - Must be optimized to avoid frame drops and excessive GPU overdraw.
3. **Quick Settings Tile + Foreground Service control**
   - Provides explicit, user-visible control and better lifecycle reliability.
   - Foreground service must be efficient and only active when needed.
4. **System/global grayscale fallback**
   - Reliable on many devices but affects the whole device and misses per-app preference.

Conclusion: implement a practical hybrid where per-app targeting drives lightweight overlay/filter behavior, with clear onboarding and safe fallback paths.

## 5) Possible Architecture
- **App Shell/UI (Compose)**
  - App selection list (future)
  - Intensity/mode controls
  - Onboarding checklist for permissions/services
- **Policy Layer**
  - Rules: targeted package names, intensity profile, schedule (future)
- **Runtime Control Layer**
  - App-foreground detection via accessibility events (future)
  - Filter/overlay coordinator with strict lifecycle controls
- **Persistence**
  - DataStore for settings and selected app packages

## 6) Battery & Performance Guidelines
- Keep long-running components disabled by default until user enables feature.
- Avoid polling; rely on event-driven accessibility callbacks.
- Minimize redraw work; update overlay only on app-change events.
- Use simple animations (short fades/transitions), avoid heavy effects.
- Profile memory and wakeups before enabling advanced behavior by default.

## 7) Phased Milestone Plan
- **M0 (this PR): Foundation**
  - Android project bootstrap, CI APK build, roadmap, minimal app shell.
- **M1: Core settings + persistence**
  - DataStore-backed grayscale mode/intensity + selected-app model.
- **M2: Onboarding + permissions UX**
  - Guided setup for accessibility/overlay/foreground service requirements.
- **M3: Runtime grayscale prototype**
  - Lightweight per-app filter orchestration for selected apps.
- **M4: Efficiency pass**
  - Battery, wakeup, memory, and rendering optimization.
- **M5: Reliability + release prep**
  - Device compatibility checks, fallback strategy, internal test track.

## 8) Engineering Principles
- Small incremental PRs.
- Measure before optimizing.
- Keep background work explicit and user-controlled.
- Favor stable APIs and predictable lifecycle behavior.
