package com.pandiandcode.cardsagainsthumanity.di

import android.content.Context
import com.pandiandcode.cardsagainsthumanity.domain.BlackDeckRepository
import com.pandiandcode.cardsagainsthumanity.domain.BlackDeckRepositoryImp
import com.pandiandcode.cardsagainsthumanity.domain.WhiteDeckRepository
import com.pandiandcode.cardsagainsthumanity.domain.WhiteDeckRepositoryImp

fun getBlackDeckRepository(context: Context): BlackDeckRepository = BlackDeckRepositoryImp(
    getBlackDeckDataSource(context))

fun getWhitekDeckRepository(context: Context): WhiteDeckRepository = WhiteDeckRepositoryImp(
    getWhiteDeckDataSource(context))