CREATE TABLE BikeService (
    id INT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE Services (
    service_id INT AUTO_INCREMENT PRIMARY KEY,
    bike_service_id INT,
    smallService DECIMAL(10, 2),
    fullService DECIMAL(10, 2),
    repair DECIMAL(10, 2),
    generalMaintenance DECIMAL(10, 2),
    FOREIGN KEY (bike_service_id) REFERENCES BikeService(id)
);

INSERT INTO BikeService (id, name) VALUES (1, 'Rowerowy Raj');
INSERT INTO Services (bike_service_id, smallService, fullService, repair, generalMaintenance) VALUES (1, 50, 120, 80, 60);

INSERT INTO BikeService (id, name) VALUES (2, 'Rowerownia');
INSERT INTO Services (bike_service_id, smallService, fullService, repair, generalMaintenance) VALUES (2, 55, 130, 90, 70);

INSERT INTO BikeService (id, name) VALUES (3, 'BikeFix');
INSERT INTO Services (bike_service_id, smallService, fullService, repair, generalMaintenance) VALUES (3, 60, 140, 85, 65);

INSERT INTO BikeService (id, name) VALUES (4, 'Napraw Rower');
INSERT INTO Services (bike_service_id, smallService, fullService, repair, generalMaintenance) VALUES (4, 50, 125, 75, 55);

INSERT INTO BikeService (id, name) VALUES (5, 'RowerSerwis');
INSERT INTO Services (bike_service_id, smallService, fullService, repair, generalMaintenance) VALUES (5, 53, 135, 95, 68);

INSERT INTO BikeService (id, name) VALUES (6, 'TwojRower');
INSERT INTO Services (bike_service_id, smallService, fullService, repair, generalMaintenance) VALUES (6, 57, 145, 88, 62);

INSERT INTO BikeService (id, name) VALUES (7, 'Rowerowo');
INSERT INTO Services (bike_service_id, smallService, fullService, repair, generalMaintenance) VALUES (7, 52, 130, 92, 66);

INSERT INTO BikeService (id, name) VALUES (8, 'BikeRepair');
INSERT INTO Services (bike_service_id, smallService, fullService, repair, generalMaintenance) VALUES (8, 59, 150, 85, 70);

INSERT INTO BikeService (id, name) VALUES (9, 'RowerSerwis24');
INSERT INTO Services (bike_service_id, smallService, fullService, repair, generalMaintenance) VALUES (9, 60, 160, 90, 75);

INSERT INTO BikeService (id, name) VALUES (10, 'Rower-Expert');
INSERT INTO Services (bike_service_id, smallService, fullService, repair, generalMaintenance) VALUES (10, 55, 140, 78, 64);