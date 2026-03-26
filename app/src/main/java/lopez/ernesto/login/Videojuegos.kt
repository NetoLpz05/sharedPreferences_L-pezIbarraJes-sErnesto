package lopez.ernesto.login

class Videojuegos {
    val LISTA_PRODUCTOS = listOf(
        Producto(1, "Nintendo Switch 2 + Mario Kart World Bundle", 490.0, "", "Consola portatil de Nintendo"),
        Producto(2, "The Legend of Zelda: Tears of the Kingdom", 70.00, "", "Uno de los mejores Zeldas de la historia btw"),
        Producto(3, "PlayStation 5 Pro", 500.00, "", "Consola última generación de Sony"),
        Producto(4, "Super Mario Galaxy", 30.50, "", "El mejor plataformas de la historia"),
        Producto(5, "Pokémon Mega Evolution TCG Elite Trainer Box", 80.00, "", "Se nos acaban las cajas apurale que se van jaja"),
        Producto(6, "Frye Amiibo Splatoon", 27.90, "", "Figura coleccionable de Frye de Splatoon 3")

    )

    fun filtrarPorNombre(query: String): List<Producto> {
        if (query.isEmpty()) return LISTA_PRODUCTOS

        return LISTA_PRODUCTOS.filter { producto ->
            producto.nombre.contains(query, ignoreCase = true)
        }
    }

    fun buscarPorId(idBuscado: Int): Producto? {
        return LISTA_PRODUCTOS.find { it.id == idBuscado }
    }
}