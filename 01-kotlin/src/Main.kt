import com.sun.org.apache.xpath.internal.operations.Bool
import java.util.*
import java.util.function.Consumer
import kotlin.collections.ArrayList

fun main(args:Array<String>) {
    print("Hola");
    var edad = 31;

    var edadProfesor = 31; //NO SE PUEDE REASIGNAR
    var numero: Int;     //SE PUEDE REASIGNAR
    //Tipos de variables
    val nombreProfesor: String = "Vicente Adrian";
    val sueldo: Double = 12.20
    val inicialProfesor: Char = 'V';
    val fechaNacimiento: Date = Date(); //Instanciar una clase
    // when en vez de Switch

    when (sueldo) {
        12.20 -> print("\nsueldo normal")
        -12.20 -> print("Sueldo negativo")
        else -> println("No se reconoce el sueldo")
    }


    var nombre:String?=null;
   // nombre="Jeferson"
    imprimirNombre(nombre)
    //if(nombre!=null) println(nombre.length)


    //IF de una linea como el operador ternario
    // val esSueldoMayorAlEstablecido= if(sueldo==12.20) true else false

    calcularSueldo(29.00)
    //Puedo poner en otro orden las variables al llamar a la funcion
    calcularSueldo(tasa = 14.00, sueldo = 4.00)
    val arregloConstante: Array<Int> = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9);
    //los arreglos constantes no tienen metodos para añadir elementos
    val arregloCumpleanos: ArrayList<Int> = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9);
    print(arregloCumpleanos);
    arregloCumpleanos.add(100);
    print(arregloCumpleanos)
    arregloCumpleanos.remove(100)
    println(arregloCumpleanos)
//FOR EACH RETORNA Unit
    arregloCumpleanos.forEach { println("Valor de la iteracion " + it); }
    arregloCumpleanos.forEach { fecha: Int -> println("Valor de la iteracion " + fecha); }
    arregloCumpleanos.forEach(Consumer { fecha: Int? -> println("Valor interacion " + fecha) })
    val respuestaMap = arregloCumpleanos.map({ x ->
        val y = x * -1 //AQUI PONER TODAS LAS OPERACIONES
        return@map y
    })

    println(respuestaMap)
    val respuestaMap2 = arregloCumpleanos.map({ x -> x * -1 })
    println(respuestaMap2)

    val respuestaFilter = arregloCumpleanos.filter { x -> x > 5 }
    println(respuestaFilter)

    //Compara si algun elemento cumple la condicion
    val respuestaAny: Boolean = arregloCumpleanos.any { value: Int -> value < 1 }
    println(respuestaAny)
    //Compara si es que todos los elementos complen la cndicion
    val respuestaAll: Boolean = arregloCumpleanos.all { value: Int -> value < 5 }
    println((respuestaAll))
//Reduce devuelve la suma de los elemento
//el acumulador siempre empieza en vacio
    val respuestaReduce: Int = arregloCumpleanos.reduce{ acc, i -> acc + i }
    println(respuestaReduce);
 //Restar iteraciones desde una semilla
    val respuestaFold:Int = arregloCumpleanos.fold(200,{acc,i -> acc-i});
    print(respuestaFold);
val arrayAtaque:ArrayList<Int> = arrayListOf(30,31,22,23,20)
   val vidaActual:Double= arrayAtaque.map{it*0.8}.filter { it>18 }.fold(100.00,{acc,danio-> acc-danio})

val nuevoNumeroUno=Suma2(1,2)
    val nuevoNumeroDos=Suma2(null,2)
    val nuevoNumeroTres=Suma2(1,null)
    val nuevoNumeroCuatro=Suma2(null,null)

    //USar companio Object
    println(Suma2.arregloNumeros)
    Suma2.agregarNumero(1)
    println(Suma2.arregloNumeros)
    Suma2.eliminarNumero(0)
    println(Suma2.arregloNumeros)
}
fun imprimirNombre(nombre:String?){
    println("\nLLAMADA ELVIS OPERATOR: "+nombre?.length)
}

 // OJO ESTE OPCIONAL NO ES COMO EL DE TYPESCRIPT DELEY HAY QUE MANDAR COMO PARAMETRO PERO PUEDE SER NULL
fun calcularSueldo(sueldo:Double,tasa:Double=12.00,opcion:Int?=null): Double? {
     print("Calculando sueldo")
    if(opcion!=null){
        return sueldo*tasa*opcion;
    }else{
        return sueldo*tasa
    }

}
//Unit es como void
fun imprimirMensaje():Unit{
    print("hola mundo"  );
}


abstract  class NumerosKotlin(var numeroUno:Int, var  numeroDos:Int){}
abstract  class NumerosJava{
    val numeroUno:Int
    val numeroDos:Int
    constructor(uno:Int,dos:Int){
        numeroUno=uno;
        numeroDos=dos;
    }
}

class Suma(uno:Int,dos:Int):NumerosKotlin(uno,dos){
    public  fun sumar():Int{
        return this.numeroUno+this.numeroDos
    }
}

class Suma2(uno:Int,dos:Int):NumerosKotlin(uno,dos){
    init {
        println("Hola init")
    }
    constructor(uno:Int?,dos:Int):this(uno ?: 0,dos){
         println("hola1")

    }
    constructor(uno:Int,dos:Int?):this(uno,if(dos==null) 0 else dos){
        println("hola2")
    }
    constructor(uno:Int?,dos:Int?):this(if(uno==null) 0 else uno,if(dos==null) 0 else dos){
        println("hola3")
    }
    companion object{
        val arregloNumeros= arrayListOf<Int>(1,2,3,4)
        fun agregarNumero(nuevoNumero:Int){
            this.arregloNumeros.add(nuevoNumero)
        }
        fun eliminarNumero(posicion:Int){
            this.arregloNumeros.removeAt(posicion)
        }
    }
}


