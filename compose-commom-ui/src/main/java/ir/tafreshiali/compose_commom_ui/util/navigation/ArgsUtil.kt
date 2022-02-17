package ir.tafreshiali.compose_commom_ui.util.navigation

import com.google.gson.Gson

/** Until now the compos - navigation lib docent support so we had to convert our objects to the json and pass the throw each screen
 * @param type a class type that we want to convert it from json*/
fun <T> String.fromJson(type: Class<T>): T {
    return Gson().fromJson(this, type)
}

fun <T> T.toJson(): String? {
    return Gson().toJson(this)
}

