package com.example.lesson22.ui.main

import android.text.Editable
import com.example.lesson22.ui.LiveCycle

interface MainContract {

    interface View {
        fun showData(list: List<String>?)
        fun showResult(result: String)
    }

    interface Presenter : LiveCycle<View>{
        fun loadData()
        fun calculate(
            it: Editable?,
            selectedItemPosition: Int,
            selectedItemPosition1: Int
        )
    }
}