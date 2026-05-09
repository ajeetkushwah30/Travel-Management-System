create database travel_company;
use travel_company;

-- bus table -----------------------------------
CREATE TABLE bus (
    bus_id INT AUTO_INCREMENT PRIMARY KEY,
    bus_number VARCHAR(50) UNIQUE NOT NULL,
    bus_name VARCHAR(100) NOT NULL,
    bus_type ENUM('AC', 'NON-AC', 'SLEEPER', 'SEMI-SLEEPER') NOT NULL,
    total_seats INT NOT NULL CHECK (total_seats > 0),
    source VARCHAR(100) NOT NULL,
    destination VARCHAR(100) NOT NULL,
    departure_time TIME NOT NULL,
    arrival_time TIME NOT NULL,
    fare DECIMAL(10,2) NOT NULL
);

-- passanger table ------------------------------

CREATE TABLE passenger (
    pass_id INT AUTO_INCREMENT PRIMARY KEY,
    pass_name VARCHAR(100) NOT NULL,
    pass_age INT,
    pass_gender VARCHAR(10),
    pass_phone VARCHAR(15) UNIQUE NOT NULL
);
 alter table passenger modify column pass_gender enum('MALE','FEMALE','OTHER');
-- booking table ----------------------------------
CREATE TABLE booking (
    booking_id INT AUTO_INCREMENT PRIMARY KEY,
    bus_id INT NOT NULL,
    pass_id INT NOT NULL,
    seat_number INT NOT NULL,
    ticket_price DECIMAL(10,2) NOT NULL,
    booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    -- same bus me same seat dubara book na ho
    UNIQUE (bus_id, seat_number),

    -- relations
    FOREIGN KEY (bus_id) REFERENCES bus(bus_id) ON DELETE CASCADE,
    FOREIGN KEY (pass_id) REFERENCES passenger(pass_id) ON DELETE CASCADE
);




