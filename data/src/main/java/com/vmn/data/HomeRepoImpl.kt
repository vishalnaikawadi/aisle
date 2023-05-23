package com.vmn.data

import com.vmn.data.network.HomeService
import com.vmn.data.utils.NetworkManager
import com.vmn.data.utils.map
import com.vmn.domain.entity.NotesEntity
import com.vmn.domain.repo.HomeRepo
import com.vmn.domain.repo.SharedPrefRepo
import com.vmn.domain.utils.Resource
import com.vmn.domain.utils.SharedPrefConstants

class HomeRepoImpl(
    private val service: HomeService,
    private val networkManager: NetworkManager,
    private val sharedPref: SharedPrefRepo
) : HomeRepo {
    override suspend fun getNotesDetails(): Resource<NotesEntity> {
        return if (networkManager.isNetworkAvailable()) {

            try {
                val response =
                    service.getNotesDetails(
                        auth = sharedPref.loadData(SharedPrefConstants.TOKEN, "")
                    )

                response?.let {
                    Resource.Success(it.map())
                } ?: run {
                    Resource.Error("No new data available")
                }

            } catch (ex: Exception) {
                Resource.Unknown
            }

        } else {
            Resource.NoInternet
        }

    }
}