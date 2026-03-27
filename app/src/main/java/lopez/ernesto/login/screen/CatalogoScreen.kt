package lopez.ernesto.login.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import lopez.ernesto.login.Producto
import lopez.ernesto.login.Videojuegos
import androidx.compose.ui.tooling.preview.Preview
import lopez.ernesto.login.R

@Composable
fun CatalogoScreen(videojuegos: Videojuegos, onProductClick: (Producto) -> Unit, onAddToCart: (Producto) -> Unit, onGoToCart: () -> Unit) {
    var searchQuery by remember { mutableStateOf("") }
    val productosFiltrados = remember(searchQuery) {
        val porNombre = videojuegos.filtrarPorNombre(searchQuery)
        val idBuscado = searchQuery.toIntOrNull()
        val porId = if (idBuscado != null) videojuegos.buscarPorId(idBuscado) else null

        if (porId != null) {
            (listOf(porId) + porNombre).distinctBy { it.id }
        } else {
            porNombre
        }
    }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Buscar por nombre o ID...") },
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = onGoToCart) {
                Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito")
            }
        }

        LazyColumn(modifier = Modifier.padding(top = 16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(productosFiltrados) { producto ->
                Card(modifier = Modifier.fillMaxWidth().clickable { onProductClick(producto) }) {
                    Row(modifier = Modifier.padding(8.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Column(modifier = Modifier.weight(1f).padding(horizontal = 12.dp)) {
                            ProductoCard(producto)
                        }
                        Button(onClick = { onAddToCart(producto) }) {
                            Text("Agregar")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProductoCard(producto: Producto) {
    Card(Modifier.padding(8.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = producto.imagen), contentDescription = producto.nombre, modifier = Modifier.size(80.dp), contentScale = ContentScale.Fit
            )
            Text(text = producto.nombre, fontWeight = FontWeight.Bold)
            Text(text = "$${producto.precio}", color = MaterialTheme.colorScheme.primary)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CatalogoPreview() {
    val videojuego = Videojuegos()

    CatalogoScreen(
        videojuegos = videojuego,
        onProductClick = { },
        onAddToCart = { },
        onGoToCart = { }
    )
}

@Preview(showBackground = true)
@Composable
fun ProductoCardPreview() {
    val producto = Producto(id = 1, nombre = "Nintendo Switch OLED - Modelo Mario", precio = 490.00, imagen = R.drawable.switch2, descripcion = "Consola Nintendo Switch 2 "
    )
    Box(modifier = Modifier.padding(16.dp)) {
        ProductoCard(producto = producto)
    }
}