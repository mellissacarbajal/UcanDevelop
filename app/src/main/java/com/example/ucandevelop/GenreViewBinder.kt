package com.example.ucandevelop

import android.view.View
import com.bobomee.android.recyclerviewhelper.expandable.TreeNode
import com.bobomee.android.recyclerviewhelper.expandable.TreeViewBinder
import android.view.animation.Animation.RELATIVE_TO_SELF
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GenreViewBinder : TreeViewBinder<GenreViewBinder.ViewHolder>(){
    override fun bindView(holder: GenreViewBinder.ViewHolder?, position: Int, node: TreeNode<*>?) {
        val genre = node?.getContent() as Genre
        holder?.sTextView?.setText(genre.title)
        holder?.mImageView?.setImageResource(genre.iconResId)

        val height = node.height

        holder?.itemView?.setPadding(60*height,3,3,3)
    }

    override fun getLayoutId(): Int {
        return R.layout.list_item_genre;
    }

    override fun provideViewHolder(itemView: View): GenreViewBinder.ViewHolder {
        return ViewHolder(itemView)
    }

    class ViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
        val sTextView: TextView
        val sArrow: ImageView
        val mImageView: ImageView

        init {

            mImageView = rootView.findViewById(R.id.list_item_genre_icon) as ImageView
            sTextView = rootView.findViewById(R.id.list_item_genre_name) as TextView
            sArrow = rootView.findViewById(R.id.list_item_genre_arrow) as ImageView
        }

        fun animateExpand() {
            val rotate = RotateAnimation(360f, 180f, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f)
            rotate.duration = 300
            rotate.fillAfter = true
            sArrow.setAnimation(rotate)
        }

        fun animateCollapse() {
            val rotate = RotateAnimation(180f, 360f, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f)
            rotate.duration = 300
            rotate.fillAfter = true
            sArrow.setAnimation(rotate)
        }
    }

}