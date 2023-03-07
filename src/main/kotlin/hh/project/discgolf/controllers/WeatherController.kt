package hh.project.discgolf.controllers

import hh.project.discgolf.services.WeatherService
import io.swagger.v3.oas.annotations.Hidden
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.client.ClientHttpRequest
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import kotlin.jvm.Throws

@RestController
@CrossOrigin
@Hidden
class WeatherController(private val weatherService: WeatherService) {

    @GetMapping(value = ["/weather"])
    fun getWeather(@RequestParam("lon") lon : String, @RequestParam("lat") lat : String ): WeatherService.WeatherObject? {
        return try {
            weatherService.handleWeather(lon, lat)
        } catch ( e : MissingServletRequestParameterException) {
            throw MissingServletRequestParameterException(e.parameterName, "")
        }

    }
}