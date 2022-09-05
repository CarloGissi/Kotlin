package com.example.mydevice.ui.cellulari

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


class CellulareAdapter(private var context: Context, private val cellList : ArrayList<Cellulare>) : RecyclerView.Adapter<CellulareAdapter.CellulareViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellulareViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cellulare_item,
            parent, false)
        return CellulareViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CellulareViewHolder, position: Int) {

        val cellulare = cellList[position]

        holder.nomeCellulare.text = cellulare.nome
        holder.prezzoCellulare.text = cellulare.prezzo.toString()
        holder.descrizioneCellulare.text = cellulare.descrizione

        Glide.with(context)
            .load(cellulare.img)
            .into(holder.imgCellulare)

        holder.itemView.setOnClickListener {
            val preferito = cellulare.preferito
            val id = cellulare.id
            val img = cellulare.img
            val nome = cellulare.nome
            val prezzo = cellulare.prezzo.toString()
            val descrizione = cellulare.descrizione
            val ram = cellulare.ram.toString()
            val colore = cellulare.colore
            val marca = cellulare.marca
            val dimMemSec = cellulare.dimMemSec.toString()
            val dimensioneSchermo = cellulare.dimensioneSchermo.toString()
            val numeroFotocamere = cellulare.numeroFotocamere.toString()
            val peso = cellulare.peso.toString()
            val bluetooth = cellulare.bluetooth
            val specFotocamere = cellulare.specFotocamere
            val sistemaOperativo = cellulare.sistemaOperativo
            val wifi = cellulare.wifi
            val sicurezzaBiometrica = cellulare.sicurezzaBiometrica

            /**set Data*/
            val mIntent = Intent(context, CellulareNewActivity::class.java)
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
            mIntent.putExtra("sicurezzaBiometrica", sicurezzaBiometrica)
            context.startActivity(mIntent)
        }
    }

    override fun getItemCount(): Int {
        return cellList.size
    }

    class CellulareViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomeCellulare: TextView = itemView.findViewById(R.id.nomeCellulare)
        val prezzoCellulare: TextView = itemView.findViewById(R.id.prezzoCellulare)
        var descrizioneCellulare: TextView = itemView.findViewById(R.id.descrizioneCellulare)
        val imgCellulare: ImageView = itemView.findViewById(R.id.imageCellulare)
    }
}
