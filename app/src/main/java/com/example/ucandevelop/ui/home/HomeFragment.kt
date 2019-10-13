package com.example.ucandevelop.ui.home

import android.widget.Toast
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bobomee.android.recyclerviewhelper.expandable.TreeNode
import com.example.ucandevelop.R
import androidx.recyclerview.widget.RecyclerView
import com.bobomee.android.recyclerviewhelper.expandable.interfaces.ExpandCollapseListener
import com.bobomee.android.recyclerviewhelper.expandable.TreeViewAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ucandevelop.ArtistViewBind
import com.example.ucandevelop.GenreViewBinder
import com.example.ucandevelop.ToastUtil
import java.util.*
import com.example.ucandevelop.Artist
import com.example.ucandevelop.Genre



class HomeFragment : Fragment() {


    private var mRoot: List<TreeNode<*>>? = null
    private lateinit var homeViewModel: HomeViewModel

    fun newInstance(): HomeFragment {
        val args = Bundle()
        val fragment = HomeFragment()
        fragment.setArguments(args)
        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        /*homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        val mRoot = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = mRoot.findViewById(R.id.text_home)

        homeViewModel.text.observe(this, Observer {
            textView.text = it
        })*/
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()

        initViews(view)
    }

    private fun initViews(_view: View) {
        val rv = _view.findViewById(R.id.recycler_view) as RecyclerView
        rv.layoutManager = LinearLayoutManager(activity)
        val treeViewAdapter =
            TreeViewAdapter(mRoot, Arrays.asList(ArtistViewBind(), GenreViewBinder()))

        rv.adapter = treeViewAdapter

        treeViewAdapter.addExpandCollapseListener(object : ExpandCollapseListener {
            override fun onGroupExpanded(_holder: RecyclerView.ViewHolder, _treeNode: TreeNode<*>) {
                if (_holder is GenreViewBinder.ViewHolder) {
                    val viewHolder = _holder
                    viewHolder.animateExpand()
                }
            }

            override fun onGroupCollapsed(
                _holder: RecyclerView.ViewHolder,
                _treeNode: TreeNode<*>
            ) {
                if (_holder is GenreViewBinder.ViewHolder) {
                    val viewHolder = _holder
                    viewHolder.animateCollapse()
                }
            }

            override fun toggle(_holder: RecyclerView.ViewHolder, _treeNode: TreeNode<*>) {
                //ToastUtil.show(getActivity(),"toggle");
            }
        })

        treeViewAdapter.addOnTreeNodeClickListener { node, holder ->
            ToastUtil.show(activity!!.applicationContext,"addOnTreeNodeClick")
        }
    }

    private fun initData() {
        mRoot = ArrayList()

        (mRoot as ArrayList<TreeNode<*>>).add(
            TreeNode(Genre("Jazz", R.drawable.ic_saxaphone)).addChild(
                TreeNode(Artist("Bill Monroe"))
            )
                .addChild(TreeNode(Artist("Earl Scruggs")))
                .addChild(TreeNode(Artist("Osborne Brothers")))
                .addChild(TreeNode(Artist("John Hartford")))
        )

        (mRoot as ArrayList<TreeNode<*>>).add(
            TreeNode(Genre("Salsa", R.drawable.ic_maracas)).addChild(
                TreeNode(Artist("Earl Scruggs"))
            )
                .addChild(TreeNode(Artist("Osborne Brothers")))
                .addChild(TreeNode(Artist("John Hartford")))
                .addChild(
                    TreeNode(Genre("Bluegrass", R.drawable.ic_banjo)).addChild(
                        TreeNode(Artist("Earl Scruggs"))
                    )
                        .addChild(TreeNode(Artist("Osborne Brothers")))
                        .addChild(TreeNode(Artist("John Hartford")))
                        .addChild(
                            TreeNode(Genre("Jazz", R.drawable.ic_saxaphone)).addChild(
                                TreeNode(Artist("Earl Scruggs"))
                            )
                                .addChild(TreeNode(Artist("Osborne Brothers")))
                                .addChild(TreeNode(Artist("John Hartford")))
                                .addChild(
                                    TreeNode(Genre("Jazz", R.drawable.ic_saxaphone)).addChild(
                                        TreeNode(Artist("Earl Scruggs"))
                                    )
                                        .addChild(TreeNode(Artist("Bill Monroe")))
                                        .addChild(TreeNode(Artist("Earl Scruggs")))
                                        .addChild(TreeNode(Artist("Osborne Brothers")))
                                        .addChild(TreeNode(Artist("John Hartford")))
                                )
                        )
                )
        )
    }
}