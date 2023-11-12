package study.jadiael.orgs.model

import java.math.BigDecimal

data class Produto (
    val nome: String,
    val descricao: String,
    val valor: BigDecimal,
    val imgUri: String? = null
)
