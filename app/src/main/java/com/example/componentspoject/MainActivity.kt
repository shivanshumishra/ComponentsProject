package com.example.componentspoject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.componentspoject.databinding.ActivityMainBinding
import com.example.componentspoject.intents.IntentsHomeActivity
import com.example.componentspoject.recyclerview.RecyclerViewCalendarActivity

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnRvActivity.setOnClickListener {
            val intent = Intent(this,RecyclerViewCalendarActivity::class.java)
            startActivity(intent)
        }

        binding.btnIntentActivity.setOnClickListener {
            Intent(this,IntentsHomeActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}