package com.example.goodread_dwgq

import android.os.Parcel
import android.os.Parcelable

class Lector(
     var id:Int,
     var nombre:String?,
     var librosLeidos: ArrayList<Libro>?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readArrayList(Libro::class.java.classLoader) as ArrayList<Libro>?
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeArray(arrayOf(Libro::class.java.classLoader)) as ArrayList<Libro>
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Lector> {
        override fun createFromParcel(parcel: Parcel): Lector {
            return Lector(parcel)
        }

        override fun newArray(size: Int): Array<Lector?> {
            return arrayOfNulls(size)
        }
    }

}