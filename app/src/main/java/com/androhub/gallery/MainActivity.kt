package com.androhub.gallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    private lateinit var resultLauncherGallery: ActivityResultLauncher<String>
    lateinit var iv : ImageView
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iv = findViewById<ImageView>(R.id.iv)

        val btnGallery = findViewById<Button>(R.id.btnGallery)
        btnGallery.setOnClickListener {
            openGallery()
        }
        resultLauncherGallery = registerForActivityResult(ActivityResultContracts.GetContent())
        {
                uri ->
            iv.setImageURI(uri)
            val uriPathHelper = URIPathHelper()
            val filePath = uriPathHelper.getPath(this, uri)
            Log.i(TAG,filePath.toString())
        }
    }

    private fun openGallery()
    {
//        val intentGallery = Intent()
//        intentGallery.type="image/*"
//        intentGallery.action = Intent.ACTION_GET_CONTENT
        resultLauncherGallery.launch("image/*")
        //  resultLauncherGallery.launch("/*")

    }

}