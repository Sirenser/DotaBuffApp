package com.example.screenapp.util

import com.example.screenapp.models.HeroResponse

// Этот обьект нужен для того чтобы хранить общую инфу о героях
// и чтобы была возможность тянуть эту инфу из всех классов, так как это общая и статичная инфа

object HeroesList {

    private val heroesInfoList = ArrayList<HeroResponse>()

    fun getHeroById(heroId: Int): HeroResponse {
        heroesInfoList.forEach {
            if (it.hero_id == heroId)
                return it
        }
        return heroesInfoList[0]
    }

    fun updateList(list: List<HeroResponse>) {
        heroesInfoList.clear()
        heroesInfoList.addAll(list)
    }

    fun checkForEmpty(): Boolean {
        println(heroesInfoList.isEmpty())
        return heroesInfoList.isEmpty()
    }


}