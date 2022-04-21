
CREATE TABLE patient (
                id INT AUTO_INCREMENT NOT NULL,
                first_name VARCHAR(100) NOT NULL,
                last_name VARCHAR(100) NOT NULL,
                date_of_birth DATE,
                sex VARCHAR(3),
                home_address VARCHAR(50),
                phone VARCHAR(20),
                PRIMARY KEY (id)
);
