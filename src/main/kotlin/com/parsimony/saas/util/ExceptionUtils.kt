package com.parsimony.saas.util

import com.parsimony.saas.excetion.DataNotFoundException
import java.util.Optional

// 여러 파라미터용
fun <T> Optional<T>.orThrowNotFound(entityName: String, vararg parameters: Pair<String, Any?>): T {
    return this.orElseThrow { DataNotFoundException(entityName, parameters.toMap()) }
}

// 단일 파라미터용
fun <T> Optional<T>.orThrowNotFound(entityName: String, parameterName: String, parameterValue: Any?): T {
    return this.orElseThrow { DataNotFoundException(entityName, parameterName, parameterValue) }
}