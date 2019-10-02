package miguel.rossi.punkapi.domain

import android.os.Parcel
import android.os.Parcelable

data class Beer(
    val id: Int,
    val name: String,
    val tagLine: String,
    val description: String,
    val imageUrl: String,
    val abv: Double,
    val foodPairing: List<String>,
    val brewersTips: String,
    val contributedBy: String
) : Parcelable {

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<Beer> {
            override fun createFromParcel(parcel: Parcel) = Beer(parcel)
            override fun newArray(size: Int): Array<Beer?> = arrayOfNulls(size)
        }
    }

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.createStringArrayList()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(name)
        dest.writeString(tagLine)
        dest.writeString(description)
        dest.writeString(imageUrl)
        dest.writeDouble(abv)
        dest.writeList(foodPairing)
        dest.writeString(brewersTips)
        dest.writeString(contributedBy)
    }

    override fun describeContents() = 0

}
