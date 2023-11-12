package study.jadiael.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import study.jadiael.orgs.R
import study.jadiael.orgs.dao.ProdutosDao
import study.jadiael.orgs.databinding.ActivityFormularioProdutoBinding
import study.jadiael.orgs.model.Produto
import study.jadiael.orgs.ui.dialog.FormularioImagemDialog
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity(R.layout.activity_formulario_produto) {
    var uriImg: String? = null

    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Cadastrar produto"
        configuraBotaoSalvar()
        setAlertDialog()
        setContentView(binding.root)
    }

    private fun configuraBotaoSalvar() {
        binding.activityFormularioProdutoBotaoSalvar.setOnClickListener {
            val produtoNovo = criaProduto()
            val dao = ProdutosDao()
            dao.adiciona(produtoNovo)
            finish()
        }
    }


    private fun criaProduto(): Produto {
        val valorEmTexto = binding.activityFormularioProdutoValor.text.toString()

        val valor = if (valorEmTexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmTexto)
        }
        return Produto(
            nome = binding.activityFormularioProdutoNome.text.toString(),
            descricao = binding.activityFormularioProdutoDescricao.text.toString(),
            valor = valor,
            imgUri = this.uriImg
        )
    }

    private fun setAlertDialog() {
        val imagemProduto = binding.activityFormularioProdutoImagem

        imagemProduto.setOnClickListener {
            FormularioImagemDialog(this).mostra(imagemProduto, this.uriImg) { imagem ->
                this.uriImg = imagem
            }
        }
    }
}