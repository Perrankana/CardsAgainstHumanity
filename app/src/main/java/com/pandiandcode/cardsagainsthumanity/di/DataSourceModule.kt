package com.pandiandcode.cardsagainsthumanity.di

import android.content.Context
import com.pandiandcode.cardsagainsthumanity.localdatasource.BlackCardDeckDataSource
import com.pandiandcode.cardsagainsthumanity.localdatasource.WhiteCardDeckDataSource
import com.pandiandcode.helpers.FileJSONReader

fun getBlackDeckDataSource(context: Context): BlackCardDeckDataSource = BlackCardDeckDataSource(
    FileJSONReader(context)
)

fun getWhiteDeckDataSource(context: Context): WhiteCardDeckDataSource = WhiteCardDeckDataSource(
    FileJSONReader(context)
)