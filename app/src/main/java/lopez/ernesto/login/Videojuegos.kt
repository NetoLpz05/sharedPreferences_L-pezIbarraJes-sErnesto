package lopez.ernesto.login

class Videojuegos {
    val listaproductos = listOf(
        Producto(1, "Nintendo Switch 2 + Mario Kart World Bundle", 490.00, R.drawable.switch2, "Consola portatil de Nintendo secuela de la Switch de 2017"),
        Producto(2, "The Legend of Zelda: Tears of the Kingdom", 70.00, R.drawable.zelda, "Uno de los mejores Zeldas de la historia btw 🙏"),
        Producto(3, "PlayStation 5 Pro", 700.00, R.drawable.play5pro, "Consola última generación de Sony (esta carisima brodel 🥀💔)"),
        Producto(4, "Super Mario Galaxy", 30.50, R.drawable.mariogalaxy, "El mejor mario de la historia"),
        Producto(5, "Pokémon Mega Evolution TCG Elite Trainer Box", 80.000, R.drawable.pokemontcg, "Se nos acaban las cajas apurale que se van jaja 😭🥀💔"),
        Producto(6, "Frye Amiibo Splatoon 3", 27.90, R.drawable.fryeamiibo, "Figura coleccionable de Frye de Splatoon 3"),
        Producto(7, "Donkey Kong Bananza", 70.00, R.drawable.dkbananza, "DK DONKEY KONG (pero semi mundo abierto y 3d) 🗣️‼️🔥"),
        Producto(8, "Street Fighter 6: Years 1-2 Fighters Edition", 60.00, R.drawable.sf6, "El mejor juego de peleas de la actualidad 🗣️‼️🔥")
    )

    fun filtrarPorNombre(query: String): List<Producto> {
        if (query.isEmpty()) return listaproductos

        return listaproductos.filter { producto ->
            producto.nombre.contains(query, ignoreCase = true)
        }
    }

    fun buscarPorId(idBuscado: Int): Producto? {
        return listaproductos.find { it.id == idBuscado }
    }
}