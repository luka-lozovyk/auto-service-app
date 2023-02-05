--liquibase formatted sql
--changeSet <postgres>:<create-orders-table>

create table orders
(
    id                  bigserial
        primary key,
    finish_date         timestamp,
    order_status        varchar(255),
    problem_description varchar(255),
    start_date          timestamp,
    total_price         numeric(19, 2),
    car_id              bigint
        constraint fkd2p23ixwrrt395glgi9nnbj23
            references cars
);

alter table orders
    owner to postgres;

--rollback DROP TABLE orders;

