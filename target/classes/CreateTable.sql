DROP TABLE IF EXISTS CATALOG;
CREATE TABLE CATALOG
(
    ID          INT8 PRIMARY KEY,
    Name        VARCHAR(100),
    Good_Type   VARCHAR(50),
    Price       DOUBLE(15),
    Color       VARCHAR(8),
    Weight      DOUBLE(15),
    isColoring  BOOLEAN,
    Consumption VARCHAR(20),
    Paint_Class VARCHAR(20),
    Grain_Size  DOUBLE(4),
    Concentrate DOUBLE(4)
);

INSERT INTO CATALOG
VALUES (1, 'StoColor Top', 'Краска', '9890.90', '#FFFFFF', '15', true, '0.1-0.2 l/m^2', 'Органическая', null, null);
INSERT INTO CATALOG
VALUES (2, 'StoDecolit MP', 'Штукатурка', '4325.00', '#FFFFFF', '20', true, '0.5-0.7 l/m^2', null, '2.0', null);
INSERT INTO CATALOG
VALUES (3, 'StoPrim Solid', 'Грунтовка', '1290.90', '#FFF8F8', '5', true, '0.1-0.2 l/m^2', null, null, '0.25');
INSERT INTO CATALOG
VALUES (4, 'Кисть (синтетика)', 'Кисть', '890.90', null, null, null, null, null, null, null);

DROP TABLE IF EXISTS STORAGE_LIST;
CREATE TABLE STORAGE_LIST
(
    ID               INT PRIMARY KEY,
    Good_ID          INT8,
    Amount           INT2,
    Manufacturer     VARCHAR(30),
    Manufacture_Date VARCHAR(30),
    Expiration_Date  VARCHAR(30),
    Stock_ID         INT8
);

INSERT INTO STORAGE_LIST
VALUES (11, 1, 5, 'Германия', '10.12.2019', '10.12.2020', 1);
INSERT INTO STORAGE_LIST
VALUES (12, 2, 1, 'Германия', '10.12.2019', '10.12.2020', 1);
INSERT INTO STORAGE_LIST
VALUES (13, 3, 3, 'Германия', '10.12.2019', '10.12.2020', 1);
INSERT INTO STORAGE_LIST
VALUES (14, 4, 10, 'Россия', '09.01.2019', 'бессрочно', 1);

DROP TABLE IF EXISTS SOLD_LIST;
CREATE TABLE SOLD_LIST
(
    ID               INT,
    Good_ID          INT8,
    Amount           INT2,
    Manufacturer     VARCHAR(30),
    Manufacture_Date VARCHAR(30),
    Expiration_Date  VARCHAR(30),
    Stock_ID         INT8,
    Cheque_ID        INT PRIMARY KEY AUTO_INCREMENT
);
