package com.example.pokemoexamen

interface CRUD<T,ID> {
    fun create(entity:T)
    suspend fun read(id:ID):T?
    fun update(entity:T)
    fun delete(id:ID)
}

