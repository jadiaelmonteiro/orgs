package study.jadiael.orgs.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.google.android.material.textfield.TextInputEditText
import study.jadiael.orgs.R
import study.jadiael.orgs.dao.ProdutosDao
import study.jadiael.orgs.model.Produto
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity(R.layout.activity_formulario_produto) {
    var uriImg: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configuraBotaoSalvar()
        setAlertDialog()

    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = findViewById<Button>(R.id.activity_formulario_produto_botao_salvar)
        botaoSalvar.setOnClickListener {
            val produtoNovo = criaProduto()
            val dao = ProdutosDao()
            dao.adiciona(produtoNovo)
            finish()
        }
    }

    private fun criaProduto(): Produto {
        val campoNome = findViewById<EditText>(R.id.activity_formulario_produto_nome)
        val nome = campoNome.text.toString()
        val campoDescricao = findViewById<EditText>(R.id.activity_formulario_produto_descricao)
        val descricao = campoDescricao.text.toString()
        val campoValor = findViewById<EditText>(R.id.activity_formulario_produto_valor)
        val valorEmTexto = campoValor.text.toString()

        val valor = if (valorEmTexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmTexto)
        }

        return Produto(
            nome = nome,
            descricao = descricao,
            valor = valor,
            imgUri = uriImg
        )
    }

    private fun setAlertDialog() {
        val imagemProduto = findViewById<ImageView>(R.id.activity_formulario_produto_imagem)

        imagemProduto.setOnClickListener {
            val inflater = LayoutInflater.from(this)
            val imagemProdutoLayout = inflater.inflate(R.layout.formulario_imagem, null)
            val btnCarregar = imagemProdutoLayout.findViewById<Button>(R.id.formulario_imagem_botao_carregar)

            btnCarregar.setOnClickListener {
                val textUri = imagemProdutoLayout.findViewById<TextInputEditText>(R.id.formulario_imagem_text_url)
                val toStringText = textUri.text.toString()

                val imgDialog = imagemProdutoLayout.findViewById<ImageView>(R.id.formulario_imagem_imagemview)
                imgDialog.load(toStringText) {
                    fallback(R.drawable.erro)
                    error(R.drawable.erro)
                }
            }

            AlertDialog.Builder(this)
                .setView(imagemProdutoLayout.rootView)
                .setPositiveButton("Confirmar") { _, _ ->
                    val textUri = imagemProdutoLayout.findViewById<TextInputEditText>(R.id.formulario_imagem_text_url)
                    val toStringText = textUri.text.toString()
                    imagemProduto.load(toStringText) {
                        fallback(R.drawable.erro)
                        error(R.drawable.erro)
                    }
                    setValueUriImg(toStringText)

                }
                .setNegativeButton("Cancelar") { _, _ -> }
                .show()
        }
    }

    private fun setValueUriImg(uriImg: String) {
        this.uriImg = uriImg
    }
}