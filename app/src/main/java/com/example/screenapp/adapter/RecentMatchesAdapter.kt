package com.example.screenapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.screenapp.databinding.ItemMatchBinding
import com.example.screenapp.models.RecentMatchesResponse
import com.example.screenapp.util.Constants
import com.example.screenapp.util.HeroesData


class RecentMatchesAdapter (var heroesData: HeroesData) : RecyclerView.Adapter<RecentMatchesAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemMatchBinding) : RecyclerView.ViewHolder(binding.root)

    private val recentMatchesList = ArrayList<RecentMatchesResponse>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMatchBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val recentMatch = recentMatchesList[position]

        val kdaString = "${recentMatch.kills}/${recentMatch.deaths}/${recentMatch.assists}"

        val heroInfo = heroesData.getHeroById(recentMatch.hero_id)

        with(holder.binding) {
            tvKda.text = kdaString
            tvHeroName.text = heroInfo.localized_name
            if (recentMatch.player_slot <= 5) {
                if (recentMatch.radiant_win) {
                    tvWinOrLose.text = "Win"
                } else {
                    tvWinOrLose.text = "Lose"
                }
            } else {
                if (!recentMatch.radiant_win) {
                    tvWinOrLose.text = "Win"
                } else {
                    tvWinOrLose.text = "Lose"
                }
            }

            Glide.with(root)
                .load("${Constants.DOTA_API_BASE_URL}${heroInfo.img}")
                .into(ivHeroAvatar)

        }

    }

    override fun getItemCount(): Int = recentMatchesList.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateRecentMatchesList(list: List<RecentMatchesResponse>) {
        recentMatchesList.clear()
        recentMatchesList.addAll(list)
        notifyDataSetChanged()

    }

}