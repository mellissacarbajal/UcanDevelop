package com.example.ucandevelop

import android.view.View
import com.bobomee.android.recyclerviewhelper.expandable.TreeViewBinder
import com.bobomee.android.recyclerviewhelper.expandable.TreeNode
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ArtistViewBind : TreeViewBinder<ArtistViewBind.ViewHolder>() {
    override fun bindView(holder: ViewHolder?, position: Int, node: TreeNode<*>?) {
        val artist = node?.getContent() as Artist
        holder?.mTextView?.setText(artist.name)

        val height = node.height

        holder?.itemView?.setPadding(120*height,3,3,3)
    }

    override fun getLayoutId(): Int {
        return R.layout.list_item_artist;
    }

    override fun provideViewHolder(itemView: View): ViewHolder {
        return ViewHolder(itemView)
    }

    class ViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {

        val mTextView: TextView

        init {
            mTextView = rootView.findViewById(R.id.list_item_artist_name) as TextView
        }
    }
}