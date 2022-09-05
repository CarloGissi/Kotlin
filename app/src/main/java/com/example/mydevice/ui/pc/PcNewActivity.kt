package com.example.mydevice.ui.pc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.Toast
import com.example.mydevice.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_new_pc.*
import kotlinx.android.synthetic.main.activity_new_pc.nome
import com.example.mydevice.ui.utilities.getProgessDrawable
import com.example.mydevice.ui.utilities.loadImage

class PcNewActivity : AppCompatActivity() {
    private lateinit var fireStoreDatabase : FirebaseFirestore
    private lateinit var checkBox: CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_pc)
        supportActionBar!!.setDefaultDisplayHomeAsUpEnabled(true)

        fireStoreDatabase= FirebaseFirestore.getInstance()

        /**get Data*/
        val pcIntent = intent
        val preferitoPc = pcIntent.getBooleanExtra("preferito", true)
        val idPc = pcIntent.getStringExtra("id")
        val nomePc = pcIntent.getStringExtra("nome")
        val descrizionePc = pcIntent.getStringExtra("descrizione")
        val prezzoPc = pcIntent.getStringExtra("prezzo")
        val imagePc = pcIntent.getStringExtra("img")
        val ramPc = pcIntent.getStringExtra("ram")
        val colorePc = pcIntent.getStringExtra("colore")
        val marcaPc = pcIntent.getStringExtra("marca")
        val dimMemSecPc = pcIntent.getStringExtra("dimMemSec")
        val dimensioneSchermoPc = pcIntent.getStringExtra("dimensioneSchermo")
        val numeroProcessorePc = pcIntent.getStringExtra("numeroProcessore")
        val pesoPc = pcIntent.getStringExtra("peso")
        val processorePc = pcIntent.getStringExtra("processore")
        val schedaGraficaPc = pcIntent.getStringExtra("schedaGrafica")
        val sistemaOperativoPc = pcIntent.getStringExtra("sistemaOperativo")
        val tipoMemSecPc = pcIntent.getStringExtra("tipoMemSec")


        /**call text and images*/
        nome.text = nomePc
        descrizione.text = descrizionePc
        prezzo.text = prezzoPc
        ram.text = ramPc
        colore.text = colorePc
        img.loadImage(imagePc, getProgessDrawable(this))
        marca.text = marcaPc
        dimMemSec.text = dimMemSecPc
        dimensioneSchermo.text = dimensioneSchermoPc
        numeroProcessore.text = numeroProcessorePc
        peso.text = pesoPc
        processore.text = processorePc
        schedaGrafica.text = schedaGraficaPc
        sistemaOperativo.text = sistemaOperativoPc
        tipoMemSec.text = tipoMemSecPc

        checkBox = findViewById(R.id.checkboxPc)

        checkBox.isChecked = preferitoPc==true
        checkBox.setOnCheckedChangeListener { checkBox, isChecked ->
            if (isChecked) {
                updatePref(idPc.toString())
                showToast("Pc aggiunto ai preferiti")
            } else {
                updateNotPref(idPc.toString())
                showToast("Pc rimosso dai preferiti")
            }
        }
    }
    /**ok now run it */

    private fun showToast(str: String){
        Toast.makeText(this, str, Toast.LENGTH_LONG).show()
    }
    private fun updatePref(id: String){
        fireStoreDatabase.collection("pc").document(id)
            .update("preferito", true)
    }
    private fun updateNotPref(id: String){
        fireStoreDatabase.collection("pc").document(id)
            .update("preferito", false)
    }
}


