import java.io.File
import java.io.FileWriter
import java.util.function.Consumer

fun main(args:Array<String>) {
 menuPrincipal()
}


fun menuPrincipal(){

    println("Menu principal porfavor seleccione una opcion");

    println("[L]istar canciones\n" +
            "[A]gregar canciones\n" +
            "[E]scoger cancion\n" +
            "[M]Eliminar todas las canciones\n" +
            "[S]alir")
    println("Porfavor seleccione una opción:");
    when (readLine()!!.toLowerCase()) {
        "l" -> {
           val canciones= cargarDatos()

           imprimirCanciones(canciones)
            menuPrincipal()

        }
        "a" -> {
           agregarCancion()
            val canciones= cargarDatos()

            imprimirCanciones(canciones)
            menuPrincipal()
        }
        "e" -> {

            println("Ingrese el numero de la canción a escoger")
            val posicion:Int= readLine()!!.toInt()
            menuCancion(posicion)
        }
        "m" -> {
            eliminarTodasLasCanciones()
            menuPrincipal()
        }
        "s" -> println("Salir")
        else -> menuPrincipal()
    }
}
fun cargarDatos():MutableList<MutableList<String>> {
    val bufferedReader = File("src/canciones.txt").bufferedReader()
    val canciones = mutableListOf<MutableList<String>>()

    bufferedReader.useLines { lines -> lines.forEach {
        val cancion = it.split(";").toMutableList<String>()
        canciones.add(cancion)
    } }
    bufferedReader.close()
    return canciones
}
fun guardarDatos(canciones: MutableList<MutableList<String>>) {
    val escribirArchivo = FileWriter("src/canciones.txt", false)

    canciones.forEach(Consumer { cancion:MutableList<String> ->
        escribirArchivo.write("${cancion[0]};${cancion[1]};${cancion[2]};${cancion[3]}\n")
         })
    escribirArchivo.close()
}
fun imprimirCanciones(canciones:MutableList<MutableList<String>>){

    var index:Int=1;
    canciones.forEach(Consumer { cancion:MutableList<String> ->
        println("${index}.-Nombre canción:"+cancion[0]+" Autor/Cancion:"+cancion[1])
        index++;
    })

}

fun agregarCancion(){

    val canciones= cargarDatos()
    println("Ingrese el nombre de la canción")
    val nombre= readLine().toString()
    println("Ingrese el autor o gurpo de la canción")
    val autor= readLine().toString()
    println("Ingrese el genero de la canción")
    val genero= readLine().toString()
    println("Ingrese los acordes separados por una coma (,)")

    val acordes= readLine().toString()
    val cancion = mutableListOf<String>(nombre,autor,genero, acordes)
    canciones.add(cancion);
    guardarDatos(canciones);

}
fun modificarAcordes(posicion:Int){

    var canciones= cargarDatos()
   println("Ingrese los acordes separados por una coma (,)")
    val acordes= readLine().toString()
    canciones[posicion-1][3]=acordes
    guardarDatos(canciones);

}
fun eliminarAcordes(posicion:Int){

    var canciones= cargarDatos()
    println("Ingrese los acordes separados por una coma (,)")
    val acordes= ""
    canciones[posicion-1][3]=acordes
    guardarDatos(canciones);

}
fun seleccinarCancion(index:Int):MutableList<String>{

    val canciones=cargarDatos()
    var cancionSeleccionada=canciones[index-1]
    return cancionSeleccionada
}
fun imprimirCancion(cancion:MutableList<String>){

    println("Nombre:${cancion[0]}\n" +
            "Autor/Grupo:${cancion[1]}\n" +
            "Genero:${cancion[2]}\n" +
            "Acordes:${cancion[3]}")

}
fun eliminarCancion(index:Int){

    var canciones=cargarDatos()
    canciones.removeAt(index-1)
    guardarDatos(canciones);
}
fun eliminarTodasLasCanciones(){

    val canciones: MutableList<MutableList<String>> = mutableListOf()
    guardarDatos(canciones);
}
fun menuCancion(posicion: Int){

    var cancion=seleccinarCancion(posicion)
    imprimirCancion(cancion)
    println("[E]eliminar cancion\n" +
            "[M]odificar cancion\n" +
            "[D]Editar acordes\n" +
            "[L]Eliminar acordes\n" +
            "[V]olver menu principal")
    println("Porfavor seleccione una opción:");
    when (readLine()!!.toLowerCase()) {
        "e" -> {
            eliminarCancion(posicion)
            menuCancion(posicion)
        }
        "m" -> {
            eliminarCancion(posicion)
            agregarCancion()
            menuPrincipal()
        }
        "d" -> {
            modificarAcordes(posicion)
            menuCancion(posicion)
        }
        "l" -> {
            eliminarAcordes(posicion)
            menuCancion(posicion)
        }
        "v" -> menuPrincipal()
        else -> menuPrincipal()
    }

}

