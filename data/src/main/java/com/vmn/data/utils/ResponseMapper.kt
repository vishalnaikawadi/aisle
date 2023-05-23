package com.vmn.data.utils

import com.vmn.data.model.GeneralInformationModel
import com.vmn.data.model.NotesModel
import com.vmn.data.model.OtpVerificationModel
import com.vmn.data.model.PhoneVerificationModel
import com.vmn.domain.entity.*


fun PhoneVerificationModel.map() = PhoneVerificationEntity(
    status = status ?: false
)

fun OtpVerificationModel.map() = OtpVerificationEntity(
    token = token.orEmpty()
)

fun NotesModel.map() = NotesEntity(
    invites = InvitesEntity(
        pendingInvitationsCount = invites?.pendingInvitationsCount ?: 0,
        profiles = invites?.profiles?.map {
            InviteProfileEntity(
                approvedTime = it.approvedTime ?: 0.0,
                disapprovedTime = it.disapprovedTime ?: 0.0,
                generalInformation = it.generalInformation?.map(),
                hasActiveSubscription = it.hasActiveSubscription ?: false,
                photos = it.photos?.map { pm ->
                    PhotoEntity(
                        photo = pm.photo.orEmpty(),
                        selected = pm.selected ?: false,
                        status = pm.status.orEmpty()
                    )
                }.orEmpty(),
                verificationStatus = it.verificationStatus.orEmpty(),
            )
        }.orEmpty(),
        totalPages = invites?.totalPages ?: 0
    ),
    likes = LikesEntity(
        canSeeProfile = likes?.canSeeProfile ?: false,
        likesReceivedCount = likes?.likesReceivedCount ?: 0,
        profiles = likes?.profiles?.map {
            LikesProfileEntity(
                avatar = it.avatar.orEmpty(),
                firstName = it.firstName.orEmpty()
            )
        }.orEmpty(),
    )
)

private fun GeneralInformationModel.map() = GeneralInformationEntity(
    age = age ?: 0,
    dateOfBirth = dateOfBirth.orEmpty(),
    firstName = firstName.orEmpty(),
    gender = gender.orEmpty(),
    height = height ?: 0,
)