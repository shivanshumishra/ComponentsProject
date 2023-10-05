package com.example.componentspoject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import com.example.componentspoject.ui.RecyclerViewFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerViewFragment = RecyclerViewFragment()
        supportFragmentManager.beginTransaction().replace(R.id.container,recyclerViewFragment).commit()
    }
}