--liquibase formatted sql
--changeSet <postgres>:<create-owners-table>

create table owners
(
    id bigserial
        primary key
);

alter table owners
    owner to postgres;

--rollback DROP TABLE owners;