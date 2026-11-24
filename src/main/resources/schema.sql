CREATE TABLE IF NOT EXISTS tournaments (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title VARCHAR(80) NOT NULL CHECK (length(trim(title)) > 0),
    discipline VARCHAR(80) NOT NULL CHECK (length(trim(discipline)) > 0),
    format VARCHAR(80) NOT NULL CHECK (length(trim(format)) > 0),
    event_date DATE NOT NULL
);
