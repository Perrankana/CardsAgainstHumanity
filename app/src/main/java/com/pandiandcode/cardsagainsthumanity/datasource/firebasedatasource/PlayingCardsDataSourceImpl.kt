package com.pandiandcode.cardsagainsthumanity.datasource.firebasedatasource

import com.pandiandcode.cardsagainsthumanity.datasource.PlayingCardsDataSource
import java.io.IOException

class PlayingCardsDataSourceImpl(private val api: CahApi = ApiFactory.getApi()) : PlayingCardsDataSource {

    override suspend fun getPlayingCards(): Result<List<PlayingCardsApiData>> = try {
        Result.Success(
            safeApiResult(
                call = { api.getPlayingCards().await() },
                errorMessage = "Error while fetching playing cards"
            )
        )
    } catch (e: Exception) {
        Result.Failed(e)
    }

    override suspend fun putPlayingCards(cards: NewPlayingCardsApiData): Result<Unit> = try {
        Result.Success(
            safeApiResult(
                call = { api.putPlayingCards(cards).await() },
                errorMessage = "Error while saving cards"
            )
        )
    } catch (e: Exception) {
        Result.Failed(e)
    }

    override suspend fun clearCards(): Result<Unit> = when (val result = getPlayingCards()) {
        is Result.Success -> {
            val ids = result.value.map { it._id }
            clearAllCards(ids)
        }
        is Result.Failed -> Result.Failed(result.exception)
    }

    private suspend fun clearAllCards(ids: List<String>): Result<Unit> {
        var failed = false
        var exception: Exception? = null
        ids.forEach { id ->
            val result = deleteCard(id)
            if (result is Result.Failed) {
                exception = result.exception
                failed = true
                return@forEach
            }
        }
        return if (failed) {
            Result.Failed(exception!!)
        } else {
            Result.Success(Unit)
        }
    }


    private suspend fun deleteCard(id: String): Result<Unit> = try {
        Result.Success(
            safeApiResult(
                call = { api.deleteCard(id).await() },
                errorMessage = "Error while deleting card: $id"
            )
        )
    } catch (e: Exception) {
        Result.Failed(e)
    }

    private suspend fun <T : Any> safeApiResult(
        call: suspend () -> retrofit2.Response<T>,
        errorMessage: String
    ): T {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage")
        }
    }

}

sealed class Result<T> {
    class Success<T>(val value: T) : Result<T>()
    class Failed<T>(val exception: Exception) : Result<T>()
}