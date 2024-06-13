package com.xboxgamecollection.api.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.UuidGenerator
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "games")
data class Game(
    @Id
    @UuidGenerator
    @ColumnDefault("gen_random_uuid()")
    val id: UUID = UUID.randomUUID(),

    @Column(name = "title", unique = true, nullable = false)
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
    val releaseDate: LocalDate,

    @Column(name = "created_at", nullable = false)
    @ColumnDefault("now()")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at", nullable = false)
    @ColumnDefault("now()")
    val updatedAt: LocalDateTime = LocalDateTime.now()
)
