package com.example.mydevice.ui.tablet

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


class TabletAdapter(private var context: Context, private val tabletList : ArrayList<Tablet>) : RecyclerView.Adapter<TabletAdapter.TabletViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TabletViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.tablet_item,
            parent, false)
        return TabletViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TabletViewHolder, position: Int) {

        val tablet = tabletList[position]

        holder.nomeTablet.text = tablet.nome
        holder.prezzoTablet.text = tablet.prezzo.toString()
        holder.descrizioneTablet.text = tablet.descrizione

        Glide.with(context)
            .load(tablet.img)
            .into(holder.imgTablet)

        holder.itemView.setOnClickListener {
            val preferito = tablet.preferito
            val id = tablet.id
            val img = tablet.img
            val nome = tablet.nome
            val prezzo = tablet.prezzo.toString()
            val descrizione = tablet.descrizione
            val ram = tablet.ram.toString()
            val colore = tablet.colore
            val marca = tablet.marca
            val dimMemSec = tablet.dimMemSec.toString()
            val dimensioneSchermo = tablet.dimensioneSchermo.toString()
            val numeroFotocamere = tablet.numeroFotocamere.toString()
            val peso = tablet.peso.toString()
            val bluetooth = tablet.bluetooth
            val specFotocamere = tablet.specFotocamere
            val sistemaOperativo = tablet.sistemaOperativo
            val wifi = tablet.wifi

            /**set Data*/
            val mIntent = Intent(context, TabletNewActivity::class.java)
            mIntent.putExtra("preferito", preferito)
            mIntent.putExtra("id", id)
            mIntent.putExtra("img", img)
            mIntent.putExtra("nome", nome)
            mIntent.putExtra("prezzo", prezzo)
            mIntent.putExtra("descrizione", descrizione)
            mIntent.putExtra("ram", ram)
            mIntent.putExtra("colore", colore)
            mIntent.putExtra("marca", marca)
            mIntent.putExtra("dimMemSec", dimMemSec)
            mIntent.putExtra("dimensioneSchermo", dimensioneSchermo)
            mIntent.putExtra("numeroFotocamere", numeroFotocamere)
            mIntent.putExtra("peso", peso)
            mIntent.putExtra("bluetooth", bluetooth)
            mIntent.putExtra("specFotocamere", specFotocamere)
            mIntent.putExtra("sistemaOperativo", sistemaOperativo)
            mIntent.putExtra("wifi", wifi)
            context.startActivity(mIntent)
        }
    }

    override fun getItemCount(): Int {
        return tabletList.size
    }

    class TabletViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomeTablet: TextView = itemView.findViewById(R.id.nomeTablet)
        val prezzoTablet: TextView = itemView.findViewById(R.id.prezzoTablet)
        var descrizioneTablet: TextView = itemView.findViewById(R.id.descrizioneTablet)
        val imgTablet: ImageView = itemView.findViewById(R.id.imageTablet)
    }
}
