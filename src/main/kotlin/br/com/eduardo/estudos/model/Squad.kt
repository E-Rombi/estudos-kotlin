package br.com.eduardo.estudos.model

import org.hibernate.validator.constraints.Length
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
@Table(name = "tb_squad")
class Squad(
    id: Long = 0,
    nome: String,
    membros: MutableSet<Membro> = HashSet()
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id = id

    @NotBlank
    @Column(unique = true)
    val nome = nome

    var quantidadeIntegrantes = 0
        private set

    @Size(min = 1, max = 6)
    @ManyToMany
    @JoinTable(
        name = "tb_squad_membro",
        joinColumns = [JoinColumn(name = "squad_id")],
        inverseJoinColumns = [JoinColumn(name = "membro_id")]
    )
    var membros = membros
        private set

    init {
        quantidadeIntegrantes = membros.size
    }

    fun adicionaMembro(membro: Membro) {
        this.membros.add(membro)
        quantidadeIntegrantes++
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Squad

        if (id != other.id) return false
        if (nome != other.nome) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + nome.hashCode()
        return result
    }


}