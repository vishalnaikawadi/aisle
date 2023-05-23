package com.vmn.data.network

import com.vmn.data.model.NotesModel
import retrofit2.http.GET
import retrofit2.http.Header

interface HomeService {

    @GET("/V1/users/test_profile_list")
    suspend fun getNotesDetails(
        @Header("Authorization") auth: String
    ): NotesModel?
}