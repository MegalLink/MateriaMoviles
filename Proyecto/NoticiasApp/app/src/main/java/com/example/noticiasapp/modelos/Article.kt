package com.example.noticiasapp.modelos

import android.os.Parcel
import android.os.Parcelable

class Article (
    var source: Source?,
    var author: String?,
    var title: String,
    var description: String?,
    var url: String?,
    var urlToImage: String?,
    var publishedAt: String?,
    var content: String?
):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Source::class.java.classLoader),
        parcel.readString(),
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeParcelable(source,p1)
        p0.writeString(author)
        p0.writeString(title)
        p0.writeString(description)
        p0.writeString(url)
        p0.writeString(urlToImage)
        p0.writeString(publishedAt)
        p0.writeString(content)
    }

    companion object CREATOR : Parcelable.Creator<Article> {
        override fun createFromParcel(parcel: Parcel): Article {
            return Article(parcel)
        }

        override fun newArray(size: Int): Array<Article?> {
            return arrayOfNulls(size)
        }
    }
}