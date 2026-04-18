package lopez.ernesto.login

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.json.JSONArray
import org.json.JSONObject

val Context.dataStore by preferencesDataStore(name = "app_prefs")

class DataStoreManager(private val context: Context) {
    private val Context.dataStore by preferencesDataStore("session_prefs")
    companion object{
        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        val USERNAME = stringPreferencesKey("username")
        val CART = stringPreferencesKey("cart")
    }

    val isLoggedInFlow: Flow<Boolean> = context.dataStore.data.map { it[IS_LOGGED_IN] ?: false }

    val userNameFlow: Flow<String> = context.dataStore.data.map { it[USERNAME] ?: "" }

    suspend fun saveSession(username: String){
        context.dataStore.edit{
            it[IS_LOGGED_IN] = true
            it[USERNAME] = username
        }
    }

    suspend fun logout(){
        context.dataStore.edit {
            it.clear()
        }
    }

    suspend fun saveCart(productos: List<Producto>) {
        val jsonArray = JSONArray()

        productos.forEach { producto ->
            val obj = JSONObject()
            obj.put("id", producto.id)
            obj.put("nombre", producto.nombre)
            obj.put("precio", producto.precio)
            obj.put("imagen", producto.imagen)
            obj.put("descripcion", producto.descripcion)
            jsonArray.put(obj)
        }

        context.dataStore.edit {
            it[CART] = jsonArray.toString()
        }
    }

    val cartFlow: Flow<List<Producto>> =
        context.dataStore.data.map { prefs ->
            val json = prefs[CART] ?: return@map emptyList()

            val jsonArray = JSONArray(json)
            val lista = mutableListOf<Producto>()

            for (i in 0 until jsonArray.length()) {
                val obj = jsonArray.getJSONObject(i)

                lista.add(
                    Producto(
                        id = obj.getInt("id"),
                        nombre = obj.getString("nombre"),
                        precio = obj.getDouble("precio"),
                        imagen = obj.getInt("imagen"),
                        descripcion = obj.getString("descripcion")
                    )
                )
            }
            return@map lista
        }
}