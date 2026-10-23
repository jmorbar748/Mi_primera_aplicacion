package com.example.miprimeraaplicacin

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.miprimeraaplicacin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //
    //private lateinit var textView: TextView
    //private lateinit var button: Button
    //
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //textView = findViewById(R.id.textView)
        //button = findViewById(R.id.button)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView.text = "Bienvenido a la aplicación!"

        binding.button.setOnClickListener {
            binding.textView.text = "Botón pulsado!"
            binding.textView.setTextColor(Color.GREEN)
        }

        initListener()

    }

    private fun initListener() {
        binding.buttonEnviar.setOnClickListener {comprobarDatos() }

        // Actualizar textEdad cuando cambia seekBar
        binding.seekBarEdad.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                val edad: Int? = seekBar?.progress
                binding.textViewResultadoEdad.text = "Edad: $edad años"
            }

        })

        binding.buttonReset.setOnClickListener { resetDatos() }
    }

    private fun resetDatos() {
        binding.editTextNombre.text.clear()
        binding.seekBarEdad.progress = 0
        binding.textViewResultadoEdad.text = "Edad: 0 años"
        binding.spinnerIdioma.setSelection(0)
    }

    private fun comprobarDatos() {
        var comprobar = true

        // Comprobar longitud del texto
        if (binding.editTextNombre.text.length < 3) {
            binding.editTextNombre.error = "El nombre debe tener al menos 3 caracteres"
            comprobar = false
        }

        // Comprobar que se introduce idiomas
        if (binding.spinnerIdioma.selectedItemPosition == 0) {
            Toast.makeText(this, "Debes seleccionar un idioma", Toast.LENGTH_SHORT).show()
            comprobar = false
        }

        if (comprobar) {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("DATA_NAME", binding.editTextNombre.text.toString())
            intent.putExtra("DATA_AGE", binding.seekBarEdad.progress.toString())
            intent.putExtra("DATA_LANGUAGE", binding.spinnerIdioma.selectedItem.toString())
            startActivity(intent)
        }

    }

}