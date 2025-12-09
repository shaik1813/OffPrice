# Android Coding Style Guide - OffPrice Project

This document outlines the coding standards and architectural patterns for the OffPrice Android project. Consistency is key, and this guide is intended for experienced Android developers to maintain a clean, scalable, and maintainable codebase.

## General Principles

1.  **Clarity over Conciseness**: Write code that is easy to understand.
2.  **SOLID Principles**: Adhere to SOLID principles in class design.
3.  **DRY (Don't Repeat Yourself)**: Abstract and reuse code where appropriate, especially for UI components and business logic.
4.  **Ownership**: Code should be owned by the feature module it belongs to.

---

## Architectural Pattern: MVI (Model-View-Intent)

We use a strict MVI pattern for all screens that manage state. This ensures a unidirectional data flow and a predictable state management system.

### 1. The Contract

Every feature screen **must** have a `Contract` file (e.g., `WishlistContract.kt`). This file is the single source of truth for the screen's API.

-   It should define the `UiState`, `UiEvent`, and `SideEffect` as inner sealed classes or interfaces.

```kotlin
// Example: WishlistContract.kt
interface WishlistContract {
    data class UiState(
        val isLoading: Boolean = false,
        val products: List<Product> = emptyList(),
        val error: String? = null
    )

    sealed interface UiEvent {
        object OnRetryClicked : UiEvent
        data class OnRemoveItem(val productId: String) : UiEvent
    }

    sealed interface SideEffect {
        data class ShowToast(val message: String) : SideEffect
        object NavigateToProductDetails : SideEffect
    }
}
```

### 2. UiState

-   Represents the **entire state** of a screen.
-   Must be a `data class` to leverage `copy()` for state updates.
-   All properties must be immutable (`val`).
-   Provide sensible default values to represent the initial screen state.

### 3. UiEvent

-   Represents any user interaction or action that can change the state (e.g., button clicks, text input).
-   Modeled as a `sealed interface` or `sealed class`.

### 4. SideEffect

-   Represents one-off events that should not be stored in the state (e.g., showing a Snackbar, navigation).
-   Modeled as a `sealed interface` or `sealed class`.
-   Exposed from the `ViewModel` via a `SharedFlow`.

### 5. ViewModel

-   The `ViewModel` is the core of the MVI pattern for a screen.
-   **State**: Exposes a single `StateFlow<UiState>`.
-   **Events**: Provides one public function `setEvent(event: UiEvent)` to receive events from the UI.
-   **Side Effects**: Exposes a `SharedFlow<SideEffect>` for one-time events.
-   State updates must be atomic and thread-safe, using `MutableStateFlow.update()`.

```kotlin
// Example: WishlistViewModel.kt
@HiltViewModel
class WishlistViewModel @Inject constructor(...) : ViewModel() {

    private val _uiState = MutableStateFlow(WishlistContract.UiState())
    val uiState: StateFlow<WishlistContract.UiState> = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<WishlistContract.SideEffect>()
    val effect: SharedFlow<WishlistContract.SideEffect> = _effect.asSharedFlow()

    fun setEvent(event: WishlistContract.UiEvent) {
        when (event) {
            // handle events
        }
    }
    
    private fun reduceState(reducer: (WishlistContract.UiState) -> WishlistContract.UiState) {
        _uiState.update(reducer)
    }
}
```

### 6. UI (Composable Screen)

-   The UI layer (Jetpack Compose) should be as stateless as possible.
-   It collects `uiState` from the `ViewModel` and recomposes on change.
-   It uses a `LaunchedEffect` block to collect `SideEffect`s.
-   It sends `UiEvent`s to the `ViewModel` based on user interactions.

```kotlin
// Example: WishlistScreen.kt
@Composable
fun WishlistScreen(viewModel: WishlistViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                // handle side effects
            }
        }
    }

    WishlistContent(
        state = state,
        onEvent = viewModel::setEvent
    )
}
```

---

## Jetpack Compose

-   **State Hoisting**: Hoist state to the lowest common ancestor. For screen-level state, the `ViewModel` is the owner.
-   **Naming**: Composable functions that emit UI must be PascalCased.
-   **Stateless Components**: Reusable components (e.g., `ProductCard`) should be stateless, receiving data and lambdas as parameters.
-   **Previews**: Provide `@Preview`s for all reusable UI components and for screens with sample data.

---

## File and Naming Conventions

-   **Screen**: A feature screen consists of:
    -   `FeatureNameScreen.kt` (the Composable entry point)
    -   `FeatureNameViewModel.kt`
    -   `FeatureNameContract.kt`
-   **Classes**: Class names are written in PascalCase (e.g., `ProductRepository`).
-   **Functions**: Function names are written in camelCase (e.g., `fetchWishlistItems`).
-   **Composable Functions**: Composable functions are written in PascalCase (e.g., `PrimaryButton`).

---

## Kotlin & Coroutines

-   **Idiomatic Kotlin**: Utilize standard library functions (`let`, `apply`, `also`, `run`) where they improve readability.
-   **Coroutines**:
    -   `ViewModel`s should use `viewModelScope`.
    -   Suspend functions should be the default for I/O operations (network, database).
    -   Favor `Flow` for streams of data.
