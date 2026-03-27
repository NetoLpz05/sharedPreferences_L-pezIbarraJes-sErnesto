package lopez.ernesto.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.input.PasswordVisualTransformation
import lopez.ernesto.login.screen.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = PreferenceManager(this)
        val cartManager = PreferenceManager(this)
        val videojuego = Videojuegos()

        enableEdgeToEdge()
        setContent {
            var screenState by remember { mutableStateOf(if (prefs.isLoggedIn()) "CATALOGO" else "LOGIN")}

            var listaCarrito by remember { mutableStateOf(cartManager.obtenerCarrito()) }
            var productoSeleccionado by remember { mutableStateOf<Producto?>(null) }

            when (screenState) {
                "LOGIN" -> {
                    LoginScreen(onLoginClick = {
                        prefs.saveLoginStatus(true)
                        screenState = "CATALOGO"
                    })
                }

                "CATALOGO" -> {
                    CatalogoScreen(videojuegos = videojuego,
                        onProductClick = { producto ->
                            productoSeleccionado = producto
                            screenState = "DETALLE"
                        },
                        onAddToCart = { producto ->
                            listaCarrito = listaCarrito + producto
                            cartManager.guardarCarrito(listaCarrito)
                        },
                        onGoToCart = {
                            screenState = "CARRITO"
                        }
                    )
                }

                "DETALLE" -> {
                    productoSeleccionado?.let { producto ->
                        DetalleProductoScreen(
                            producto = producto, onBackClick = { screenState = "CATALOGO" }, onAddToCart = { p ->
                                listaCarrito = listaCarrito + p
                                cartManager.guardarCarrito(listaCarrito)
                            }
                        )
                    }
                }

                "CARRITO" -> {
                    CarritoScreen(productos = listaCarrito, onBack = { screenState = "CATALOGO" }
                    )
                }
            }
        }
    }
}

@Composable
fun LoginScreen(onLoginClick: () -> Unit) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Login", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = email,
            {email = it; errorMessage =""},
            label = {Text("Correo electrónico")},
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(value = password,
            {password = it; errorMessage =""},
            label = {Text("Contraseña")}, modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(), singleLine = true)

        if(errorMessage.isNotEmpty()){
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 8.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            if (email == "itskirito05@gmail.com" && password == "luffyyonko"){
                onLoginClick()
            } else {
                errorMessage = "Credenciales incorrectas. Intenta de nuevo..."
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Entrar")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    LoginScreen(onLoginClick = { })
}