package com.example.dwgq_application1

class BBaseDatosMemoria {
    companion object{
        val arregloBEntrenador = arrayListOf<Bentrenador>()
        init {
            arregloBEntrenador
                .add(Bentrenador(1,"Daniel","dan@a.com"))

            arregloBEntrenador
                .add(Bentrenador(2,"Wladimir","wl@a.com"))

            arregloBEntrenador
                .add(Bentrenador(3,"Garrido","gr@a.com"))
        }
    }
}