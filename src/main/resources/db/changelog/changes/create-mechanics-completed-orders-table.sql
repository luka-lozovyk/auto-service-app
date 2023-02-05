--liquibase formatted sql
--changeSet <postgres>:<create-mechanics-completed-orders-table>

create table mechanics_completed_orders
(
    mechanic_id        bigint not null
        constraint fki6br1ek54g7tr93w5f4q1n8yi
            references mechanics,
    completed_order_id bigint not null
        constraint uk_8dnsihvt515kmr1koqxfndkaa
            unique
        constraint fk7fg6250hlbhpldb34g287xomc
            references orders
);

alter table mechanics_completed_orders
    owner to postgres;

--rollback DROP TABLE mechanics_completed_orders;

