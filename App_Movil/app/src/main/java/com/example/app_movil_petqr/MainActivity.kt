package com.example.app_movil_petqr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnEmpezar = findViewById<Button>(R.id.btnEmpezarApp)
        btnEmpezar.setOnClickListener {
            val lanzar = Intent(this,Login::class.java)
            startActivity(lanzar)
        }

        val btnRegistrarFormulario = findViewById<Button>(R.id.btnRegistrarFormulario)
        btnRegistrarFormulario.setOnClickListener {
            val lanzar1 = Intent(this,AgregaUsuario::class.java)
            startActivity(lanzar1)
        }
    }
}