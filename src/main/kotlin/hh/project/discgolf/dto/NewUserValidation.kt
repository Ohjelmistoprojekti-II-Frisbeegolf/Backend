package hh.project.discgolf.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class NewUserValidation(
    @field:NotBlank
    @field:Size(min = 5, max = 20, message = "Username must be 5-20 characters long")
    val username : String,

    @field:NotBlank
    @field:Size(min = 7, max = 32, message = "Password must be 7-32 characters long")
    val password : String,

    @field:NotBlank
    val passwordCheck : String,

) {
}