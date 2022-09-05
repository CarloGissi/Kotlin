package com.example.mydevice.ui.pc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydevice.R
import com.example.mydevice.databinding.FragmentPcBinding
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.toObject


class PcFragment : Fragment() {

    private var _binding: FragmentPcBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding : FragmentPcBinding get() = _binding!!

    private lateinit var progressBar: ProgressBar
    private lateinit var pcRecyclerview : RecyclerView
    private lateinit var pcArrayList : ArrayList<Pc>
    private lateinit var pcAdapter: PcAdapter
    private lateinit var fireStoreDatabase : FirebaseFirestore



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return inflater.inflate(R.layout.fragment_pc, container, false)
        /*_binding = FragmentPcBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root*/
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        progressBar = view.findViewById(R.id.ProgressBar)
        progressBar.visibility = View.VISIBLE

        fireStoreDatabase= FirebaseFirestore.getInstance()
        pcArrayList = ArrayList()
        pcAdapter = PcAdapter(context!!, pcArrayList)
        val layoutManager = LinearLayoutManager(context)
        pcRecyclerview = view.findViewById(R.id.recyclerViewPc)
        pcRecyclerview.layoutManager = layoutManager
        pcRecyclerview.setHasFixedSize(true)


        val bundle = getArguments()
        val dispositivo= bundle?.getString("dispositivo")
        val sistema= bundle?.getString("sistemaOperativo")
        val prezzoMin= bundle?.getFloat("prezzoMin")
        val prezzoMax= bundle?.getFloat("prezzoMax")
        val ram= bundle?.getInt("ram")
        val utilizzo= bundle?.getString("utilizzo")
        val tipo= bundle?.getString("tipo")
        val numProcessore= bundle?.getInt("numProcessore")
        val dimMemSec= bundle?.getInt("dimMemSec")


        if(dispositivo == "null"){
            getAllPc(prezzoMin!!,prezzoMax!!)
        }else if(dispositivo=="Computer" && sistema=="null" && utilizzo=="null" && tipo=="null"){
            getAllPc(prezzoMin!!,prezzoMax!!)
        }else if(dispositivo=="Computer" && sistema!="null" && utilizzo=="null" && tipo=="null"){
            getAllPcSistema(sistema!!,prezzoMin!!, prezzoMax!!)
        }else if(dispositivo=="Computer" && sistema=="null" && utilizzo!="null" && tipo=="null"){
            if(utilizzo=="Game"){
                getAllPcRamGame(ram!!,prezzoMin!!, prezzoMax!!, numProcessore!!, dimMemSec!!)
            }else if(utilizzo=="Casa/Studio"){
                getAllPcRamCasaStudio(ram!!, prezzoMin!!, prezzoMax!!, numProcessore!!, dimMemSec!!)
            }else if(utilizzo=="Lavoro"){
                getAllPcRamLavoro(ram!!,prezzoMin!!, prezzoMax!!, numProcessore!!, dimMemSec!!)
            }
        }else if(dispositivo=="Computer" && sistema=="null" && utilizzo=="null" && tipo!="null"){
            getAllPcTipo(tipo!!,prezzoMin!!, prezzoMax!!)
        }else if(dispositivo=="Computer" && sistema!="null" && utilizzo!="null" && tipo!="null"){
            if(utilizzo=="Game"){
                getAllPcFiltratiGame(sistema!!, ram!!, tipo!!, prezzoMin!!, prezzoMax!!, numProcessore!!, dimMemSec!!)
            }else if(utilizzo=="Casa/Studio"){
                getAllPcFiltratiCasaStud(sistema!!, ram!!, tipo!!, prezzoMin!!, prezzoMax!!, numProcessore!!, dimMemSec!!)
            }else if(utilizzo=="Lavoro"){
                getAllPcFiltratiLav(sistema!!, ram!!, tipo!!, prezzoMin!!, prezzoMax!!, numProcessore!!, dimMemSec!!)
            }
        }else if(dispositivo=="Computer" && sistema!="null" && utilizzo!="null" && tipo=="null"){
            if(utilizzo=="Game"){
                getAllPcNoTipoGame(sistema!!, ram!!, prezzoMin!!, prezzoMax!!, numProcessore!!, dimMemSec!!)
            }else if(utilizzo=="Casa/Studio"){
                getAllPcNoTipoCasaStud(sistema!!, ram!!, prezzoMin!!, prezzoMax!!, numProcessore!!, dimMemSec!!)
            }else if(utilizzo=="Lavoro"){
                getAllPcNoTipoLav(sistema!!, ram!!, prezzoMin!!, prezzoMax!!, numProcessore!!, dimMemSec!!)
            }
        }else if(dispositivo=="Computer" && sistema!="null" && utilizzo=="null" && tipo!="null"){
            getAllPcTipoSistema(tipo!!,prezzoMin!!, prezzoMax!!, sistema!!)
        }else if(dispositivo=="Computer" && sistema=="null" && utilizzo!="null" && tipo!="null"){
            if(utilizzo=="Game"){
                getAllPcTipoGame(ram!!, tipo!!, prezzoMin!!, prezzoMax!!, numProcessore!!, dimMemSec!!)
            }else if(utilizzo=="Casa/Studio"){
                getAllPcTipoCasaStud(ram!!, tipo!!, prezzoMin!!, prezzoMax!!, numProcessore!!, dimMemSec!!)
            }else if(utilizzo=="Lavoro"){
                getAllPcTipoLav(ram!!, tipo!!, prezzoMin!!, prezzoMax!!, numProcessore!!, dimMemSec!!)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun showToast(str: String){
        Toast.makeText(this.context, str, Toast.LENGTH_LONG).show()
    }

    private fun getAllPc(prezzoMin:Float, prezzoMax:Float){
        fireStoreDatabase.collection("pc")
            .whereGreaterThanOrEqualTo("prezzo", prezzoMin)
            .whereLessThanOrEqualTo("prezzo", prezzoMax)
            .get().addOnSuccessListener{documents ->
                for(document in documents){
                    val u1 = document.toObject<Pc>()
                    val u2 = Pc(document.id, u1?.descrizione, u1?.nome, u1?.ram, u1?.img, u1?.colore, u1?.marca, u1?.dimMemSec, u1?.dimensioneSchermo, u1?.numeroProcessore, u1?.peso, u1?.preferito, u1?.prezzo, u1?.processore, u1?.schedaGrafica, u1?.sistemaOperativo, u1?.tipoMemSec, u1.tipo)
                    pcArrayList.add(u2)
                    }
                pcRecyclerview.adapter = pcAdapter
                progressBar.visibility = View.GONE
            }
    }
    private fun getAllPcSistema(sistema: String, prezzoMin: Float, prezzoMax:Float){
        fireStoreDatabase.collection("pc")
            .whereEqualTo("sistemaOperativo", sistema)
            .get().addOnSuccessListener{documents ->
                for(document in documents){
                    val u1 = document.toObject<Pc>()
                    val u2 = Pc(document.id, u1?.descrizione, u1?.nome, u1?.ram, u1?.img, u1?.colore, u1?.marca, u1?.dimMemSec, u1?.dimensioneSchermo, u1?.numeroProcessore, u1?.peso, u1?.preferito, u1?.prezzo, u1?.processore, u1?.schedaGrafica, u1?.sistemaOperativo, u1?.tipoMemSec, u1.tipo)
                    if(u2.prezzo!! >= prezzoMin && u2.prezzo!! <= prezzoMax){
                        pcArrayList.add(u2)
                    }

                }
                pcRecyclerview.adapter = pcAdapter
                progressBar.visibility = View.GONE
            }
    }
    private fun getAllPcRamGame(ram: Int, prezzoMin: Float, prezzoMax:Float, numProcessore: Int, dimMemSec: Int){
        fireStoreDatabase.collection("pc")
            .whereGreaterThan("ram", ram)
            .get().addOnSuccessListener{documents ->
                for(document in documents){
                    val u1 = document.toObject<Pc>()
                    val u2 = Pc(document.id, u1?.descrizione, u1?.nome, u1?.ram, u1?.img, u1?.colore, u1?.marca, u1?.dimMemSec, u1?.dimensioneSchermo, u1?.numeroProcessore, u1?.peso, u1?.preferito, u1?.prezzo, u1?.processore, u1?.schedaGrafica, u1?.sistemaOperativo, u1?.tipoMemSec, u1.tipo)
                    if(u2.prezzo!! >= prezzoMin && u2.prezzo!! <= prezzoMax && u2.numeroProcessore!!.toFloat() > numProcessore && u2.dimMemSec!! > dimMemSec && (u2.tipoMemSec == "HD" || u2.tipoMemSec == "SSD" )){
                        pcArrayList.add(u2)
                    }
                }
                pcRecyclerview.adapter = pcAdapter
                progressBar.visibility = View.GONE
            }
    }

    private fun getAllPcRamLavoro(ram: Int, prezzoMin: Float, prezzoMax:Float, numProcessore: Int, dimMemSec: Int){
        fireStoreDatabase.collection("pc")
            .whereGreaterThanOrEqualTo("ram", ram)
            .get().addOnSuccessListener{documents ->
                for(document in documents){
                    val u1 = document.toObject<Pc>()
                    val u2 = Pc(document.id, u1?.descrizione, u1?.nome, u1?.ram, u1?.img, u1?.colore, u1?.marca, u1?.dimMemSec, u1?.dimensioneSchermo, u1?.numeroProcessore, u1?.peso, u1?.preferito, u1?.prezzo, u1?.processore, u1?.schedaGrafica, u1?.sistemaOperativo, u1?.tipoMemSec, u1.tipo)
                    if(u2.prezzo!! >= prezzoMin && u2.prezzo!! <= prezzoMax && u2.numeroProcessore!!.toFloat() >= numProcessore && u2.dimMemSec!! >= dimMemSec && (u2.tipoMemSec == "HD" || u2.tipoMemSec == "SSD" )){
                        pcArrayList.add(u2)
                    }
                }
                pcRecyclerview.adapter = pcAdapter
                progressBar.visibility = View.GONE
            }
    }
    private fun getAllPcRamCasaStudio(ram: Int, prezzoMin: Float, prezzoMax:Float, numProcessore: Int, dimMemSec: Int){
        fireStoreDatabase.collection("pc")
            .whereLessThanOrEqualTo("ram", ram)
            .get().addOnSuccessListener{documents ->
                for(document in documents){
                    val u1 = document.toObject<Pc>()
                    val u2 = Pc(document.id, u1?.descrizione, u1?.nome, u1?.ram, u1?.img, u1?.colore, u1?.marca, u1?.dimMemSec, u1?.dimensioneSchermo, u1?.numeroProcessore, u1?.peso, u1?.preferito, u1?.prezzo, u1?.processore, u1?.schedaGrafica, u1?.sistemaOperativo, u1?.tipoMemSec, u1.tipo)
                    if(u2.prezzo!! >= prezzoMin && u2.prezzo!! <= prezzoMax && u2.numeroProcessore!!.toFloat() <= numProcessore && u2.dimMemSec!! <= dimMemSec && (u2.tipoMemSec == "HD" || u2.tipoMemSec == "SSD")){
                        pcArrayList.add(u2)
                    }
                }
                pcRecyclerview.adapter = pcAdapter
                progressBar.visibility = View.GONE
            }
    }
    private fun getAllPcTipo(tipo: String, prezzoMin:Float, prezzoMax:Float){
        fireStoreDatabase.collection("pc")
            .whereEqualTo("tipo", tipo)
            .get().addOnSuccessListener{documents ->
                for(document in documents){
                    val u1 = document.toObject<Pc>()
                    val u2 = Pc(document.id, u1?.descrizione, u1?.nome, u1?.ram, u1?.img, u1?.colore, u1?.marca, u1?.dimMemSec, u1?.dimensioneSchermo, u1?.numeroProcessore, u1?.peso, u1?.preferito, u1?.prezzo, u1?.processore, u1?.schedaGrafica, u1?.sistemaOperativo, u1?.tipoMemSec, u1.tipo)
                    if(u2.prezzo!! >= prezzoMin && u2.prezzo!! <= prezzoMax){
                        pcArrayList.add(u2)
                    }
                }
                pcRecyclerview.adapter = pcAdapter
                progressBar.visibility = View.GONE
            }
    }

    private fun getAllPcFiltratiGame(sistema:String, ram: Int, tipo:String, prezzoMin:Float, prezzoMax:Float, numProcessore: Int, dimMemSec: Int){
        fireStoreDatabase.collection("pc")
            .whereEqualTo("sistemaOperativo", sistema)
            .whereEqualTo("tipo", tipo)
            .get().addOnSuccessListener{documents ->
                for(document in documents){
                    val u1 = document.toObject<Pc>()
                    val u2 = Pc(document.id, u1?.descrizione, u1?.nome, u1?.ram, u1?.img, u1?.colore, u1?.marca, u1?.dimMemSec, u1?.dimensioneSchermo, u1?.numeroProcessore, u1?.peso, u1?.preferito, u1?.prezzo, u1?.processore, u1?.schedaGrafica, u1?.sistemaOperativo, u1?.tipoMemSec, u1.tipo)
                    if(u2.prezzo!! >= prezzoMin && u2.prezzo!! <= prezzoMax && u2.ram!!>=ram && u2.numeroProcessore!!.toFloat() > numProcessore && u2.dimMemSec!! > dimMemSec && (u2.tipoMemSec == "HD" || u2.tipoMemSec == "SSD" )){
                        pcArrayList.add(u2)
                    }
                }
                pcRecyclerview.adapter = pcAdapter
                progressBar.visibility = View.GONE
            }
    }
    private fun getAllPcFiltratiLav(sistema:String, ram: Int, tipo:String, prezzoMin:Float, prezzoMax:Float, numProcessore: Int, dimMemSec: Int){
        fireStoreDatabase.collection("pc")
            .whereEqualTo("sistemaOperativo", sistema)
            .whereEqualTo("tipo", tipo)
            .get().addOnSuccessListener{documents ->
                for(document in documents){
                    val u1 = document.toObject<Pc>()
                    val u2 = Pc(document.id, u1?.descrizione, u1?.nome, u1?.ram, u1?.img, u1?.colore, u1?.marca, u1?.dimMemSec, u1?.dimensioneSchermo, u1?.numeroProcessore, u1?.peso, u1?.preferito, u1?.prezzo, u1?.processore, u1?.schedaGrafica, u1?.sistemaOperativo, u1?.tipoMemSec, u1.tipo)
                    if(u2.prezzo!! >= prezzoMin && u2.prezzo!! <= prezzoMax && u2.ram!!>=ram && u2.numeroProcessore!!.toFloat() >= numProcessore && u2.dimMemSec!! >= dimMemSec && (u2.tipoMemSec == "HD" || u2.tipoMemSec == "SSD" )){
                        pcArrayList.add(u2)
                    }
                }
                pcRecyclerview.adapter = pcAdapter
                progressBar.visibility = View.GONE
            }
    }

    private fun getAllPcFiltratiCasaStud(sistema:String, ram: Int, tipo:String, prezzoMin:Float, prezzoMax:Float, numProcessore: Int, dimMemSec: Int){
        fireStoreDatabase.collection("pc")
            .whereEqualTo("sistemaOperativo", sistema)
            .whereEqualTo("tipo", tipo)
            .get().addOnSuccessListener{documents ->
                for(document in documents){
                    val u1 = document.toObject<Pc>()
                    val u2 = Pc(document.id, u1?.descrizione, u1?.nome, u1?.ram, u1?.img, u1?.colore, u1?.marca, u1?.dimMemSec, u1?.dimensioneSchermo, u1?.numeroProcessore, u1?.peso, u1?.preferito, u1?.prezzo, u1?.processore, u1?.schedaGrafica, u1?.sistemaOperativo, u1?.tipoMemSec, u1.tipo)
                    if(u2.prezzo!! >= prezzoMin && u2.prezzo!! <= prezzoMax && u2.ram!!<=ram && u2.numeroProcessore!!.toFloat() <= numProcessore && u2.dimMemSec!! <= dimMemSec && (u2.tipoMemSec == "HD" || u2.tipoMemSec == "SSD")){
                        pcArrayList.add(u2)
                    }
                }
                pcRecyclerview.adapter = pcAdapter
                progressBar.visibility = View.GONE
            }
    }
    private fun getAllPcNoTipoGame(sistema:String, ram: Int, prezzoMin:Float, prezzoMax:Float, numProcessore: Int, dimMemSec: Int){
        fireStoreDatabase.collection("pc")
            .whereEqualTo("sistemaOperativo", sistema)
            .get().addOnSuccessListener{documents ->
                for(document in documents){
                    val u1 = document.toObject<Pc>()
                    val u2 = Pc(document.id, u1?.descrizione, u1?.nome, u1?.ram, u1?.img, u1?.colore, u1?.marca, u1?.dimMemSec, u1?.dimensioneSchermo, u1?.numeroProcessore, u1?.peso, u1?.preferito, u1?.prezzo, u1?.processore, u1?.schedaGrafica, u1?.sistemaOperativo, u1?.tipoMemSec, u1.tipo)
                    if(u2.prezzo!! >= prezzoMin && u2.prezzo!! <= prezzoMax && u2.ram!!>=ram && u2.numeroProcessore!!.toFloat() > numProcessore && u2.dimMemSec!! > dimMemSec && (u2.tipoMemSec == "HD" || u2.tipoMemSec == "SSD" )){
                        pcArrayList.add(u2)
                    }
                }
                pcRecyclerview.adapter = pcAdapter
                progressBar.visibility = View.GONE
            }
    }
    private fun getAllPcNoTipoLav(sistema:String, ram: Int, prezzoMin:Float, prezzoMax:Float, numProcessore: Int, dimMemSec: Int){
        fireStoreDatabase.collection("pc")
            .whereEqualTo("sistemaOperativo", sistema)
            .get().addOnSuccessListener{documents ->
                for(document in documents){
                    val u1 = document.toObject<Pc>()
                    val u2 = Pc(document.id, u1?.descrizione, u1?.nome, u1?.ram, u1?.img, u1?.colore, u1?.marca, u1?.dimMemSec, u1?.dimensioneSchermo, u1?.numeroProcessore, u1?.peso, u1?.preferito, u1?.prezzo, u1?.processore, u1?.schedaGrafica, u1?.sistemaOperativo, u1?.tipoMemSec, u1.tipo)
                    if(u2.prezzo!! >= prezzoMin && u2.prezzo!! <= prezzoMax && u2.ram!!>=ram && u2.numeroProcessore!!.toFloat() >= numProcessore && u2.dimMemSec!! >= dimMemSec && (u2.tipoMemSec == "HD" || u2.tipoMemSec == "SSD" )){
                        pcArrayList.add(u2)
                    }
                }
                pcRecyclerview.adapter = pcAdapter
                progressBar.visibility = View.GONE
            }
    }
    private fun getAllPcNoTipoCasaStud(sistema:String, ram: Int, prezzoMin:Float, prezzoMax:Float, numProcessore: Int, dimMemSec: Int){
        fireStoreDatabase.collection("pc")
            .whereEqualTo("sistemaOperativo", sistema)
            .get().addOnSuccessListener{documents ->
                for(document in documents){
                    val u1 = document.toObject<Pc>()
                    val u2 = Pc(document.id, u1?.descrizione, u1?.nome, u1?.ram, u1?.img, u1?.colore, u1?.marca, u1?.dimMemSec, u1?.dimensioneSchermo, u1?.numeroProcessore, u1?.peso, u1?.preferito, u1?.prezzo, u1?.processore, u1?.schedaGrafica, u1?.sistemaOperativo, u1?.tipoMemSec, u1.tipo)
                    if(u2.prezzo!! >= prezzoMin && u2.prezzo!! <= prezzoMax && u2.ram!!<=ram && u2.numeroProcessore!!.toFloat() <= numProcessore && u2.dimMemSec!! <= dimMemSec && (u2.tipoMemSec == "HD" || u2.tipoMemSec == "SSD")){
                        pcArrayList.add(u2)
                    }
                }
                pcRecyclerview.adapter = pcAdapter
                progressBar.visibility = View.GONE
            }
    }
    private fun getAllPcTipoSistema(tipo: String, prezzoMin:Float, prezzoMax:Float, sistema: String){
        fireStoreDatabase.collection("pc")
            .whereEqualTo("tipo", tipo)
            .whereEqualTo("sistemaOperativo", sistema)
            .get().addOnSuccessListener{documents ->
                for(document in documents){
                    val u1 = document.toObject<Pc>()
                    val u2 = Pc(document.id, u1?.descrizione, u1?.nome, u1?.ram, u1?.img, u1?.colore, u1?.marca, u1?.dimMemSec, u1?.dimensioneSchermo, u1?.numeroProcessore, u1?.peso, u1?.preferito, u1?.prezzo, u1?.processore, u1?.schedaGrafica, u1?.sistemaOperativo, u1?.tipoMemSec, u1.tipo)
                    if(u2.prezzo!! >= prezzoMin && u2.prezzo!! <= prezzoMax){
                        pcArrayList.add(u2)
                    }
                }
                pcRecyclerview.adapter = pcAdapter
                progressBar.visibility = View.GONE
            }
    }
    private fun getAllPcTipoGame(ram: Int, tipo:String, prezzoMin:Float, prezzoMax:Float, numProcessore: Int, dimMemSec: Int){
        fireStoreDatabase.collection("pc")
            .whereEqualTo("tipo", tipo)
            .get().addOnSuccessListener{documents ->
                for(document in documents){
                    val u1 = document.toObject<Pc>()
                    val u2 = Pc(document.id, u1?.descrizione, u1?.nome, u1?.ram, u1?.img, u1?.colore, u1?.marca, u1?.dimMemSec, u1?.dimensioneSchermo, u1?.numeroProcessore, u1?.peso, u1?.preferito, u1?.prezzo, u1?.processore, u1?.schedaGrafica, u1?.sistemaOperativo, u1?.tipoMemSec, u1.tipo)
                    if(u2.prezzo!! >= prezzoMin && u2.prezzo!! <= prezzoMax && u2.ram!!>=ram && u2.numeroProcessore!!.toFloat() > numProcessore && u2.dimMemSec!! > dimMemSec && (u2.tipoMemSec == "HD" || u2.tipoMemSec == "SSD" )){
                        pcArrayList.add(u2)
                    }
                }
                pcRecyclerview.adapter = pcAdapter
                progressBar.visibility = View.GONE
            }
    }
    private fun getAllPcTipoLav(ram: Int, tipo:String, prezzoMin:Float, prezzoMax:Float, numProcessore: Int, dimMemSec: Int){
        fireStoreDatabase.collection("pc")
            .whereEqualTo("tipo", tipo)
            .get().addOnSuccessListener{documents ->
                for(document in documents){
                    val u1 = document.toObject<Pc>()
                    val u2 = Pc(document.id, u1?.descrizione, u1?.nome, u1?.ram, u1?.img, u1?.colore, u1?.marca, u1?.dimMemSec, u1?.dimensioneSchermo, u1?.numeroProcessore, u1?.peso, u1?.preferito, u1?.prezzo, u1?.processore, u1?.schedaGrafica, u1?.sistemaOperativo, u1?.tipoMemSec, u1.tipo)
                    if(u2.prezzo!! >= prezzoMin && u2.prezzo!! <= prezzoMax && u2.ram!!>=ram && u2.numeroProcessore!!.toFloat() >= numProcessore && u2.dimMemSec!! >= dimMemSec && (u2.tipoMemSec == "HD" || u2.tipoMemSec == "SSD" )){
                        pcArrayList.add(u2)
                    }
                }
                pcRecyclerview.adapter = pcAdapter
                progressBar.visibility = View.GONE
            }
    }
    private fun getAllPcTipoCasaStud(ram: Int, tipo:String, prezzoMin:Float, prezzoMax:Float, numProcessore: Int, dimMemSec: Int){
        fireStoreDatabase.collection("pc")
            .whereEqualTo("tipo", tipo)
            .get().addOnSuccessListener{documents ->
                for(document in documents){
                    val u1 = document.toObject<Pc>()
                    val u2 = Pc(document.id, u1?.descrizione, u1?.nome, u1?.ram, u1?.img, u1?.colore, u1?.marca, u1?.dimMemSec, u1?.dimensioneSchermo, u1?.numeroProcessore, u1?.peso, u1?.preferito, u1?.prezzo, u1?.processore, u1?.schedaGrafica, u1?.sistemaOperativo, u1?.tipoMemSec, u1.tipo)
                    if(u2.prezzo!! >= prezzoMin && u2.prezzo!! <= prezzoMax && u2.ram!!<=ram && u2.numeroProcessore!!.toFloat() <= numProcessore && u2.dimMemSec!! <= dimMemSec && (u2.tipoMemSec == "HD" || u2.tipoMemSec == "SSD")){
                        pcArrayList.add(u2)
                    }
                }
                pcRecyclerview.adapter = pcAdapter
                progressBar.visibility = View.GONE
            }
    }
}