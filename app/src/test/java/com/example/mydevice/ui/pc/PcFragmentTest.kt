package com.example.mydevice.ui.pc


import com.example.mydevice.ui.pc.Pc
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.toObject
import org.junit.Test

import org.junit.Assert.*


internal class PcFragmentTest {
    //ipotizziamo che i dati siano presi da firebase per testare una parte delle funzioni che prelevano dati da firebase
    // unico tipo di funzione che ha più senso testare
    @Test
    fun controlloDatiFireBase(){
        var prezzoMin = 0
        var prezzoMax = 250
        var pcArrayList : ArrayList<Pc>
        pcArrayList = ArrayList()
        var pc = Pc("4B4igaKHO3KfFy0DI2uQ","PC ULTRA SLIM HP EliteDesk 800 G1 USDT Core i5-4570s 3.40 GHz 16 Gb 240 GB SSD DVD-rw",
            "HP EliteDesk" , 8, "https://firebasestorage.googleapis.com/v0/b/mydevice-d0532.appspot.com/o/img%2Fhp.jpg?alt=media&token=7db376bd-5ebd-44ee-9e13-13b1744f9c5b",
            "nero", "HP", 256, 15.1, 4, 9.4, true, 240.20, "Intel i5", "Intel", "Linux", "SSD", "notebook")
        if(pc.prezzo!!>=prezzoMin && pc.prezzo!!<=prezzoMax){
            pcArrayList.add(pc)
        }
        val dim = pcArrayList.size
        assertEquals(dim, 1)
    }


}