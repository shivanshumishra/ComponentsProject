package com.example.componentspoject.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.componentspoject.R
import com.example.componentspoject.ui.adapter.ListAdapter

class RecyclerViewFragment : Fragment() {
    private lateinit var dataset : MutableList<String>
    private lateinit var customAdapter : ListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recycler_view, container, false)
        dataset = mutableListOf<String>()
        customAdapter = ListAdapter(dataset,this::deleteItem)

        val recyclerView: RecyclerView = view.findViewById(R.id.rvItems)
        val addBtn : Button = view.findViewById(R.id.btnAdd)
        val editText : EditText = view.findViewById(R.id.editTextItemValue)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = customAdapter
        addBtn.setOnClickListener {
            if(editText.text.isNullOrBlank()){
                Toast.makeText(requireContext(),"Please enter list item",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            dataset.add(editText.text.toString())
            customAdapter.notifyDataSetChanged()
        }
        return view
    }

    private fun deleteItem(index : Int){
        dataset.removeAt(index)
        customAdapter.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}