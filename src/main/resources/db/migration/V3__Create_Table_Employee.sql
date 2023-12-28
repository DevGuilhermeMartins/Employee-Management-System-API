CREATE TABLE tb_employee (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    birthday DATE NOT NULL,
    salary DOUBLE PRECISION NOT NULL,
    admission_date DATE,
    email VARCHAR(255) NOT NULL,
    department_id BIGINT REFERENCES tb_department(id),
    position_id BIGINT REFERENCES tb_position(id)
);