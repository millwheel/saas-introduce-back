package com.parsimony.saas

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SaasApplication

fun main(args: Array<String>) {
    runApplication<SaasApplication>(*args)
}
