package study.jadiael.orgs.ui.dialog;

import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputEditText
import study.jadiael.orgs.R
import study.jadiael.orgs.extensios.tentaCarregarImagem

class FormularioImagemDialog(val context: Context) {

    fun mostra(imagemProduto: ImageView, quandoImgCarregada: (imagem: String) -> Unit) {

        imagemProduto.setOnClickListener {
            val inflater = LayoutInflater.from(context)
            val imagemProdutoLayout = inflater.inflate(R.layout.formulario_imagem, null)
            val btnCarregar = imagemProdutoLayout.findViewById<Button>(R.id.formulario_imagem_botao_carregar)

            btnCarregar.setOnClickListener {
                val textUri = imagemProdutoLayout.findViewById<TextInputEditText>(R.id.formulario_imagem_text_url)
                val toStringText = textUri.text.toString()

                val imgDialog = imagemProdutoLayout.findViewById<ImageView>(R.id.formulario_imagem_imagemview)
                imgDialog.tentaCarregarImagem(toStringText)
            }

            AlertDialog.Builder(context)
                .setView(imagemProdutoLayout.rootView)
                .setPositiveButton("Confirmar") { _, _ ->
                    val textUri = imagemProdutoLayout.findViewById<TextInputEditText>(R.id.formulario_imagem_text_url)
                    val toStringText = textUri.text.toString()
                    imagemProduto.tentaCarregarImagem(toStringText)
                    quandoImgCarregada(toStringText)

                }
                .setNegativeButton("Cancelar") { _, _ -> }
                .show()
        }
    }
}
