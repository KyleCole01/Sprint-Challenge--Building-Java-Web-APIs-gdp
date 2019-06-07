package com.example.kylecolegdp.controller

import com.example.kylecolegdp.CheckCountry
import com.example.kylecolegdp.KylecolegdpApplication
import com.example.kylecolegdp.exception.ResourceNotFoundException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/countries")
class GDPController {

    val logger: Logger = LoggerFactory.getLogger(GDPController::class.java)
    // all countries in alphabetical order
    // /countries/names
    @GetMapping(value = ["/names"])
    fun getAllNames(): ResponseEntity<Any> {
        logger.info("/countries/names has been accessed")

        val rtnDog = KylecolegdpApplication.getOurGDPList().gdpList.sortedBy { gdp -> gdp.country }
        return ResponseEntity(rtnDog, HttpStatus.OK)
    }

    //countries/economy
    @GetMapping(value = ["/economy"])
    fun getAllEconomies(): ResponseEntity<Any> {
        logger.info("/countries/economy has been accessed")

        val rtnDog = KylecolegdpApplication.getOurGDPList().gdpList.sortedByDescending { gdp -> gdp.economy }
        return ResponseEntity(rtnDog, HttpStatus.OK)
    }

    // countries by id countries/country/5
    @GetMapping(value = ["/country/{id}"])
    fun getDogDetail(@PathVariable id: Long, request: HttpServletRequest): ResponseEntity<Any> {
        logger.info("/country/$id has been accessed")
        val rtnGdp = KylecolegdpApplication.getOurGDPList().gdpList[id.toInt() - 1]
        return ResponseEntity(rtnGdp, HttpStatus.OK)
    }

    //single country detail  /countries/china
    @GetMapping(value = ["/{country}"])
    fun getCountries(@PathVariable country: String): ResponseEntity<*> {
        logger.info("/countries/$country has been accessed")
        val rtnDogs = KylecolegdpApplication.getOurGDPList().findCountry(CheckCountry { gdp -> gdp.country.toUpperCase() == country.toUpperCase() })
                ?: throw ResourceNotFoundException(message = "Could not find $country", cause = null)
        return ResponseEntity(rtnDogs, HttpStatus.OK)
    }

    //median stats    /countries/stats/median
    @GetMapping(value = ["/stats/median"])
    fun getMedian(): ResponseEntity<Any> {
        logger.info("/stats/median has been accessed")

        val rtnCtry = KylecolegdpApplication.getOurGDPList().gdpList.size

        return ResponseEntity(KylecolegdpApplication.getOurGDPList().gdpList[(rtnCtry / 2).toInt() - 1], HttpStatus.OK)
    }

    //median stats    /countries/stats/median
    @GetMapping(value = ["/total"])
    fun getTotal(): ResponseEntity<Any> {
        logger.info("STRETCH GOAL!!!!!  /countries/total has been accessed")
        val rtnCtry = KylecolegdpApplication.getOurGDPList().findTotal()
        return ResponseEntity(rtnCtry, HttpStatus.OK)
    }

    //table sorted by gdp
    // countries/economy/table
    @GetMapping(value = ["/economy/table"])
    fun getCountryTable(): ModelAndView {
        logger.info("countries/economy/table has been accessed")
        val mav = ModelAndView("countries")
        mav.addObject("countryList", KylecolegdpApplication.getOurGDPList().gdpList)
        return mav
    }


}