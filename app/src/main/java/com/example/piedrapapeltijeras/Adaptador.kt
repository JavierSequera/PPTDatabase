package com.example.piedrapapeltijeras

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adaptador(
    val tasks: List<EntidadPuntuacion>
    ) : RecyclerView.Adapter<Adaptador.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = tasks[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.activity_login, parent, false))
    }

    override fun getItemCount(): Int {return tasks.size}

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTask = view.findViewById<TextView>(R.id.texto)

        fun bind(task: EntidadPuntuacion) {
            tvTask.text = task.nickName
        }
    }
}