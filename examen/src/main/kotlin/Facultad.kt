import java.time.LocalDate

class Facultad(
    private var nombre:String,
    private var codigo:Int,
    private var promedioDeReprobados: Double,
    private var tieneMaestrias: Boolean,
    private var fechaDeInaguracion: LocalDate,
    private var listaAlumnos: ArrayList<Alumno>? = arrayListOf<Alumno>()//lista Alumnos
) {

    override fun toString(): String {
        return  "Facultad" + "\n"+
                "Nombre = $nombre" + "\n"+
                "Codigo = $codigo" +"\n"+
                "Promedio De Reprobados = $promedioDeReprobados" +"\n"+
                "Tiene Maestrias = $tieneMaestrias" +"\n"+
                "Fecha De Inaguracion = $fechaDeInaguracion" +"\n"+
                "Lista de Alumnos = \n" + printAlumnos();
    }

    private fun escribirAlumnos():String{
        var alumnosLista=""
        listaAlumnos?.forEach { alumno: Alumno ->
            alumnosLista += "$alumno,"
        }
        return alumnosLista
    }

    public fun escribir(): String {
        val lista =escribirAlumnos()
        return "$nombre,$codigo,$promedioDeReprobados,$tieneMaestrias,$fechaDeInaguracion,$lista"
    }

    public fun printAlumnos():String{
        var alumnosLista=""
        listaAlumnos?.forEach { alumno: Alumno ->
            alumnosLista += alumno.imprimir()
        }
        return alumnosLista
    }

    public fun setFechaInaguracion(fechaDeInaguracion: LocalDate){
        this.fechaDeInaguracion=fechaDeInaguracion
    }
    public fun getFechaInaguracion():LocalDate{
        return this.fechaDeInaguracion
    }
    public fun getListaAlumnos(): ArrayList<Alumno>? {
        return this.listaAlumnos
    }



    public fun setNombre(nombre: String){
        this.nombre=nombre
    }
    public fun getNombre():String{
        return this.nombre
    }
    public fun setCodigo(codigo: Int){
        this.codigo=codigo
    }
    public fun getCodigo():Int{
        return this.codigo
    }
    public fun setPromedioReprobados(promedioDeReprobados: Double){
        this.promedioDeReprobados=promedioDeReprobados
    }
    public fun getPromedioReprobados():Double{
        return this.promedioDeReprobados
    }
    public fun setTieneMaestrias(tieneMaestrias: Boolean){
        this.tieneMaestrias=tieneMaestrias
    }
    public fun getTieneMestrias():Boolean{
        return tieneMaestrias
    }


}