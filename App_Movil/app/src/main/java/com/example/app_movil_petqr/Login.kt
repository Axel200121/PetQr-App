package com.example.app_movil_petqr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnRegistraFormulario = findViewById<Button>(R.id.btnRegistrarFormulario)
        btnRegistraFormulario.setOnClickListener {
            val lanzarRegistroFormulario = Intent(this,AgregaUsuario::class.java)
            startActivity(lanzarRegistroFormulario)
        }
    }
}