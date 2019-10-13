package com.example.ucandevelop

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import com.bobomee.android.recyclerviewhelper.expandable.interfaces.LayoutItemType

class Genre : Parcelable, LayoutItemType {

    val iconResId: Int
    lateinit var title: String

    constructor(title: String, iconResId: Int) {
        this.title = title
        this.iconResId = iconResId
    }

    private constructor(`in`: Parcel) {
        iconResId = `in`.readInt()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(iconResId)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is Genre) return false

        val genre = o as Genre?

        return iconResId == genre!!.iconResId

    }

    override fun hashCode(): Int {
        return iconResId
    }

    override fun getLayoutId(): Int {
        return R.layout.list_item_genre
    }

    companion object {

        @SuppressLint("ParcelCreator")
        val CREATOR: Parcelable.Creator<Genre> = object : Parcelable.Creator<Genre> {
            override fun createFromParcel(`in`: Parcel): Genre {
                return Genre(`in`)
            }

            override fun newArray(size: Int): Array<Genre?> {
                return arrayOfNulls(size)
            }
        }
    }
}