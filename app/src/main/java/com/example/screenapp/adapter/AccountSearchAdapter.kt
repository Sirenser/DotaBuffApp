package com.example.screenapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.screenapp.databinding.ItemAccountCardBinding
import com.example.screenapp.models.AccountSearchResponse

class AccountSearchAdapter(private val listener: OnItemAccClicked) :
    RecyclerView.Adapter<AccountSearchAdapter.AccountViewHolder>() {

    class AccountViewHolder(val binding: ItemAccountCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val accountSearchList = ArrayList<AccountSearchResponse>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAccountCardBinding.inflate(inflater, parent, false)

        return AccountViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {

        with(holder.binding) {
            tvAccountName.text = accountSearchList[position].personaname
            tvAccountId.text = accountSearchList[position].account_id.toString()
            Glide.with(holder.binding.root)
                .load(accountSearchList[position].avatarfull)
                .into(ivAccountAvatar)
        }
        holder.itemView.setOnClickListener {
            listener.onClicked(accountId = accountSearchList[position].account_id.toLong())
        }

    }

    override fun getItemCount(): Int = accountSearchList.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateAccountSearchList(list: List<AccountSearchResponse>) {
        accountSearchList.clear()
        accountSearchList.addAll(list)
        notifyDataSetChanged()
    }


}

fun interface OnItemAccClicked {
    fun onClicked(accountId: Long)
}