package br.com.eduardo.estudos.repository

import br.com.eduardo.estudos.model.Membro
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MembroRepository : JpaRepository<Membro, Long> {

    fun findByEmail(email: String): Optional<Membro>
}