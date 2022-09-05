package com.example.mydevice.ui.preferiti

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
import com.example.mydevice.ui.cellulari.CellulareNewActivity
import com.example.mydevice.ui.pc.PcNewActivity
import com.example.mydevice.ui.tablet.TabletNewActivity


class PreferitiAdapter(private var context: Context, /*private val pcList : ArrayList<Pc>, private val tabList : ArrayList<Tablet>, private val cellList : ArrayList<Cellulare>,*/ private val prefList : ArrayList<Preferiti>) : RecyclerView.Adapter<PreferitiAdapter.PreferitiViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreferitiViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.preferiti_item,
            parent, false)
        return PreferitiViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PreferitiViewHolder, position: Int) {


        val pref = prefList[position]

        holder.nomePreferito.text = pref.nome
        holder.prezzoPreferito.text = pref.prezzo.toString()
        holder.descrizionePreferito.text = pref.descrizione

        Glide.with(context)
            .load(pref.img)
            .into(holder.imgPreferito)

        holder.itemView.setOnClickListener {
            if (pref.tipo == "desktop" || pref.tipo=="notebook") {
                //val pc = pcList[position]
                val preferito = pref.preferito
                val id = pref.id
                val img = pref.img
                val nome = pref.nome
                val prezzo = pref.prezzo.toString()
                val descrizione = pref.descrizione
                val ram = pref.ram.toString()
                val colore = pref.colore
                val marca = pref.marca
                val dimMemSec = pref.dimMemSec.toString()
                val dimensioneSchermo = pref.dimensioneSchermo.toString()
                val numeroProcessore = pref.numeroProcessore.toString()
                val peso = pref.peso.toString()
                val processore = pref.processore
                val schedaGrafica = pref.schedaGrafica
                val sistemaOperativo = pref.sistemaOperativo
                val tipoMemSec = pref.tipoMemSec

                /**set Data*/
                val mIntent = Intent(context, PcNewActivity::class.java)
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
                mIntent.putExtra("numeroProcessore", numeroProcessore)
                mIntent.putExtra("peso", peso)
                mIntent.putExtra("processore", processore)
                mIntent.putExtra("schedaGrafica", schedaGrafica)
                mIntent.putExtra("sistemaOperativo", sistemaOperativo)
                mIntent.putExtra("tipoMemSec", tipoMemSec)
                context.startActivity(mIntent)
            } else if (pref.tipo == "tablet") {
                //val tabl = tabList[position]
                val preferito = pref.preferito
                val id = pref.id
                val img = pref.img
                val nome = pref.nome
                val prezzo = pref.prezzo.toString()
                val descrizione = pref.descrizione
                val ram = pref.ram.toString()
                val colore = pref.colore
                val marca = pref.marca
                val dimMemSec = pref.dimMemSec.toString()
                val dimensioneSchermo = pref.dimensioneSchermo.toString()
                val numeroFotocamere = pref.numeroFotocamere.toString()
                val peso = pref.peso.toString()
                val bluetooth = pref.bluetooth
                val specFotocamere = pref.specFotocamere
                val sistemaOperativo = pref.sistemaOperativo
                val wifi = pref.wifi

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

            } else if(pref.tipo == "cellulare"){
                //val cell = cellList[position]
                val preferito = pref.preferito
                val id = pref.id
                val img = pref.img
                val nome = pref.nome
                val prezzo = pref.prezzo.toString()
                val descrizione = pref.descrizione
                val ram = pref.ram.toString()
                val colore = pref.colore
                val marca = pref.marca
                val dimMemSec = pref.dimMemSec.toString()
                val dimensioneSchermo = pref.dimensioneSchermo.toString()
                val numeroFotocamere = pref.numeroFotocamere.toString()
                val peso = pref.peso.toString()
                val bluetooth = pref.bluetooth
                val specFotocamere = pref.specFotocamere
                val sistemaOperativo = pref.sistemaOperativo
                val wifi = pref.wifi
                val sicurezzaBiometrica = pref.sicurezzaBiometrica

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
    }

    override fun getItemCount(): Int {
        return prefList.size
    }

    class PreferitiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomePreferito: TextView = itemView.findViewById(R.id.nomePreferito)
        val prezzoPreferito: TextView = itemView.findViewById(R.id.prezzoPreferito)
        var descrizionePreferito: TextView = itemView.findViewById(R.id.descrizionePreferito)
        val imgPreferito: ImageView = itemView.findViewById(R.id.imagePreferito)
    }
}
