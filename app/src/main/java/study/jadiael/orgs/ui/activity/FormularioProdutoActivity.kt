package study.jadiael.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import study.jadiael.orgs.R
import study.jadiael.orgs.dao.ProdutosDao
import study.jadiael.orgs.model.Produto
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity(R.layout.activity_formulario_produto) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val botaoSalvar = findViewById<Button>(R.id.salvar)
        botaoSalvar.setOnClickListener {
            val campoNome = findViewById<EditText>(R.id.nome)
            val nome = campoNome.text.toString()

            val campoDescricao = findViewById<EditText>(R.id.descricao)
            val descricao = campoDescricao.text.toString()

            val campoValor = findViewById<EditText>(R.id.valor)
            val valorEmTexto = campoValor.text.toString()

            val valor = if(valorEmTexto.isBlank()) {
                BigDecimal.ZERO
            } else {
                BigDecimal(valorEmTexto)
            }
            val produtoNovo = Produto(
                nome = nome,
                descricao = descricao,
                valor = valor
            )

            Log.i("FormularioProduto", "Produto salvo $produtoNovo")
            val dao = ProdutosDao()
            dao.adiciona(produtoNovo)
            Log.i("FormularioProduto", "Produto salvo com dao ${dao.buscaTodos()}")
            finish()
        }
    }
}