package com.wikilift.conversor_moneda

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

import androidx.fragment.app.Fragment
import com.wikilift.conversor_moneda.databinding.FragmentMainScreenBinding


class MainScreen : Fragment(R.layout.fragment_main__screen), View.OnClickListener {
    private lateinit var binding: FragmentMainScreenBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainScreenBinding.bind(view)
        binding.button.setOnClickListener(this)
        binding.button2.setOnClickListener(this)
        binding.result.setOnClickListener(this)

    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {

        when (v) {
            binding.button -> {
                binding.button.hideKeyboard()
                try {

                    if (binding.txtInput.length() < 1) {
                        binding.txtInput.requestFocus()
                        binding.txtInput.error = "Debe introducir la cantidad"
                        return
                    }


                    binding.result.text = "${binding.txtInput.text.toString().toDouble() * 1.21}$"
                    binding.result.visibility=View.VISIBLE
                    Toast.makeText(context, "Operación realizada correctamente", Toast.LENGTH_SHORT)
                        .show()

                } catch (e: Exception) {
                    Toast.makeText(
                        context,
                        "Valor erróneo, introduzca un valor numérico",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
            binding.button2 -> {
                binding.button.hideKeyboard()
                try {

                    if (binding.txtInput.length() < 1) {
                        binding.txtInput.requestFocus()
                        binding.txtInput.error = "Debe introducir la cantidad"
                        return
                    }


                    binding.result.text = "${binding.txtInput.text.toString().toDouble() * 0.89}€"
                    binding.result.visibility=View.VISIBLE
                    Toast.makeText(context, "Operación realizada correctamente", Toast.LENGTH_SHORT)
                        .show()

                } catch (e: Exception) {
                    Toast.makeText(
                        context,
                        "Valor erróneo, introduzca un valor numérico",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            binding.result->{
                val myClipboard: ClipboardManager = activity?.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                val clip: ClipData=ClipData.newPlainText("simple text",binding.result.text.toString())
                myClipboard.setPrimaryClip(clip)
                Toast.makeText(context, "Resultado copiado al portapapeles", Toast.LENGTH_SHORT).show()
            }

        }
    }

}