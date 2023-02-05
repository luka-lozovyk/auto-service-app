--liquibase formatted sql
--changeSet <postgres>:<create-spare-parts-table>

create table spare_parts
(
    id    bigserial
        primary key,
    name  varchar(255),
    price numeric(19, 2)
);

alter table spare_parts
    owner to postgres;

--rollback DROP TABLE spare_parts;
