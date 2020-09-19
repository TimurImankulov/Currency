package com.example.lesson22.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import com.example.lesson22.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private var adapter: CurrencySpinnerAdapter? = null
    private var presenter: MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter()
        presenter?.bind(this)
        setupListener()
        presenter?.loadData()
    }

    private fun setupListener() {
        etValueOne.doAfterTextChanged {
            presenter?.calculate(it, spValueTwo.selectedItemPosition, spValueOne.selectedItemPosition)
        }
    }

    override fun showData(list: List<String>?) {
        adapter = CurrencySpinnerAdapter(applicationContext, R.layout.item_spinner, list!!)
        spValueOne.adapter = adapter
        spValueTwo.adapter = adapter
    }

    override fun showResult(result: String) {
        etValueTwo.setText(result)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.unbind()
    }
}

