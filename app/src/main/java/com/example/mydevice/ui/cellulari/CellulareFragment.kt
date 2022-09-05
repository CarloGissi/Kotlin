package com.example.mydevice.ui.cellulari

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
import com.example.mydevice.databinding.FragmentCellulareBinding
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.toObject


class CellulareFragment : Fragment() {

    private var _binding: FragmentCellulareBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding : FragmentCellulareBinding get() = _binding!!

    private lateinit var progressBar: ProgressBar
    private lateinit var cellulareRecyclerview : RecyclerView
    private lateinit var cellulareArrayList : ArrayList<Cellulare>
    private lateinit var cellulareAdapter: CellulareAdapter
    private lateinit var fireStoreDatabase : FirebaseFirestore


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return inflater.inflate(R.layout.fragment_cellulare, container, false)
        /*_binding = FragmentPcBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = view.findViewById(R.id.ProgressBar)
        progressBar.visibility = View.VISIBLE

        fireStoreDatabase= FirebaseFirestore.getInstance()
        cellulareArrayList = ArrayList()
        cellulareAdapter = CellulareAdapter(context!!, cellulareArrayList)
        val layoutManager = LinearLayoutManager(context)
        cellulareRecyclerview = view.findViewById(R.id.recyclerViewCellulare)
        cellulareRecyclerview.layoutManager = layoutManager
        cellulareRecyclerview.setHasFixedSize(true)

        val bundle = getArguments()
        val dispositivo= bundle?.getString("dispositivo")
        val sistema= bundle?.getString("sistemaOperativo")
        val prezzoMin= bundle?.getFloat("prezzoMin")
        val prezzoMax= bundle?.getFloat("prezzoMax")

        if(dispositivo == "null"){
            getAllCell(prezzoMin!!,prezzoMax!!)
        }else if(dispositivo=="Cellulare" && sistema=="null"){
            getAllCell(prezzoMin!!,prezzoMax!!)
        }else if(dispositivo=="Cellulare" && sistema!="null" ) {
            getAllCellSistema(sistema!!, prezzoMin!!, prezzoMax!!)
        }


        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showToast(str: String){
        Toast.makeText(this.context, str, Toast.LENGTH_LONG).show()
    }

    private fun getAllCell(prezzoMin:Float, prezzoMax:Float){
        fireStoreDatabase.collection("cellulari")
            .whereGreaterThanOrEqualTo("prezzo", prezzoMin)
            .whereLessThanOrEqualTo("prezzo", prezzoMax)
            .get().addOnSuccessListener{documents ->
                for(document in documents){
                    val u1 = document.toObject<Cellulare>()
                    val u2 = Cellulare(document.id, u1?.descrizione, u1?.nome, u1?.ram, u1?.img, u1?.colore, u1?.marca, u1?.dimMemSec, u1?.dimensioneSchermo, u1?.numeroFotocamere, u1?.peso, u1?.preferito, u1?.prezzo, u1?.bluetooth, u1?.specFotocamere, u1?.sistemaOperativo, u1?.wifi, u1.sicurezzaBiometrica, u1.tipo)
                    cellulareArrayList.add(u2)
                }
                cellulareRecyclerview.adapter = cellulareAdapter
                progressBar.visibility = View.GONE
            }
    }
    private fun getAllCellSistema(sistema:String, prezzoMin:Float, prezzoMax:Float){
        fireStoreDatabase.collection("cellulari")
            .whereEqualTo("sistemaOperativo", sistema)
            .get().addOnSuccessListener{documents ->
                for(document in documents){
                    val u1 = document.toObject<Cellulare>()
                    val u2 = Cellulare(document.id, u1?.descrizione, u1?.nome, u1?.ram, u1?.img, u1?.colore, u1?.marca, u1?.dimMemSec, u1?.dimensioneSchermo, u1?.numeroFotocamere, u1?.peso, u1?.preferito, u1?.prezzo, u1?.bluetooth, u1?.specFotocamere, u1?.sistemaOperativo, u1?.wifi, u1.sicurezzaBiometrica, u1.tipo)
                    if(u2.prezzo!! >= prezzoMin && u2.prezzo!! <= prezzoMax){
                        cellulareArrayList.add(u2)
                    }
                }
                cellulareRecyclerview.adapter = cellulareAdapter
                progressBar.visibility = View.GONE
            }
    }
}

