package br.com.eduardo.estudos.controller

import br.com.eduardo.estudos.controller.request.SquadRequest
import br.com.eduardo.estudos.controller.request.validators.SquadRequestValidator
import br.com.eduardo.estudos.repository.MembroRepository
import br.com.eduardo.estudos.repository.SquadRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/squads")
class SquadController(
    val repository: SquadRepository,
    val membroRepository: MembroRepository,
    val validator: SquadRequestValidator
) {

    @InitBinder
    fun validator(webDataBinder: WebDataBinder) {
        webDataBinder.addValidators(validator)
    }

    @PostMapping
    fun cadastraSquad(@RequestBody @Valid request: SquadRequest, uriComponentsBuilder: UriComponentsBuilder): ResponseEntity<*> {
        var squad = request.toModel(membroRepository)

        squad = repository.save(squad)

        val uri = uriComponentsBuilder.path("/squads/{id}").buildAndExpand(squad.id).toUri()
        return ResponseEntity.created(uri).body("")
    }

}