package com.example.componentspoject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.componentspoject.databinding.ActivityMainBinding
import com.example.componentspoject.ui.calendar.CalendarFragment
import com.example.componentspoject.ui.recycleview.RecyclerViewFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        val recyclerViewFragment = RecyclerViewFragment()
        switchFragment(recyclerViewFragment)
        val calendarFragment = CalendarFragment()
        binding.fragment1.setOnClickListener {
            switchFragment(recyclerViewFragment)
        }
        binding.calendarFragment.setOnClickListener {
            switchFragment(calendarFragment)
        }
        setContentView(view)
    }

    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container,fragment).commit()
    }
}