package ir.tafreshiali.compose_commom_ui.input.plate

sealed class PlateInputType(val maxEntry: Int) {
    object First : PlateInputType(maxEntry = 2)
    object Alphabetical: PlateInputType(maxEntry = 0)
    object Second : PlateInputType(maxEntry = 3)
    object Third : PlateInputType(maxEntry = 2)
}
