CREATE TABLE IF NOT EXISTS authorities (
  id serial primary key,
  authority VARCHAR(50) NOT NULL unique
);

insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');