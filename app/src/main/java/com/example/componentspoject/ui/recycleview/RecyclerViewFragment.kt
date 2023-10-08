package com.example.componentspoject.ui.recycleview

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.componentspoject.MainActivity
import com.example.componentspoject.databinding.FragmentRecyclerViewBinding
import com.example.componentspoject.ui.recycleview.adapter.ListAdapter


class RecyclerViewFragment : Fragment() {
    private lateinit var dataset : MutableList<String>
    private lateinit var customAdapter : ListAdapter
    private lateinit var viewVModel: RecycleViewVModel
    private lateinit var binding: FragmentRecyclerViewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecyclerViewBinding.inflate(layoutInflater)
        val view = binding.root
        dataset = mutableListOf<String>()
        viewVModel = ViewModelProvider(this).get(RecycleViewVModel::class.java)
        customAdapter = ListAdapter(viewVModel.dataset.value ?: arrayListOf(),viewVModel::deleteItemFromList,this::showDatePicker)
        val recyclerView: RecyclerView = binding.rvItems
        val addBtn : Button = binding.btnAdd
        val editText : EditText = binding.editTextItemValue
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = customAdapter
        subscribe()
        addBtn.setOnClickListener {
            if(editText.text.isNullOrBlank()){
                Toast.makeText(requireContext(),"Please enter list item",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            viewVModel.addItemToList(editText.text.toString())
        }
        return view
    }

    private fun showDatePicker(view: View?) : String {
        val c = Calendar.getInstance()
        val year: Int = c.get(Calendar.YEAR)
        val month: Int = c.get(Calendar.MONTH)
        val day: Int = c.get(Calendar.DAY_OF_MONTH)
        val date : MutableLiveData<String> = MutableLiveData("")

        val datePickerDialog = view?.context?.let {
            DatePickerDialog( // on below line we are passing context.
                it,
                { view, year, monthOfYear, dayOfMonth ->
                    date.postValue(dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)

                },
                year, month, day
            )
        }
        datePickerDialog?.show()
        return date.value ?: ""
    }

    private fun subscribe() {
        viewVModel.dataset.observe(viewLifecycleOwner) {
            customAdapter.notifyDataSetChanged()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}