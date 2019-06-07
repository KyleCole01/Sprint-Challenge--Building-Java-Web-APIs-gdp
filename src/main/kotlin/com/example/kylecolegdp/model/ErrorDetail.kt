package com.example.kylecolegdp.model

import java.sql.Timestamp

class ErrorDetail(val title: String,
                  val status: Int,
                  val detail: String,
                  val timestamp: Timestamp,
                  val developerMessage: String)
