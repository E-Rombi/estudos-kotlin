package br.com.eduardo.estudos.controller.request.validators

import br.com.eduardo.estudos.controller.request.SquadRequest
import br.com.eduardo.estudos.repository.MembroRepository
import br.com.eduardo.estudos.repository.SquadRepository
import com.fasterxml.jackson.module.kotlin.jsonMapper
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator

@Component
class SquadRequestValidator(
        val repository: SquadRepository,
        val membroRepository: MembroRepository
) : Validator{

    override fun supports(clazz: Class<*>): Boolean {
        return SquadRequest().javaClass.isAssignableFrom(clazz)
    }

    override fun validate(target: Any, errors: Errors) {
        if (errors.hasErrors()) return

        target as SquadRequest

        val inexistentes : MutableSet<Long> = HashSet()

        target.membros.forEach {
            val possivelMembro = membroRepository.findById(it)
            if (possivelMembro.isEmpty) inexistentes.add(it)
        }

        val possivelSquad = repository.findByNome(target.nome)

        if (possivelSquad.isPresent) errors.rejectValue("nome", null.toString(), "Nome já cadastrado !")
        if (inexistentes.size > 0) errors.rejectValue("membros", null.toString(), "Membros informados inexistentes ! - $inexistentes")
    }
}