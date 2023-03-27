package hh.project.discgolf.services

import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service

// Link source: https://tienisto.medium.com/securing-spring-boot-with-jwt-kotlin-7b529f99ca47
@Service
class HashService {

    fun checkBcrypt(input : String, hash : String) : Boolean = BCrypt.checkpw(input, hash)

    fun hashBcrypt(input: String) : String = BCrypt.hashpw(input, BCrypt.gensalt())
}