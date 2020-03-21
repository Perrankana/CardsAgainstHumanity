package com.pandiandcode.cardsagainsthumanity.di

import android.content.Context
import com.pandiandcode.cardsagainsthumanity.domain.usecases.GetBlackCard

fun getBlackCard(context: Context): GetBlackCard = GetBlackCard(getBlackDeckRepository(context))