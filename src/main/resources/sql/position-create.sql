DROP TABLE IF EXISTS position CASCADE;
CREATE TABLE position(
    position_id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    duty TEXT,
    department INT REFERENCES department(department_id),
    size INT
);