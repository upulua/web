DROP TABLE IF EXISTS history CASCADE;
CREATE TABLE history (
    history_id SERIAL PRIMARY KEY,
    worker_id INT REFERENCES worker(worker_id),
    position_id INT REFERENCES position(position_id),
    start_date TIMESTAMP NOT NULL,
    finish_date TIMESTAMP
);
--
-- SELECT * FROM worker JOIN department ON worker.worker_id = department.director
-- JOIN position ON department.department_id = position.department;
