CREATE TYPE gender AS enum ('MALE', 'FEMALE');

CREATE TABLE employee (
    employee_id SERIAL PRIMARY KEY,
    first_name varchar (30),
    last_name varchar (30),
    department_id integer,
    job_title varchar (100),
    gender gender,
    date_of_birth date
);
