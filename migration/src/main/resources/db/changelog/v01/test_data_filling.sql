INSERT INTO address
values (2, 'building', 'city', 'street');
INSERT INTO couriers
values (4, 22, 'name', '89200000001', 'FREE');
INSERT INTO customers
values (5, 4, 'name', 2);
INSERT INTO restaurants
values (6, 5, 'name', 2, 222);
INSERT INTO restaurant_menu_items
values (7, 'description', 'name', 5000, '3333', 6);
INSERT INTO orders
values (8, 'CUSTOMER_CREATED', '2023-10-10 17:31:53', 4, 5, 6);
INSERT INTO order_items
values (9, 'description', 'name', 5000, 5, 8, 7);