INSERT INTO ADDRESS (line1, line2, line3, postcode, country)
VALUES ('address1', 'address1', 'address1', 'PE90HL', 'UK');

INSERT INTO ADDRESS (line1, line2, line3, postcode, country)
VALUES ('address2', 'address2', 'address2', 'WA20HL', 'UK');

INSERT INTO CUSTOMER (age, name, email, address_id)
VALUES (30, 'Thomas', 'thomas@thomas.com' , 1);

INSERT INTO CUSTOMER (age, name, email, address_id)
VALUES (18, 'Anthony', 'anthony@thomas.com' , 2);

INSERT INTO CUSTOMER (age, name, email, address_id)
VALUES (18, 'Mark', 'Mark@thomas.com' , 2);

INSERT INTO HOLIDAY (name, description, minimum_age, country, price, flights_included, rating)
VALUES ('The young blast!', 'An amazing holiday', 18, 'ITALY', 299.50, true, 4);

INSERT INTO HOLIDAY (name, description, minimum_age, country, price, flights_included, rating)
VALUES ('The old blast!', 'An amazing holiday', 55, 'UNITED_KINGDOM', 599.99, true, 7);

INSERT INTO HOLIDAY (name, description, minimum_age, country, price, flights_included, rating)
VALUES ('The spain blast!', 'An amazing holiday', 18, 'SPAIN', 599.00, false, 7);

INSERT INTO BOOKING (booking_reference, customer_id, holiday_id, booking_date_time, start_date, end_date, booking_status)
VALUES ('4534534', 1, 1, '2019-06-26 18:47:52', '2019-06-26', '2019-06-26', 'ACTIVE');

INSERT INTO BOOKING (booking_reference, customer_id, holiday_id, booking_date_time, start_date, end_date, booking_status)
VALUES ('5521534', 1, 2, '2019-06-26 18:47:52', '2019-06-26', '2019-06-26', 'CANCELLED');

