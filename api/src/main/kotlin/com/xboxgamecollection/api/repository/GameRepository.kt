package com.xboxgamecollection.api.repository

import com.xboxgamecollection.api.model.Game
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface GameRepository : JpaRepository<Game, Long> {
    @Query("SELECT g FROM Game g WHERE lower(g.title) LIKE lower(concat('%', :search, '%')) AND lower(g.genre) LIKE lower(concat('%', :genre, '%'))")
    fun search(@Param("search") search: String, @Param("genre") genre: String): List<Game>
}
