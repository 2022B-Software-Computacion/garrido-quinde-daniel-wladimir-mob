package com.example.goodread_dwgq

import android.os.Parcel
import android.os.Parcelable

class Lector(
    private var id:Int,
    private var nombre:String?,
    private var librosLeidos: Parcelable?
):Parcelable {
    constructor(parcel: Parcel):this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readParcelable(Libro::class.java.classLoader)
        ){

    }
    

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeParcelable(librosLeidos,flags)
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