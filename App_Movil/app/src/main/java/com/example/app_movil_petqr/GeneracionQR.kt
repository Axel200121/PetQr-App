package com.example.app_movil_petqr

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import java.lang.Exception

class GeneracionQR : AppCompatActivity() {
    var idMascota:String?=null
    var txtDescripcionQR:EditText?=null
    var imgQR:ImageView?=null
    var btnGenerarQr:Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generacion_qr)
        txtDescripcionQR=findViewById(R.id.txtDescripcionQR)
        imgQR=findViewById(R.id.imgQR)
        btnGenerarQr=findViewById(R.id.btnGenerarQr)

        idMascota=intent.getStringExtra("idMascota").toString()
        txtDescripcionQR?.setText(idMascota)

        btnGenerarQr?.setOnClickListener(View.OnClickListener {
            try {
                var barcodeEncoder:BarcodeEncoder= BarcodeEncoder()
                var bitmap:Bitmap=barcodeEncoder.encodeBitmap(
                    txtDescripcionQR?.text.toString(),
                    BarcodeFormat.QR_CODE,
                    750,
                    750
                )
                imgQR?.setImageBitmap(bitmap)
            }catch (e: Exception){
                e.printStackTrace()
            }
        })



    }
}