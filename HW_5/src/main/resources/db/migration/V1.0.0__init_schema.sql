/*CREATE TABLE Client (
    id int PRIMARY KEY,
    name varchar(50)
)

CREATE TABLE Manager (
    no int PRIMARY KEY,
    label varchar(50)
)*/
CREATE TABLE Client
(
    id int
        constraint client_pk
            primary key,
    name varchar(50)
);

CREATE TABLE Manager
(
    no int
        constraint manager_pk
            primary key,
    label varchar(50),
    param1 varchar(20)
);

CREATE SEQUENCE seq
    INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;
ALTER TABLE seq
    OWNER TO postgres;
