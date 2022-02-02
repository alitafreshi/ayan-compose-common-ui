package ir.tafreshiali.compose_commom_ui.responsiveness

import androidx.compose.ui.unit.Dp

/**
 * a sealed class that enables supporting multi screen sizes
 * */
sealed class Dimensions {
    object Width : Dimensions()
    object Height : Dimensions()

    sealed class DimensionOperator {
        object LessThan : DimensionOperator()
        object GraterThan : DimensionOperator()
    }

    /**
     * a class that compare screen width and height size with compare method
     * @property operator
     * @property dimensions
     * @property value screen width or height
     * */
    class DimensionComparator(
        val operator: DimensionOperator,
        private val dimensions: Dimensions,
        val value: Dp
    ) {

        /**
         * this method compare screen width / height size and return a boolean based on the related conditions about screen sizes
         * @param screenWidth screen width in dp
         * @param screenHeight screen height in dp
         * @return the comparison result of screen width / height
         *
         * */
        fun compare(screenWidth: Dp, screenHeight: Dp): Boolean {
            return if (dimensions is Width) {
                when (operator) {
                    is DimensionOperator.LessThan -> screenWidth < value
                    is DimensionOperator.GraterThan -> screenWidth > value
                }
            } else {
                when (operator) {
                    is DimensionOperator.LessThan -> screenHeight < value
                    is DimensionOperator.GraterThan -> screenHeight > value
                }
            }

        }
    }
}

/**
 * create a instance of DimensionComparator class with ( leesThan operator) it improves the readability of Dimensions Apis
 *
 * @param value screen width / height in dp
 * */

infix fun Dimensions.lessThan(value: Dp): Dimensions.DimensionComparator {

    return Dimensions.DimensionComparator(
        operator = Dimensions.DimensionOperator.LessThan,
        dimensions = this,
        value = value
    )
}


/**
 * create a instance of DimensionComparator class with ( graterThan operator) it improves the readability of Dimensions Apis
 *
 *  @param value screen width / height in dp
 * */
infix fun Dimensions.graterThan(value: Dp): Dimensions.DimensionComparator {

    return Dimensions.DimensionComparator(
        operator = Dimensions.DimensionOperator.GraterThan,
        dimensions = this,
        value = value
    )
}
