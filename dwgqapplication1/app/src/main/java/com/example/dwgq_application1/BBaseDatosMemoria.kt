package com.example.dwgq_application1

class BBaseDatosMemoria {
    companion object{
        val arregloBEntrenador = arrayListOf<Bentrenador>()
        init {
            arregloBEntrenador
                .add(Bentrenador("Daniel","dan@a.com"))

            arregloBEntrenador
                .add(Bentrenador("Wladimir","wl@a.com"))

            arregloBEntrenador
                .add(Bentrenador("Garrido","gr@a.com"))
        }
    }
}