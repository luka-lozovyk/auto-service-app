--liquibase formatted sql
--changeSet <postgres>:<create-cars-table>

create table cars
(
    id                  bigserial
        primary key,
    manufacturer        varchar(255),
    model               varchar(255),
    registration_number varchar(255),
    year                integer not null,
    owner_id            bigint
        constraint fkhcsx2hgskre1qwetp67r7qfr
            references owners
);

alter table cars
    owner to postgres;

--rollback DROP TABLE cars;

