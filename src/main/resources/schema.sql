DROP TABLE IF EXISTS CUSTOMER;
DROP TABLE IF EXISTS ADDRESS;
DROP TABLE IF EXISTS HOLIDAY;
DROP TABLE IF EXISTS BOOKING;

CREATE TABLE ADDRESS (
  address_id INT AUTO_INCREMENT PRIMARY KEY,
  line1 VARCHAR(250) NOT NULL,
  line2 VARCHAR(250) NULL,
  line3 VARCHAR(250) NULL,
  postcode  VARCHAR(250) NOT NULL,
  country VARCHAR(250) NOT NULL
);


CREATE TABLE CUSTOMER (
  customer_id INT AUTO_INCREMENT PRIMARY KEY,
  age INT NOT NULL,
  name VARCHAR(250) NOT NULL,
  email VARCHAR(250) DEFAULT NULL,
  address_id INT NOT NULL
);


CREATE TABLE HOLIDAY (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  description VARCHAR(250) NOT NULL,
  minimum_age INT NOT NULL,
  country VARCHAR(250) NOT NULL,
  price DOUBLE DEFAULT NULL,
  flights_included VARCHAR(250),
  rating INT
);


CREATE TABLE BOOKING (
  booking_reference VARCHAR(250) PRIMARY KEY,
  customer_id INT NOT NULL,
  holiday_id INT NOT NULL,
  booking_date_time TIMESTAMP(9) NULL,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  booking_status VARCHAR(250) NOT NULL
);


