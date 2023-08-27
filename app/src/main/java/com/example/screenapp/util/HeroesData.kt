package com.example.screenapp.util

import com.example.screenapp.models.HeroResponse


class HeroesData {

    private val heroesInfoList = ArrayList<HeroResponse>()

    fun getHeroById(heroId: Int): HeroResponse {
        heroesInfoList.forEach {
            if (it.heroId == heroId)
                return it
        }
        return heroesInfoList[0]
    }

    fun updateList(list: List<HeroResponse>) {
        heroesInfoList.clear()
        heroesInfoList.addAll(list)
    }

    fun isEmpty(): Boolean {
        return heroesInfoList.isEmpty()
    }
}