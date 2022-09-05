package com.example.mydevice.ui.preferiti

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydevice.R
import com.example.mydevice.databinding.FragmentPreferitiBinding
import com.example.mydevice.ui.cellulari.Cellulare
import com.example.mydevice.ui.pc.Pc
import com.example.mydevice.ui.tablet.Tablet
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.toObject


class PreferitiFragment : Fragment() {

    private var _binding: FragmentPreferitiBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding : FragmentPreferitiBinding get() = _binding!!

    private lateinit var progressBar: ProgressBar
    private lateinit var prefRecyclerview : RecyclerView
    private lateinit var prefArrayList : ArrayList<Preferiti>
    private lateinit var prefAdapter: PreferitiAdapter
    private lateinit var fireStoreDatabase : FirebaseFirestore


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return inflater.inflate(R.layout.fragment_preferiti, container, false)
        /*_binding = FragmentPcBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = view.findViewById(R.id.ProgressBar)
        progressBar.visibility = View.VISIBLE

        fireStoreDatabase= FirebaseFirestore.getInstance()


        prefArrayList = ArrayList()

        prefAdapter = PreferitiAdapter(context!!,/* pcArrayList, tabArrayList, cellArrayList,*/ prefArrayList)

        val layoutManager = LinearLayoutManager(context)
        prefRecyclerview = view.findViewById(R.id.recyclerViewPreferiti)
        prefRecyclerview.layoutManager = layoutManager
        prefRecyclerview.setHasFixedSize(true)


        getAll()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun getAll(){
        fireStoreDatabase.collection("pc")
            .whereEqualTo("preferito", true)
            .get().addOnSuccessListener{documents ->
                for(document in documents){
                    val u1 = document.toObject<Pc>()
                    val u3 = Preferiti(document.id, u1?.descrizione, u1?.nome, u1?.ram, u1?.img, u1?.colore, u1?.marca, u1?.dimMemSec, u1?.dimensioneSchermo,null, u1?.peso, u1?.preferito, u1?.prezzo, null,null,u1?.sistemaOperativo, null, u1?.tipo, u1?.numeroProcessore, u1?.processore, u1?.schedaGrafica, u1?.tipoMemSec, null)
                    prefArrayList.add(u3)
                }

            }
        fireStoreDatabase.collection("tablet")
            .whereEqualTo("preferito", true)
            .get().addOnSuccessListener{documents ->
                for(document in documents){
                    val u1 = document.toObject<Tablet>()
                     val u3 = Preferiti(document.id, u1?.descrizione, u1?.nome, u1?.ram, u1?.img, u1?.colore, u1?.marca, u1?.dimMemSec, u1?.dimensioneSchermo,u1?.numeroFotocamere, u1?.peso, u1?.preferito, u1?.prezzo, u1?.bluetooth,u1?.specFotocamere,u1?.sistemaOperativo, u1?.wifi, u1?.tipo, null,null, null, null, null)
                    prefArrayList.add(u3)
                }
            }
        fireStoreDatabase.collection("cellulari")
            .whereEqualTo("preferito", true)
            .get().addOnSuccessListener{documents ->
                for(document in documents){
                    val u1 = document.toObject<Cellulare>()
                    val u3 = Preferiti(document.id, u1?.descrizione, u1?.nome, u1?.ram, u1?.img, u1?.colore, u1?.marca, u1?.dimMemSec, u1?.dimensioneSchermo,u1?.numeroFotocamere, u1?.peso, u1?.preferito, u1?.prezzo, u1?.bluetooth,u1?.specFotocamere,u1?.sistemaOperativo, u1?.wifi, u1?.tipo, null,null, null, null, u1?.sicurezzaBiometrica)
                    prefArrayList.add(u3)                }
               prefRecyclerview.adapter = prefAdapter
                progressBar.visibility = View.GONE
            }
    }
}