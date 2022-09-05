package com.example.mydevice.ui.tablet


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.Toast
import com.example.mydevice.R
import com.example.mydevice.ui.utilities.getProgessDrawable
import com.example.mydevice.ui.utilities.loadImage
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_new_tablet.*

class TabletNewActivity : AppCompatActivity() {
    private lateinit var fireStoreDatabase : FirebaseFirestore
    private lateinit var checkBox: CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_tablet)
        supportActionBar!!.setDefaultDisplayHomeAsUpEnabled(true)

        fireStoreDatabase= FirebaseFirestore.getInstance()

        /**get Data*/
        val tabletIntent = intent
        val preferitoTablet = tabletIntent.getBooleanExtra("preferito", true)
        val idTablet = tabletIntent.getStringExtra("id")
        val nomeTablet = tabletIntent.getStringExtra("nome")
        val descrizioneTablet = tabletIntent.getStringExtra("descrizione")
        val prezzoTablet = tabletIntent.getStringExtra("prezzo")
        val imageTablet = tabletIntent.getStringExtra("img")
        val ramTablet = tabletIntent.getStringExtra("ram")
        val coloreTablet = tabletIntent.getStringExtra("colore")
        val marcaTablet = tabletIntent.getStringExtra("marca")
        val dimMemSecTablet = tabletIntent.getStringExtra("dimMemSec")
        val dimensioneSchermoTablet = tabletIntent.getStringExtra("dimensioneSchermo")
        val numeroFotocamereTablet = tabletIntent.getStringExtra("numeroFotocamere")
        val pesoTablet = tabletIntent.getStringExtra("peso")
        val bluetoothTablet = tabletIntent.getStringExtra("bluetooth")
        val specFotocamereTablet = tabletIntent.getStringExtra("specFotocamere")
        val sistemaOperativoTablet = tabletIntent.getStringExtra("sistemaOperativo")
        val wifiTablet = tabletIntent.getStringExtra("wifi")


        /**call text and images*/
        nome.text = nomeTablet
        descrizione.text = descrizioneTablet
        prezzo.text = prezzoTablet
        ram.text = ramTablet
        colore.text = coloreTablet
        img.loadImage(imageTablet, getProgessDrawable(this))
        marca.text = marcaTablet
        dimMemSec.text = dimMemSecTablet
        dimensioneSchermo.text = dimensioneSchermoTablet
        numeroFotocamere.text = numeroFotocamereTablet
        peso.text = pesoTablet
        bluetooth.text = bluetoothTablet
        specFotocamere.text = specFotocamereTablet
        sistemaOperativo.text = sistemaOperativoTablet
        wifi.text = wifiTablet

        checkBox = findViewById(R.id.checkboxTablet)

        checkBox.isChecked = preferitoTablet==true
        checkBox.setOnCheckedChangeListener { checkBox, isChecked ->
            if (isChecked) {
                updatePref(idTablet.toString())
                showToast("Tablet aggiunto ai preferiti")
            } else {
                updateNotPref(idTablet.toString())
                showToast("Tablet rimosso dai preferiti")
            }
        }
    }
    /**ok now run it */

    private fun showToast(str: String){
        Toast.makeText(this, str, Toast.LENGTH_LONG).show()
    }
    private fun updatePref(id: String){
        fireStoreDatabase.collection("tablet").document(id)
            .update("preferito", true)
    }
    private fun updateNotPref(id: String){
        fireStoreDatabase.collection("tablet").document(id)
            .update("preferito", false)
    }
}


