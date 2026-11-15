CREATE DATABASE IF NOT EXISTS user_registration;
USE user_registration;

CREATE TABLE IF NOT EXISTS users (
    id            INT PRIMARY KEY AUTO_INCREMENT,
    first_name    VARCHAR(50)  NOT NULL,
    last_name     VARCHAR(50)  NOT NULL,
    gender        VARCHAR(10),
    age           INT,
    phone         VARCHAR(15),
    email         VARCHAR(100),
    continent     VARCHAR(30),
    hobbies       VARCHAR(500),
    photo         VARCHAR(500),
    date_of_birth DATE
);

SELECT * FROM users;


INSERT INTO users (first_name, last_name, gender, age, phone, email, continent, hobbies, photo, date_of_birth) VALUES
('Aarav',  'Sharma',  'Male',   25, '617-555-0142', 'sharma.aa@northeastern.edu',  'Asia',          'Cricket, chess',       NULL, '2001-03-15'),
('Emily',  'Johnson', 'Female', 28, '857-555-0198', 'johnson.em@northeastern.edu', 'North America', 'Painting, hiking',     NULL, '1998-07-22'),
('Lucas',  'Martin',  'Male',   22, '781-555-0123', 'martin.lu@northeastern.edu',  'Europe',        'Football, guitar',     NULL, '2003-11-30'),
('Sofia',  'Garcia',  'Female', 31, '508-555-0167', 'garcia.so@northeastern.edu',  'South America', 'Dancing, photography', NULL, '1995-01-08'),
('Kwame',  'Mensah',  'Male',   26, '339-555-0185', 'mensah.kw@northeastern.edu',  'Africa',        'Basketball, reading',  NULL, '2000-09-05'),
('Mei',    'Tanaka',  'Female', 22, '978-555-0110', 'tanaka.me@northeastern.edu',  'Asia',          'Piano, cooking',       NULL, '2004-05-19');

SELECT * FROM users;