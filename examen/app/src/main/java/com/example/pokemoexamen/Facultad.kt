package com.example.pokemoexamen

import java.util.*

class Facultad(
    public var nombre:String?,
    public var codigo:Number?,
    public var tieneMaestrias:Boolean?,
    public var cantidadAlumnos: Number?,
    public var alumnos:ArrayList<Alumno>?
) {
    override fun toString(): String {
        return "Facultad(nombre=$nombre, codigo=$codigo, tieneMaestrias=$tieneMaestrias, cantidadAlumnos=$cantidadAlumnos)"
    }
}