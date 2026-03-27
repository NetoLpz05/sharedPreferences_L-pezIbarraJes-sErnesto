package lopez.ernesto.login

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PreferenceManager(context: Context){
    //Logueo a la app
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveLoginStatus(isLoggedIn: Boolean){
        sharedPreferences.edit().putBoolean("is_logged_in", isLoggedIn).apply()
    }

    fun isLoggedIn(): Boolean{
        return sharedPreferences.getBoolean("is_logged_in", false)
    }

    fun logout(){
        sharedPreferences.edit().clear().apply()
    }

    //Cosas del carrito
    private val prefs = context.getSharedPreferences("cart_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun guardarCarrito(productos: List<Producto>) {
        val json = gson.toJson(productos)
        prefs.edit().putString("cart_list", json).apply()
    }

    fun obtenerCarrito(): List<Producto> {
        val json = prefs.getString("cart_list", null) ?: return emptyList()
        val type = object : TypeToken<List<Producto>>() {}.type
        return gson.fromJson(json, type)
    }
}