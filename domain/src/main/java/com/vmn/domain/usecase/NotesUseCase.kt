package com.vmn.domain.usecase

import com.vmn.domain.entity.NotesEntity
import com.vmn.domain.repo.HomeRepo
import com.vmn.domain.utils.Resource

interface NotesUseCase {
    suspend fun getNotesDetails(
    ): Resource<NotesEntity>
}

class NotesUseCaseImpl(
    private val repo: HomeRepo
) : NotesUseCase {
    override suspend fun getNotesDetails(): Resource<NotesEntity> {
        return repo.getNotesDetails()
    }
}