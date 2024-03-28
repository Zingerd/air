CREATE TABLE air_company (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    company_type VARCHAR(255) NOT NULL,
    founded_at DATE NOT NULL
);

CREATE TABLE airplane (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    factory_serial_number VARCHAR(255) NOT NULL,
    air_company_id INT NOT NULL,
    number_of_flights INT NOT NULL,
    flight_distance FLOAT NOT NULL,
    fuel_capacity FLOAT NOT NULL,
    type VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (air_company_id) REFERENCES air_company(ID)
);

CREATE TABLE flight (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    flight_status VARCHAR(255) NOT NULL,
    air_company_id INT NOT NULL,
    airplane_id INT NOT NULL,
    departure_country VARCHAR(255) NOT NULL,
    destination_country VARCHAR(255) NOT NULL,
    distance FLOAT NOT NULL,
    estimated_flight_time TIME NOT NULL,
    started_at DATETIME,
    ended_at DATETIME,
    delay_started_at DATETIME,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (air_company_id) REFERENCES air_company(ID),
    FOREIGN KEY (airplane_id) REFERENCES airplane(ID)
);

INSERT INTO air_company (name, company_type, founded_at)
VALUES ('ABY', 'Air', '2000-01-01');
INSERT INTO air_company (name, company_type, founded_at)
VALUES ('FLY', 'Air', '2020-01-01');
INSERT INTO air_company (name, company_type, founded_at)
VALUES ('WOW', 'Air', '2024-01-01');

INSERT INTO airplane (name, factory_serial_number, air_company_id, number_of_flights, flight_distance, fuel_capacity, type, created_at)
VALUES ('Boeing 747', 'XXS234', 1, 100, 10000, 1000, 'passengers', CURRENT_TIMESTAMP);

INSERT INTO airplane (name, factory_serial_number, air_company_id, number_of_flights, flight_distance, fuel_capacity, type, created_at)
VALUES ('Bleriot XI', 'AAWE34', 2, 100, 10000, 1000, 'passengers', CURRENT_TIMESTAMP);

INSERT INTO airplane (name, factory_serial_number, air_company_id, number_of_flights, flight_distance, fuel_capacity, type, created_at)
VALUES ('Airbus A380', 'KKE321', 3, 100, 10000, 1000, 'passengers', CURRENT_TIMESTAMP);

INSERT INTO airplane (name, factory_serial_number, air_company_id, number_of_flights, flight_distance, fuel_capacity, type, created_at)
VALUES ('Bell X-1', 'XXAA24', 1, 100, 10000, 1000, 'passengers', CURRENT_TIMESTAMP);

INSERT INTO flight (flight_status, air_company_id, airplane_id,departure_country, destination_country, distance,
                    estimated_flight_time, started_at, ended_at, delay_started_at, created_at)
VALUES ('ACTIVE', 1, 4, 'USA', 'UKR', 1000, '06:00:00',
        '2024-03-26 08:00:00', '2024-03-26 10:00:00', NULL, CURRENT_TIMESTAMP);

INSERT INTO flight (flight_status, air_company_id, airplane_id, departure_country, destination_country,
                    distance, estimated_flight_time, started_at, ended_at, delay_started_at, created_at)
VALUES ('ACTIVE', 2, 2, 'USA', 'UK', 1000,'02:00:00',
        '2024-03-27 08:00:00', '2024-03-27 10:00:00', NULL, CURRENT_TIMESTAMP);

INSERT INTO flight (flight_status, air_company_id, airplane_id, departure_country, destination_country,
                    distance, estimated_flight_time, started_at, ended_at, delay_started_at, created_at)
VALUES ('COMPLETED', 1, 1, 'KK', 'AA', 1000, '02:00:00',
        '2024-03-27 08:00:00', '2024-03-27 11:00:00', NULL, CURRENT_TIMESTAMP);
