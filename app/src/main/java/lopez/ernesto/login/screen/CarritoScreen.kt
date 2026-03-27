package lopez.ernesto.login.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import lopez.ernesto.login.Producto
import androidx.compose.ui.tooling.preview.Preview
import lopez.ernesto.login.R

@Composable
fun CarritoScreen(productos: List<Producto>, onBack: () -> Unit) {
    val total = productos.sumOf { it.precio }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Tu Carrito", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(Modifier.weight(1f)) {
            items(productos) { prod ->
                ListItem(headlineContent = { Text(prod.nombre) }, trailingContent = { Text("$${prod.precio}") })
            }
        }
        Text("Total: $$total", style = MaterialTheme.typography.headlineMedium)
        Text("Items: ${productos.size}")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onBack, Modifier.fillMaxWidth()) {
            Text("Seguir Comprando")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CarritoPreview() {
    val testList = listOf(
        Producto(1, "Nintendo Switch 2", 490.0, R.drawable.switch2, ""),
        Producto(2, "Zelda: Tears of the Kingdom", 70.0, R.drawable.zelda, ""),
        Producto(4, "Super Mario Galaxy", 30.5, R.drawable.mariogalaxy, "")
    )

    CarritoScreen(
        productos = testList,
        onBack = { }
    )
}