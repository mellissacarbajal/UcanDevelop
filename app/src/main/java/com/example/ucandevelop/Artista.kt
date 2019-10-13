package com.example.ucandevelop

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import com.bobomee.android.recyclerviewhelper.expandable.interfaces.LayoutItemType

class Artist : Parcelable, LayoutItemType {

    val name: String?

    constructor(name: String) {
        this.name = name
    }

    private constructor(`in`: Parcel) {
        name = `in`.readString()
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is Artist) return false

        val artist = o as Artist?

        return if (name != null) name == artist!!.name else artist!!.name == null
    }

    override fun hashCode(): Int {
        return name?.hashCode() ?: 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun getLayoutId(): Int {
        return R.layout.list_item_artist
    }

    companion object {

        @SuppressLint("ParcelCreator")
        val CREATOR: Parcelable.Creator<Artist> = object : Parcelable.Creator<Artist> {
            override fun createFromParcel(`in`: Parcel): Artist {
                return Artist(`in`)
            }

            override fun newArray(size: Int): Array<Artist?> {
                return arrayOfNulls(size)
            }
        }
    }
}