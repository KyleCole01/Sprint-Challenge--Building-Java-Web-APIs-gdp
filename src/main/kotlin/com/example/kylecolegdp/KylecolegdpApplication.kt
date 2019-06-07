package com.example.kylecolegdp

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.servlet.DispatcherServlet
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@SpringBootApplication
class KylecolegdpApplication {


    companion object {
        private lateinit var ourGDPList: GDPList


        @JvmStatic
        fun main(args: Array<String>) {
            ourGDPList = GDPList()

            val ctx = SpringApplication.run(KylecolegdpApplication::class.java, *args)

            val dispatcherServlet = ctx.getBean("dispatcherServlet") as DispatcherServlet
            dispatcherServlet.setThrowExceptionIfNoHandlerFound(true)
        }

        fun getOurGDPList(): GDPList = ourGDPList


    }
}