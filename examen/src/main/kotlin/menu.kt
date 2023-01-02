import java.time.LocalDate

public fun iniciarMenu(listaFacultades: ArrayList<Facultad>) {
    val menuGeneral = "----------Sistema de Gestion de Facultades------------" + "\n" +
            "Seleccione la opcion(Numero): "+"\n"+
            "1) Crear nueva facultad" + "\n" +
            "2) Mostrar Facultades" +"\n" +
            "3) Modificar Facultad" +"\n" +
            "4) Eliminar Facultad"+"\n" +
            "5) Salir del sistema"
    println(menuGeneral)
    val respuesta = try { readLine()!!.toInt() }
        catch (e: NumberFormatException) {
            println("Error")
            iniciarMenu(listaFacultades)
            null
        }

    when(respuesta){
        1->{crearNuevaFacultad(listaFacultades)}
        2->{mostrarFacultades(listaFacultades)}
        3->{
            println("Elija la facultad a modificar: ")
            val valor = menuFacultadesIndexed(listaFacultades)
            if (valor != null) {
                if (valor<listaFacultades.size){
                    modificarFacultad(listaFacultades,valor)
                }else{
                    println("error")
                    iniciarMenu(listaFacultades)
                }
            }

        }
        4->{borrarFacultad(listaFacultades)}
        5-> println("Gracias por preferirnos")
        else -> {
            println("Error")
            iniciarMenu(listaFacultades)
        }
    }

}

public fun borrarFacultad(listaFacultades: ArrayList<Facultad>){
    println("que facultad va a borrar?:\n")
    val respuesta = menuFacultadesIndexed(listaFacultades)
    if (respuesta != null) {
        if (respuesta<listaFacultades.size){
            listaFacultades.remove(listaFacultades[respuesta])
            println("Eliminado con exito")
            iniciarMenu(listaFacultades)
        }else{
            println("Valor equivocado")
            borrarFacultad(listaFacultades)
        }
    }
    //submenu(listaFacultades)
}

public fun submenu(listaFacultades: ArrayList<Facultad>){
    val menu ="Seleccione la opcion:\n" +
            "1) Volver al menu\n" +
            "2) Cerrar aplicacion\n"
    println(menu)
    val respuesta = try { readLine()!!.toInt() }
    catch (e: NumberFormatException) {
        println("Error")
        submenu(listaFacultades)
        null
    }
    when(respuesta){
        1->iniciarMenu(listaFacultades)
        2->{
            println("gracias por preferirnos")
        }
        else -> {
            println("error")
            submenu(listaFacultades)
        }
    }
}

public fun mostrarFacultades(listaFacultades: ArrayList<Facultad>){
    listaFacultades.forEach { println(it.toString()) }
    submenu(listaFacultades)
}

public fun menuFacultadesIndexed(listaFacultades: ArrayList<Facultad>): Int? {
    listaFacultades.forEachIndexed { index, facultad -> println("$index) ${facultad.getNombre()}")}
    val respuesta = try { readLine()!!.toInt() }
    catch (e: NumberFormatException) {
        println("Error")
        borrarFacultad(listaFacultades)
        null
    }
    return respuesta
}

public fun crearNuevaFacultad(listaFacultades: ArrayList<Facultad>){
    println("Ingresar el nombre: ")
    val nombre = try { readLine()!!.toString() }
    catch (e: NumberFormatException) {
        println("ERROR INTENTE DE NUEVO")
        crearNuevaFacultad(listaFacultades)
        error("error")
    }
    println("Ingresar codigo: ")
    val codigo = try { readLine()!!.toInt() }
    catch (e: NumberFormatException) {
        println("ERROR INTENTE DE NUEVO")
        crearNuevaFacultad(listaFacultades)
        error("error")
    }
    println("Ingresar promedio: ")
    val promedio = try { readLine()!!.toDouble() }
    catch (e: NumberFormatException) {println("ERROR INTENTE DE NUEVO")
        crearNuevaFacultad(listaFacultades)
        error("error")
    }
    println("Ingresar si tiene maestrias: ")
    val tieneMaestrias = try { readLine()!!.toBoolean() }
    catch (e: NumberFormatException) {println("ERROR INTENTE DE NUEVO")
        crearNuevaFacultad(listaFacultades)
        error("error")
    }
    println("Ingresar fecha en el formato YY-MM-DD: ")
    val fecha = try { LocalDate.parse(readLine()!!) }
    catch (e: NumberFormatException) {
        crearNuevaFacultad(listaFacultades)
        error("error")
    }
    println("Crear Alumnos: ")
    val alumnos:ArrayList<Alumno> = arrayListOf()
    listaFacultades.add(Facultad(nombre,codigo,promedio,tieneMaestrias,fecha,alumnos))
    println("Facultad creada con exito, para ingresar los alumnos dirigirese a la seccion de modificar alumno")
    iniciarMenu(listaFacultades)
}

public fun modificarFacultad(listaFacultades: ArrayList<Facultad>,valor:Int?) {
    var facultad = listaFacultades[valor!!]
    println("Que desea modificar? \n")
    println(
        "Facultad: " + "\n" +
                "1) Nombre: " + facultad.getNombre() + "\n" +
                "2) Codigo: " + facultad.getCodigo() + "\n" +
                "3) Promedio De Reprobados: " + facultad.getPromedioReprobados() + "\n" +
                "4) Tiene Maestrias: " + facultad.getTieneMestrias() + "\n" +
                "5) Fecha De Inaguracion: " + facultad.getFechaInaguracion() + "\n" +
                "6) Lista de Alumnos: " + facultad.getListaAlumnos() + "\n" +
                "7) salir"
    )
    val respuesta = try {
        readLine()!!.toInt()
    } catch (e: NumberFormatException) {
        println("Error")
        modificarFacultad(listaFacultades, valor)
        null
    }
    when (respuesta) {
        1 -> {
            println("Ingrese nuevo nombre: ")
            val aux = try {
                readLine()!!.toString()
            } catch (e: NumberFormatException) {
                println("Error")
                modificarFacultad(listaFacultades, valor)
                error("error")
            }
            facultad.setNombre(aux)
            println("exito")
            modificarFacultad(listaFacultades, valor)
        }

        2 -> {
            println("Ingrese nuevo Codigo: ")
            val aux = try {
                readLine()!!.toInt()
            } catch (e: NumberFormatException) {
                println("Error")
                modificarFacultad(listaFacultades, valor)
                error("error")
            }
            facultad.setCodigo(aux)
            println("exito")
            modificarFacultad(listaFacultades, valor)
        }

        3 -> {
            println("Ingrese nuevo promedio de reprobados: ")
            val aux = try {
                readLine()!!.toDouble()
            } catch (e: NumberFormatException) {
                println("Error")
                modificarFacultad(listaFacultades, valor)
                error("error")
            }
            facultad.setPromedioReprobados(aux)
            println("exito")
            modificarFacultad(listaFacultades, valor)
        }

        4 -> {
            println("Ingrese nuevo booleano, false o true: ")
            val aux = try {
                readLine()!!.toBoolean()
            } catch (e: NumberFormatException) {
                println("Error")
                modificarFacultad(listaFacultades, valor)
                error("error")
            }
            facultad.setTieneMaestrias(aux)
            println("exito")
            modificarFacultad(listaFacultades, valor)
        }

        5 -> {
            println("Ingrese nueva fecha de inaguracion, formato YY-MM-DD: ")
            val aux = try {
                LocalDate.parse(readLine()!!)
            } catch (e: NumberFormatException) {
                println("Error")
                modificarFacultad(listaFacultades, valor)
                error("error")
            }
            facultad.setFechaInaguracion(aux)
            println("exito")
            modificarFacultad(listaFacultades, valor)
        }

        6 -> {
            seleccionarAlumno(listaFacultades,valor)
        }

        7 -> {
            iniciarMenu(listaFacultades)
        }

        else -> {
            modificarFacultad(listaFacultades, valor)
        }
    }
}

public fun seleccionarAlumno(listaFacultades: ArrayList<Facultad>, valor:Int){
    val facultadActual = listaFacultades[valor]
    var listaAlumno = facultadActual.getListaAlumnos()
    println("Que alumno desea modificar? ")
    if (listaAlumno != null) {
        listaAlumno.forEachIndexed { index, alumno ->
            println("$index) ${alumno.getNombre()}")
        }
            println("${listaAlumno.size+1}) Crear Nuevo Alumno")
        val aux = try {
            readLine()!!.toInt()
        } catch (e: NumberFormatException) {
            println("Error")
            seleccionarAlumno(listaFacultades, valor)
            error("error")
        }
        var esNuevoAlumno:Boolean=false
        if (aux>listaAlumno.size){
            println("exito")
            esNuevoAlumno=true
        }
        modificarAlumnos(listaFacultades,valor,aux,esNuevoAlumno)
    }

}

public fun modificarAlumnos(listaFacultades: ArrayList<Facultad>,valorFacultad:Int,valorAlumno: Int, esNuevoAlumno:Boolean){
    if (esNuevoAlumno){
        crearAlumno(listaFacultades,valorFacultad)
    }else{
        val alumnoCambiar = listaFacultades[valorFacultad].getListaAlumnos()?.get(valorAlumno)
        println("que desea hacer? : ")
        println("1) Editar Alumno\n" +
                "2) Borrar Alumno")
        val respuesta = try {
            readLine()!!.toInt()
        } catch (e: NumberFormatException) {
            println("Error")
            modificarAlumnos(listaFacultades,valorFacultad,valorAlumno,esNuevoAlumno)
            error("error")
        }
        when(respuesta){
            1->{
                println(alumnoCambiar?.imprimirMenu())
                val respuesta2 = try {
                    readLine()!!.toInt()
                } catch (e: NumberFormatException) {
                    println("Error")
                    modificarAlumnos(listaFacultades,valorFacultad,valorAlumno,esNuevoAlumno)
                    error("error")
                }
                when(respuesta2){
                    1->{
                        println("Ingrese el nuevo codigo: ")
                        val aux = readLine()!!.toInt()
                        alumnoCambiar?.setCodigo(aux)
                        println("cambio con exito")
                    }
                    2->{
                        println("Ingrese el nuevo nombre: ")
                        val aux = readLine()!!.toString()
                        alumnoCambiar?.setNombre(aux)
                        println("cambio con exito")
                    }
                    3->{println("Ingrese el nuevo apellido: ")
                        val aux = readLine()!!.toString()
                        alumnoCambiar?.setApellido(aux)
                        println("cambio con exito")}
                    4->{println("Ingrese el nuevo IRA: ")
                        val aux = readLine()!!.toDouble()
                        alumnoCambiar?.setIRA(aux)
                        println("cambio con exito")}
                    5->{println("Ingrese si el almuno es repetido (true/false: ")
                        val aux = readLine()!!.toBoolean()
                        alumnoCambiar?.setEsRepetido(aux)
                        println("cambio con exito")}
                    6->{println("Ingrese el sexo (h/m): ")
                        val aux = readLine()!!.toCharArray()[0]
                        alumnoCambiar?.setSexo(aux)
                        println("cambio con exito")}
                }
            }
            2->{listaFacultades[valorFacultad].getListaAlumnos()?.removeAt(valorAlumno)
            println("Eliminado con exito")
                iniciarMenu(listaFacultades)
            }
        }
    }
}

public fun crearAlumno(listaFacultades: ArrayList<Facultad>, valorFacultad: Int) {
    println("Ingrese el nuevo codigo: ")
    val codigo = readLine()!!.toInt()
    println("Ingrese el nuevo nombre: ")
    val nombre = readLine()!!.toString()
    println("Ingrese el nuevo apellido: ")
    val apellido = readLine()!!.toString()
    println("Ingrese el nuevo IRA: ")
    val IRA = readLine()!!.toDouble()
    println("Ingrese si el almuno es repetido (true/false: ")
    val esRepetido = readLine()!!.toBoolean()
    println("Ingrese el sexo (h/m): ")
    val sexo = readLine()!!.toCharArray()[0]
    listaFacultades[valorFacultad].getListaAlumnos()?.add(Alumno(codigo,nombre, apellido, IRA, esRepetido, sexo))
    println("Exito")
    iniciarMenu(listaFacultades)
}





