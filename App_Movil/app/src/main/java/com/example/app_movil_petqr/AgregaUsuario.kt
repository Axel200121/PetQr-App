package com.example.app_movil_petqr

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AgregaUsuario : AppCompatActivity() {
    var txt_nombre:EditText?=null;
    var txt_apellidos:EditText?=null;
    var txt_telefono:EditText?=null;
    var txt_direccion:EditText?=null;
    var txt_correo:EditText?=null;
    var txt_password:EditText?=null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_usuario)

        txt_nombre = findViewById(R.id.txt_nombre)
        txt_apellidos = findViewById(R.id.txt_apellidos)
        txt_telefono = findViewById(R.id.txt_telefono)
        txt_direccion = findViewById(R.id.txt_telefono)
        txt_correo = findViewById(R.id.txt_correo)
        txt_password = findViewById(R.id.txt_password)
    }


}