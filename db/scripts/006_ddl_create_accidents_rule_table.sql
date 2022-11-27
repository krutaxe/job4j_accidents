CREATE TABLE accident_rule (
  accident_id int references accident(id),
  rule_id int references rule(id)
);