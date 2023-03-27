package hh.project.discgolf.dto

import hh.project.discgolf.enums.UserRole
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import kotlin.math.min

data class NewUserValidation(
    @NotBlank
    @Size(min = 5, max = 20, message = "Username must be 5-20 characters long")
    val username : String,

    @NotBlank
    @Size(min = 7, max = 32, message = "Password must be 7-32 characters long")
    val password : String,

    @NotBlank
    @Size(min = 7, max = 32, message = "Password must be 7-32 characters long")
    val passwordCheck : String,

) {
}