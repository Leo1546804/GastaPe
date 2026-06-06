package com.example.gastape.UI.auth

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gastape.PrincipalActivity
import com.example.gastape.R
import com.google.android.material.button.MaterialButton

class LoginActivity : AppCompatActivity() {
    //Declaracion de variable TextView
    private lateinit var tvRegistro : TextView
    private lateinit var btnIniciarSesion : MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        //Enlace de variable TextView con la interfaz usuario
        tvRegistro = findViewById<TextView>(R.id.tvRegistro)
        tvRegistro.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            //esto es para ejecutar el cambio de pantalla
            startActivity(intent)
        }

        btnIniciarSesion = findViewById<MaterialButton>(R.id.btnIniciarSesion)
        btnIniciarSesion.setOnClickListener {
            val intent = Intent(this, PrincipalActivity::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}