package com.example.gastape.UI.auth

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gastape.R

class RegistroActivity : AppCompatActivity() {

    private lateinit var imgRegresar : ImageView
    private lateinit var tvRegresar: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)

        // Imagen para regresar al login
        imgRegresar = findViewById<ImageView>(R.id.imgRegresar)
        imgRegresar.setOnClickListener {
            finish()
        }

        // TextView para regresar al login
        tvRegresar = findViewById<TextView>(R.id.tvRegresar)
        tvRegresar.setOnClickListener {
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}