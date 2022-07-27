package com.example.app_movil_petqr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator

class LectorQR : AppCompatActivity() {
    var btnLectorQR:Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lector_qr)
        btnLectorQR=findViewById(R.id.btnLectroQR)
        btnLectorQR?.setOnClickListener {
            iniciarScanner()
        }
    }

    fun iniciarScanner(){
        IntentIntegrator(this).initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data)
        if (result != null){
            if (result.contents==null){
                Toast.makeText(this,"Cancelado",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"El valor escaneado es: ${result?.contents}",Toast.LENGTH_LONG).show()
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}