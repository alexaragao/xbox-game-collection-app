CREATE TABLE user_games_collection (
    game_id UUID NOT NULL,
    user_id UUID NOT NULL,
    notes VARCHAR(255),
    created_at TIMESTAMP DEFAULT now() NOT NULL,
    updated_at TIMESTAMP DEFAULT now() NOT NULL,
    PRIMARY KEY (game_id, user_id),
    FOREIGN KEY (game_id) REFERENCES games(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
