package br.com.eduardo.estudos.controller

import br.com.eduardo.estudos.controller.request.MembroRequest
import br.com.eduardo.estudos.controller.request.validators.MembroRequestValidator
import br.com.eduardo.estudos.model.Membro
import br.com.eduardo.estudos.repository.MembroRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI
import javax.validation.Valid

@RestController
@RequestMapping("/membros")
class MembroController(
        val repository: MembroRepository,
        val validator: MembroRequestValidator
        ) {

    @InitBinder
    fun binder(dataBinder: WebDataBinder) {
        dataBinder.addValidators(validator)
    }

    @PostMapping
    fun cadastraMembro(@RequestBody @Valid request: MembroRequest, builder: UriComponentsBuilder): ResponseEntity<*> {
        var membro : Membro = request.toModel()

        repository.save(membro)

        val uri : URI = builder.path("/membros/{id}").buildAndExpand(membro.id).toUri()

        return ResponseEntity.created(uri).body("")
    }
}