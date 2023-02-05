--liquibase formatted sql
--changeSet <postgres>:<create-orders-spare-parts-table>

create table orders_spare_parts
(
    order_id      bigint not null
        constraint fkk883nh31tgkoo1m1oj5j3y2a
            references orders,
    spare_part_id bigint not null
        constraint fk2laxwu11t7x53wcwgcledgy0v
            references spare_parts
);

alter table orders_spare_parts
    owner to postgres;

--rollback DROP TABLE orders_spare_parts;