CREATE TABLE game_barcodes (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid() NOT NULL,
    description VARCHAR(100),
    upc CHAR(20) UNIQUE NOT NULL,
    game_id UUID NOT NULL,
    FOREIGN KEY (game_id) REFERENCES games(id)
);
