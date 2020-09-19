package com.example.lesson22.ui.main

import android.text.Editable
import android.util.Log
import com.example.lesson22.BuildConfig
import com.example.lesson22.data.model.CurrencyModel
import com.example.lesson22.data.remote.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter : MainContract.Presenter {

    private val values = arrayListOf<String>()
    private var view: MainContract.View? = null
    override fun loadData() {
        RetrofitBuilder.getService()?.getCurrencies(BuildConfig.API_KEY)
            ?.enqueue(object : Callback<CurrencyModel> {
                override fun onResponse(
                    call: Call<CurrencyModel>,
                    response: Response<CurrencyModel>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        val data = response.body()
                        getData(data)
                    }
                }

                override fun onFailure(call: Call<CurrencyModel>, t: Throwable) {
                    Log.e("NETWORK", t.localizedMessage)
                }
            })
    }

    override fun calculate(
        editable: Editable?,
        selectedItemPosition: Int,
        selectedItemPosition1: Int
    ) {
        val text = editable.toString()
        if (text.isNotEmpty()) {
            val result =
                text.toDouble() * (values[selectedItemPosition].toDouble() / values[selectedItemPosition1].toDouble())
            view?.showResult(result.toString())
        } else {
            view?.showResult("")
        }
    }

    private fun getData(data: CurrencyModel?) {
        val keys = data?.rates?.keySet()?.toList()
        if (keys != null) {
            for (item in keys) {
                values.add(data?.rates.get(item).toString())
            }
        }
        view?.showData(keys)
    }

    override fun bind(view: MainContract.View) {//?
        this.view = view
    }

    override fun unbind() {
        this.view = null
    }
}