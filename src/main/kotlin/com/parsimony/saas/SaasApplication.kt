package com.parsimony.saas

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync
class SaasApplication

fun main(args: Array<String>) {
    runApplication<SaasApplication>(*args)
}
