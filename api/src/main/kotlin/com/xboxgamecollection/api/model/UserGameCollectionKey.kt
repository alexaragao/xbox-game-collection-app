package com.xboxgamecollection.api.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable
import java.util.UUID

@Embeddable
data class UserGameCollectionKey(
    @Column(name = "user_id", nullable = false)
    var userId: UUID,

    @Column(name = "game_id", nullable = false)
    var gameId: UUID
) : Serializable
