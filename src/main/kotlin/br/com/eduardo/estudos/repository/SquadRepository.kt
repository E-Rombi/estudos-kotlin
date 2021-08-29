package br.com.eduardo.estudos.repository

import br.com.eduardo.estudos.model.Squad
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SquadRepository : JpaRepository<Squad, Long> {

    fun findByNome(nome: String): Optional<Squad>
}