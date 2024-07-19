package com.xboxgamecollection.app.features.userCollection.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AddGameToUserCollectionRequest(
    val gameId: String,
    val notes: String
)
