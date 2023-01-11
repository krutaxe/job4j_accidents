CREATE TABLE IF NOT EXISTS accident_rule (
  accident_id int references accident(id),
  rule_id int references rule(id)
);