package com.example.lesson22.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.example.lesson22.BuildConfig.API_KEY
import com.example.lesson22.R
import com.example.lesson22.data.remote.RetrofitBuilder
import com.example.lesson22.data.remote.model.CurrencyModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val values = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupListener()
        setupNetwork()

    }

    private fun setupListener() {
        //  etValueOne.addTextChangedListener(textWatcher)
        etValueOne.doAfterTextChanged {
            calculate(it.toString())
        }
    }

    //code for Java
//    private val textWatcher = object : TextWatcher {
//        override fun afterTextChanged(txt: Editable?) {
//            etValueTwo.setText(txt.toString())
//        }
//
//        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//    }

    private fun calculate(value: String) {
        if (value.isNotEmpty()) {
            val result =
                value.toDouble() * (values[spValueTwo.selectedItemPosition].toDouble() / values[spValueOne.selectedItemPosition].toDouble())
            etValueTwo.setText(result.toString())
        }
    }

    private fun setupNetwork() {
        RetrofitBuilder.getService()?.getCurrencies(API_KEY)
            ?.enqueue(object : Callback<CurrencyModel> {
                override fun onResponse(
                    call: Call<CurrencyModel>,
                    response: Response<CurrencyModel>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        getData(response.body())
                    }
                }

                override fun onFailure(call: Call<CurrencyModel>, t: Throwable) {
                    Toast.makeText(applicationContext,getString(R.string.nodata), Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun getData(data: CurrencyModel?) {
        val keys = data?.rates?.keySet()?.toList()
        if (keys != null) {
            for (item in keys) {
                values.add(data?.rates.get(item).toString())
            }
        }
        val adapter = CurrencySpinnerAdapter(applicationContext, R.layout.item_spinner, keys!!)
        spValueOne.adapter = adapter
        spValueTwo.adapter = adapter
        spValueOne.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            }
        }

        spValueTwo.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                calculate(etValueTwo.text.toString())
            }
        }
    }
}

