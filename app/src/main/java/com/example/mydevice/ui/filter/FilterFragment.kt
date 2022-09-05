package com.example.mydevice.ui.filter

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.mydevice.R
import com.example.mydevice.databinding.FragmentFilterBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.slider.RangeSlider
import kotlinx.android.synthetic.main.fragment_filter.*
import java.text.NumberFormat
import java.util.*
import com.example.mydevice.ui.cellulari.CellulareFragment
import com.example.mydevice.ui.pc.PcFragment
import com.example.mydevice.ui.tablet.TabletFragment

class FilterFragment : Fragment() {

    private var _binding: FragmentFilterBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabFrag = TabletFragment()
        val cellFrag = CellulareFragment()
        val pcFrag = PcFragment()
        val bundle = Bundle()
        var fm = fragmentManager



        setRangerSlider()
        setToggleMenuButtons()

        binding.buttonCerca.setOnClickListener {
            val prezzoMin = binding.rangeSlider.values[0]
            val prezzoMax = binding.rangeSlider.values[1]
            bundle.putFloat("prezzoMin", prezzoMin)
            bundle.putFloat("prezzoMax", prezzoMax)
            if (PcButton.isChecked or CellulareButton.isChecked or TabletButton.isChecked) {
                if (PcButton.isChecked) {
                    val dimMemSec = 512
                    val numProcessore = 4
                    val ram = 8
                    bundle.putInt("ram", ram!!)
                    bundle.putInt("numProcessore", numProcessore!!)
                    bundle.putInt("dimMemSec", dimMemSec!!)
                    if (PcButton.isChecked && !buttonSO1.isChecked && !buttonSO2.isChecked && !buttonSO3.isChecked && !game.isChecked && !studio.isChecked && !lavoro.isChecked && !desktop.isChecked && !notebook.isChecked) {
                        val dispositivo = binding.PcButton.text.toString()
                        val sistemaOperativo = "null"
                        val utilizzo = "null"
                        val tipo = "null"
                        bundle.putString("dispositivo", dispositivo)
                        bundle.putString("sistemaOperativo", sistemaOperativo)
                        bundle.putString("utilizzo", utilizzo)
                        bundle.putString("tipo", tipo)
                        pcFrag.setArguments(bundle)
                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                    } else if (PcButton.isChecked && (buttonSO1.isChecked || buttonSO2.isChecked || buttonSO3.isChecked) && !game.isChecked && !studio.isChecked && !lavoro.isChecked && !desktop.isChecked && !notebook.isChecked) {
                        val dispositivo = binding.PcButton.text.toString()
                        val utilizzo = "null"
                        val tipo = "null"
                        bundle.putString("dispositivo", dispositivo)
                        bundle.putString("utilizzo", utilizzo)
                        bundle.putString("tipo", tipo)
                        when(buttonSO1.isChecked || buttonSO2.isChecked || buttonSO3.isChecked){
                            buttonSO1.isChecked->{
                                val sistemaOperativo = binding.buttonSO1.text.toString()
                                bundle.putString("sistemaOperativo", sistemaOperativo)
                                pcFrag.setArguments(bundle)
                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                            }
                            buttonSO2.isChecked->{
                                val sistemaOperativo = binding.buttonSO2.text.toString()
                                bundle.putString("sistemaOperativo", sistemaOperativo)
                                pcFrag.setArguments(bundle)
                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                            }
                            buttonSO3.isChecked->{
                                val sistemaOperativo = binding.buttonSO3.text.toString()
                                bundle.putString("sistemaOperativo", sistemaOperativo)
                                pcFrag.setArguments(bundle)
                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                            }
                            else->{
                                val sistemaOperativo = "null"
                                bundle.putString("sistemaOperativo", sistemaOperativo)
                                pcFrag.setArguments(bundle)
                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                            }
                        }
                    } else if (PcButton.isChecked && !buttonSO1.isChecked && !buttonSO2.isChecked && !buttonSO3.isChecked && (game.isChecked || studio.isChecked || lavoro.isChecked) && !desktop.isChecked && !notebook.isChecked) {
                        val dispositivo = binding.PcButton.text.toString()
                        val sistemaOperativo = "null"
                        val tipo = "null"
                        bundle.putString("dispositivo", dispositivo)
                        bundle.putString("tipo", tipo)
                        bundle.putString("sistemaOperativo", sistemaOperativo)
                        when(game.isChecked || studio.isChecked || lavoro.isChecked){
                            game.isChecked->{
                                val utilizzo = binding.game.text.toString()
                                bundle.putString("utilizzo", utilizzo)
                                pcFrag.setArguments(bundle)
                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                            }
                            studio.isChecked->{
                                val utilizzo = binding.studio.text.toString()
                                bundle.putString("utilizzo", utilizzo)
                                pcFrag.setArguments(bundle)
                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                            }
                            lavoro.isChecked->{
                                val utilizzo = binding.lavoro.text.toString()
                                bundle.putString("utilizzo", utilizzo)
                                pcFrag.setArguments(bundle)
                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                            }
                            else->{
                                val utilizzo = "null"
                                bundle.putString("utilizzo", utilizzo)
                                pcFrag.setArguments(bundle)
                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                            }
                        }
                    } else if (PcButton.isChecked && !buttonSO1.isChecked && !buttonSO2.isChecked && !buttonSO3.isChecked && !game.isChecked && !studio.isChecked && !lavoro.isChecked && (desktop.isChecked || notebook.isChecked)) {
                        val dispositivo = binding.PcButton.text.toString()
                        val sistemaOperativo = "null"
                        val utilizzo = "null"
                        bundle.putInt("dimMemSec", dimMemSec!!)
                        bundle.putString("utilizzo", utilizzo)
                        bundle.putString("sistemaOperativo", sistemaOperativo)
                        when(desktop.isChecked || notebook.isChecked){
                            desktop.isChecked->{
                                val tipo = "desktop"
                                bundle.putString("tipo", tipo)
                                pcFrag.setArguments(bundle)
                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                            }
                            notebook.isChecked->{
                                val tipo = "notebook"
                                bundle.putString("tipo", tipo)
                                pcFrag.setArguments(bundle)
                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                            }
                            else->{
                                val tipo = "null"
                                bundle.putString("tipo", tipo)
                                pcFrag.setArguments(bundle)
                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                            }
                        }
                    } else if (PcButton.isChecked && (buttonSO1.isChecked || buttonSO2.isChecked || buttonSO3.isChecked) && (game.isChecked || studio.isChecked || lavoro.isChecked) && (desktop.isChecked || notebook.isChecked)) {
                        val dispositivo = binding.PcButton.text.toString()
                        bundle.putString("dispositivo", dispositivo)
                        when (buttonSO1.isChecked || buttonSO2.isChecked || buttonSO3.isChecked) {
                            buttonSO1.isChecked -> {
                                val sistemaOperativo = binding.buttonSO1.text.toString()
                                bundle.putString("sistemaOperativo", sistemaOperativo)
                                when (game.isChecked || studio.isChecked || lavoro.isChecked) {
                                    game.isChecked -> {
                                        val utilizzo = binding.game.text.toString()
                                        bundle.putString("utilizzo", utilizzo)
                                        when (desktop.isChecked || notebook.isChecked) {
                                            desktop.isChecked -> {
                                                val tipo = binding.desktop.text.toString()
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }
                                            notebook.isChecked -> {
                                                val tipo = binding.notebook.text.toString()
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }

                                            else -> {
                                                val tipo = "null"
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }
                                        }
                                    }
                                    studio.isChecked -> {
                                        val utilizzo = binding.studio.text.toString()
                                        bundle.putString("utilizzo", utilizzo)
                                        when (desktop.isChecked || notebook.isChecked) {
                                            desktop.isChecked -> {
                                                val tipo = binding.desktop.text.toString()
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }
                                            notebook.isChecked -> {
                                                val tipo = binding.notebook.text.toString()
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }

                                            else -> {
                                                val tipo = "null"
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }
                                        }
                                    }
                                    lavoro.isChecked -> {
                                        val utilizzo = binding.lavoro.text.toString()
                                        bundle.putString("utilizzo", utilizzo)
                                        when (desktop.isChecked || notebook.isChecked) {
                                            desktop.isChecked -> {
                                                val tipo = binding.desktop.text.toString()
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }
                                            notebook.isChecked -> {
                                                val tipo = binding.notebook.text.toString()
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }

                                            else -> {
                                                val tipo = "null"
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }
                                        }
                                    }
                                    else -> {
                                        val utilizzo = "null"
                                        bundle.putString("utilizzo", utilizzo)
                                        when (desktop.isChecked || notebook.isChecked) {
                                            desktop.isChecked -> {
                                                val tipo = binding.desktop.text.toString()
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }
                                            notebook.isChecked -> {
                                                val tipo = binding.notebook.text.toString()
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }

                                            else -> {
                                                val tipo = "null"
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }
                                        }
                                    }
                                }
                            }
                            buttonSO2.isChecked -> {
                                val sistemaOperativo = binding.buttonSO2.text.toString()
                                bundle.putString("sistemaOperativo", sistemaOperativo)
                                when (game.isChecked || studio.isChecked || lavoro.isChecked) {
                                    game.isChecked -> {
                                        val utilizzo = binding.game.text.toString()
                                        bundle.putString("utilizzo", utilizzo)
                                        when (desktop.isChecked || notebook.isChecked) {
                                            desktop.isChecked -> {
                                                val tipo = binding.desktop.text.toString()
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }
                                            notebook.isChecked -> {
                                                val tipo = binding.notebook.text.toString()
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }

                                            else -> {
                                                val tipo = "null"
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }
                                        }
                                    }
                                    studio.isChecked -> {
                                        val utilizzo = binding.studio.text.toString()
                                        bundle.putString("utilizzo", utilizzo)
                                        when (desktop.isChecked || notebook.isChecked) {
                                            desktop.isChecked -> {
                                                val tipo = binding.desktop.text.toString()
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }
                                            notebook.isChecked -> {
                                                val tipo = binding.notebook.text.toString()
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }

                                            else -> {
                                                val tipo = "null"
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }
                                        }
                                    }
                                    lavoro.isChecked -> {
                                        val utilizzo = binding.lavoro.text.toString()
                                        bundle.putString("utilizzo", utilizzo)
                                        when (desktop.isChecked || notebook.isChecked) {
                                            desktop.isChecked -> {
                                                val tipo = binding.desktop.text.toString()
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }
                                            notebook.isChecked -> {
                                                val tipo = binding.notebook.text.toString()
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }

                                            else -> {
                                                val tipo = "null"
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }
                                        }
                                    }
                                    else -> {
                                        val utilizzo = "null"
                                        bundle.putString("utilizzo", utilizzo)
                                        when (desktop.isChecked || notebook.isChecked) {
                                            desktop.isChecked -> {
                                                val tipo = binding.desktop.text.toString()
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }
                                            notebook.isChecked -> {
                                                val tipo = binding.notebook.text.toString()
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }

                                            else -> {
                                                val tipo = "null"
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }
                                        }
                                    }
                                }
                            }
                            buttonSO3.isChecked -> {
                                val sistemaOperativo = binding.buttonSO3.text.toString()
                                bundle.putString("sistemaOperativo", sistemaOperativo)
                                when (game.isChecked || studio.isChecked || lavoro.isChecked) {
                                    game.isChecked -> {
                                        val utilizzo = binding.game.text.toString()
                                        bundle.putString("utilizzo", utilizzo)
                                        when (desktop.isChecked || notebook.isChecked) {
                                            desktop.isChecked -> {
                                                val tipo = binding.desktop.text.toString()
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }
                                            notebook.isChecked -> {
                                                val tipo = binding.notebook.text.toString()
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }

                                            else -> {
                                                val tipo = "null"
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }
                                        }
                                    }
                                    studio.isChecked -> {
                                        val utilizzo = binding.studio.text.toString()
                                        bundle.putString("utilizzo", utilizzo)
                                        when (desktop.isChecked || notebook.isChecked) {
                                            desktop.isChecked -> {
                                                val tipo = binding.desktop.text.toString()
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }
                                            notebook.isChecked -> {
                                                val tipo = binding.notebook.text.toString()
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }

                                            else -> {
                                                val tipo = "null"
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }
                                        }
                                    }
                                    lavoro.isChecked -> {
                                        val utilizzo = binding.lavoro.text.toString()
                                        bundle.putString("utilizzo", utilizzo)
                                        when (desktop.isChecked || notebook.isChecked) {
                                            desktop.isChecked -> {
                                                val tipo = binding.desktop.text.toString()
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }
                                            notebook.isChecked -> {
                                                val tipo = binding.notebook.text.toString()
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }

                                            else -> {
                                                val tipo = "null"
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }
                                        }
                                    }
                                    else -> {
                                        val utilizzo = "null"
                                        bundle.putString("utilizzo", utilizzo)
                                        when (desktop.isChecked || notebook.isChecked) {
                                            desktop.isChecked -> {
                                                val tipo = binding.desktop.text.toString()
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }
                                            notebook.isChecked -> {
                                                val tipo = binding.notebook.text.toString()
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }

                                            else -> {
                                                val tipo = "null"
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }
                                        }
                                    }
                                }
                            }
                            else -> {
                                val sistemaOperativo = "null"
                                bundle.putString("sistemaOperativo", sistemaOperativo)
                                when (game.isChecked || studio.isChecked || lavoro.isChecked) {
                                    game.isChecked -> {
                                        val utilizzo = binding.game.text.toString()
                                        bundle.putString("utilizzo", utilizzo)
                                        when (desktop.isChecked || notebook.isChecked) {
                                            desktop.isChecked -> {
                                                val tipo = binding.desktop.text.toString()
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }
                                            notebook.isChecked -> {
                                                val tipo = binding.notebook.text.toString()
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }

                                            else -> {
                                                val tipo = "null"
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }
                                        }
                                    }
                                    studio.isChecked -> {
                                        val utilizzo = binding.studio.text.toString()
                                        bundle.putString("utilizzo", utilizzo)
                                        when (desktop.isChecked || notebook.isChecked) {
                                            desktop.isChecked -> {
                                                val tipo = binding.desktop.text.toString()
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }
                                            notebook.isChecked -> {
                                                val tipo = binding.notebook.text.toString()
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }

                                            else -> {
                                                val tipo = "null"
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }
                                        }
                                    }
                                    lavoro.isChecked -> {
                                        val utilizzo = binding.lavoro.text.toString()
                                        bundle.putString("utilizzo", utilizzo)
                                        when (desktop.isChecked || notebook.isChecked) {
                                            desktop.isChecked -> {
                                                val tipo = binding.desktop.text.toString()
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }
                                            notebook.isChecked -> {
                                                val tipo = binding.notebook.text.toString()
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }

                                            else -> {
                                                val tipo = "null"
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }
                                        }
                                    }
                                    else -> {
                                        val utilizzo = "null"
                                        bundle.putString("utilizzo", utilizzo)
                                        when (desktop.isChecked || notebook.isChecked) {
                                            desktop.isChecked -> {
                                                val tipo = binding.desktop.text.toString()
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }
                                            notebook.isChecked -> {
                                                val tipo = binding.notebook.text.toString()
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }

                                            else -> {
                                                val tipo = "null"
                                                bundle.putString("tipo", tipo)
                                                pcFrag.setArguments(bundle)
                                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else if ((PcButton.isChecked && (buttonSO1.isChecked || buttonSO2.isChecked || buttonSO3.isChecked) && (game.isChecked || studio.isChecked || lavoro.isChecked) && !desktop.isChecked && !notebook.isChecked)) {
                        val dispositivo = binding.PcButton.text.toString()
                        val tipo = "null"
                        bundle.putString("dispositivo", dispositivo)
                        bundle.putString("tipo", tipo)
                        when (buttonSO1.isChecked || buttonSO2.isChecked || buttonSO3.isChecked) {
                            buttonSO1.isChecked -> {
                                val sistemaOperativo = binding.buttonSO1.text.toString()
                                bundle.putString("sistemaOperativo", sistemaOperativo)
                                when (game.isChecked || studio.isChecked || lavoro.isChecked) {
                                    game.isChecked -> {
                                        val utilizzo = binding.game.text.toString()
                                        bundle.putString("utilizzo", utilizzo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                    lavoro.isChecked -> {
                                        val utilizzo = binding.lavoro.text.toString()
                                        bundle.putString("utilizzo", utilizzo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                    studio.isChecked -> {
                                        val utilizzo = binding.studio.text.toString()
                                        bundle.putString("utilizzo", utilizzo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                    else -> {
                                        val utilizzo = "null"
                                        bundle.putString("utilizzo", utilizzo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                }

                            }
                            buttonSO2.isChecked -> {
                                val sistemaOperativo = binding.buttonSO2.text.toString()
                                bundle.putString("sistemaOperativo", sistemaOperativo)
                                when (game.isChecked || studio.isChecked || lavoro.isChecked) {
                                    game.isChecked -> {
                                        val utilizzo = binding.game.text.toString()
                                        bundle.putString("utilizzo", utilizzo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                    lavoro.isChecked -> {
                                        val utilizzo = binding.lavoro.text.toString()
                                        bundle.putString("utilizzo", utilizzo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                    studio.isChecked -> {
                                        val utilizzo = binding.studio.text.toString()
                                        bundle.putString("utilizzo", utilizzo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                    else -> {
                                        val utilizzo = "null"
                                        bundle.putString("utilizzo", utilizzo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                }
                            }
                            buttonSO3.isChecked -> {
                                val sistemaOperativo = binding.buttonSO3.text.toString()
                                bundle.putString("sistemaOperativo", sistemaOperativo)
                                when (game.isChecked || studio.isChecked || lavoro.isChecked) {
                                    game.isChecked -> {
                                        val utilizzo = binding.game.text.toString()
                                        bundle.putString("utilizzo", utilizzo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                    lavoro.isChecked -> {
                                        val utilizzo = binding.lavoro.text.toString()
                                        bundle.putString("utilizzo", utilizzo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                    studio.isChecked -> {
                                        val utilizzo = binding.studio.text.toString()
                                        bundle.putString("utilizzo", utilizzo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                    else -> {
                                        val utilizzo = "null"
                                        bundle.putString("utilizzo", utilizzo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                }
                            }
                            else -> {
                                val sistemaOperativo = "null"
                                bundle.putString("sistemaOperativo", sistemaOperativo)
                                when (game.isChecked || studio.isChecked || lavoro.isChecked) {
                                    game.isChecked -> {
                                        val utilizzo = binding.game.text.toString()
                                        bundle.putString("utilizzo", utilizzo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                    lavoro.isChecked -> {
                                        val utilizzo = binding.lavoro.text.toString()
                                        bundle.putString("utilizzo", utilizzo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                    studio.isChecked -> {
                                        val utilizzo = binding.studio.text.toString()
                                        bundle.putString("utilizzo", utilizzo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                    else -> {
                                        val utilizzo = "null"
                                        bundle.putString("utilizzo", utilizzo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                }
                            }
                        }
                    } else if ((PcButton.isChecked && (buttonSO1.isChecked || buttonSO2.isChecked || buttonSO3.isChecked) && !game.isChecked && !studio.isChecked && !lavoro.isChecked && (desktop.isChecked || notebook.isChecked))) {
                        val dispositivo = binding.PcButton.text.toString()
                        val utilizzo = "null"
                        bundle.putString("dispositivo", dispositivo)
                        bundle.putString("utilizzo", utilizzo)
                        when (buttonSO1.isChecked || buttonSO2.isChecked || buttonSO3.isChecked) {
                            buttonSO1.isChecked -> {
                                val sistemaOperativo = binding.buttonSO1.text.toString()
                                bundle.putString("sistemaOperativo", sistemaOperativo)
                                when (desktop.isChecked || notebook.isChecked) {
                                    desktop.isChecked -> {
                                        val tipo = binding.desktop.text.toString()
                                        bundle.putString("tipo", tipo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                    notebook.isChecked -> {
                                        val tipo = binding.notebook.text.toString()
                                        bundle.putString("tipo", tipo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                    else -> {
                                        val tipo = "null"
                                        bundle.putString("tipo", tipo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                }
                            }
                            buttonSO2.isChecked->{
                                val sistemaOperativo = binding.buttonSO2.text.toString()
                                bundle.putString("sistemaOperativo", sistemaOperativo)
                                when (desktop.isChecked || notebook.isChecked) {
                                    desktop.isChecked -> {
                                        val tipo = binding.desktop.text.toString()
                                        bundle.putString("tipo", tipo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                    notebook.isChecked -> {
                                        val tipo = binding.notebook.text.toString()
                                        bundle.putString("tipo", tipo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                    else -> {
                                        val tipo = "null"
                                        bundle.putString("tipo", tipo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                }
                            }
                            buttonSO3.isChecked->{
                                val sistemaOperativo = binding.buttonSO3.text.toString()
                                bundle.putString("sistemaOperativo", sistemaOperativo)
                                when (desktop.isChecked || notebook.isChecked) {
                                    desktop.isChecked -> {
                                        val tipo = binding.desktop.text.toString()
                                        bundle.putString("tipo", tipo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                    notebook.isChecked -> {
                                        val tipo = binding.notebook.text.toString()
                                        bundle.putString("tipo", tipo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                    else -> {
                                        val tipo = "null"
                                        bundle.putString("tipo", tipo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                }
                            }
                            else -> {
                                val sistemaOperativo = "null"
                                bundle.putString("sistemaOperativo", sistemaOperativo)
                                when (desktop.isChecked || notebook.isChecked) {
                                    desktop.isChecked -> {
                                        val tipo = binding.desktop.text.toString()
                                        bundle.putString("tipo", tipo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                    notebook.isChecked -> {
                                        val tipo = binding.notebook.text.toString()
                                        bundle.putString("tipo", tipo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                    else -> {
                                        val tipo = "null"
                                        bundle.putString("tipo", tipo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                }
                            }
                        }
                    }else if((PcButton.isChecked && !buttonSO1.isChecked && !buttonSO2.isChecked && !buttonSO3.isChecked && (game.isChecked || studio.isChecked || lavoro.isChecked) && (desktop.isChecked || notebook.isChecked))) {
                        val dispositivo = binding.PcButton.text.toString()
                        val sistemaOperativo = "null"
                        bundle.putString("dispositivo", dispositivo)
                        bundle.putString("sistemaOperativo", sistemaOperativo)
                        when (game.isChecked || studio.isChecked || lavoro.isChecked) {
                            game.isChecked -> {
                                val utilizzo = binding.game.text.toString()
                                bundle.putString("utilizzo", utilizzo)
                                when (desktop.isChecked || notebook.isChecked) {
                                    desktop.isChecked -> {
                                        val tipo = binding.desktop.text.toString()
                                        bundle.putString("tipo", tipo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                    notebook.isChecked -> {
                                        val tipo = binding.notebook.text.toString()
                                        bundle.putString("tipo", tipo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                    else -> {
                                        val tipo = "null"
                                        bundle.putString("tipo", tipo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                }
                            }
                            studio.isChecked->{
                                val utilizzo = binding.studio.text.toString()
                                bundle.putString("utilizzo", utilizzo)
                                when (desktop.isChecked || notebook.isChecked) {
                                    desktop.isChecked -> {
                                        val tipo = binding.desktop.text.toString()
                                        bundle.putString("tipo", tipo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                    notebook.isChecked -> {
                                        val tipo = binding.notebook.text.toString()
                                        bundle.putString("tipo", tipo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                    else -> {
                                        val tipo = "null"
                                        bundle.putString("tipo", tipo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                }
                            }
                            lavoro.isChecked->{
                                val utilizzo = binding.lavoro.text.toString()
                                bundle.putString("utilizzo", utilizzo)
                                when (desktop.isChecked || notebook.isChecked) {
                                    desktop.isChecked -> {
                                        val tipo = binding.desktop.text.toString()
                                        bundle.putString("tipo", tipo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                    notebook.isChecked -> {
                                        val tipo = binding.notebook.text.toString()
                                        bundle.putString("tipo", tipo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                    else -> {
                                        val tipo = "null"
                                        bundle.putString("tipo", tipo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                }
                            }
                            else -> {
                                val utilizzo = "null"
                                bundle.putString("utilizzo", utilizzo)
                                when (desktop.isChecked || notebook.isChecked) {
                                    desktop.isChecked -> {
                                        val tipo = binding.desktop.text.toString()
                                        bundle.putString("tipo", tipo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                    notebook.isChecked -> {
                                        val tipo = binding.notebook.text.toString()
                                        bundle.putString("tipo", tipo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                    else -> {
                                        val tipo = "null"
                                        bundle.putString("tipo", tipo)
                                        pcFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,pcFrag)?.commit()
                                    }
                                }
                            }
                        }
                    }
                        } else if (CellulareButton.isChecked) {
                            if(CellulareButton.isChecked && !buttonSO1.isChecked && !buttonSO2.isChecked &&! buttonSO3.isChecked){
                                val dispositivo = binding.CellulareButton.text.toString()
                                val sistemaOperativo = "null"
                                bundle.putString("dispositivo", dispositivo)
                                bundle.putString("sistemaOperativo", sistemaOperativo)
                                cellFrag.setArguments(bundle)
                                fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,cellFrag)?.commit()
                            }else if(CellulareButton.isChecked && (buttonSO1.isChecked || buttonSO2.isChecked || buttonSO3.isChecked)){
                                val dispositivo = binding.CellulareButton.text.toString()
                                bundle.putString("dispositivo", dispositivo)
                                when(buttonSO1.isChecked || buttonSO2.isChecked || buttonSO3.isChecked){
                                    buttonSO1.isChecked->{
                                        val sistemaOperativo = binding.buttonSO1.text.toString()
                                        bundle.putString("sistemaOperativo", sistemaOperativo)
                                        cellFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,cellFrag)?.commit()
                                    }
                                    buttonSO2.isChecked->{
                                        val sistemaOperativo = binding.buttonSO2.text.toString()
                                        bundle.putString("sistemaOperativo", sistemaOperativo)
                                        cellFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,cellFrag)?.commit()
                                    }
                                    buttonSO3.isChecked->{
                                        val sistemaOperativo = binding.buttonSO3.text.toString()
                                        bundle.putString("sistemaOperativo", sistemaOperativo)
                                        cellFrag.setArguments(bundle)
                                        fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,cellFrag)?.commit()
                                    }else->{
                                    val sistemaOperativo = "null"
                                    bundle.putString("sistemaOperativo", sistemaOperativo)
                                    cellFrag.setArguments(bundle)
                                    fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,cellFrag)?.commit()
                                }
                                }
                            }

                    } else if (TabletButton.isChecked) {
                        val ram = 4
                        val dimMemSec = 128
                        bundle.putInt("ram", ram)
                        bundle.putInt("dimMemSec", dimMemSec)
                        if(TabletButton.isChecked && !buttonSO1.isChecked && !buttonSO2.isChecked && !buttonSO3.isChecked && !game.isChecked && !studio.isChecked && !lavoro.isChecked){
                            val dispositivo = binding.TabletButton.text.toString()
                            val sistemaOperativo = "null"
                            val utilizzo = "null"
                            bundle.putString("dispositivo", dispositivo)
                            bundle.putString("sistemaOperativo", sistemaOperativo)
                            bundle.putString("utilizzo", utilizzo)
                            tabFrag.setArguments(bundle)
                            fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,tabFrag)?.commit()
                        }else if(TabletButton.isChecked && (buttonSO1.isChecked || buttonSO2.isChecked || buttonSO3.isChecked) && !game.isChecked && !studio.isChecked && !lavoro.isChecked){
                            val dispositivo = binding.TabletButton.text.toString()
                            bundle.putString("dispositivo", dispositivo)
                            when(buttonSO1.isChecked || buttonSO2.isChecked || buttonSO3.isChecked){
                                buttonSO1.isChecked->{
                                    val sistemaOperativo = binding.buttonSO1.text.toString()
                                    val utilizzo = "null"
                                    bundle.putString("sistemaOperativo", sistemaOperativo)
                                    bundle.putString("utilizzo", utilizzo)
                                    tabFrag.setArguments(bundle)
                                    fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,tabFrag)?.commit()
                                }
                                buttonSO2.isChecked->{
                                    val sistemaOperativo = binding.buttonSO2.text.toString()
                                    val utilizzo = "null"
                                    bundle.putString("sistemaOperativo", sistemaOperativo)
                                    bundle.putString("utilizzo", utilizzo)
                                    tabFrag.setArguments(bundle)
                                    fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,tabFrag)?.commit()
                                }
                                buttonSO3.isChecked->{
                                    val sistemaOperativo = binding.buttonSO3.text.toString()
                                    val utilizzo = "null"
                                    bundle.putString("sistemaOperativo", sistemaOperativo)
                                    bundle.putString("utilizzo", utilizzo)
                                    tabFrag.setArguments(bundle)
                                    fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,tabFrag)?.commit()
                                }
                                else->{
                                    val sistemaOperativo = "null"
                                    val utilizzo = "null"
                                    bundle.putString("sistemaOperativo", sistemaOperativo)
                                    bundle.putString("utilizzo", utilizzo)
                                    tabFrag.setArguments(bundle)
                                    fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,tabFrag)?.commit()
                                }
                            }
                        }else if(TabletButton.isChecked && !buttonSO1.isChecked && !buttonSO2.isChecked && !buttonSO3.isChecked && (game.isChecked || studio.isChecked || lavoro.isChecked)){
                            val dispositivo = binding.TabletButton.text.toString()
                            val sistemaOperativo = "null"
                            bundle.putString("dispositivo", dispositivo)
                            bundle.putString("sistemaOperativo", sistemaOperativo)
                            when(game.isChecked || studio.isChecked || lavoro.isChecked){
                                game.isChecked->{
                                    val utilizzo = binding.game.text.toString()
                                    bundle.putString("utilizzo", utilizzo)
                                    tabFrag.setArguments(bundle)
                                    fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,tabFrag)?.commit()
                                }
                                studio.isChecked->{
                                    val utilizzo = binding.studio.text.toString()
                                    bundle.putString("utilizzo", utilizzo)
                                    tabFrag.setArguments(bundle)
                                    fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,tabFrag)?.commit()
                                }
                                lavoro.isChecked->{
                                    val utilizzo = binding.lavoro.text.toString()
                                    bundle.putString("utilizzo", utilizzo)
                                    tabFrag.setArguments(bundle)
                                    fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,tabFrag)?.commit()
                                }
                                else->{
                                    val utilizzo = "null"
                                    bundle.putString("utilizzo", utilizzo)
                                    tabFrag.setArguments(bundle)
                                    fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,tabFrag)?.commit()
                                }
                            }
                        }else if(TabletButton.isChecked && (buttonSO1.isChecked || buttonSO2.isChecked || buttonSO3.isChecked) && (game.isChecked || studio.isChecked || lavoro.isChecked)){
                            val dispositivo = binding.TabletButton.text.toString()
                            bundle.putString("dispositivo", dispositivo)
                            when(buttonSO1.isChecked || buttonSO2.isChecked || buttonSO3.isChecked){
                                buttonSO1.isChecked->{
                                    val sistemaOperativo = binding.buttonSO1.text.toString()
                                    bundle.putString("sistemaOperativo", sistemaOperativo)
                                    when(game.isChecked || studio.isChecked || lavoro.isChecked){
                                        game.isChecked->{
                                            val utilizzo = binding.game.text.toString()
                                            bundle.putString("utilizzo", utilizzo)
                                            tabFrag.setArguments(bundle)
                                            fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,tabFrag)?.commit()
                                        }
                                        studio.isChecked->{
                                            val utilizzo = binding.studio.text.toString()
                                            bundle.putString("utilizzo", utilizzo)
                                            tabFrag.setArguments(bundle)
                                            fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,tabFrag)?.commit()
                                        }
                                        lavoro.isChecked->{
                                            val utilizzo = binding.lavoro.text.toString()
                                            bundle.putString("utilizzo", utilizzo)
                                            tabFrag.setArguments(bundle)
                                            fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,tabFrag)?.commit()
                                        }
                                        else->{
                                            val utilizzo = "null"
                                            bundle.putString("utilizzo", utilizzo)
                                            tabFrag.setArguments(bundle)
                                            fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,tabFrag)?.commit()
                                        }
                                    }
                                }
                                buttonSO2.isChecked->{
                                    val sistemaOperativo = binding.buttonSO2.text.toString()
                                    bundle.putString("sistemaOperativo", sistemaOperativo)
                                    when(game.isChecked || studio.isChecked || lavoro.isChecked){
                                        game.isChecked->{
                                            val utilizzo = binding.game.text.toString()
                                            bundle.putString("utilizzo", utilizzo)
                                            tabFrag.setArguments(bundle)
                                            fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,tabFrag)?.commit()
                                        }
                                        studio.isChecked->{
                                            val utilizzo = binding.studio.text.toString()
                                            bundle.putString("utilizzo", utilizzo)
                                            tabFrag.setArguments(bundle)
                                            fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,tabFrag)?.commit()
                                        }
                                        lavoro.isChecked->{
                                            val utilizzo = binding.lavoro.text.toString()
                                            bundle.putString("utilizzo", utilizzo)
                                            tabFrag.setArguments(bundle)
                                            fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,tabFrag)?.commit()
                                        }
                                        else->{
                                            val utilizzo = "null"
                                            bundle.putString("utilizzo", utilizzo)
                                            tabFrag.setArguments(bundle)
                                            fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,tabFrag)?.commit()
                                        }
                                    }
                            }
                                buttonSO3.isChecked->{
                                    val sistemaOperativo = binding.buttonSO3.text.toString()
                                    bundle.putString("sistemaOperativo", sistemaOperativo)
                                    when(game.isChecked || studio.isChecked || lavoro.isChecked){
                                        game.isChecked->{
                                            val utilizzo = binding.game.text.toString()
                                            bundle.putString("utilizzo", utilizzo)
                                            tabFrag.setArguments(bundle)
                                            fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,tabFrag)?.commit()
                                        }
                                        studio.isChecked->{
                                            val utilizzo = binding.studio.text.toString()
                                            bundle.putString("utilizzo", utilizzo)
                                            tabFrag.setArguments(bundle)
                                            fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,tabFrag)?.commit()
                                        }
                                        lavoro.isChecked->{
                                            val utilizzo = binding.lavoro.text.toString()
                                            bundle.putString("utilizzo", utilizzo)
                                            tabFrag.setArguments(bundle)
                                            fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,tabFrag)?.commit()
                                        }
                                        else->{
                                            val utilizzo = "null"
                                            bundle.putString("utilizzo", utilizzo)
                                            tabFrag.setArguments(bundle)
                                            fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,tabFrag)?.commit()
                                        }
                                    }
                                }
                                else->{
                                    val sistemaOperativo = "null"
                                    bundle.putString("sistemaOperativo", sistemaOperativo)
                                    when(game.isChecked || studio.isChecked || lavoro.isChecked){
                                        game.isChecked->{
                                            val utilizzo = binding.game.text.toString()
                                            bundle.putString("utilizzo", utilizzo)
                                            tabFrag.setArguments(bundle)
                                            fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,tabFrag)?.commit()
                                        }
                                        studio.isChecked->{
                                            val utilizzo = binding.studio.text.toString()
                                            bundle.putString("utilizzo", utilizzo)
                                            tabFrag.setArguments(bundle)
                                            fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,tabFrag)?.commit()
                                        }
                                        lavoro.isChecked->{
                                            val utilizzo = binding.lavoro.text.toString()
                                            bundle.putString("utilizzo", utilizzo)
                                            tabFrag.setArguments(bundle)
                                            fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,tabFrag)?.commit()
                                        }
                                        else->{
                                            val utilizzo = "null"
                                            bundle.putString("utilizzo", utilizzo)
                                            tabFrag.setArguments(bundle)
                                            fm?.beginTransaction()?.replace(R.id.nav_host_fragment_activity_main,tabFrag)?.commit()
                                        }
                                    }
                                }
                        }
                    }
                } else {
                    showToast("Nessun dispositivo selezionato")
                }
                }

            }

        }

    private fun showToast(str: String){
        Toast.makeText(this.context, str, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setToggleMenuButtons() {
        binding.materialButtonToggleGroupTipologia.addOnButtonCheckedListener { /*toggleButton*/_, checkedId, /*isChecked*/_ ->
            toggleButton(view!!.findViewById(checkedId))
        }
        binding.materialButtonToggleGroupUtilizzo.addOnButtonCheckedListener { _, checkedId, _ ->
            toggleButton(view!!.findViewById(checkedId))
        }
        binding.materialButtonToggleGroupDispositivo.addOnButtonCheckedListener { _, checkedId, isChecked ->
            toggleButton(view!!.findViewById(checkedId))

            if (isChecked){
                when(checkedId){
                    R.id.PcButton -> {
                        twTipologia.visibility = View.VISIBLE
                        materialButtonToggleGroupTipologia.visibility = View.VISIBLE
                        twUtilizzo.visibility = View.VISIBLE
                        materialButtonToggleGroupUtilizzo.visibility = View.VISIBLE
                        view1.visibility = View.VISIBLE
                        view2.visibility = View.VISIBLE
                        twSO.visibility = View.VISIBLE
                        buttonSO1.text = "Windows"
                        buttonSO2.text = "MacOS"
                        buttonSO3.text = "Linux"
                        materialButtonToggleGroupSistemaOperativo.visibility = View.VISIBLE
                    }
                    R.id.CellulareButton -> {
                        twSO.visibility = View.VISIBLE
                        buttonSO1.text = "Android"
                        buttonSO2.text = "iOS"
                        buttonSO3.text = "Windows"
                        materialButtonToggleGroupSistemaOperativo.visibility = View.VISIBLE

                    }
                    R.id.TabletButton -> {
                        twUtilizzo.visibility = View.VISIBLE
                        materialButtonToggleGroupUtilizzo.visibility = View.VISIBLE
                        twSO.visibility = View.VISIBLE
                        buttonSO1.text = "Android"
                        buttonSO2.text = "iOS"
                        buttonSO3.text = "Windows"
                        materialButtonToggleGroupSistemaOperativo.visibility = View.VISIBLE
                        view1.visibility = View.VISIBLE


                    }
                }
            }
            else {

                when (checkedId) {
                    R.id.PcButton -> {
                        twTipologia.visibility = View.INVISIBLE
                        materialButtonToggleGroupTipologia.visibility = View.INVISIBLE
                        twUtilizzo.visibility = View.INVISIBLE
                        materialButtonToggleGroupUtilizzo.visibility = View.INVISIBLE
                        view1.visibility = View.INVISIBLE
                        view2.visibility = View.INVISIBLE
                        twSO.visibility = View.INVISIBLE
                        buttonSO1.text = "Windows"
                        buttonSO2.text = "MacOS"
                        buttonSO3.text = "Linux"
                        materialButtonToggleGroupSistemaOperativo.visibility = View.INVISIBLE
                    }

                    R.id.CellulareButton -> {
                        if (PcButton.isChecked){
                            twTipologia.visibility = View.VISIBLE
                            materialButtonToggleGroupTipologia.visibility = View.VISIBLE
                            twUtilizzo.visibility = View.VISIBLE
                            materialButtonToggleGroupUtilizzo.visibility = View.VISIBLE
                            view1.visibility = View.VISIBLE
                            view2.visibility = View.VISIBLE
                            twSO.visibility = View.VISIBLE
                            buttonSO1.text = "Windows"
                            buttonSO2.text = "MacOS"
                            buttonSO3.text = "Linux"
                            materialButtonToggleGroupSistemaOperativo.visibility = View.VISIBLE
                        }
                        else if (TabletButton.isChecked){
                            twUtilizzo.visibility = View.VISIBLE
                            materialButtonToggleGroupUtilizzo.visibility = View.VISIBLE
                            twSO.visibility = View.VISIBLE
                            buttonSO1.text = "Android"
                            buttonSO2.text = "iOS"
                            buttonSO3.text = "Windows"
                            materialButtonToggleGroupSistemaOperativo.visibility = View.VISIBLE
                            view1.visibility = View.VISIBLE

                        }
                        else {
                            twSO.visibility = View.INVISIBLE
                            buttonSO1.text = "Android"
                            buttonSO2.text = "iOS"
                            buttonSO3.text = "Windows"
                            materialButtonToggleGroupSistemaOperativo.visibility = View.INVISIBLE
                        }
                    }
                    R.id.TabletButton -> {
                        if (PcButton.isChecked){
                            twTipologia.visibility = View.VISIBLE
                            materialButtonToggleGroupTipologia.visibility = View.VISIBLE
                            twUtilizzo.visibility = View.VISIBLE
                            materialButtonToggleGroupUtilizzo.visibility = View.VISIBLE
                            view1.visibility = View.VISIBLE
                            view2.visibility = View.VISIBLE
                            twSO.visibility = View.VISIBLE
                            buttonSO1.text = "Windows"
                            buttonSO2.text = "MacOS"
                            buttonSO3.text = "Linux"
                            materialButtonToggleGroupSistemaOperativo.visibility = View.VISIBLE
                        }
                        else if (CellulareButton.isChecked){
                            twSO.visibility = View.VISIBLE
                            buttonSO1.text = "Android"
                            buttonSO2.text = "iOS"
                            buttonSO3.text = "Windows"
                            materialButtonToggleGroupSistemaOperativo.visibility = View.VISIBLE
                            twUtilizzo.visibility = View.INVISIBLE
                            materialButtonToggleGroupUtilizzo.visibility = View.INVISIBLE
                            view1.visibility = View.INVISIBLE
                        }
                        else {
                            twUtilizzo.visibility = View.INVISIBLE
                            materialButtonToggleGroupUtilizzo.visibility = View.INVISIBLE
                            twSO.visibility = View.INVISIBLE
                            buttonSO1.text = "Android"
                            buttonSO2.text = "iOS"
                            buttonSO3.text = "Windows"
                            materialButtonToggleGroupSistemaOperativo.visibility =
                                View.INVISIBLE
                            view1.visibility = View.INVISIBLE
                        }
                    }
                }
            }
        }
        binding.materialButtonToggleGroupSistemaOperativo.addOnButtonCheckedListener { _, checkedId, _ ->
            toggleButton(view!!.findViewById(checkedId))
        }

    }

    //funzione che setta lo stile dei bottoni al click
    private fun toggleButton(button: MaterialButton) {
        if (button.textColors.defaultColor == ContextCompat.getColor(this.context!!, R.color.white)) {
            button.strokeColor =
                ColorStateList.valueOf(ContextCompat.getColor(this.context!!, R.color.selected_item))
            button.setTextColor(ContextCompat.getColor(this.context!!, R.color.selected_item))
        } else {
            button.strokeColor = ColorStateList.valueOf(ContextCompat.getColor(this.context!!, R.color.white))
            button.setTextColor(ContextCompat.getColor(this.context!!, R.color.white))
        }
    }

    private fun setRangerSlider() {
        binding.rangeSlider.addOnChangeListener { rangeSlider, /*value*/ _, /*fromUser*/ _ ->
            // Responds to when slider's value is changed
            binding.lengthTextView.text = getString(R.string.length_range_current,
                rangeSlider.values[0].toInt(), rangeSlider.values[1].toInt())
            if (rangeSlider.values[1].toInt() == 2000) {
                binding.lengthTextView.text = getString(
                    R.string.length_max,
                    binding.lengthTextView.text.toString()
                )
            }
        }

        rangeSlider.setLabelFormatter { value: Float ->
            val format = NumberFormat.getCurrencyInstance()
            format.maximumFractionDigits = 0
            format.currency = Currency.getInstance("EUR")
            format.format(value.toDouble())
        }

        binding.rangeSlider.addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: RangeSlider) {
                // Responds to when slider's touch event is being started
            }

            override fun onStopTrackingTouch(slider: RangeSlider) {
                // Responds to when slider's touch event is being stopped
            }
        })
    }
}