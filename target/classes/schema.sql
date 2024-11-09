
CREATE TABLE Client (
    uniqId INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    phoneNumber INT NOT NULL
);

CREATE TABLE RequestOrder (
    id INT AUTO_INCREMENT PRIMARY KEY,
    mechanic VARCHAR(255) NOT NULL,
    service VARCHAR(255) NOT NULL,
    bikeModel VARCHAR(255) NOT NULL,
    uniqId INT NOT NULL
);

CREATE TABLE Services (
    serviceId INT AUTO_INCREMENT PRIMARY KEY,
    smallservice INT,
    fullservice INT,
    repair INT
);

CREATE TABLE Bike (
    id INT AUTO_INCREMENT PRIMARY KEY,
    bike VARCHAR(255) NOT NULL,
    modelType VARCHAR(255) NOT NULL,
    serialNumber INT NOT NULL
);