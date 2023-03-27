DROP TABLE IF EXISTS worker CASCADE;
CREATE TABLE worker(
  worker_id SERIAL PRIMARY KEY,
  name TEXT NOT NULL,
  education TEXT,
  start_date TIMESTAMP NOT NULL,
  address TEXT
);