package study.jadiael.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import study.jadiael.orgs.R
import study.jadiael.orgs.databinding.ProdutoItemBinding
import study.jadiael.orgs.extensios.tentaCarregarImagem
import study.jadiael.orgs.model.Produto
import java.text.NumberFormat
import java.util.Locale

class ListaProdutosAdapter(
    private val context: Context,
    produtos: List<Produto>
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProdutoItemBinding
            .inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    class ViewHolder(binding: ProdutoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val nome = binding.produtoItemNome
        private val descricao = binding.produtoItemDescricao
        private val valor = binding.produtoItemValor

        fun vincula(produto: Produto) {
            nome.text = produto.nome
            descricao.text = produto.descricao
            val valorEmMoeda: String = formataParaMoedaBrasileira(produto)
            valor.text = valorEmMoeda

            setVisibilidade(produto, itemView)
            itemView.findViewById<ImageView>(R.id.imageView).tentaCarregarImagem(produto.imgUri)

        }

        private fun formataParaMoedaBrasileira(produto: Produto): String {
            val formatador: NumberFormat = NumberFormat
                .getCurrencyInstance(Locale("pt", "br"))
            return formatador.format(produto.valor)
        }

        fun setVisibilidade(produto: Produto, itemView: View) {
            val visibilidade = if (produto.imgUri != null) {
                View.VISIBLE
            } else {
                View.GONE
            }

            itemView.findViewById<ImageView>(R.id.imageView).visibility = visibilidade
        }

    }

    override fun getItemCount(): Int {
        return produtos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto)
    }

    fun atualiza(produtos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }
}
