CREATE TABLE IF NOT EXISTS accident (
  id serial primary key,
  name text,
  text text,
  address text,
  accident_type_id int references accident_type(id),
  rule_id int(3)[] references rule(id)
);