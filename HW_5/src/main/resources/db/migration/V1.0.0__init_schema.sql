/*CREATE TABLE Client (
    id int PRIMARY KEY,
    name varchar(50)
)

CREATE TABLE Manager (
    no int PRIMARY KEY,
    label varchar(50)
)*/
CREATE TABLE "Client"
(
    id int
        constraint client_pk
            primary key,
    name varchar(50)
);

CREATE TABLE "Manager"
(
    no int
        constraint manager_pk
            primary key,
    label varchar(50)
);
