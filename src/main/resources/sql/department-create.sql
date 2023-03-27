DROP TABLE IF EXISTS department CASCADE;
CREATE TABLE department (
    department_id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    head_department INT REFERENCES department(department_id),
    director INT REFERENCES worker(worker_id)
);