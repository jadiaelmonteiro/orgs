package study.jadiael.orgs.ui.activity

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import study.jadiael.orgs.R
import study.jadiael.orgs.model.Produto
import study.jadiael.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import java.math.BigDecimal

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = ListaProdutosAdapter(
            context = this, produtos = listOf(
                Produto(
                    nome = "Cesta de frutas",
                    descricao = "Banana e maçã",
                    valor = BigDecimal("5.66")
                ),
                Produto(
                    nome = "Proteínas",
                    descricao = "Frango, carnes e peixe",
                    valor = BigDecimal("59.99")
                ),
                Produto(
                    nome = "Carboidratos",
                    descricao = "Arroz, macarrão e pão",
                    valor = BigDecimal("19.99")
                )
            )
        )
    }
}