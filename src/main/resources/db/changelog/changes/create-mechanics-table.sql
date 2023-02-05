--liquibase formatted sql
--changeSet <postgres>:<create-mechanics-table>

create table mechanics
(
    id        bigserial
        primary key,
    full_name varchar(255)
);

alter table mechanics
    owner to postgres;

--rollback DROP TABLE mechanics;
