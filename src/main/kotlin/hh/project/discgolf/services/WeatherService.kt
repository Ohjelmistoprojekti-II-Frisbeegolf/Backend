package hh.project.discgolf.services

import org.springframework.stereotype.Service

@Service
class WeatherService {

    fun fetchWeather(lon : Double, lat : Double) : String {
        return "$lon $lat"
    }
}