import kotlinx.serialization.Serializable

@Serializable
sealed interface BottomNavScreen {
    @Serializable
    data object Item1 : BottomNavScreen

    @Serializable
    data object Item2 : BottomNavScreen

    @Serializable
    data object Item3 : BottomNavScreen

    @Serializable
    data object Item4 : BottomNavScreen

    @Serializable
    data object Item5 : BottomNavScreen
}