package br.com.eduardo.estudos.controller.request

import br.com.eduardo.estudos.model.Membro
import br.com.eduardo.estudos.model.Squad
import br.com.eduardo.estudos.repository.MembroRepository
import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class SquadRequest(
    nome: String,
    membros : MutableSet<Long>
) {

    @Deprecated("Utilizado para Spring Validator")
    constructor() : this("", HashSet())

    @NotBlank
    val nome = nome

    @Size(min = 1, max = 6)
    val membros = membros


    fun toModel(membroRepository: MembroRepository): Squad {
        var novosMembros : MutableSet<Membro> = HashSet()

        this.membros.forEach {
            val possivelMembro = membroRepository.findById(it)
            if (possivelMembro.isPresent) {
                novosMembros.add(possivelMembro.get())
            }
        }

        return Squad(nome = this.nome, membros = novosMembros)
    }
}
