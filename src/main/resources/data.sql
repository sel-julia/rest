drop table if exists USER;

CREATE TABLE USER (
    id    LONG AUTO_INCREMENT PRIMARY KEY,
    name   VARCHAR(40)  NOT NULL UNIQUE,
    age LONG
);

INSERT INTO USER (name, age) VALUES
('Ivan Ivanov', 23),
('Petr Petrov', 34),
('Andrei Andreev', 12),
('Alexandr Aleksandrov', 65);