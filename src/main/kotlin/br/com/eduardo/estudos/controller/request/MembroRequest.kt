package br.com.eduardo.estudos.controller.request

import br.com.eduardo.estudos.model.Membro
import br.com.eduardo.estudos.model.Password
import java.time.LocalDate
import javax.validation.constraints.Email
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

class MembroRequest(

        @NotBlank
        nome: String,

        @Email @NotBlank
        email: String,

        @NotBlank
        password: String,

        dataNascimento: LocalDate
) {

    @NotBlank
    val nome = nome

    @Email @NotBlank
    val email = email

    @NotBlank
    val password = password

    val dataNascimento = dataNascimento


    fun toModel(): Membro {
        return Membro(nome = this.nome, email = this.email, password = Password(this.password),
                    dataNascimento = this.dataNascimento)
    }

    @Deprecated("Utilizado para Spring Validator")
    constructor() : this("", "", "", LocalDate.MIN)


}
