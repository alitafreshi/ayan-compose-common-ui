package ir.tafreshiali.compose_commom_ui.util

sealed class AppLanguages(val languageCode: String) {
    object Persian : AppLanguages(languageCode = "ira")
    object English : AppLanguages(languageCode = "en")
}
