
CREATE TABLE Client (
    clientid INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phoneNumber INT NOT NULL
);

CREATE TABLE RequestOrder (
    orderId INT AUTO_INCREMENT PRIMARY KEY,
    service VARCHAR(255) NOT NULL,
    bikeModel VARCHAR(255)NOT NULL,
    client VARCHAR(255)NOT NULL
);

CREATE TABLE Services (
    serviceId INT AUTO_INCREMENT PRIMARY KEY,
    smallservice INT,
    fullservice INT,
    repairType VARCHAR(255),
    repair INT
);

CREATE TABLE Bike (
    bikeId INT AUTO_INCREMENT PRIMARY KEY,
    brand VARCHAR(255) NOT NULL,
    modelType VARCHAR(255) NOT NULL,
    serialNumber INT NOT NULL
);

CREATE TABLE Mechanic (
    mechanicId INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    user_name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
)