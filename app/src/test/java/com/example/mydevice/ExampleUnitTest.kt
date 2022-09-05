package com.example.mydevice

import com.example.mydevice.ui.pc.Pc
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.toObject
import org.junit.Test

import org.junit.Assert.*
/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    @Test
    fun controlloDatiFireBase(){
        var prezzoMin = 0
        var prezzoMax = 250
        var pcArrayList : ArrayList<Pc>
        pcArrayList = ArrayList()
        var pcArrayList2 : ArrayList<Pc>
        pcArrayList2 = ArrayList()
        var fireStoreDatabase : FirebaseFirestore = FirebaseFirestore.getInstance()
        fireStoreDatabase.collection("pc")
            .get().addOnSuccessListener{documents ->
                for(document in documents){
                    val u1 = document.toObject<Pc>()
                    val u2 = Pc(document.id, u1?.descrizione, u1?.nome, u1?.ram, u1?.img, u1?.colore, u1?.marca, u1?.dimMemSec, u1?.dimensioneSchermo, u1?.numeroProcessore, u1?.peso, u1?.preferito, u1?.prezzo, u1?.processore, u1?.schedaGrafica, u1?.sistemaOperativo, u1?.tipoMemSec, u1.tipo)
                    pcArrayList.add(u2)
                }
            }
        var pc1 = Pc("4B4igaKHO3KfFy0DI2uQ","PC ULTRA SLIM HP EliteDesk 800 G1 USDT Core i5-4570s 3.40 GHz 16 Gb 240 GB SSD DVD-rw",
            "HP EliteDesk" , 8, "https://firebasestorage.googleapis.com/v0/b/mydevice-d0532.appspot.com/o/img%2Fhp.jpg?alt=media&token=7db376bd-5ebd-44ee-9e13-13b1744f9c5b",
        "nero", "HP", 240, 15.1, 4, 9.4, true, 181.21, "Intel i5", "Intel", "Linux", "SSD", "notebook")
        pcArrayList2.add(pc1)
        assertEquals(pcArrayList[1], pcArrayList2[0])
    }
}