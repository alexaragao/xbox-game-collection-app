CREATE TABLE games (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid() NOT NULL,
    title VARCHAR(255) UNIQUE NOT NULL,
    description TEXT NOT NULL,
    publisher VARCHAR(255) NOT NULL,
    developer VARCHAR(255) NOT NULL,
    genre VARCHAR(255) NOT NULL,
    boxart_url VARCHAR(255) NOT NULL,
    cover_url VARCHAR(255) NOT NULL,
    rewards_url VARCHAR(255) NOT NULL,
    is_physical BOOLEAN NOT NULL,
    is_digital BOOLEAN NOT NULL,
    release_date DATE NOT NULL,
    created_at TIMESTAMP DEFAULT now() NOT NULL,
    updated_at TIMESTAMP DEFAULT now() NOT NULL
);
