package com.example.studentsrecyclerapp.models

import android.os.Parcel
import android.os.Parcelable

data class Student(
    var name: String,
    var id: String,
    var phone: String,
    var address: String,
    val avatarUrl: String,
    var isChecked: Boolean
): Parcelable {

    constructor(parcel: Parcel) : this(
        name = parcel.readString() ?: "",
        id = parcel.readString() ?: "",
        phone = parcel.readString() ?: "",
        address = parcel.readString() ?: "",
        avatarUrl = parcel.readString() ?: "",
        isChecked = parcel.readByte() != 0.toByte()
    )
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(id)
        parcel.writeString(phone)
        parcel.writeString(address)
        parcel.writeString(avatarUrl)
        parcel.writeByte(if (isChecked) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Student> {
        override fun createFromParcel(parcel: Parcel): Student {
            return Student(parcel)
        }

        override fun newArray(size: Int): Array<Student?> {
            return arrayOfNulls(size)
        }
    }
}
