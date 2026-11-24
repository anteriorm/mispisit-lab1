CREATE TABLE IF NOT EXISTS tournaments (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title VARCHAR(80) NOT NULL CHECK (length(trim(title)) > 0),
    discipline VARCHAR(80) NOT NULL CHECK (length(trim(discipline)) > 0),
    format VARCHAR(80) NOT NULL CHECK (length(trim(format)) > 0),
    event_date DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS tournament_notifications (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    tournament_id BIGINT NOT NULL UNIQUE REFERENCES tournaments(id),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    status VARCHAR(16) NOT NULL CHECK (status = 'NEW')
);
