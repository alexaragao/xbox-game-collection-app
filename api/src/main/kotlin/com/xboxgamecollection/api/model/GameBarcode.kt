package com.xboxgamecollection.api.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.UuidGenerator
import java.util.UUID

@Entity
@Table(name = "game_barcodes")
data class GameBarcode(
    @Id
    @UuidGenerator
    @ColumnDefault("gen_random_uuid()")
    val id: UUID = UUID.randomUUID(),

    @Column(name = "description", nullable = true)
    val description: String,

    @Column(name = "upc", unique = true, nullable = false)
    val upc: String,

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    val game: Game
)
