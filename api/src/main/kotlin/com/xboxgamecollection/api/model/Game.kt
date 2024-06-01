package com.xboxgamecollection.api.model

import java.time.LocalDateTime
import jakarta.persistence.*

@Entity
@Table(name = "games")
data class Game(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long = 0,

    @Column(name = "title", nullable = false)
    val title: String,

    @Column(name = "description", nullable = false)
    val description: String,

    @Column(name = "publisher", nullable = false)
    val publisher: String,

    @Column(name = "developer", nullable = false)
    val developer: String,

    @Column(name = "genre", nullable = false)
    val genre: String,

    @Column(name = "boxart_url", nullable = false)
    val boxArtUrl: String,

    @Column(name = "cover_url", nullable = false)
    val coverUrl: String,

    @Column(name = "rewards_url", nullable = false)
    val rewardsUrl: String,

    @Column(name = "is_physical", nullable = false)
    val isPhysical: Boolean,

    @Column(name = "is_digital", nullable = false)
    val isDigital: Boolean,

    @Column(name = "release_date", nullable = false)
    val releaseDate: LocalDateTime,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime = LocalDateTime.now()
)
