package com.xboxgamecollection.api.repository

import com.xboxgamecollection.api.model.Game
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface GameRepository : JpaRepository<Game, UUID> {
    @Query("SELECT g FROM Game g WHERE g.title LIKE %:search% AND g.genre = :genre")
    fun search(@Param("search") search: String, @Param("genre") genre: String): List<Game>
}
