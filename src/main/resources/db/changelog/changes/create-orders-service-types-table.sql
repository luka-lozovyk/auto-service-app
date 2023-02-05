--liquibase formatted sql
--changeSet <postgres>:<create-orders-service-types-table>

create table orders_service_types
(
    order_id        bigint not null
        constraint fkq4wvwvb7jelweu513ti6sn875
            references orders,
    service_type_id bigint not null
        constraint fk8jiqcjd250mwy2qkflwx0be9t
            references service_types
);

alter table orders_service_types
    owner to postgres;

--rollback DROP TABLE orders_service_types;