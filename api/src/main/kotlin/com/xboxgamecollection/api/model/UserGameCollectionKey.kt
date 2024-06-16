package com.xboxgamecollection.api.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class UserGameCollectionKey(
    @Column(name = "user_id", nullable = false)
    var userId: String,

    @Column(name = "game_id", nullable = false)
    var gameId: String
) : Serializable
