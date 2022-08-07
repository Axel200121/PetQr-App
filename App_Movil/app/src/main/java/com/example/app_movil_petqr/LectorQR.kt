package com.example.app_movil_petqr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.postDelayed
import com.google.zxing.integration.android.IntentIntegrator

class LectorQR : AppCompatActivity() {
    var btnLectorQR:Button?=null
    var tvValorQR:TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lector_qr)
        btnLectorQR=findViewById(R.id.btnLectroQR)
        tvValorQR=findViewById(R.id.tvValorQR)
        btnLectorQR?.setOnClickListener {
            iniciarScanner()
        }
    }

    fun iniciarScanner(){
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("Scaneamos su información de la mascota")
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data)
        if (result != null){
            if (result.contents==null){
                    Toast.makeText(this,"Cancelado",Toast.LENGTH_LONG).show()
            }else{
                tvValorQR?.setText(result.contents)
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}