CREATE TABLE users (
  id serial primary key,
  username VARCHAR(50) NOT NULL unique,
  password VARCHAR(100) NOT NULL,
  enabled boolean default true,
  authority_id int not null references authorities(id)
);

insert into users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$XVRaB/knggdPWngKwkYjPO0jJECPKHsjzAoNseW/J4cxvnSu6433',
(select id from authorities where authority = 'ROLE_ADMIN'));