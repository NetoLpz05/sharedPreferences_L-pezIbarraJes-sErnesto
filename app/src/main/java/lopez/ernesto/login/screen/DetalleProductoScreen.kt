package lopez.ernesto.login.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import lopez.ernesto.login.Producto
import lopez.ernesto.login.R

@Composable
fun DetalleProductoScreen(producto: Producto, onBackClick: () -> Unit, onAddToCart: (Producto) -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                IconButton(onClick = onBackClick) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Volver", tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Image(painter = painterResource(id = producto.imagen), contentDescription = producto.nombre, modifier = Modifier.fillMaxWidth().height(300.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = producto.nombre, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "$${producto.precio}", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.ExtraBold, modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Descripción:", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth())
            Text(text = producto.descripcion, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.fillMaxWidth().padding(top = 8.dp))

            Spacer(modifier = Modifier.weight(1f).heightIn(min = 32.dp))

            Button(onClick = { onAddToCart(producto) }, modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp), contentPadding = PaddingValues(16.dp)) {
                Icon(Icons.Default.ShoppingCart, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Agregar al carrito", style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetalleProductoPreview() {
    val productoPrueba = Producto(
        id = 99,
        nombre = "Pokémon TCG Mega Evolution ETB",
        precio = 999.99,
        imagen = R.drawable.pokemontcg,
        descripcion = "Esta es una descripción de prueba"
    )
    DetalleProductoScreen(
        producto = productoPrueba,
        onBackClick = {},
        onAddToCart = {}
    )
}

