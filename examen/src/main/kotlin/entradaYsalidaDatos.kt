import java.io.File
import java.io.IOException
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

public fun leerArchivoTexto():ArrayList<String>{
    val path = "file.txt"
    val archivo:ArrayList<String> = arrayListOf<String>()
    try {
        val sc = Scanner(File(path))
        while (sc.hasNextLine()) {
            val line = sc.nextLine()
            if(line==""){
                println("good")
            }else{
                archivo.add(line)}
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return archivo;
}

public fun crearFacultades(listaFacultades:ArrayList<String>):ArrayList<Facultad>{
    val facultades:ArrayList<Facultad> = arrayListOf<Facultad>()
    listaFacultades.forEach {
            facultad:String->
        val aux = facultad.split(",")
        val alumnos = crearAlumnos(aux.subList(5,aux.size-1))
        facultades.add(Facultad(aux[0],aux[1].toInt(),aux[2].toDouble(),aux[3].toBoolean(),
            LocalDate.parse(aux[4]),alumnos))

    }
    return facultades
}

public fun crearAlumnos(listaAlumnos:List<String>): ArrayList<Alumno>? {
    val alumnos:ArrayList<Alumno> = arrayListOf<Alumno>()

    if (listaAlumnos.size%6!=0){
        error("error en creacion de alumnos"+"\n"+listaAlumnos)
        return null
    }
    else{
        for (i in 0..listaAlumnos.size-6 step 6){
            alumnos.add(Alumno(listaAlumnos[i].toInt(),listaAlumnos[i+1].toString(),listaAlumnos[i+2].toString()
                ,listaAlumnos[i+3].toDouble(),listaAlumnos[i+4].toBoolean(),listaAlumnos[i+5].toCharArray()[0]))
        }
        return alumnos
    }
}

public fun leerDatos(): ArrayList<Facultad> {
    val archivoEnString = leerArchivoTexto()
    val datosComoObjetos = crearFacultades(archivoEnString);
    return datosComoObjetos
}

public fun escribirDatos(listaFacultades: ArrayList<Facultad>){
    val file = File("file.txt")
    var cvs = ""
    listaFacultades.forEach {
        cvs += it.escribir() +"\n"
    }
    file.writeText(cvs, Charsets.UTF_8)

}