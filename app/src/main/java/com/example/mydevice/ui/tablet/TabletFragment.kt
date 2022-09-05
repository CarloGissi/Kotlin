package com.example.mydevice.ui.tablet

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
import com.example.mydevice.databinding.FragmentTabletBinding
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.toObject


class TabletFragment : Fragment() {

    private var _binding: FragmentTabletBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding : FragmentTabletBinding get() = _binding!!

    private lateinit var progressBar: ProgressBar
    private lateinit var tabletRecyclerview : RecyclerView
    private lateinit var tabletArrayList : ArrayList<Tablet>
    private lateinit var tabletAdapter: TabletAdapter
    private lateinit var fireStoreDatabase : FirebaseFirestore


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return inflater.inflate(R.layout.fragment_tablet, container, false)
        /*_binding = FragmentPcBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = view.findViewById(R.id.ProgressBar)
        progressBar.visibility = View.VISIBLE

        fireStoreDatabase = FirebaseFirestore.getInstance()
        tabletArrayList = ArrayList()
        tabletAdapter = TabletAdapter(context!!, tabletArrayList)
        val layoutManager = LinearLayoutManager(context)
        tabletRecyclerview = view.findViewById(R.id.recyclerViewTablet)
        tabletRecyclerview.layoutManager = layoutManager
        tabletRecyclerview.setHasFixedSize(true)



        val bundle = getArguments()
        val dispositivo= bundle?.getString("dispositivo")
        val sistema= bundle?.getString("sistemaOperativo")
        val prezzoMin= bundle?.getFloat("prezzoMin")
        val prezzoMax= bundle?.getFloat("prezzoMax")
        val ram= bundle?.getInt("ram")
        val utilizzo= bundle?.getString("utilizzo")
        val dimMemSec= bundle?.getInt("dimMemSec")


        if (dispositivo == "null") {
            getAllTablet(prezzoMin!!, prezzoMax!!)
        } else if (dispositivo == "Tablet" && sistema == "null" && utilizzo == "null") {
            getAllTablet(prezzoMin!!, prezzoMax!!)
        } else if (dispositivo == "Tablet" && sistema != "null" && utilizzo == "null") {
            getAllTabletSistema(sistema!!, prezzoMin!!, prezzoMax!!)
        } else if (dispositivo == "Tablet" && sistema == "null" && utilizzo != "null") {
            if (utilizzo == "Game") {
                getAllTabletGame(ram!!, prezzoMin!!, prezzoMax!!, dimMemSec!!)
            } else if (utilizzo == "Casa/Studio") {
                getAllTabletCasaStudio(ram!!, prezzoMin!!, prezzoMax!!, dimMemSec!!)
            } else if (utilizzo == "Lavoro") {
                getAllTabletLavoro(ram!!, prezzoMin!!, prezzoMax!!, dimMemSec!!)
            }
        }else if(dispositivo == "Tablet" && sistema != "null" && utilizzo != "null"){
            if (utilizzo == "Game") {
                getAllTabletGameSistema(sistema!!, ram!!, prezzoMin!!, prezzoMax!!, dimMemSec!!)
            } else if (utilizzo == "Casa/Studio") {
                getAllTabletCasaStudioSistema(sistema!!, ram!!, prezzoMin!!, prezzoMax!!, dimMemSec!!)
            } else if (utilizzo == "Lavoro") {
                getAllTabletLavoroSistema(sistema!!, ram!!, prezzoMin!!, prezzoMax!!, dimMemSec!!)
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

    private fun getAllTablet(prezzoMin: Float, prezzoMax: Float){
        fireStoreDatabase.collection("tablet")
            .whereGreaterThanOrEqualTo("prezzo", prezzoMin)
            .whereLessThanOrEqualTo("prezzo", prezzoMax)
            .get().addOnSuccessListener{documents ->
                for(document in documents){
                    val u1 = document.toObject<Tablet>()
                    val u2 = Tablet(document.id, u1?.descrizione, u1?.nome, u1?.ram, u1?.img, u1?.colore, u1?.marca, u1?.dimMemSec, u1?.dimensioneSchermo, u1?.numeroFotocamere, u1?.peso, u1?.preferito, u1?.prezzo, u1?.bluetooth, u1?.specFotocamere, u1?.sistemaOperativo, u1?.wifi, u1.tipo)
                    tabletArrayList.add(u2)
                }
                tabletRecyclerview.adapter = tabletAdapter
                progressBar.visibility = View.GONE
            }
    }
    private fun getAllTabletSistema(sistema: String, prezzoMin: Float, prezzoMax: Float){
        fireStoreDatabase.collection("tablet")
            .whereEqualTo("sistemaOperativo", sistema)
            .get().addOnSuccessListener{documents ->
                for(document in documents){
                    val u1 = document.toObject<Tablet>()
                    val u2 = Tablet(document.id, u1?.descrizione, u1?.nome, u1?.ram, u1?.img, u1?.colore, u1?.marca, u1?.dimMemSec, u1?.dimensioneSchermo, u1?.numeroFotocamere, u1?.peso, u1?.preferito, u1?.prezzo, u1?.bluetooth, u1?.specFotocamere, u1?.sistemaOperativo, u1?.wifi, u1.tipo)
                    if(u2.prezzo!! >= prezzoMin && u2.prezzo!! <= prezzoMax){
                        tabletArrayList.add(u2)
                    }
                }
                tabletRecyclerview.adapter = tabletAdapter
                progressBar.visibility = View.GONE
            }
    }

    private fun getAllTabletGame(ram: Int, prezzoMin: Float, prezzoMax: Float, dimMemSec: Int){
        fireStoreDatabase.collection("tablet")
            .whereGreaterThan("ram", ram)
            .get().addOnSuccessListener{documents ->
                for(document in documents){
                    val u1 = document.toObject<Tablet>()
                    val u2 = Tablet(document.id, u1?.descrizione, u1?.nome, u1?.ram, u1?.img, u1?.colore, u1?.marca, u1?.dimMemSec, u1?.dimensioneSchermo, u1?.numeroFotocamere, u1?.peso, u1?.preferito, u1?.prezzo, u1?.bluetooth, u1?.specFotocamere, u1?.sistemaOperativo, u1?.wifi, u1.tipo)
                    if(u2.prezzo!! >= prezzoMin && u2.prezzo!! <= prezzoMax && u2.dimMemSec!! > dimMemSec){
                        tabletArrayList.add(u2)
                    }
                }
                tabletRecyclerview.adapter = tabletAdapter
                progressBar.visibility = View.GONE
            }
    }
    private fun getAllTabletLavoro(ram: Int, prezzoMin: Float, prezzoMax: Float, dimMemSec: Int){
        fireStoreDatabase.collection("tablet")
            .whereGreaterThanOrEqualTo("ram", ram)
            .get().addOnSuccessListener{documents ->
                for(document in documents){
                    val u1 = document.toObject<Tablet>()
                    val u2 = Tablet(document.id, u1?.descrizione, u1?.nome, u1?.ram, u1?.img, u1?.colore, u1?.marca, u1?.dimMemSec, u1?.dimensioneSchermo, u1?.numeroFotocamere, u1?.peso, u1?.preferito, u1?.prezzo, u1?.bluetooth, u1?.specFotocamere, u1?.sistemaOperativo, u1?.wifi, u1.tipo)
                    if(u2.prezzo!! >= prezzoMin && u2.prezzo!! <= prezzoMax && u2.dimMemSec!! >= dimMemSec){
                        tabletArrayList.add(u2)
                    }
                }
                tabletRecyclerview.adapter = tabletAdapter
                progressBar.visibility = View.GONE
            }
    }
    private fun getAllTabletCasaStudio(ram: Int, prezzoMin: Float, prezzoMax: Float, dimMemSec: Int){
        fireStoreDatabase.collection("tablet")
            .whereLessThanOrEqualTo("ram", ram)
            .get().addOnSuccessListener{documents ->
                for(document in documents){
                    val u1 = document.toObject<Tablet>()
                    val u2 = Tablet(document.id, u1?.descrizione, u1?.nome, u1?.ram, u1?.img, u1?.colore, u1?.marca, u1?.dimMemSec, u1?.dimensioneSchermo, u1?.numeroFotocamere, u1?.peso, u1?.preferito, u1?.prezzo, u1?.bluetooth, u1?.specFotocamere, u1?.sistemaOperativo, u1?.wifi, u1.tipo)
                    if(u2.prezzo!! >= prezzoMin && u2.prezzo!! <= prezzoMax && u2.dimMemSec!! <= dimMemSec){
                        tabletArrayList.add(u2)
                    }
                }
                tabletRecyclerview.adapter = tabletAdapter
                progressBar.visibility = View.GONE
            }
    }
    private fun getAllTabletGameSistema(sistema:String, ram: Int, prezzoMin: Float, prezzoMax: Float, dimMemSec: Int){
        fireStoreDatabase.collection("tablet")
            .whereEqualTo("sistemaOperativo", sistema)
            .get().addOnSuccessListener{documents ->
                for(document in documents){
                    val u1 = document.toObject<Tablet>()
                    val u2 = Tablet(document.id, u1?.descrizione, u1?.nome, u1?.ram, u1?.img, u1?.colore, u1?.marca, u1?.dimMemSec, u1?.dimensioneSchermo, u1?.numeroFotocamere, u1?.peso, u1?.preferito, u1?.prezzo, u1?.bluetooth, u1?.specFotocamere, u1?.sistemaOperativo, u1?.wifi, u1.tipo)
                    if(u2.prezzo!! >= prezzoMin && u2.prezzo!! <= prezzoMax && u2.dimMemSec!! > dimMemSec && u2.ram!!>ram){
                        tabletArrayList.add(u2)
                    }
                }
                tabletRecyclerview.adapter = tabletAdapter
                progressBar.visibility = View.GONE
            }
    }
    private fun getAllTabletLavoroSistema(sistema:String, ram: Int, prezzoMin: Float, prezzoMax: Float, dimMemSec: Int){
        fireStoreDatabase.collection("tablet")
            .whereEqualTo("sistemaOperativo", sistema)
            .get().addOnSuccessListener{documents ->
                for(document in documents){
                    val u1 = document.toObject<Tablet>()
                    val u2 = Tablet(document.id, u1?.descrizione, u1?.nome, u1?.ram, u1?.img, u1?.colore, u1?.marca, u1?.dimMemSec, u1?.dimensioneSchermo, u1?.numeroFotocamere, u1?.peso, u1?.preferito, u1?.prezzo, u1?.bluetooth, u1?.specFotocamere, u1?.sistemaOperativo, u1?.wifi, u1.tipo)
                    if(u2.prezzo!! >= prezzoMin && u2.prezzo!! <= prezzoMax && u2.dimMemSec!! >= dimMemSec && u2.ram!!>=ram){
                        tabletArrayList.add(u2)
                    }
                }
                tabletRecyclerview.adapter = tabletAdapter
                progressBar.visibility = View.GONE
            }
    }
    private fun getAllTabletCasaStudioSistema(sistema:String, ram: Int, prezzoMin: Float, prezzoMax: Float, dimMemSec: Int){
        fireStoreDatabase.collection("tablet")
            .whereEqualTo("sistemaOperativo", sistema)
            .get().addOnSuccessListener{documents ->
                for(document in documents){
                    val u1 = document.toObject<Tablet>()
                    val u2 = Tablet(document.id, u1?.descrizione, u1?.nome, u1?.ram, u1?.img, u1?.colore, u1?.marca, u1?.dimMemSec, u1?.dimensioneSchermo, u1?.numeroFotocamere, u1?.peso, u1?.preferito, u1?.prezzo, u1?.bluetooth, u1?.specFotocamere, u1?.sistemaOperativo, u1?.wifi, u1.tipo)
                    if(u2.prezzo!! >= prezzoMin && u2.prezzo!! <= prezzoMax && u2.dimMemSec!! <= dimMemSec && u2.ram!!<=ram){
                        tabletArrayList.add(u2)
                    }
                }
                tabletRecyclerview.adapter = tabletAdapter
                progressBar.visibility = View.GONE
            }
    }
}