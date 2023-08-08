package com.example.screenapp.util

import com.example.screenapp.models.HeroResponse


class HeroesData {

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