import com.sun.jdi.IntegerValue
import java.util.*
import kotlin.collections.ArrayList

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    //tipos de variables:
    //Inmutables= No se puede re asignar
    val inmutable:String = "Daniel";

    // MUTABLES (Re Asignar)
    var mutable:String="daniel2"
    mutable="daniel3"
    //val >var


    //Sintaxis duck typing
    //si se ve como pato, vuela como pato y grazna como pato => es un Pato :v
    val ejemploVariable = "STRING";
    val edadEjemplo: Int = 12;
    ejemploVariable.trim()

    //variables primitivas
    val nombreProfesor: String = "DANIEL2222"
    val sueldo: Double = 1.8;
    //clases Java
    val fechaNacimiento:Date = Date();
    println(fechaNacimiento)

    //no existe switch
    val estadoCivilWhen = "S"
    when (estadoCivilWhen) {
        ("S") -> {
            println("acercarse")
        }
        "C" -> {
            println("alejarse")
        }
        "UN" -> println("hablar")
        else -> println("No reconnocido")
    }

    val sumaUno = Suma(1,1);
    val sumaDos = Suma(null,1);
    val sumaTres = Suma(1,null);
    val sumaCuatro = Suma(null,null);

    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    Suma.pi
    Suma.elevarAlCuadrado(2)
    println("historial: " + Suma.historialSumas)

    /*
    * ARREGLOS
    * TIPOS DE ARREGLOS: ESTATICO Y DINAMICO
    * */

    val arregloEstatico: Array<Int> = arrayOf<Int>(1,2,3)
    println("arreglo estatico: "+arregloEstatico)

    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(1,2,3,4,5,  6, 7, 8, 9, 10)
    println("arreglo sin agregar nada: " + arregloDinamico);
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println("arreglo dinamico modifiado: " + arregloDinamico);

    // OPERADORES -> Sirven para los arreglos estáticos y dinámicos

    // FOR EACH -> Unit
    // Iterar un arreglo

    val forEach:Unit = arregloDinamico.forEach {
        valorActual: Int ->
            println("valor actual: $valorActual")
    }
    arregloEstatico.forEachIndexed{
      indice:Int,valorActual: Int ->
        println("valor $valorActual Indice: $indice"  )
    }

    println("respuesta for each: "+ forEach)

    //map, sirve para cambiar todos los valores de un arreglo, esto devuelve
    //un arreglo mdofificado, dianmicos y estaticos

    val respuestaMap: List<Double> = arregloDinamico.map {
        valorACTUAL:Int -> return@map valorACTUAL.toDouble() + 100.00
    }

    println("respuestaMap: " + respuestaMap)
    val mapDos = arregloDinamico.map { it + 15 }//lo mismo que hacer la func flecha
    println("map 2: "+mapDos);

        /*
    * filter -> filtrar arreglos
    *devolver una expresion
    * nuevo arreglo filtrado
    * */

    val respuestaFilter: List<Int> = arregloDinamico
        .filter { valorActual: Int ->
            val mayoresACinco: Boolean = valorActual > 5// Expresion Condicion
            return@filter mayoresACinco
        }
    val respuestaFilterDos = arregloDinamico.filter { it <= 5 }
    println("respuesta filter: "+respuestaFilter)
    println("respuesta filter 2 :"+respuestaFilterDos)

    // OR AND
    // OR ->  ANY (Alguno cumple?)
    // AND ->  ALL (Todos cumplen?)

    val respuestaAny: Boolean = arregloDinamico
        .any { valorActual: Int ->
            return@any (valorActual > 5)
        }
    println("respuesta Any" + respuestaAny) // true

    val respuestaAll: Boolean = arregloDinamico
        .all { valorActual: Int ->
            return@all (valorActual > 5)
        }
    println("respuestaAll" + respuestaAll) // false

    // REDUCE -> Valor acumulado
    // Valor acumulado = 0 (Siempre 0 en lenguaje Kotlin)
    // [1, 2, 3, 4, 5] -> Sumeme todos los valores del arreglo
    // valorIteracion1 = valorEmpieza + 1 = 0 + 1 = 1 -> Iteracion 1

    val respuestaReduce: Int = arregloDinamico
        .reduce { // acumulado = 0 -> SIEMPRE EMPIEZA EN 0
                acumulado: Int, valorActual: Int ->
            return@reduce (acumulado + valorActual)
        }
    println("respuesta reduce"+respuestaReduce)


}

//funciones fuera de main
fun imprimirNombre (nombre:String):Unit{
    println("Nombre: $nombre")
}

fun calcularSueldo(
    sueldo: Double, //Requerido
    tasa:Double = 12.00, //opcional/defecto
    bono : Double? = null,//opcional, a veces es un double a veces es un null
):Double{
    /*String -> String?
    * Int -> Int?
    * Date -> Date?
    * */
    if(bono == null){
        return sueldo*(100/sueldo)
    }else{
        return sueldo * (100/tasa) + bono
    }
}

abstract class Numeros( // Constructor Primario
//     uno: Int, // Parametro
    protected val numeroUno: Int, // Propiedad de la clase protected
    protected val numeroDos: Int // Propiedad de la clase protected
) {
//    protected val numeroUno: Int
    init { // Bloque codigo constructor PRIMARIO
        this.numeroUno
        numeroUno
        this.numeroDos
        numeroDos
        println("Inicializado con constructor PRIMARIO")
    }
}

class Suma( // Constructor Primario Suma
    uno: Int, // Parametro
    dos: Int // Parametro
) : Numeros(uno, dos) {//se llama a la clase numeros
    init { // Bloque constructor primario
        this.numeroUno
        this.numeroDos
        println("init con primario")
    }

    constructor(//  Segundo constructor
        uno: Int?, // parametro posible null
        dos: Int // parametros
    ) : this(  // llamada constructor primario
        if (uno == null) 0 else uno,
        dos
    ) {
        println("init con secundario")
    }

    constructor(//  tercer constructor
        uno: Int, // parametros
        dos: Int? // parametro posible null
    ) : this(  // llamada constructor primario
        uno,
        if (dos == null) 0 else uno
    ){
        println("init con tercer")
    }

    constructor(//  cuarto constructor
        uno: Int?, // parametro posible null
        dos: Int? // parametro posible null
    ) : this(  // llamada constructor primario
        if (uno == null) 0 else uno,
        if (dos == null) 0 else uno
    ){
        println("init con cuarto constr")
    }

    public fun sumar(): Int {
        val total = numeroUno + numeroDos
        agregarHistorial(total)
        return total
    }
    companion object { // Atributos y Metodos "Compartidos"
        // entre las instancias
        val pi = 3.14
        fun elevarAlCuadrado(num: Int): Int {
            return num * num
        }
        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorNuevaSuma:Int){
            historialSumas.add(valorNuevaSuma)
        }
    }
}







