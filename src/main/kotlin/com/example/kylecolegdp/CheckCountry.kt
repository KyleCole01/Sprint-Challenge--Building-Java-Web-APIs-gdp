package com.example.kylecolegdp

import com.example.kylecolegdp.model.GDP

interface CheckCountry {
    fun test(d: GDP):Boolean

    companion object{
        inline operator fun invoke(crossinline  op: (d: GDP) -> Boolean) = object : CheckCountry{
            override fun test(d: GDP): Boolean = op(d)

        }
    }
}