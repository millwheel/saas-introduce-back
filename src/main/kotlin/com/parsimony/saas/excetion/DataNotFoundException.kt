package com.parsimony.saas.excetion

class DataNotFoundException(
    entityName: String,
    parameters: Map<String, Any?>
) : RuntimeException(
    "$entityName not found with (${parameters.entries.joinToString(", ") { "${it.key} = ${it.value}" }})"
) {

    // 단일 파라미터용 편의 생성자
    constructor(entityName: String, parameterName: String, parameterValue: Any?) :
            this(entityName, mapOf(parameterName to parameterValue))
}