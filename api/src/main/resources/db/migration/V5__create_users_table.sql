CREATE TABLE users (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    nickname VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT now() NOT NULL,
    updated_at TIMESTAMP DEFAULT now() NOT NULL
);
