package com.example.ucandevelop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NivelAdapter : RecyclerView.Adapter<NivelAdapter.ViewHolder>(){

    private var resource: Int = 0
    private lateinit var ejer: ArrayList<Ejercicios>

    fun nivelMessage(ejer: ArrayList<Ejercicios>, resource: Int){
        this.ejer = ejer
        this.resource = resource
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View = LayoutInflater.from(parent.context).inflate(resource,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ejer.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var nivel: String = ejer.get(position).nivel

        holder.nivel.setText(nivel)
    }

    class ViewHolder: RecyclerView.ViewHolder {
        var nivel: TextView
        var view: View

        constructor(view: View) : super(view) {
            this.view = view
            this.nivel = view.findViewById(R.id.list_item_genre_name) as TextView
        }
    }

}