package com.example.lesson22.ui

interface LiveCycle<V> {
    fun bind(view: V)
    fun unbind()
}