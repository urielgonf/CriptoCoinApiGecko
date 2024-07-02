package com.myportfolio.portfoliocritocoinapplication.data.repository.CoinRepositories

import com.myportfolio.portfoliocritocoinapplication.data.Coins.model.CoinDetailModel
import com.myportfolio.portfoliocritocoinapplication.data.Coins.model.CoinModel
import com.myportfolio.portfoliocritocoinapplication.data.Coins.model.CoinProvider
import com.myportfolio.portfoliocritocoinapplication.data.Coins.model.CoinsCache
import com.myportfolio.portfoliocritocoinapplication.data.Coins.network.CoinService
import javax.inject.Inject
class CoinRepository @Inject constructor(
    private val api: CoinService,
    private val coinProvider: CoinProvider,


) {
    private val coinCache = mutableMapOf<String, CoinsCache>()
    private var allCoinsCache: Pair<List<CoinModel>, Long>? = null
    private val cacheDuration = 30 * 1000 // 1 minute in milliseconds

    suspend fun getAllCoins(): List<CoinModel> {
        // Verificar si el caché ya tiene los datos y si sigue siendo válido
        allCoinsCache?.let { (coins, timestamp) ->
            if (System.currentTimeMillis() - timestamp < cacheDuration) {
                return coins
            }
        }

        // Si no está en caché o es inválido, obtener de la API
        val response = api.getCoins()
        allCoinsCache = Pair(response, System.currentTimeMillis())
        coinProvider.coins = response
        return response
    }

    suspend fun getCoinById(id: String): CoinDetailModel? {
        // Verificar si el caché ya tiene los datos y si sigue siendo válido
        coinCache[id]?.let { cachedCoin ->
            if (System.currentTimeMillis() - cachedCoin.timestamp < cacheDuration) {
                return cachedCoin.coinDetail
            }
        }

        // Si no está en caché o es inválido, obtener de la API
        val response = api.getCoinById(id)
        response?.let {
            coinProvider.coin = it
            coinCache[id] = CoinsCache(it, System.currentTimeMillis())
        }
        return response
    }

    // Método para verificar si la caché se ha destruido o expirado
    fun isCacheValid(id: String): Boolean {
        coinCache[id]?.let { cachedCoin ->
            return System.currentTimeMillis() - cachedCoin.timestamp < cacheDuration
        }
        return false
    }

    // Método para destruir la caché manualmente
    fun clearCache() {
        coinCache.clear()
        allCoinsCache = null
    }
}
