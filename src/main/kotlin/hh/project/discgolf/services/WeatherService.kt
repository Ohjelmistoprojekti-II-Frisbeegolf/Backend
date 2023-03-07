package hh.project.discgolf.services

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@Service
class WeatherService {
    fun handleWeather(lon : String, lat : String): WeatherObject? {
        return try {
            val lonAsDouble = parseToDouble(lon)
            val latAsDouble = parseToDouble(lat)
            val response = fetchWeather(lonAsDouble, latAsDouble)
            generateGsonObject(response)
        } catch ( e : NumberFormatException) {
            throw DataIntegrityViolationException(e.message!!)
        } catch ( e : JsonSyntaxException) {
            throw JsonSyntaxException("Could not parse JSON.")
        }
    }

    @Throws(NumberFormatException::class)
    fun parseToDouble( value : String) : Double = value.toDouble()

    //https://zetcode.com/kotlin/getpostrequest/
    fun fetchWeather(lon : Double, lat : Double) : HttpResponse<String> {
        val client = HttpClient.newBuilder().build()
        val request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.openweathermap.org/data/2.5/weather?lat=$lat&lon=$lon&units=metric&appid=${System.getenv("API_KEY")}"))
            .build()
        return client.send(request, HttpResponse.BodyHandlers.ofString())
    }

    //https://www.techiedelight.com/parse-a-json-string-in-kotlin/
    @Throws(JsonSyntaxException::class)
    fun generateGsonObject(response: HttpResponse<String>): WeatherObject? {
        return Gson().fromJson(response.body(), WeatherObject::class.java)
    }

    class WeatherObject {
        var name : String? = ""
        var main : Main? = null
        var weather : Array<Weather>? = null
        var wind : Wind? = null
    }

    class Weather {
        var description : String? = ""
        var icon : String? = ""
    }

    class Main {
        var temp : Double? = 0.0
    }

    class Wind {
        var speed : Double? = 0.0
    }
}


