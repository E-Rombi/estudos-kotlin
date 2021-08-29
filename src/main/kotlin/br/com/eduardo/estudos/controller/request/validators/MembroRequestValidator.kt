package br.com.eduardo.estudos.controller.request.validators

import br.com.eduardo.estudos.controller.request.MembroRequest
import br.com.eduardo.estudos.repository.MembroRepository
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator
import java.time.LocalDate
import java.time.Period

@Component
class MembroRequestValidator(
        val repository: MembroRepository
) : Validator{

    override fun supports(clazz: Class<*>): Boolean {
        return MembroRequest().javaClass.isAssignableFrom(clazz)
    }

    override fun validate(target: Any, errors: Errors) {
        if (errors.hasErrors()) return

        target as MembroRequest

        val period = Period.between(target.dataNascimento, LocalDate.now())
        if (period.years < 18) errors.rejectValue("dataNascimento", null.toString(), "Necessário ter 18 anos !")

        val possivelMembro = repository.findByEmail(target.email)
        if (possivelMembro.isPresent) errors.rejectValue("email", null.toString(), "Email já cadastrado !")
    }
}