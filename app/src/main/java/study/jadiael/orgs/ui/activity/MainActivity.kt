package study.jadiael.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import study.jadiael.orgs.R
import study.jadiael.orgs.model.Produto
import study.jadiael.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import java.math.BigDecimal

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fab.setOnClickListener {
            val intent = Intent(this, FormularioProdutoActivity::class.java)
            startActivity(intent)
        }
    }
}