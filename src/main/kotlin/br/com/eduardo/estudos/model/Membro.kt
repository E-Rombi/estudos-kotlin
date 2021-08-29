package br.com.eduardo.estudos.model

import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "tb_membro")
class Membro(
        id : Long = 0,
        nome: String,
        email: String,
        password: Password,
        dataNascimento: LocalDate = LocalDate.now()
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id = id

    @NotBlank
    val nome = nome

    @Email
    @NotBlank
    @Column(unique = true)
    val email = email

    @NotBlank
    val senha = password.encoded

    val dataNascimento = dataNascimento

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Membro

        if (email != other.email) return false

        return true
    }

    override fun hashCode(): Int {
        return email.hashCode()
    }


}