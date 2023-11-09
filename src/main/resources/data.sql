INSERT INTO roles (name)
VALUES ('admin'),
       ('user');

INSERT INTO categories (name)
VALUES ('Beauty'),
       ('House renovation'),
       ('Car Restoration'),
       ('Cleaning');


INSERT INTO users (name, email, password, role_id, city_name, avatar, about_me, status, is_approved,
                   approved_by_user_id,
                   approved_date, contact_id)
VALUES ('Patrycja', 'patrycja@example.com', 'password123', (SELECT id FROM roles WHERE name = 'admin'), 'CityA',
        'https://cdn.logojoy.com/wp-content/uploads/2018/05/01104800/1050.png', 'About Patrycja', 'TO_REVIEW', TRUE,
        NULL, NULL,
        (SELECT id FROM contact_details WHERE user_id = 1)),
       ('Magda', 'magda@example.com', 'password123', (SELECT id FROM roles WHERE name = 'admin'), 'CityB',
        'https://www.logodee.com/wp-content/uploads/2020/03/Modern-Company-Logo-Design-Ideas.jpg', 'About Magda',
        'TO_REVIEW', TRUE, NULL, NULL,
        (SELECT id FROM contact_details WHERE user_id = 2)),
       ('Marcin', 'marcin@example.com', 'password123', (SELECT id FROM roles WHERE name = 'admin'), 'CityC',
        'https://1000logos.net/wp-content/uploads/2022/10/Noodles-and-Company-Logo-1995.png', 'About Marcin',
        'USER_REVISION', FALSE, NULL, NULL,
        (SELECT id FROM contact_details WHERE user_id = 3)),
       ('User1', 'user1@example.com', 'password456', (SELECT id FROM roles WHERE name = 'user'), 'CityD',
        'https://www.thelogocreative.co.uk/wp-content/uploads/Mobilunity-Logo-Design-min.jpg', 'About User1',
        'TO_REVIEW', TRUE, NULL, NULL,
        (SELECT id FROM contact_details WHERE user_id = 4)),
       ('User2', 'user2@example.com', 'password456', (SELECT id FROM roles WHERE name = 'user'), 'CityE',
        'https://www.newdesigngroup.ca/ndgcnt/uploads/2014/11/Caterpillar_logo.png', 'About User2', 'TO_REVIEW', TRUE,
        NULL, NULL,
        (SELECT id FROM contact_details WHERE user_id = 5)),
       ('User3', 'user3@example.com', 'password456', (SELECT id FROM roles WHERE name = 'user'), 'CityF',
        'https://cdn.dribbble.com/users/4504621/screenshots/14791146/media/96389471f11f8aad56a7d578d6573711.jpg',
        'Nasza firma, Dom-Odnova, to esencja rodzinnej pasji do renowacji i odrodzenia piękna starych domów, nurt, który płynie w naszych żyłach od pokoleń. Historia naszego przedsiębiorstwa sięga początków dwudziestego wieku, kiedy to pradziadek Antoni, z zawodu cieśla i mistrz stolarski, rozpoczął przygodę z odbudową zniszczonych dworków. Jego praca, pełna zaangażowania i miłości do detalu, szybko zdobyła uznanie wśród lokalnej społeczności.

        Po latach pałeczka przekazywana była z ojca na syna, aż dotarła do mnie – prawnuka Antoniego. Współcześnie, Dom-Odnova łączy tradycyjne metody rzemieślnicze z nowoczesnymi technologiami, by przywracać dawny blask budynkom z duszą. Każdy projekt traktujemy jak dzieło sztuki, dokładając wszelkich starań, aby odzwierciedlić w nim zarówno historię murów, jak i osobowość ich mieszkańców.

        Nasza praca to nie tylko renowacja – to dialog z przeszłością i pieczołowite tworzenie przestrzeni, która będzie służyć kolejnym pokoleniom. Dumni z naszego dziedzictwa, z zapałem wyruszamy naprzeciw nowym wyzwaniom, pamiętając o rzemiośle naszych przodków i przekazując tę pasję dalej. Dom-Odnova to więcej niż firma – to opowieść o domach, które dzięki naszej pracy mogą opowiadać własne historie.',

        'USER_REVISION', FALSE, NULL, NULL,
        (SELECT id FROM contact_details WHERE user_id = 6)),
       ('User4', 'user4@example.com', 'password456', (SELECT id FROM roles WHERE name = 'user'), 'CityG',
        'ahttps://cdn1.designhill.com/uploads/personal_designs/thumbs/a6d4072bf8723f18ec58bcd3c4a64a97-0c8ed5efd029f7ebd92af130517aac1816750927963243.jpg?ver=2.12.64',
        'About User4', 'USER_REVISION', FALSE, NULL, NULL,
        (SELECT id FROM contact_details WHERE user_id = 7));



INSERT INTO contact_details (user_id, street_name, street_number, apart_number, post_code, city_name, phone_number,
                             webpage)
VALUES (1, 'Bukowa', '1', '2A', '10001', 'Warsaw', '+48666111333', 'www.example1.com'),
       (2, 'Akacjowa', '2', '5B', '10002', 'Cracow', '+48999555666', 'www.example2.com'),
       (3, 'Świerkowa', '3', '12', '10003', 'Poznan', '+48777111222', 'www.example3.com'),
       (4, 'Kaktusowa', '4', '4D', '10004', 'Warsaw', '+448909809767', 'www.example4.com'),
       (5, 'Pistacjowa', '5', '9C', '10005', 'Gdansk', '+48515112098', 'www.example5.com'),
       (6, 'Migdałowa', '6', '10E', '10006', 'Warsaw', '+503505202', 'www.example6.com'),
       (7, 'Fistaszkowa', '7', '7F', '10007', 'Cracow', '+444555666', 'www.example7.com');


-- INSERT INTO images
-- (file, category_id, description, city_name, user_id, is_approved, approved_by_user_id, approved_date, status)
-- VALUES ('https://i.insider.com/5f15db39f34d0566ad407306?width=700', 1, 'Description1', 'Warsaw', 1, TRUE, 2,
--         '2023-10-29', 'ACCEPTED'),
--        ('https://thinkwood-wordpress.s3.amazonaws.com/wp-content/uploads/2021/09/22160454/ThinkWood_BeforeAfter-LivingRoom-01.png',
--         2, 'Description2', 'Cracow', 2, TRUE, 1, '2023-10-28', 'ACCEPTED'),
--        ('https://www.younghouselove.com/wp-content/uploads/2018/09/Beach-House-Before-After-Doorway-Side-By-Side-650x457.jpg',
--         1, 'Description3', 'Cracow', 3, FALSE, NULL, NULL, 'TO_REVIEW'),
--        ('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRu5DIHmy951O9FLSLPNdix22i7SfDeijyeNw&usqp=CAU', 2,
--         'Description4', 'Warsaw', 4, TRUE, 3, '2023-10-27', 'ACCEPTED'),
--        ('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRgn90OZSJ7hUaVV67326jeg_f0cYwrS2FFVw&usqp=CAU', 1,
--         'Description5', 'Cracow', 5, FALSE, NULL, NULL, 'TO_REVIEW'),
--        ('https://i.pinimg.com/originals/7c/64/6c/7c646cc9678ad5788f275dfa5f53084d.jpg', 2, 'Description6', 'Cracow', 6,
--         TRUE, 1, '2023-10-26', 'ACCEPTED'),
--        ('https://i0.wp.com/detourdetroiter.com/wpcom-142100448/wp-content/uploads/2020/09/north-end-reno-1-1.jpg?fit=1265%2C838&ssl=1',
--         1, 'Description7', 'Sopot', 7, FALSE, NULL, NULL, 'TO_REVIEW');
