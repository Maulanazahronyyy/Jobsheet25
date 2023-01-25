package com.Maulanazhrny.jobsheet25

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SiswaAdapter(private val data: ArrayList<Siswa>): RecyclerView.Adapter<SiswaAdapter.SiswaViewHolder>(){


    class SiswaViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val judull = itemView.findViewById<TextView>(R.id.judul)
        private val isii = itemView.findViewById<TextView>(R.id.isi)
        fun bind(get: Siswa) {
            judull.text = get.judull
            isii.text = get.isii
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiswaViewHolder {
        return SiswaViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_siswa, parent, false))
    }

    override fun onBindViewHolder(holder: SiswaViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}