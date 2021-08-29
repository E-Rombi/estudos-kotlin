package br.com.eduardo.estudos.model

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class Password(
        password: String
) {

    var encoded : String? = null
        private set

    init {
        encoded = encode(password)
    }


    fun encode(password: String): String {
        return BCryptPasswordEncoder().encode(password)
    }

}
