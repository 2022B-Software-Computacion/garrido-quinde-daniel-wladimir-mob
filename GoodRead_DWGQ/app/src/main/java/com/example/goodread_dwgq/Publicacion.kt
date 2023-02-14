package com.example.goodread_dwgq

import android.os.Parcel
import android.os.Parcelable

class Publicacion (
    private var id:Int,
    private var lector: Lector?,
    private var libro: Libro?,
    private var comentario:String?
        ):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readParcelable(Lector::class.java.classLoader),
        parcel.readParcelable(Libro::class.java.classLoader),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeParcelable(lector, flags)
        parcel.writeParcelable(libro, flags)
        parcel.writeString(comentario)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Publicacion> {
        override fun createFromParcel(parcel: Parcel): Publicacion {
            return Publicacion(parcel)
        }

        override fun newArray(size: Int): Array<Publicacion?> {
            return arrayOfNulls(size)
        }
    }
}

}