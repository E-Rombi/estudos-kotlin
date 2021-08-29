package br.com.eduardo.estudos

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EstudosApplication

fun main(args: Array<String>) {
    runApplication<EstudosApplication>(*args)
}
