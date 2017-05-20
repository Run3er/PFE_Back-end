-- create global schema (for administrator users)
CREATE SCHEMA IF NOT EXISTS tenants_global;
-- create tenants table inside
CREATE TABLE IF NOT EXISTS tenants_global.tenant
(
  id     UUID                   NOT NULL PRIMARY KEY,
  pseudo CHARACTER VARYING(255) NOT NULL
);
-- populate with tenants
-- INSERT INTO tenants_global.tenant (id, pseudo) VALUES ('43d44eae-cd10-4ffb-97e1-c3e189119659', 'tenant_a');
-- INSERT INTO tenants_global.tenant (id, pseudo) VALUES ('b37bfdf1-1a7f-466e-bcb4-6ebf6d53eb50', 'tenant_b');

-- create mock-intended-only schema (needed for hibernate initialization)
CREATE SCHEMA IF NOT EXISTS default_mock_tenant;

-- create tenant schema (for tenant users) : 43d44eae-cd10-4ffb-97e1-c3e189119659
CREATE SCHEMA IF NOT EXISTS "43d44eae-cd10-4ffb-97e1-c3e189119659";

SET search_path = "43d44eae-cd10-4ffb-97e1-c3e189119659";
-- create users table
CREATE TABLE IF NOT EXISTS user_account
(
  id            UUID        NOT NULL PRIMARY KEY,
  login         VARCHAR(10) NOT NULL,
  password_hash BYTEA       NOT NULL,
  password_salt BYTEA       NOT NULL,
  first_name    VARCHAR(40) NOT NULL,
  last_name     VARCHAR(40) NOT NULL
);
-- populate with user (login:"Frodo"; password:"Baggins")
INSERT INTO user_account (id, login, password_hash, password_salt, first_name, last_name) VALUES
  ('73c60097-4c7c-4c74-a347-5f69fcaef9fb', 'Frodo',
   E'\\x6DB3B66707B40240B7DD71B549AE1536EF6E52C9F0FDCCE781F9AC1EEB574C0B', E'\\x0A2EBE7A87F3504A2C6F8846E7EDA2AF', 'Frodo', 'Baggins');
-- create hibernate sequence
CREATE SEQUENCE IF NOT EXISTS hibernate_sequence
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;

-- create tenant schema (for tenant users) : b37bfdf1-1a7f-466e-bcb4-6ebf6d53eb50
CREATE SCHEMA IF NOT EXISTS "b37bfdf1-1a7f-466e-bcb4-6ebf6d53eb50";

SET search_path = "b37bfdf1-1a7f-466e-bcb4-6ebf6d53eb50";
-- create users table
CREATE TABLE IF NOT EXISTS user_account
(
  id            UUID        NOT NULL PRIMARY KEY,
  login         VARCHAR(10) NOT NULL,
  password_hash BYTEA       NOT NULL,
  password_salt BYTEA       NOT NULL,
  first_name    VARCHAR(40) NOT NULL,
  last_name     VARCHAR(40) NOT NULL
);
-- populate with user (login:"Bilbo"; password:"Baggins")
INSERT INTO user_account (id, login, password_hash, password_salt, first_name, last_name) VALUES
  ('73c60097-4c7c-4c74-a347-5f69fcaef9fb', 'Bilbo',
   E'\\x6DB3B66707B40240B7DD71B549AE1536EF6E52C9F0FDCCE781F9AC1EEB574C0B', E'\\x0A2EBE7A87F3504A2C6F8846E7EDA2AF', 'Bilbo', 'Baggins');
-- create hibernate sequence
CREATE SEQUENCE IF NOT EXISTS hibernate_sequence
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;