package com.example.lesson22.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.lesson22.R

class CurrencySpinnerAdapter(context: Context, resource: Int, objects: List<String>) :
    ArrayAdapter<String>(context, resource, objects) {

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_spinner, parent,false)
        view.findViewById<TextView>(R.id.tvTitle).text = getItem(position)
        return view
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_spinner, parent,false)

        view.findViewById<TextView>(R.id.tvTitle).text = getItem(position)
        return view
    }
}