INSERT INTO roles (name)
VALUES ('admin'),
       ('user');

INSERT INTO categories (name)
VALUES ('Beauty'),
       ('House renovation'),
       ('Car Restoration'),
       ('Cleaning');


INSERT INTO users (name, email, password, role_id, city_name, status, is_approved)
-- delete sensitive data (password) -these are required only for tests
VALUES ('Patrycja', 'patrycja@example.com', 'password123', (SELECT id FROM roles WHERE name = 'admin'), 'CityA',
        'TO_REVIEW', TRUE),
       ('Magda', 'magda@example.com', 'password123', (SELECT id FROM roles WHERE name = 'admin'), 'CityB',
        'TO_REVIEW', TRUE),
       ('Marcin', 'marcin@example.com', 'password123', (SELECT id FROM roles WHERE name = 'admin'), 'CityC',
        'USER_REVISION', FALSE),
       ('User1', 'user1@example.com', 'password456', (SELECT id FROM roles WHERE name = 'user'), 'CityD', 'TO_REVIEW',
        TRUE),
       ('User2', 'user2@example.com', 'password456', (SELECT id FROM roles WHERE name = 'user'), 'CityE', 'TO_REVIEW',
        TRUE),
       ('User3', 'user3@example.com', 'password456', (SELECT id FROM roles WHERE name = 'user'), 'CityF',
        'USER_REVISION', FALSE),
       ('User4', 'user4@example.com', 'password456', (SELECT id FROM roles WHERE name = 'user'), 'CityG',
        'USER_REVISION', FALSE);


INSERT INTO contact_details(user_id, street_name, street_number, apart_number, post_code, phone_number, webpage)
VALUES (1, 'Bukowa', '1', '2A', '10001', '+48666111333', 'www.example1.com'),
       (2, 'Akacjowa', '2', '5B', '10002', '+48999555666', 'www.example2.com'),
       (3, 'Świerkowa', '3', '12', '10003', '+48777111222', 'www.example3.com'),
       (4, 'Kaktusowa', '4', '4D', '10004', '+448909809767', 'www.example4.com'),
       (5, 'Pistacjowa', '5', '9C', '10005', '+48515112098', 'www.example5.com'),
       (6, 'Migdałowa', '6', '10E', '10006', '+503505202', 'www.example6.com'),
       (7, 'Fistaszkowa', '7', '7F', '10007', '+444555666', 'www.example7.com');


INSERT INTO images
(file, category_id, description, city_name, user_id, is_approved, approved_by_user_id, approved_date, status)
VALUES ('file1.jpg', 1, 'Description1', 'Warsaw', 1, TRUE, 2, '2023-10-29', 'ACCEPTED'),
       ('file2.jpg', 2, 'Description2', 'Cracow', 2, TRUE, 1, '2023-10-28', 'ACCEPTED'),
       ('file3.jpg', 1, 'Description3', 'Cracow', 3, FALSE, NULL, NULL, 'TO_REVIEW'),
       ('file4.jpg', 2, 'Description4', 'Warsaw', 4, TRUE, 3, '2023-10-27', 'ACCEPTED'),
       ('file5.jpg', 1, 'Description5', 'Cracow', 5, FALSE, NULL, NULL, 'TO_REVIEW'),
       ('file6.jpg', 2, 'Description6', 'Cracow', 6, TRUE, 1, '2023-10-26', 'ACCEPTED'),
       ('file7.jpg', 1, 'Description7', 'Sopot', 7, FALSE, NULL, NULL, 'TO_REVIEW');
