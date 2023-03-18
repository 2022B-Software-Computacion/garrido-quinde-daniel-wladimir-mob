package com.example.pokemoexamen

import java.util.Date

class Alumno(
    public var nombre:String?,
    public var ira:Number?,
    public var esRepetido:Boolean?,
    public var sexo:String?,
    public var id: Number?
) {
    override fun toString(): String {
        return "${nombre} - ${ira} - Tiene maestrias: ${esRepetido}"
    }
}