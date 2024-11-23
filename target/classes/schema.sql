
CREATE TABLE Client (
    clientid INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255),
    phoneNumber INT
);

CREATE TABLE RequestOrder (
    orderId INT AUTO_INCREMENT PRIMARY KEY,
    service VARCHAR(255) ,
    bikeModel VARCHAR(255),
    client VARCHAR(255)
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
    brand VARCHAR(255),
    modelType VARCHAR(255),
    serialNumber INT
);

CREATE TABLE Mechanic (
    mechanicId INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    user_name VARCHAR(255),
    password VARCHAR(255)
)