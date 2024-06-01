package com.xboxgamecollection.api.repository

import com.xboxgamecollection.api.model.Game
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GameRepository : JpaRepository<Game, Long>
