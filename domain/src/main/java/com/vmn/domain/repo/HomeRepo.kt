package com.vmn.domain.repo

import com.vmn.domain.entity.NotesEntity
import com.vmn.domain.utils.Resource

interface HomeRepo {

    suspend fun getNotesDetails(): Resource<NotesEntity>
}