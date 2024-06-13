package com.xboxgamecollection.api.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.UuidGenerator
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "users")
data class User(
    @Id
    @UuidGenerator
    @ColumnDefault("gen_random_uuid()")
    val id: UUID = UUID.randomUUID(),

    @Column(name = "nickname", nullable = false)
    val nickname: String,

    @Column(name = "password", nullable = false)
    val password: String,

    @Column(name = "created_at", nullable = false)
    @ColumnDefault("now()")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at", nullable = false)
    @ColumnDefault("now()")
    val updatedAt: LocalDateTime = LocalDateTime.now(),

//    @OneToMany(mappedBy = "users", cascade = [CascadeType.ALL], orphanRemoval = true)
//    val games: List<UserGameCollection> = mutableListOf()
)
