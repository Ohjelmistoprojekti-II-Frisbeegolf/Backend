package hh.project.discgolf.controllers

import hh.project.discgolf.services.WeatherService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.client.ClientHttpRequest
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@RestController
@CrossOrigin
class WeatherController(private val weatherService: WeatherService) {

    @GetMapping(value = ["/weather/lon={lon}/lat={lat}"])
    fun getWeather(@PathVariable("lon") lon : Double, @PathVariable("lat") lat : Double ) = weatherService.handleWeather(lon, lat)


}