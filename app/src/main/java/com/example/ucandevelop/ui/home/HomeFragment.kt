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
import androidx.recyclerview.widget.RecyclerView
import com.bobomee.android.recyclerviewhelper.expandable.interfaces.ExpandCollapseListener
import com.bobomee.android.recyclerviewhelper.expandable.TreeViewAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ucandevelop.*
import com.example.ucandevelop.R
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList


@Suppress("UNUSED_LAMBDA_EXPRESSION")
class HomeFragment : Fragment() {

    private lateinit var db: DatabaseReference
    private var mRoot: List<TreeNode<*>>? = null
    private lateinit var homeViewModel: HomeViewModel
    private val ejercicios: ArrayList<Ejercicios> = ArrayList()
    private lateinit var nAdapter: NivelAdapter

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
        val treeViewAdapter = TreeViewAdapter(mRoot, Arrays.asList(ArtistViewBind(), GenreViewBinder()))

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
        db = FirebaseDatabase.getInstance().getReference()
        var facil = db.child("Ejercicios")
        var query = facil.orderByChild("nivel").equalTo("facil").limitToFirst(10)
        mRoot = ArrayList()

        query.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(this@HomeFragment.context,"error de llamado", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()){
                    for (ds:DataSnapshot in p0.children){
                        val id: String = ds.key.toString()
                        val descripcion: String = ds.child("descripcion").value.toString()
                        val ayuda: String = ds.child("ayuda").value.toString()
                        val nivel: String = ds.child("nivel").value.toString()
                        ejercicios.add(Ejercicios(id,descripcion,ayuda,nivel))
                    }

                    //nAdapter = NivelAdapter().nivelMessage(ejercicios,R.layout.list_item_genre)

                }
            }

        })

        (mRoot as ArrayList<TreeNode<*>>).add(
            TreeNode(Genre("probando", R.drawable.ic_saxaphone))
                .addChild(TreeNode(Artist("")))
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