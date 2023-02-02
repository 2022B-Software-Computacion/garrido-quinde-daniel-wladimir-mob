package com.example.dwgq_application1

import android.os.Parcel
import android.os.Parcelable

class Bentrenador(
    var id:Int,
    var nombre:String?,
    var descripcion: String?)
    : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun toString(): String {
        return "$nombre - $descripcion"
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(nombre)
        p0?.writeString(descripcion)
        p0?.writeInt(id)
    }


    companion object CREATOR : Parcelable.Creator<Bentrenador> {
        override fun createFromParcel(parcel: Parcel): Bentrenador {
            return Bentrenador(parcel)
        }

        override fun newArray(size: Int): Array<Bentrenador?> {
            return arrayOfNulls(size)
        }
    }
}