package study.jadiael.orgs.dao

import study.jadiael.orgs.model.Produto
import java.math.BigDecimal

class ProdutosDao {

    fun adiciona(produto: Produto) {
        produtos.add(produto)
    }

    fun buscaTodos() : List<Produto> {
        return produtos.toList()
    }

    companion object {
        private val produtos = mutableListOf<Produto>(
            Produto(nome="Cesta de frutas", descricao="Sala de frutas", valor = BigDecimal("123.123") )
        )
    }

}