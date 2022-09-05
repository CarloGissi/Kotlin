package com.example.mydevice.ui.cellulari

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.Toast
import com.example.mydevice.R
import com.example.mydevice.ui.utilities.getProgessDrawable
import com.example.mydevice.ui.utilities.loadImage
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_new_cellulare.*

class CellulareNewActivity : AppCompatActivity() {
    private lateinit var fireStoreDatabase : FirebaseFirestore
    private lateinit var checkBox: CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_cellulare)
        supportActionBar!!.setDefaultDisplayHomeAsUpEnabled(true)

        fireStoreDatabase= FirebaseFirestore.getInstance()

        /**get Data*/
        val cellulareIntent = intent
        val preferitoCellulare = cellulareIntent.getBooleanExtra("preferito", true)
        val idCellulare = cellulareIntent.getStringExtra("id")
        val nomeCellulare = cellulareIntent.getStringExtra("nome")
        val descrizioneCellulare = cellulareIntent.getStringExtra("descrizione")
        val prezzoCellulare = cellulareIntent.getStringExtra("prezzo")
        val imageCellulare = cellulareIntent.getStringExtra("img")
        val ramCellulare = cellulareIntent.getStringExtra("ram")
        val coloreCellulare = cellulareIntent.getStringExtra("colore")
        val marcaCellulare = cellulareIntent.getStringExtra("marca")
        val dimMemSecCellulare = cellulareIntent.getStringExtra("dimMemSec")
        val dimensioneSchermoCellulare = cellulareIntent.getStringExtra("dimensioneSchermo")
        val numeroFotocamereCellulare = cellulareIntent.getStringExtra("numeroFotocamere")
        val pesoCellulare = cellulareIntent.getStringExtra("peso")
        val bluetoothCellulare = cellulareIntent.getStringExtra("bluetooth")
        val specFotocamereCellulare = cellulareIntent.getStringExtra("specFotocamere")
        val sistemaOperativoCellulare = cellulareIntent.getStringExtra("sistemaOperativo")
        val wifiCellulare = cellulareIntent.getStringExtra("wifi")
        val sicurezzaBiometricaCellulare = cellulareIntent.getStringExtra("sicurezzaBiometrica")


        /**call text and images*/
        nome.text = nomeCellulare
        descrizione.text = descrizioneCellulare
        prezzo.text = prezzoCellulare
        ram.text = ramCellulare
        colore.text = coloreCellulare
        img.loadImage(imageCellulare, getProgessDrawable(this))
        marca.text = marcaCellulare
        dimMemSec.text = dimMemSecCellulare
        dimensioneSchermo.text = dimensioneSchermoCellulare
        numeroFotocamere.text = numeroFotocamereCellulare
        peso.text = pesoCellulare
        bluetooth.text = bluetoothCellulare
        specFotocamere.text = specFotocamereCellulare
        sistemaOperativo.text = sistemaOperativoCellulare
        wifi.text = wifiCellulare
        sicurezzaBiometrica.text = sicurezzaBiometricaCellulare

        checkBox = findViewById(R.id.checkboxCellulare)

        checkBox.isChecked = preferitoCellulare==true
        checkBox.setOnCheckedChangeListener { checkBox, isChecked ->
            if (isChecked) {
                updatePref(idCellulare.toString())
                showToast("Cellulare aggiunto ai preferiti")
            } else {
                updateNotPref(idCellulare.toString())
                showToast("Cellulare rimosso dai preferiti")
            }
        }
    }
    /**ok now run it */

    private fun showToast(str: String){
        Toast.makeText(this, str, Toast.LENGTH_LONG).show()
    }
    private fun updatePref(id: String){
        fireStoreDatabase.collection("cellulari").document(id)
            .update("preferito", true)
    }
    private fun updateNotPref(id: String){
        fireStoreDatabase.collection("cellulari").document(id)
            .update("preferito", false)
    }
}


