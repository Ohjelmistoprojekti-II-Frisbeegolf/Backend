package hh.project.discgolf.services

import hh.project.discgolf.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService (private val userRepository: UserRepository){

}