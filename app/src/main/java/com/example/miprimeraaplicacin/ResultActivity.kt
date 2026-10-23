package com.example.miprimeraaplicacin

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.miprimeraaplicacin.databinding.ResultActivityBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ResultActivityBinding

    private var name: String? = ""
    private var age: String? = ""
    private var language: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ResultActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        name = intent.extras?.getString("DATA_NAME")
        age = intent.extras?.getString("DATA_AGE")
        language = intent.extras?.getString("DATA_LANGUAGE")

        binding.textViewNombre.text = name
        binding.textViewEdad.text = age
        binding.textViewIdioma.text = language
    }
}