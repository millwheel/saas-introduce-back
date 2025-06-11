package com.parsimony.saas.excetion.custom

class DataNotFoundException(
    entityName: String,
    parameters: Map<String, Any?>
) : RuntimeException(
    "$entityName not found with (${parameters.entries.joinToString(", ") { "${it.key} = ${it.value}" }})"
) {

    constructor(entityName: String, parameterName: String, parameterValue: Any?) :
            this(entityName, mapOf(parameterName to parameterValue))
}