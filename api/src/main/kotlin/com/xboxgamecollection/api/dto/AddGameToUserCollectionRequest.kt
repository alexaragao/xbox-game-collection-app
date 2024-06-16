package com.xboxgamecollection.api.dto

data class AddGameToUserCollectionRequest(
    val gameId: String,
    val notes: String
)
