/*CREATE TABLE Client (
    id int PRIMARY KEY,
    name varchar(50)
)

CREATE TABLE Manager (
    no int PRIMARY KEY,
    label varchar(50)
)*/
/*DROP TABLE IF EXISTS Client;*/
CREATE TABLE Client
(
    id bigint
        constraint client_pk
            primary key,
    name varchar(255)
);
/*DROP TABLE IF EXISTS Manager;*/
CREATE TABLE Manager
(
    no bigint
        constraint manager_pk
            primary key,
    label varchar(255),
    param1 varchar(255)
);

/*DROP SEQUENCE IF EXISTS seq;*/
CREATE SEQUENCE seq
    INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;
ALTER TABLE seq
    OWNER TO postgres;
