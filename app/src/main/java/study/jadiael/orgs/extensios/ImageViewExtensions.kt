package study.jadiael.orgs.extensios;

import android.widget.ImageView
import coil.load
import study.jadiael.orgs.R
fun ImageView.tentaCarregarImagem(url: String? = null) {
    load(url) {
        fallback(R.drawable.erro)
        error(R.drawable.erro)
        placeholder(R.drawable.placeholder)
    }
}