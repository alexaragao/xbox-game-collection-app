package com.xboxgamescollection.api.repository

import com.xboxgamescollection.api.model.Game
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GameRepository : JpaRepository<Game, Long>
