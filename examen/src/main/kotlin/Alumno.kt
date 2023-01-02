class Alumno(
    private var codigoUnico: Int? = null,
    private var nombre:String,
    private var apellido:String,
    private var IRA: Double,
    private var esRepetido:Boolean,
    private var sexo:Char
){
    //Setters and getters
    public fun setCodigo(codigo:Int){
        this.codigoUnico=codigo;
    }
    public fun getCodigo(): Int? {
        return codigoUnico;
    }
    public fun setNombre(nombre: String){
        this.nombre=nombre
    }
    public fun getNombre():String{
        return nombre
    }
    public fun setApellido(Apellido: String){
        this.apellido=Apellido
    }
    public fun getApellido():String{
        return apellido
    }
    public fun setIRA(IRA: Double){
        this.IRA=IRA
    }
    public fun getIRA():Double{
        return IRA
    }
    public fun setEsRepetido(repetido: Boolean){
        this.esRepetido=repetido
    }
    public fun getEsRepetido():Boolean{
        return this.esRepetido
    }
    public fun setSexo(sexo: Char){
        this.sexo=sexo
    }
    public fun getSexo():Char{
        return this.sexo
    }

    override fun toString(): String {
        return "$codigoUnico,$nombre,$apellido,$IRA,$esRepetido,$sexo"
    }

    public fun imprimir():String{
        val alumno = "\n\tAlumno:\n" +
                "\tCodigo: $codigoUnico\n" +
                "\tNombre: $nombre $apellido\n" +
                "\tIRA: $IRA\n" +
                "\tAlumno Repetido de semestre: $esRepetido\n" +
                "\tSexo: $sexo\n"
        return alumno
    }

    public fun imprimirMenu():String{
        val alumno = "\n\tAlumno:\n" +
                "\t1)Codigo: $codigoUnico\n" +
                "\t2)Nombre: $nombre \n" +
                "\t3)Apellido: $apellido\n" +
                "\t4)IRA: $IRA\n" +
                "\t5)Alumno Repetido de semestre: $esRepetido\n" +
                "\t6)Sexo: $sexo\n"
        return alumno
    }

}