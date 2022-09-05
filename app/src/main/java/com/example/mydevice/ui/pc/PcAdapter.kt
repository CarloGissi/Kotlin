package com.example.mydevice.ui.pc

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mydevice.R


class PcAdapter(private var context: Context, private val pcList : ArrayList<Pc>) : RecyclerView.Adapter<PcAdapter.PcViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PcViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.pc_item,
            parent,false)
        return PcViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PcViewHolder, position: Int) {

        val pc = pcList[position]

        holder.nomePc.text = pc.nome
        holder.prezzoPc.text = pc.prezzo.toString()
        holder.descrizionePc.text = pc.descrizione

        Glide.with(context)
            .load(pc.img)
            .into(holder.imgPc)

        holder.itemView.setOnClickListener {
            val preferito = pc.preferito
            val id = pc.id
            val img = pc.img
            val nome = pc.nome
            val prezzo = pc.prezzo.toString()
            val descrizione = pc.descrizione
            val ram = pc.ram.toString()
            val colore = pc.colore
            val marca = pc.marca
            val dimMemSec = pc.dimMemSec.toString()
            val dimensioneSchermo = pc.dimensioneSchermo.toString()
            val numeroProcessore = pc.numeroProcessore.toString()
            val peso = pc.peso.toString()
            val processore = pc.processore
            val schedaGrafica = pc.schedaGrafica
            val sistemaOperativo = pc.sistemaOperativo
            val tipoMemSec = pc.tipoMemSec

            /**set Data*/
            val mIntent = Intent(context,PcNewActivity::class.java)
            mIntent.putExtra("preferito", preferito)
            mIntent.putExtra("id", id)
            mIntent.putExtra("img",img)
            mIntent.putExtra("nome",nome)
            mIntent.putExtra("prezzo", prezzo)
            mIntent.putExtra("descrizione",descrizione)
            mIntent.putExtra("ram",ram)
            mIntent.putExtra("colore",colore)
            mIntent.putExtra("marca",marca)
            mIntent.putExtra("dimMemSec",dimMemSec)
            mIntent.putExtra("dimensioneSchermo",dimensioneSchermo)
            mIntent.putExtra("numeroProcessore",numeroProcessore)
            mIntent.putExtra("peso",peso)
            mIntent.putExtra("processore",processore)
            mIntent.putExtra("schedaGrafica",schedaGrafica)
            mIntent.putExtra("sistemaOperativo",sistemaOperativo)
            mIntent.putExtra("tipoMemSec",tipoMemSec)
            context.startActivity(mIntent)
        }
    }

    override fun getItemCount(): Int {
        return pcList.size
    }

    class PcViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val nomePc : TextView = itemView.findViewById(R.id.nomePc)
        val prezzoPc : TextView = itemView.findViewById(R.id.prezzoPc)
        var descrizionePc : TextView = itemView.findViewById(R.id.descrizionePc)
        val imgPc : ImageView = itemView.findViewById(R.id.imagePc)
    }
}