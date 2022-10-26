CREATE DATABASE "beabus" CHARACTER SET utf8mb4
USE beabus ;

CREATE TABLE user
(
    user_sn INT NOT NULL AUTO_INCREMENT,
    user_id VARCHAR(20) PRIMARY KEY,
    user_password VARCHAR(40),
    user_name VARCHAR(10),
    email VARCHAR(50),
    role VARCHAR(10)

);

CREATE DATABASE "route" CHARACTER SET utf8mb4
USE route ;

CREATE TABLE bus_700
(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    station_name VARCHAR(30)
);

