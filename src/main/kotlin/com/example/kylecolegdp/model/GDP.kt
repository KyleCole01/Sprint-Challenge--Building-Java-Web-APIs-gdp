package com.example.kylecolegdp.model

import java.util.concurrent.atomic.AtomicLong

class GDP(var country: String, var economy: Int) {
    var id: Long = counter.incrementAndGet()

    companion object {
        private val counter = AtomicLong()
    }
}