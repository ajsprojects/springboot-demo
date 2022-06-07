INSERT INTO CUSTOMER (age, name, email, postcode)
VALUES (30, 'Thomas', 'thomas@thomas.com', 'pe90hl');

INSERT INTO HOLIDAY (name, description, minimum_age, country, price, flights_included, rating)
VALUES ('The young blast!', 'An amazing holiday', 18, 'ITALY', 299.00, true, 4);

INSERT INTO HOLIDAY (name, description, minimum_age, country, price, flights_included, rating)
VALUES ('The old blast!', 'An amazing holiday', 55, 'UNITED_KINGDOM', 599.00, true, 7);

INSERT INTO HOLIDAY (name, description, minimum_age, country, price, flights_included, rating)
VALUES ('The spain blast!', 'An amazing holiday', 18, 'SPAIN', 599.00, false, 7);

INSERT INTO BOOKING (booking_reference, customer_id, holiday_id, booking_date_time, start_date, end_date, booking_status)
VALUES ('4534534', 1, 1, '2019-06-26 18:47:52.69', '2019-06-26', '2019-06-26', 'ACTIVE');


