CREATE TABLE CITIES
(
    id         int primary key auto_increment not null,
    name       VARCHAR(255) NOT NULL,
    population int check ( population >= 0 )
);

CREATE TABLE CATS
(
    id         INT primary key auto_increment ,
    super_name VARCHAR(255),
    name       VARCHAR(255),
    age        INT,
    id_city int,
    FOREIGN KEY (id_city) references CITIES(id) on delete SET NULL
);

INSERT INTO CITIES (id, name, population)
VALUES (1, 'Kola', 9000);
INSERT INTO CITIES (id, name, population)
VALUES (2, 'Marrakesh', 29000);
INSERT INTO CITIES (id, name, population)
VALUES (3, 'Moscow', 1339000);
INSERT INTO CITIES (id, name, population)
VALUES (4, 'Singapore', 28999000);
INSERT INTO CITIES (id, name, population)
VALUES (5, 'London', 2000000);
INSERT INTO CITIES (id, name, population)
VALUES (6, 'Rome', 4000000);

INSERT INTO CATS (id, super_name, name, age, id_city)
VALUES (1, 'Капитан Америкот', 'Мурзик', 49, 2);
INSERT INTO CATS (id, super_name, name, age, id_city)
VALUES (2, 'Халкот', 'Котопульт', 5, 1);
INSERT INTO CATS (id, super_name, name, age, id_city)
VALUES (3, 'Супермяу', 'Снежок', 7, 1);
INSERT INTO CATS (id, super_name, name, age, id_city)
VALUES (4, 'Аквакот', 'Пухляш', 4, 3);
INSERT INTO CATS (id, super_name, name, age, id_city)
VALUES (5, 'Марсианский Мяухантер', 'Пушок', 2, 3);

INSERT INTO CATS (id, super_name, name, age, id_city)
VALUES (6, 'Свободный от оков', 'Пушок', 2, null);

