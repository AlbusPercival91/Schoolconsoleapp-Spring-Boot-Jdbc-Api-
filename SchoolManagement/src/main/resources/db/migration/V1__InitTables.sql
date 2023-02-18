DROP table if EXISTS movie;

CREATE TABLE movie (
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    release_date DATE,
    unique (name)
);
