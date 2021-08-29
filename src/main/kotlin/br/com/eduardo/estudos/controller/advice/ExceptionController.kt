package br.com.eduardo.estudos.controller.advice

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.http.HttpStatus
import javax.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import java.util.HashMap
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.function.Consumer

@ControllerAdvice
class ExceptionController {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    fun validation(e: MethodArgumentNotValidException, request: HttpServletRequest?): ResponseEntity<*> {
        val status = HttpStatus.UNPROCESSABLE_ENTITY
        val erros: MutableMap<String, Any> = HashMap()

        e.bindingResult.fieldErrors.forEach(Consumer { error: FieldError ->
            erros[error.field] = error.defaultMessage.orEmpty()
        })

        return ResponseEntity.status(status).body<Map<String, Any>>(erros)
    }
}