package com.example.componentspoject.intents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.componentspoject.R
import com.example.componentspoject.databinding.ActivityIntentsHomeBinding

class IntentsHomeActivity : AppCompatActivity() {
    lateinit var binding : ActivityIntentsHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntentsHomeBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)

        binding.btnYoutube.setOnClickListener {
            Intent(Intent.ACTION_MAIN).also {
                it.`package` = "com.google.android.youtube"
                try {
                    startActivity(it)
                } catch (e : Exception){
                    Toast.makeText(this,"Can not open youtube, not found",Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.btnEmail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_EMAIL, arrayOf("test@test.com"))
                putExtra(Intent.EXTRA_SUBJECT, "This is my subject")
                putExtra(Intent.EXTRA_TEXT, "This is the content of email")
            }
            if(intent.resolveActivity(packageManager) != null){
                startActivity(intent)
            }
        }
    }
}