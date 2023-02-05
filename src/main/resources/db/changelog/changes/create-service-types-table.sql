--liquibase formatted sql
--changeSet <postgres>:<create-service-types-table>

create table service_types
(
    id            bigserial
        primary key,
    price         numeric(19, 2),
    salary_status varchar(255),
    mechanic_id   bigint
        constraint fkfp3yrg3gj8m9mo4y8jbd4rfsk
            references mechanics,
    order_id      bigint
        constraint fktpsa31fp3wk6ji73eshrxgow3
            references orders
);

alter table service_types
    owner to postgres;

--rollback DROP TABLE service_types;