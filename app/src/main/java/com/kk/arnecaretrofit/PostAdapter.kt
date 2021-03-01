package com.kk.arnecaretrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class PostAdapter (private val mContext:Context,
                   private val postListe:List<Post>,
                    private val pdi:PostsDaoInterface):RecyclerView.Adapter<PostAdapter.CardTasarimTutucu>(){
                       inner class CardTasarimTutucu(tasarim: View):RecyclerView.ViewHolder(tasarim){
                           var textViewKullaniciAdi:TextView
                           var textViewLikeSayisi:TextView
                           var textViewIzlenmeSayisi:TextView
                           var textViewYorumSayisi:TextView
                           var postThumbFoto:ImageView
                           var postProfilFoto:ImageView
                           init {
                               textViewKullaniciAdi=tasarim.findViewById(R.id.textViewKullaniciAdi)
                               textViewLikeSayisi=tasarim.findViewById(R.id.textViewLikeSayisi)
                               textViewIzlenmeSayisi=tasarim.findViewById(R.id.textViewGoruntulenmeSayisi)
                               textViewYorumSayisi=tasarim.findViewById(R.id.textViewYorumSayisi)
                               postThumbFoto=tasarim.findViewById(R.id.imageViewPostFotografi)
                               postProfilFoto=tasarim.findViewById(R.id.imageViewMiniProfilFotografi)
                           }
                       }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.post_card_tasarim,parent,false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
 val post= postListe.get(position)

        //kullanıcı adı
    holder.textViewKullaniciAdi.text="${post.attendeeName}"

        //kucuk profıl fotografı
        if ("${post.attendeeProfileImg}"==""){
            holder.postProfilFoto.setImageResource(R.drawable.ic_baseline_perm_identity_24)
        }
        else{
            Picasso.get()
                .load("https://v5.arneca.com${post.attendeeProfileImg}")
                .error(R.drawable.ic_baseline_share_24)
                .into(holder.postProfilFoto)
        }

        // thumbnail fotografları
            if ("${post.mediaThumb}"==""){
                holder.postThumbFoto.setImageResource(R.drawable.fotoyok)
            }
            else{
            Picasso.get()
                .load("${post.mediaThumb}")
                .error(R.drawable.ic_baseline_share_24)
                .into(holder.postThumbFoto)
        }
        //like,izlenme,yorum sayısı
        holder.textViewYorumSayisi.text="${post.commentCount}"
        holder.textViewLikeSayisi.text="${post.likeCount}"
        holder.textViewYorumSayisi.text="${post.videoView}"

    }

    override fun getItemCount(): Int {
return postListe.size    }


}