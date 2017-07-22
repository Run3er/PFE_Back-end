-- create global schema (for administrator users)
CREATE SCHEMA IF NOT EXISTS tenants_global;

-- SET THE <tenant_schema_name> HERE  !
SET search_path = tenants_global;

-- create tenants table inside
CREATE TABLE IF NOT EXISTS tenant
(
  id     UUID                   NOT NULL PRIMARY KEY,
  pseudo CHARACTER VARYING(255) NOT NULL
);
ALTER TABLE tenant
  OWNER TO tenants_global;
-- populate with tenants
INSERT INTO tenant (id, pseudo) VALUES ('43d44eae-cd10-4ffb-97e1-c3e189119659', 'tenant_a');
INSERT INTO tenant (id, pseudo) VALUES ('b37bfdf1-1a7f-466e-bcb4-6ebf6d53eb50', 'tenant_b');

-- create users table inside
CREATE TABLE IF NOT EXISTS user_account (
  id            UUID                  NOT NULL,
  first_name    CHARACTER VARYING(40) NOT NULL,
  last_name     CHARACTER VARYING(40) NOT NULL,
  login         CHARACTER VARYING(10) NOT NULL,
  password_hash BYTEA                 NOT NULL,
  password_salt BYTEA                 NOT NULL
);
ALTER TABLE user_account
  OWNER TO tenants_global;
-- populate with user (login:"Frodo"; password:"Baggins")
INSERT INTO tenants_global.user_account (id, login, password_hash, password_salt, first_name, last_name) VALUES
  ('73c60097-4c7c-4c74-a347-5f69fcaef9fb', 'Frodo',
   E'\\x6DB3B66707B40240B7DD71B549AE1536EF6E52C9F0FDCCE781F9AC1EEB574C0B', E'\\x0A2EBE7A87F3504A2C6F8846E7EDA2AF',
   'Frodo', 'Baggins');

-- create mock-intended-only schema (needed for hibernate initialization)
CREATE SCHEMA IF NOT EXISTS default_mock_tenant;

-- create tenant schemas
CREATE SCHEMA IF NOT EXISTS "43d44eae-cd10-4ffb-97e1-c3e189119659";
CREATE SCHEMA IF NOT EXISTS "b37bfdf1-1a7f-466e-bcb4-6ebf6d53eb50";


-- SET THE <tenant_schema_name> HERE  !
SET search_path = "43d44eae-cd10-4ffb-97e1-c3e189119659";


--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.2
-- Dumped by pg_dump version 9.6.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: action; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE action (
  id uuid NOT NULL,
  reference uuid NOT NULL,
  advancement integer NOT NULL,
  closure_date timestamp without time zone,
  closure_planned_date timestamp without time zone NOT NULL,
  comment character varying(255),
  creation_date timestamp without time zone NOT NULL,
  description character varying(255) NOT NULL,
  priority integer NOT NULL,
  status integer NOT NULL,
  last_version_id uuid,
  supervisor_id uuid NOT NULL
);


ALTER TABLE action OWNER TO postgres;

--
-- Name: change_request; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE change_request (
  id uuid NOT NULL,
  reference uuid NOT NULL,
  decision_date timestamp without time zone,
  decision_planned_date timestamp without time zone NOT NULL,
  description character varying(255) NOT NULL,
  impacts character varying(255),
  priority integer NOT NULL,
  request_date timestamp without time zone NOT NULL,
  requester character varying(255) NOT NULL,
  status integer NOT NULL,
  last_version_id uuid
);


ALTER TABLE change_request OWNER TO postgres;

--
-- Name: communication_plan; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE communication_plan (
  id uuid NOT NULL,
  reference uuid NOT NULL,
  name character varying(255) NOT NULL,
  last_version_id uuid,
  supervisor_id uuid NOT NULL
);


ALTER TABLE communication_plan OWNER TO postgres;

--
-- Name: construction_site; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE construction_site (
  id uuid NOT NULL,
  advancement integer NOT NULL,
  budget_consumed numeric(19,2),
  budget_to_consume numeric(19,2),
  charge_consumed numeric(10,0),
  budget_initial numeric(19,2),
  charge_prevision numeric(10,0),
  comment character varying(255),
  end_date timestamp without time zone NOT NULL,
  goal character varying(255),
  name character varying(255) NOT NULL,
  start_date timestamp without time zone NOT NULL
);


ALTER TABLE construction_site OWNER TO postgres;

--
-- Name: construction_site_actions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE construction_site_actions (
  construction_site_id uuid NOT NULL,
  actions_id uuid NOT NULL
);


ALTER TABLE construction_site_actions OWNER TO postgres;

--
-- Name: construction_site_archived_updates; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE construction_site_archived_updates (
  construction_site_id uuid NOT NULL,
  archived_updates_id uuid NOT NULL
);


ALTER TABLE construction_site_archived_updates OWNER TO postgres;

--
-- Name: construction_site_change_requests; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE construction_site_change_requests (
  construction_site_id uuid NOT NULL,
  change_requests_id uuid NOT NULL
);


ALTER TABLE construction_site_change_requests OWNER TO postgres;

--
-- Name: construction_site_documents; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE construction_site_documents (
  construction_site_id uuid NOT NULL,
  documents_id uuid NOT NULL
);


ALTER TABLE construction_site_documents OWNER TO postgres;

--
-- Name: construction_site_human_resources; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE construction_site_human_resources (
  construction_site_id uuid NOT NULL,
  human_resources_id uuid NOT NULL
);


ALTER TABLE construction_site_human_resources OWNER TO postgres;

--
-- Name: construction_site_milestones; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE construction_site_milestones (
  construction_site_id uuid NOT NULL,
  milestones_id uuid NOT NULL
);


ALTER TABLE construction_site_milestones OWNER TO postgres;

--
-- Name: construction_site_pending_issues; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE construction_site_pending_issues (
  construction_site_id uuid NOT NULL,
  pending_issues_id uuid NOT NULL
);


ALTER TABLE construction_site_pending_issues OWNER TO postgres;

--
-- Name: construction_site_resources; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE construction_site_resources (
  construction_site_id uuid NOT NULL,
  resources_id uuid NOT NULL
);


ALTER TABLE construction_site_resources OWNER TO postgres;

--
-- Name: construction_site_risks; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE construction_site_risks (
  construction_site_id uuid NOT NULL,
  risks_id uuid NOT NULL
);


ALTER TABLE construction_site_risks OWNER TO postgres;

--
-- Name: construction_site_todos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE construction_site_todos (
  construction_site_id uuid NOT NULL,
  todos_id uuid NOT NULL
);


ALTER TABLE construction_site_todos OWNER TO postgres;

--
-- Name: construction_site_update; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE construction_site_update (
  id uuid NOT NULL,
  advancement integer NOT NULL,
  budget_consumed numeric(19,2),
  budget_to_consume numeric(19,2),
  charge_consumed numeric(10,0),
  update_time timestamp without time zone NOT NULL
);


ALTER TABLE construction_site_update OWNER TO postgres;

--
-- Name: construction_site_update_actions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE construction_site_update_actions (
  construction_site_update_id uuid NOT NULL,
  actions_id uuid NOT NULL
);


ALTER TABLE construction_site_update_actions OWNER TO postgres;

--
-- Name: construction_site_update_change_requests; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE construction_site_update_change_requests (
  construction_site_update_id uuid NOT NULL,
  change_requests_id uuid NOT NULL
);


ALTER TABLE construction_site_update_change_requests OWNER TO postgres;

--
-- Name: construction_site_update_documents; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE construction_site_update_documents (
  construction_site_update_id uuid NOT NULL,
  documents_id uuid NOT NULL
);


ALTER TABLE construction_site_update_documents OWNER TO postgres;

--
-- Name: construction_site_update_human_resources; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE construction_site_update_human_resources (
  construction_site_update_id uuid NOT NULL,
  human_resources_id uuid NOT NULL
);


ALTER TABLE construction_site_update_human_resources OWNER TO postgres;

--
-- Name: construction_site_update_milestones; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE construction_site_update_milestones (
  construction_site_update_id uuid NOT NULL,
  milestones_id uuid NOT NULL
);


ALTER TABLE construction_site_update_milestones OWNER TO postgres;

--
-- Name: construction_site_update_pending_issues; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE construction_site_update_pending_issues (
  construction_site_update_id uuid NOT NULL,
  pending_issues_id uuid NOT NULL
);


ALTER TABLE construction_site_update_pending_issues OWNER TO postgres;

--
-- Name: construction_site_update_resources; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE construction_site_update_resources (
  construction_site_update_id uuid NOT NULL,
  resources_id uuid NOT NULL
);


ALTER TABLE construction_site_update_resources OWNER TO postgres;

--
-- Name: construction_site_update_risks; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE construction_site_update_risks (
  construction_site_update_id uuid NOT NULL,
  risks_id uuid NOT NULL
);


ALTER TABLE construction_site_update_risks OWNER TO postgres;

--
-- Name: construction_site_update_todos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE construction_site_update_todos (
  construction_site_update_id uuid NOT NULL,
  todos_id uuid NOT NULL
);


ALTER TABLE construction_site_update_todos OWNER TO postgres;

--
-- Name: contribution; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE contribution (
  id uuid NOT NULL,
  role integer NOT NULL,
  user_id uuid NOT NULL,
  organization_id uuid NOT NULL,
  project_id uuid NOT NULL
);


ALTER TABLE contribution OWNER TO postgres;

--
-- Name: contributor_organization; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE contributor_organization (
  id uuid NOT NULL,
  address character varying(255),
  complementary_info character varying(255),
  email character varying(254) NOT NULL,
  fax_numbers character varying(150),
  full_name character varying(100) NOT NULL,
  legal_type integer,
  role integer NOT NULL,
  telephone_numbers character varying(150),
  trade_register character varying(20)
);


ALTER TABLE contributor_organization OWNER TO postgres;

--
-- Name: contributor_organization_contributions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE contributor_organization_contributions (
  contributor_organization_id uuid NOT NULL,
  contributions_id uuid NOT NULL
);


ALTER TABLE contributor_organization_contributions OWNER TO postgres;

--
-- Name: document; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE document (
  id uuid NOT NULL,
  reference uuid NOT NULL,
  name character varying(255) NOT NULL,
  os_id character varying(260) NOT NULL,
  content_type character varying(260) NOT NULL,
  last_version_id uuid
);


ALTER TABLE document OWNER TO postgres;

--
-- Name: human_resource; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE human_resource (
  id uuid NOT NULL,
  reference uuid NOT NULL,
  name character varying(255) NOT NULL,
  last_version_id uuid
);


ALTER TABLE human_resource OWNER TO postgres;

--
-- Name: milestone; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE milestone (
  id uuid NOT NULL,
  reference uuid NOT NULL,
  description character varying(255),
  due_date timestamp without time zone NOT NULL,
  name character varying(255) NOT NULL,
  last_version_id uuid
);


ALTER TABLE milestone OWNER TO postgres;

--
-- Name: pending_issue; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE pending_issue (
  id uuid NOT NULL,
  reference uuid NOT NULL,
  creation_date timestamp without time zone NOT NULL,
  decisions character varying(255),
  description character varying(255) NOT NULL,
  impacts character varying(255),
  priority integer NOT NULL,
  resolution_date timestamp without time zone,
  resolution_planned_date timestamp without time zone NOT NULL,
  status integer NOT NULL,
  last_version_id uuid,
  supervisor_id uuid NOT NULL
);


ALTER TABLE pending_issue OWNER TO postgres;

--
-- Name: project; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE project (
  id uuid NOT NULL,
  advancement integer NOT NULL,
  budget_consumed numeric(19,2),
  budget_to_consume numeric(19,2),
  charge_consumed numeric(10,0),
  status integer NOT NULL,
  final_client character varying(255),
  history_decisions character varying(255),
  hypotheses_constraints character varying(255),
  main_contact character varying(255),
  sponsors character varying(255),
  budget_initial numeric(19,2),
  charge_prevision numeric(10,0),
  comment character varying(255),
  end_date timestamp without time zone NOT NULL,
  goal character varying(255),
  name character varying(255) NOT NULL,
  start_date timestamp without time zone NOT NULL
);


ALTER TABLE project OWNER TO postgres;

--
-- Name: project_actions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE project_actions (
  project_id uuid NOT NULL,
  actions_id uuid NOT NULL
);


ALTER TABLE project_actions OWNER TO postgres;

--
-- Name: project_archived_updates; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE project_archived_updates (
  project_id uuid NOT NULL,
  archived_updates_id uuid NOT NULL
);


ALTER TABLE project_archived_updates OWNER TO postgres;

--
-- Name: project_change_requests; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE project_change_requests (
  project_id uuid NOT NULL,
  change_requests_id uuid NOT NULL
);


ALTER TABLE project_change_requests OWNER TO postgres;

--
-- Name: project_communication_plans; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE project_communication_plans (
  project_id uuid NOT NULL,
  communication_plans_id uuid NOT NULL
);


ALTER TABLE project_communication_plans OWNER TO postgres;

--
-- Name: project_construction_sites; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE project_construction_sites (
  project_id uuid NOT NULL,
  construction_sites_id uuid NOT NULL
);


ALTER TABLE project_construction_sites OWNER TO postgres;

--
-- Name: project_documents; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE project_documents (
  project_id uuid NOT NULL,
  documents_id uuid NOT NULL
);


ALTER TABLE project_documents OWNER TO postgres;

--
-- Name: project_human_resources; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE project_human_resources (
  project_id uuid NOT NULL,
  human_resources_id uuid NOT NULL
);


ALTER TABLE project_human_resources OWNER TO postgres;

--
-- Name: project_milestones; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE project_milestones (
  project_id uuid NOT NULL,
  milestones_id uuid NOT NULL
);


ALTER TABLE project_milestones OWNER TO postgres;

--
-- Name: project_pending_issues; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE project_pending_issues (
  project_id uuid NOT NULL,
  pending_issues_id uuid NOT NULL
);


ALTER TABLE project_pending_issues OWNER TO postgres;

--
-- Name: project_resources; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE project_resources (
  project_id uuid NOT NULL,
  resources_id uuid NOT NULL
);


ALTER TABLE project_resources OWNER TO postgres;

--
-- Name: project_reunion_plannings; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE project_reunion_plannings (
  project_id uuid NOT NULL,
  reunion_plannings_id uuid NOT NULL
);


ALTER TABLE project_reunion_plannings OWNER TO postgres;

--
-- Name: project_risks; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE project_risks (
  project_id uuid NOT NULL,
  risks_id uuid NOT NULL
);


ALTER TABLE project_risks OWNER TO postgres;

--
-- Name: project_sub_projects; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE project_sub_projects (
  project_id uuid NOT NULL,
  sub_projects_id uuid NOT NULL
);


ALTER TABLE project_sub_projects OWNER TO postgres;

--
-- Name: project_todos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE project_todos (
  project_id uuid NOT NULL,
  todos_id uuid NOT NULL
);


ALTER TABLE project_todos OWNER TO postgres;

--
-- Name: project_update; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE project_update (
  id uuid NOT NULL,
  advancement integer NOT NULL,
  budget_consumed numeric(19,2),
  budget_to_consume numeric(19,2),
  charge_consumed numeric(10,0),
  status integer NOT NULL,
  update_time timestamp without time zone NOT NULL
);


ALTER TABLE project_update OWNER TO postgres;

--
-- Name: project_update_actions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE project_update_actions (
  project_update_id uuid NOT NULL,
  actions_id uuid NOT NULL
);


ALTER TABLE project_update_actions OWNER TO postgres;

--
-- Name: project_update_change_requests; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE project_update_change_requests (
  project_update_id uuid NOT NULL,
  change_requests_id uuid NOT NULL
);


ALTER TABLE project_update_change_requests OWNER TO postgres;

--
-- Name: project_update_communication_plans; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE project_update_communication_plans (
  project_update_id uuid NOT NULL,
  communication_plans_id uuid NOT NULL
);


ALTER TABLE project_update_communication_plans OWNER TO postgres;

--
-- Name: project_update_documents; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE project_update_documents (
  project_update_id uuid NOT NULL,
  documents_id uuid NOT NULL
);


ALTER TABLE project_update_documents OWNER TO postgres;

--
-- Name: project_update_human_resources; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE project_update_human_resources (
  project_update_id uuid NOT NULL,
  human_resources_id uuid NOT NULL
);


ALTER TABLE project_update_human_resources OWNER TO postgres;

--
-- Name: project_update_milestones; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE project_update_milestones (
  project_update_id uuid NOT NULL,
  milestones_id uuid NOT NULL
);


ALTER TABLE project_update_milestones OWNER TO postgres;

--
-- Name: project_update_pending_issues; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE project_update_pending_issues (
  project_update_id uuid NOT NULL,
  pending_issues_id uuid NOT NULL
);


ALTER TABLE project_update_pending_issues OWNER TO postgres;

--
-- Name: project_update_resources; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE project_update_resources (
  project_update_id uuid NOT NULL,
  resources_id uuid NOT NULL
);


ALTER TABLE project_update_resources OWNER TO postgres;

--
-- Name: project_update_reunion_plannings; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE project_update_reunion_plannings (
  project_update_id uuid NOT NULL,
  reunion_plannings_id uuid NOT NULL
);


ALTER TABLE project_update_reunion_plannings OWNER TO postgres;

--
-- Name: project_update_risks; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE project_update_risks (
  project_update_id uuid NOT NULL,
  risks_id uuid NOT NULL
);


ALTER TABLE project_update_risks OWNER TO postgres;

--
-- Name: project_update_todos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE project_update_todos (
  project_update_id uuid NOT NULL,
  todos_id uuid NOT NULL
);


ALTER TABLE project_update_todos OWNER TO postgres;

--
-- Name: project_update_writeups; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE project_update_writeups (
  project_update_id uuid NOT NULL,
  writeups_id uuid NOT NULL
);


ALTER TABLE project_update_writeups OWNER TO postgres;

--
-- Name: project_writeups; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE project_writeups (
  project_id uuid NOT NULL,
  writeups_id uuid NOT NULL
);


ALTER TABLE project_writeups OWNER TO postgres;

--
-- Name: resource; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE resource (
  id uuid NOT NULL,
  reference uuid NOT NULL,
  name character varying(100) NOT NULL,
  type integer NOT NULL,
  last_version_id uuid
);


ALTER TABLE resource OWNER TO postgres;

--
-- Name: reunion_planning; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE reunion_planning (
  id uuid NOT NULL,
  reference uuid NOT NULL,
  date timestamp without time zone,
  location character varying(255),
  name character varying(255) NOT NULL,
  status integer NOT NULL,
  last_version_id uuid
);


ALTER TABLE reunion_planning OWNER TO postgres;

--
-- Name: risk; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE risk (
  id uuid NOT NULL,
  reference uuid NOT NULL,
  action_plan character varying(255) NOT NULL,
  category integer NOT NULL,
  cause character varying(255),
  closure_date timestamp without time zone,
  comment character varying(255),
  decision character varying(255),
  description character varying(255) NOT NULL,
  detection_date timestamp without time zone NOT NULL,
  impact integer NOT NULL,
  probability integer NOT NULL,
  qualification_date timestamp without time zone NOT NULL,
  status integer NOT NULL,
  last_version_id uuid
);


ALTER TABLE risk OWNER TO postgres;

--
-- Name: sub_project; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE sub_project (
  id uuid NOT NULL,
  advancement integer NOT NULL,
  budget_consumed numeric(19,2),
  budget_to_consume numeric(19,2),
  charge_consumed numeric(10,0),
  status integer NOT NULL,
  budget_initial numeric(19,2),
  charge_prevision numeric(10,0),
  comment character varying(255),
  end_date timestamp without time zone NOT NULL,
  goal character varying(255),
  name character varying(255) NOT NULL,
  start_date timestamp without time zone NOT NULL
);


ALTER TABLE sub_project OWNER TO postgres;

--
-- Name: sub_project_actions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE sub_project_actions (
  sub_project_id uuid NOT NULL,
  actions_id uuid NOT NULL
);


ALTER TABLE sub_project_actions OWNER TO postgres;

--
-- Name: sub_project_archived_updates; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE sub_project_archived_updates (
  sub_project_id uuid NOT NULL,
  archived_updates_id uuid NOT NULL
);


ALTER TABLE sub_project_archived_updates OWNER TO postgres;

--
-- Name: sub_project_change_requests; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE sub_project_change_requests (
  sub_project_id uuid NOT NULL,
  change_requests_id uuid NOT NULL
);


ALTER TABLE sub_project_change_requests OWNER TO postgres;

--
-- Name: sub_project_construction_sites; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE sub_project_construction_sites (
  sub_project_id uuid NOT NULL,
  construction_sites_id uuid NOT NULL
);


ALTER TABLE sub_project_construction_sites OWNER TO postgres;

--
-- Name: sub_project_documents; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE sub_project_documents (
  sub_project_id uuid NOT NULL,
  documents_id uuid NOT NULL
);


ALTER TABLE sub_project_documents OWNER TO postgres;

--
-- Name: sub_project_human_resources; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE sub_project_human_resources (
  sub_project_id uuid NOT NULL,
  human_resources_id uuid NOT NULL
);


ALTER TABLE sub_project_human_resources OWNER TO postgres;

--
-- Name: sub_project_milestones; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE sub_project_milestones (
  sub_project_id uuid NOT NULL,
  milestones_id uuid NOT NULL
);


ALTER TABLE sub_project_milestones OWNER TO postgres;

--
-- Name: sub_project_pending_issues; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE sub_project_pending_issues (
  sub_project_id uuid NOT NULL,
  pending_issues_id uuid NOT NULL
);


ALTER TABLE sub_project_pending_issues OWNER TO postgres;

--
-- Name: sub_project_resources; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE sub_project_resources (
  sub_project_id uuid NOT NULL,
  resources_id uuid NOT NULL
);


ALTER TABLE sub_project_resources OWNER TO postgres;

--
-- Name: sub_project_risks; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE sub_project_risks (
  sub_project_id uuid NOT NULL,
  risks_id uuid NOT NULL
);


ALTER TABLE sub_project_risks OWNER TO postgres;

--
-- Name: sub_project_todos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE sub_project_todos (
  sub_project_id uuid NOT NULL,
  todos_id uuid NOT NULL
);


ALTER TABLE sub_project_todos OWNER TO postgres;

--
-- Name: sub_project_update; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE sub_project_update (
  id uuid NOT NULL,
  advancement integer NOT NULL,
  budget_consumed numeric(19,2),
  budget_to_consume numeric(19,2),
  charge_consumed numeric(10,0),
  status integer NOT NULL,
  update_time timestamp without time zone NOT NULL
);


ALTER TABLE sub_project_update OWNER TO postgres;

--
-- Name: sub_project_update_actions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE sub_project_update_actions (
  sub_project_update_id uuid NOT NULL,
  actions_id uuid NOT NULL
);


ALTER TABLE sub_project_update_actions OWNER TO postgres;

--
-- Name: sub_project_update_change_requests; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE sub_project_update_change_requests (
  sub_project_update_id uuid NOT NULL,
  change_requests_id uuid NOT NULL
);


ALTER TABLE sub_project_update_change_requests OWNER TO postgres;

--
-- Name: sub_project_update_documents; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE sub_project_update_documents (
  sub_project_update_id uuid NOT NULL,
  documents_id uuid NOT NULL
);


ALTER TABLE sub_project_update_documents OWNER TO postgres;

--
-- Name: sub_project_update_human_resources; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE sub_project_update_human_resources (
  sub_project_update_id uuid NOT NULL,
  human_resources_id uuid NOT NULL
);


ALTER TABLE sub_project_update_human_resources OWNER TO postgres;

--
-- Name: sub_project_update_milestones; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE sub_project_update_milestones (
  sub_project_update_id uuid NOT NULL,
  milestones_id uuid NOT NULL
);


ALTER TABLE sub_project_update_milestones OWNER TO postgres;

--
-- Name: sub_project_update_pending_issues; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE sub_project_update_pending_issues (
  sub_project_update_id uuid NOT NULL,
  pending_issues_id uuid NOT NULL
);


ALTER TABLE sub_project_update_pending_issues OWNER TO postgres;

--
-- Name: sub_project_update_resources; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE sub_project_update_resources (
  sub_project_update_id uuid NOT NULL,
  resources_id uuid NOT NULL
);


ALTER TABLE sub_project_update_resources OWNER TO postgres;

--
-- Name: sub_project_update_risks; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE sub_project_update_risks (
  sub_project_update_id uuid NOT NULL,
  risks_id uuid NOT NULL
);


ALTER TABLE sub_project_update_risks OWNER TO postgres;

--
-- Name: sub_project_update_todos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE sub_project_update_todos (
  sub_project_update_id uuid NOT NULL,
  todos_id uuid NOT NULL
);


ALTER TABLE sub_project_update_todos OWNER TO postgres;

--
-- Name: tenant; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tenant (
  id uuid NOT NULL,
  pseudo character varying(100) NOT NULL
);


ALTER TABLE tenant OWNER TO postgres;

--
-- Name: tenant_details; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tenant_details (
  id uuid NOT NULL,
  address character varying(255),
  client_code character varying(255) NOT NULL,
  complementary_info character varying(255),
  contact_email character varying(254),
  contact_name character varying(100),
  contact_telephone_numbers character varying(100),
  email character varying(254) NOT NULL,
  fax_numbers character varying(150),
  full_name character varying(100) NOT NULL,
  telephone_numbers character varying(150),
  website_url character varying(2000)
);


ALTER TABLE tenant_details OWNER TO postgres;

--
-- Name: todo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE todo (
  id uuid NOT NULL,
  reference uuid NOT NULL,
  charge integer NOT NULL,
  description character varying(255) NOT NULL,
  estimation_date timestamp without time zone NOT NULL,
  last_version_id uuid
);


ALTER TABLE todo OWNER TO postgres;

--
-- Name: user_account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE user_account (
  id uuid NOT NULL,
  first_name character varying(40) NOT NULL,
  last_name character varying(40) NOT NULL,
  login character varying(10) NOT NULL,
  password_hash bytea NOT NULL,
  password_salt bytea NOT NULL
);


ALTER TABLE user_account OWNER TO postgres;

--
-- Name: writeup; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE writeup (
  id uuid NOT NULL,
  reference uuid NOT NULL,
  description character varying(255) NOT NULL,
  name character varying(255) NOT NULL,
  last_version_id uuid
);


ALTER TABLE writeup OWNER TO postgres;


--
-- Name: action action_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY action
  ADD CONSTRAINT action_pkey PRIMARY KEY (id);


--
-- Name: change_request change_request_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY change_request
  ADD CONSTRAINT change_request_pkey PRIMARY KEY (id);


--
-- Name: communication_plan communication_plan_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY communication_plan
  ADD CONSTRAINT communication_plan_pkey PRIMARY KEY (id);


--
-- Name: construction_site_actions construction_site_actions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_actions
  ADD CONSTRAINT construction_site_actions_pkey PRIMARY KEY (construction_site_id, actions_id);


--
-- Name: construction_site_archived_updates construction_site_archived_updates_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_archived_updates
  ADD CONSTRAINT construction_site_archived_updates_pkey PRIMARY KEY (construction_site_id, archived_updates_id);


--
-- Name: construction_site_change_requests construction_site_change_requests_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_change_requests
  ADD CONSTRAINT construction_site_change_requests_pkey PRIMARY KEY (construction_site_id, change_requests_id);


--
-- Name: construction_site_documents construction_site_documents_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_documents
  ADD CONSTRAINT construction_site_documents_pkey PRIMARY KEY (construction_site_id, documents_id);


--
-- Name: construction_site_human_resources construction_site_human_resources_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_human_resources
  ADD CONSTRAINT construction_site_human_resources_pkey PRIMARY KEY (construction_site_id, human_resources_id);


--
-- Name: construction_site_milestones construction_site_milestones_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_milestones
  ADD CONSTRAINT construction_site_milestones_pkey PRIMARY KEY (construction_site_id, milestones_id);


--
-- Name: construction_site_pending_issues construction_site_pending_issues_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_pending_issues
  ADD CONSTRAINT construction_site_pending_issues_pkey PRIMARY KEY (construction_site_id, pending_issues_id);


--
-- Name: construction_site construction_site_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site
  ADD CONSTRAINT construction_site_pkey PRIMARY KEY (id);


--
-- Name: construction_site_resources construction_site_resources_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_resources
  ADD CONSTRAINT construction_site_resources_pkey PRIMARY KEY (construction_site_id, resources_id);


--
-- Name: construction_site_risks construction_site_risks_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_risks
  ADD CONSTRAINT construction_site_risks_pkey PRIMARY KEY (construction_site_id, risks_id);


--
-- Name: construction_site_todos construction_site_todos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_todos
  ADD CONSTRAINT construction_site_todos_pkey PRIMARY KEY (construction_site_id, todos_id);


--
-- Name: construction_site_update_actions construction_site_update_actions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_update_actions
  ADD CONSTRAINT construction_site_update_actions_pkey PRIMARY KEY (construction_site_update_id, actions_id);


--
-- Name: construction_site_update_change_requests construction_site_update_change_requests_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_update_change_requests
  ADD CONSTRAINT construction_site_update_change_requests_pkey PRIMARY KEY (construction_site_update_id, change_requests_id);


--
-- Name: construction_site_update_documents construction_site_update_documents_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_update_documents
  ADD CONSTRAINT construction_site_update_documents_pkey PRIMARY KEY (construction_site_update_id, documents_id);


--
-- Name: construction_site_update_human_resources construction_site_update_human_resources_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_update_human_resources
  ADD CONSTRAINT construction_site_update_human_resources_pkey PRIMARY KEY (construction_site_update_id, human_resources_id);


--
-- Name: construction_site_update_milestones construction_site_update_milestones_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_update_milestones
  ADD CONSTRAINT construction_site_update_milestones_pkey PRIMARY KEY (construction_site_update_id, milestones_id);


--
-- Name: construction_site_update_pending_issues construction_site_update_pending_issues_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_update_pending_issues
  ADD CONSTRAINT construction_site_update_pending_issues_pkey PRIMARY KEY (construction_site_update_id, pending_issues_id);


--
-- Name: construction_site_update construction_site_update_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_update
  ADD CONSTRAINT construction_site_update_pkey PRIMARY KEY (id);


--
-- Name: construction_site_update_resources construction_site_update_resources_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_update_resources
  ADD CONSTRAINT construction_site_update_resources_pkey PRIMARY KEY (construction_site_update_id, resources_id);


--
-- Name: construction_site_update_risks construction_site_update_risks_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_update_risks
  ADD CONSTRAINT construction_site_update_risks_pkey PRIMARY KEY (construction_site_update_id, risks_id);


--
-- Name: construction_site_update_todos construction_site_update_todos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_update_todos
  ADD CONSTRAINT construction_site_update_todos_pkey PRIMARY KEY (construction_site_update_id, todos_id);


--
-- Name: contribution contribution_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contribution
  ADD CONSTRAINT contribution_pkey PRIMARY KEY (id);


--
-- Name: contributor_organization contributor_organization_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contributor_organization
  ADD CONSTRAINT contributor_organization_pkey PRIMARY KEY (id);


--
-- Name: document document_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY document
  ADD CONSTRAINT document_pkey PRIMARY KEY (id);


--
-- Name: human_resource human_resource_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY human_resource
  ADD CONSTRAINT human_resource_pkey PRIMARY KEY (id);


--
-- Name: milestone milestone_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY milestone
  ADD CONSTRAINT milestone_pkey PRIMARY KEY (id);


--
-- Name: pending_issue pending_issue_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pending_issue
  ADD CONSTRAINT pending_issue_pkey PRIMARY KEY (id);


--
-- Name: project_actions project_actions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_actions
  ADD CONSTRAINT project_actions_pkey PRIMARY KEY (project_id, actions_id);


--
-- Name: project_archived_updates project_archived_updates_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_archived_updates
  ADD CONSTRAINT project_archived_updates_pkey PRIMARY KEY (project_id, archived_updates_id);


--
-- Name: project_change_requests project_change_requests_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_change_requests
  ADD CONSTRAINT project_change_requests_pkey PRIMARY KEY (project_id, change_requests_id);


--
-- Name: project_communication_plans project_communication_plans_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_communication_plans
  ADD CONSTRAINT project_communication_plans_pkey PRIMARY KEY (project_id, communication_plans_id);


--
-- Name: project_construction_sites project_construction_sites_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_construction_sites
  ADD CONSTRAINT project_construction_sites_pkey PRIMARY KEY (project_id, construction_sites_id);


--
-- Name: project_documents project_documents_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_documents
  ADD CONSTRAINT project_documents_pkey PRIMARY KEY (project_id, documents_id);


--
-- Name: project_human_resources project_human_resources_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_human_resources
  ADD CONSTRAINT project_human_resources_pkey PRIMARY KEY (project_id, human_resources_id);


--
-- Name: project_milestones project_milestones_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_milestones
  ADD CONSTRAINT project_milestones_pkey PRIMARY KEY (project_id, milestones_id);


--
-- Name: project_pending_issues project_pending_issues_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_pending_issues
  ADD CONSTRAINT project_pending_issues_pkey PRIMARY KEY (project_id, pending_issues_id);


--
-- Name: project project_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project
  ADD CONSTRAINT project_pkey PRIMARY KEY (id);


--
-- Name: project_resources project_resources_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_resources
  ADD CONSTRAINT project_resources_pkey PRIMARY KEY (project_id, resources_id);


--
-- Name: project_reunion_plannings project_reunion_plannings_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_reunion_plannings
  ADD CONSTRAINT project_reunion_plannings_pkey PRIMARY KEY (project_id, reunion_plannings_id);


--
-- Name: project_risks project_risks_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_risks
  ADD CONSTRAINT project_risks_pkey PRIMARY KEY (project_id, risks_id);


--
-- Name: project_sub_projects project_sub_projects_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_sub_projects
  ADD CONSTRAINT project_sub_projects_pkey PRIMARY KEY (project_id, sub_projects_id);


--
-- Name: project_todos project_todos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_todos
  ADD CONSTRAINT project_todos_pkey PRIMARY KEY (project_id, todos_id);


--
-- Name: project_update_actions project_update_actions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_update_actions
  ADD CONSTRAINT project_update_actions_pkey PRIMARY KEY (project_update_id, actions_id);


--
-- Name: project_update_change_requests project_update_change_requests_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_update_change_requests
  ADD CONSTRAINT project_update_change_requests_pkey PRIMARY KEY (project_update_id, change_requests_id);


--
-- Name: project_update_communication_plans project_update_communication_plans_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_update_communication_plans
  ADD CONSTRAINT project_update_communication_plans_pkey PRIMARY KEY (project_update_id, communication_plans_id);


--
-- Name: project_update_documents project_update_documents_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_update_documents
  ADD CONSTRAINT project_update_documents_pkey PRIMARY KEY (project_update_id, documents_id);


--
-- Name: project_update_human_resources project_update_human_resources_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_update_human_resources
  ADD CONSTRAINT project_update_human_resources_pkey PRIMARY KEY (project_update_id, human_resources_id);


--
-- Name: project_update_milestones project_update_milestones_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_update_milestones
  ADD CONSTRAINT project_update_milestones_pkey PRIMARY KEY (project_update_id, milestones_id);


--
-- Name: project_update_pending_issues project_update_pending_issues_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_update_pending_issues
  ADD CONSTRAINT project_update_pending_issues_pkey PRIMARY KEY (project_update_id, pending_issues_id);


--
-- Name: project_update project_update_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_update
  ADD CONSTRAINT project_update_pkey PRIMARY KEY (id);


--
-- Name: project_update_resources project_update_resources_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_update_resources
  ADD CONSTRAINT project_update_resources_pkey PRIMARY KEY (project_update_id, resources_id);


--
-- Name: project_update_reunion_plannings project_update_reunion_plannings_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_update_reunion_plannings
  ADD CONSTRAINT project_update_reunion_plannings_pkey PRIMARY KEY (project_update_id, reunion_plannings_id);


--
-- Name: project_update_risks project_update_risks_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_update_risks
  ADD CONSTRAINT project_update_risks_pkey PRIMARY KEY (project_update_id, risks_id);


--
-- Name: project_update_todos project_update_todos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_update_todos
  ADD CONSTRAINT project_update_todos_pkey PRIMARY KEY (project_update_id, todos_id);


--
-- Name: project_update_writeups project_update_writeups_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_update_writeups
  ADD CONSTRAINT project_update_writeups_pkey PRIMARY KEY (project_update_id, writeups_id);


--
-- Name: project_writeups project_writeups_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_writeups
  ADD CONSTRAINT project_writeups_pkey PRIMARY KEY (project_id, writeups_id);


--
-- Name: resource resource_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY resource
  ADD CONSTRAINT resource_pkey PRIMARY KEY (id);


--
-- Name: reunion_planning reunion_planning_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reunion_planning
  ADD CONSTRAINT reunion_planning_pkey PRIMARY KEY (id);


--
-- Name: risk risk_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY risk
  ADD CONSTRAINT risk_pkey PRIMARY KEY (id);


--
-- Name: sub_project_actions sub_project_actions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_actions
  ADD CONSTRAINT sub_project_actions_pkey PRIMARY KEY (sub_project_id, actions_id);


--
-- Name: sub_project_archived_updates sub_project_archived_updates_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_archived_updates
  ADD CONSTRAINT sub_project_archived_updates_pkey PRIMARY KEY (sub_project_id, archived_updates_id);


--
-- Name: sub_project_change_requests sub_project_change_requests_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_change_requests
  ADD CONSTRAINT sub_project_change_requests_pkey PRIMARY KEY (sub_project_id, change_requests_id);


--
-- Name: sub_project_construction_sites sub_project_construction_sites_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_construction_sites
  ADD CONSTRAINT sub_project_construction_sites_pkey PRIMARY KEY (sub_project_id, construction_sites_id);


--
-- Name: sub_project_documents sub_project_documents_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_documents
  ADD CONSTRAINT sub_project_documents_pkey PRIMARY KEY (sub_project_id, documents_id);


--
-- Name: sub_project_human_resources sub_project_human_resources_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_human_resources
  ADD CONSTRAINT sub_project_human_resources_pkey PRIMARY KEY (sub_project_id, human_resources_id);


--
-- Name: sub_project_milestones sub_project_milestones_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_milestones
  ADD CONSTRAINT sub_project_milestones_pkey PRIMARY KEY (sub_project_id, milestones_id);


--
-- Name: sub_project_pending_issues sub_project_pending_issues_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_pending_issues
  ADD CONSTRAINT sub_project_pending_issues_pkey PRIMARY KEY (sub_project_id, pending_issues_id);


--
-- Name: sub_project sub_project_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project
  ADD CONSTRAINT sub_project_pkey PRIMARY KEY (id);


--
-- Name: sub_project_resources sub_project_resources_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_resources
  ADD CONSTRAINT sub_project_resources_pkey PRIMARY KEY (sub_project_id, resources_id);


--
-- Name: sub_project_risks sub_project_risks_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_risks
  ADD CONSTRAINT sub_project_risks_pkey PRIMARY KEY (sub_project_id, risks_id);


--
-- Name: sub_project_todos sub_project_todos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_todos
  ADD CONSTRAINT sub_project_todos_pkey PRIMARY KEY (sub_project_id, todos_id);


--
-- Name: sub_project_update_actions sub_project_update_actions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_update_actions
  ADD CONSTRAINT sub_project_update_actions_pkey PRIMARY KEY (sub_project_update_id, actions_id);


--
-- Name: sub_project_update_change_requests sub_project_update_change_requests_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_update_change_requests
  ADD CONSTRAINT sub_project_update_change_requests_pkey PRIMARY KEY (sub_project_update_id, change_requests_id);


--
-- Name: sub_project_update_documents sub_project_update_documents_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_update_documents
  ADD CONSTRAINT sub_project_update_documents_pkey PRIMARY KEY (sub_project_update_id, documents_id);


--
-- Name: sub_project_update_human_resources sub_project_update_human_resources_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_update_human_resources
  ADD CONSTRAINT sub_project_update_human_resources_pkey PRIMARY KEY (sub_project_update_id, human_resources_id);


--
-- Name: sub_project_update_milestones sub_project_update_milestones_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_update_milestones
  ADD CONSTRAINT sub_project_update_milestones_pkey PRIMARY KEY (sub_project_update_id, milestones_id);


--
-- Name: sub_project_update_pending_issues sub_project_update_pending_issues_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_update_pending_issues
  ADD CONSTRAINT sub_project_update_pending_issues_pkey PRIMARY KEY (sub_project_update_id, pending_issues_id);


--
-- Name: sub_project_update sub_project_update_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_update
  ADD CONSTRAINT sub_project_update_pkey PRIMARY KEY (id);


--
-- Name: sub_project_update_resources sub_project_update_resources_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_update_resources
  ADD CONSTRAINT sub_project_update_resources_pkey PRIMARY KEY (sub_project_update_id, resources_id);


--
-- Name: sub_project_update_risks sub_project_update_risks_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_update_risks
  ADD CONSTRAINT sub_project_update_risks_pkey PRIMARY KEY (sub_project_update_id, risks_id);


--
-- Name: sub_project_update_todos sub_project_update_todos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_update_todos
  ADD CONSTRAINT sub_project_update_todos_pkey PRIMARY KEY (sub_project_update_id, todos_id);


--
-- Name: tenant_details tenant_details_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tenant_details
  ADD CONSTRAINT tenant_details_pkey PRIMARY KEY (id);


--
-- Name: tenant tenant_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tenant
  ADD CONSTRAINT tenant_pkey PRIMARY KEY (id);


--
-- Name: todo todo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY todo
  ADD CONSTRAINT todo_pkey PRIMARY KEY (id);


--
-- Name: project_archived_updates uk_56hpu9xcr2ejyd1gr99aoidcj; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_archived_updates
  ADD CONSTRAINT uk_56hpu9xcr2ejyd1gr99aoidcj UNIQUE (archived_updates_id);


--
-- Name: sub_project_construction_sites uk_7jax9xxf61q58rjgimj1t0k7t; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_construction_sites
  ADD CONSTRAINT uk_7jax9xxf61q58rjgimj1t0k7t UNIQUE (construction_sites_id);


--
-- Name: tenant_details uk_7uh73xcewr7s2qad802ml6ctq; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tenant_details
  ADD CONSTRAINT uk_7uh73xcewr7s2qad802ml6ctq UNIQUE (client_code);


--
-- Name: contributor_organization_contributions uk_9yoop07gt6w8r4foc7vfelhjr; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contributor_organization_contributions
  ADD CONSTRAINT uk_9yoop07gt6w8r4foc7vfelhjr UNIQUE (contributions_id);


--
-- Name: project_construction_sites uk_cl0qtf9j8rv6joscsitv6rylo; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_construction_sites
  ADD CONSTRAINT uk_cl0qtf9j8rv6joscsitv6rylo UNIQUE (construction_sites_id);


--
-- Name: construction_site_archived_updates uk_es2vsv49ne0gbi1dspt03q2fd; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_archived_updates
  ADD CONSTRAINT uk_es2vsv49ne0gbi1dspt03q2fd UNIQUE (archived_updates_id);


--
-- Name: tenant uk_gn6vtsavd1ajpbieirvoiyup5; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tenant
  ADD CONSTRAINT uk_gn6vtsavd1ajpbieirvoiyup5 UNIQUE (pseudo);


--
-- Name: sub_project_archived_updates uk_lja4g0353wf25ajimeeio4gwy; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_archived_updates
  ADD CONSTRAINT uk_lja4g0353wf25ajimeeio4gwy UNIQUE (archived_updates_id);


--
-- Name: project_sub_projects uk_p0t59dbserr8eek9rcgbu8q9t; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_sub_projects
  ADD CONSTRAINT uk_p0t59dbserr8eek9rcgbu8q9t UNIQUE (sub_projects_id);


--
-- Name: user_account uk_plpggm55i6uhyv404q6pyu0ub; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_account
  ADD CONSTRAINT uk_plpggm55i6uhyv404q6pyu0ub UNIQUE (login);


--
-- Name: contribution ukemf5t25e81c8jldhois4tofpp; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contribution
  ADD CONSTRAINT ukemf5t25e81c8jldhois4tofpp UNIQUE (user_id, project_id, role);


--
-- Name: user_account user_account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_account
  ADD CONSTRAINT user_account_pkey PRIMARY KEY (id);


--
-- Name: writeup writeup_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY writeup
  ADD CONSTRAINT writeup_pkey PRIMARY KEY (id);


--
-- Name: construction_site_update_human_resources fk14f1w6m9e9rdwebpqcuvotnsn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_update_human_resources
  ADD CONSTRAINT fk14f1w6m9e9rdwebpqcuvotnsn FOREIGN KEY (human_resources_id) REFERENCES human_resource(id);


--
-- Name: project_update_change_requests fk18gjp7yidef4xkk31ipcrggf9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_update_change_requests
  ADD CONSTRAINT fk18gjp7yidef4xkk31ipcrggf9 FOREIGN KEY (change_requests_id) REFERENCES change_request(id);


--
-- Name: sub_project_documents fk18mhfc620jii10eq3x1soqg31; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_documents
  ADD CONSTRAINT fk18mhfc620jii10eq3x1soqg31 FOREIGN KEY (sub_project_id) REFERENCES sub_project(id);


--
-- Name: construction_site_change_requests fk18sqaerap633595nd5836f3pc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_change_requests
  ADD CONSTRAINT fk18sqaerap633595nd5836f3pc FOREIGN KEY (change_requests_id) REFERENCES change_request(id);


--
-- Name: construction_site_documents fk1gy4fgkiop0ewy7pm9msidfnq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_documents
  ADD CONSTRAINT fk1gy4fgkiop0ewy7pm9msidfnq FOREIGN KEY (construction_site_id) REFERENCES construction_site(id);


--
-- Name: construction_site_update_milestones fk1nko8vh0273nryjpioqef8np7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_update_milestones
  ADD CONSTRAINT fk1nko8vh0273nryjpioqef8np7 FOREIGN KEY (construction_site_update_id) REFERENCES construction_site_update(id);


--
-- Name: construction_site_documents fk1qrym9edj7fcowf7m46uiwigm; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_documents
  ADD CONSTRAINT fk1qrym9edj7fcowf7m46uiwigm FOREIGN KEY (documents_id) REFERENCES document(id);


--
-- Name: sub_project_pending_issues fk1u7vsgc106bjyp6e0pldgnqf4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_pending_issues
  ADD CONSTRAINT fk1u7vsgc106bjyp6e0pldgnqf4 FOREIGN KEY (sub_project_id) REFERENCES sub_project(id);


--
-- Name: todo fk1y0y2r8kix9ont9i6md9qra8a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY todo
  ADD CONSTRAINT fk1y0y2r8kix9ont9i6md9qra8a FOREIGN KEY (last_version_id) REFERENCES todo(id);


--
-- Name: construction_site_milestones fk237q06rxe1x14par0l79jftcm; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_milestones
  ADD CONSTRAINT fk237q06rxe1x14par0l79jftcm FOREIGN KEY (construction_site_id) REFERENCES construction_site(id);


--
-- Name: construction_site_todos fk2klcqesn1dksw4t8mhk8btq8v; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_todos
  ADD CONSTRAINT fk2klcqesn1dksw4t8mhk8btq8v FOREIGN KEY (todos_id) REFERENCES todo(id);


--
-- Name: sub_project_update_pending_issues fk2ss0gj20qbq8s3w6m5f2bohmh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_update_pending_issues
  ADD CONSTRAINT fk2ss0gj20qbq8s3w6m5f2bohmh FOREIGN KEY (sub_project_update_id) REFERENCES sub_project_update(id);


--
-- Name: sub_project_actions fk2ywklrajlgkt099wowbc1dblh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_actions
  ADD CONSTRAINT fk2ywklrajlgkt099wowbc1dblh FOREIGN KEY (actions_id) REFERENCES action(id);


--
-- Name: sub_project_update_resources fk3etmeik7920x3mfjf8dghkaw0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_update_resources
  ADD CONSTRAINT fk3etmeik7920x3mfjf8dghkaw0 FOREIGN KEY (sub_project_update_id) REFERENCES sub_project_update(id);


--
-- Name: sub_project_update_todos fk3gfc27l9q4hriwsi04n0etw4d; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_update_todos
  ADD CONSTRAINT fk3gfc27l9q4hriwsi04n0etw4d FOREIGN KEY (todos_id) REFERENCES todo(id);


--
-- Name: project_milestones fk3hsnoi9hhmgc0hghagdaqihm7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_milestones
  ADD CONSTRAINT fk3hsnoi9hhmgc0hghagdaqihm7 FOREIGN KEY (milestones_id) REFERENCES milestone(id);


--
-- Name: sub_project_update_resources fk3monc0htucs4ujm53kr0cp134; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_update_resources
  ADD CONSTRAINT fk3monc0htucs4ujm53kr0cp134 FOREIGN KEY (resources_id) REFERENCES resource(id);


--
-- Name: project_milestones fk47iegkdifs5srqq8q4dfdwy9x; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_milestones
  ADD CONSTRAINT fk47iegkdifs5srqq8q4dfdwy9x FOREIGN KEY (project_id) REFERENCES project(id);


--
-- Name: project_todos fk4c9odw2w2gj9dluag7r1wfkmc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_todos
  ADD CONSTRAINT fk4c9odw2w2gj9dluag7r1wfkmc FOREIGN KEY (project_id) REFERENCES project(id);


--
-- Name: project_update_human_resources fk4ealg79ao9ligcsiskrsunnke; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_update_human_resources
  ADD CONSTRAINT fk4ealg79ao9ligcsiskrsunnke FOREIGN KEY (human_resources_id) REFERENCES human_resource(id);


--
-- Name: sub_project_update_milestones fk4fi3k1yg6dwhd0sy36xq70wpp; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_update_milestones
  ADD CONSTRAINT fk4fi3k1yg6dwhd0sy36xq70wpp FOREIGN KEY (milestones_id) REFERENCES milestone(id);


--
-- Name: sub_project_update_risks fk4guqhtuydkycyk8fpcqpjlrw4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_update_risks
  ADD CONSTRAINT fk4guqhtuydkycyk8fpcqpjlrw4 FOREIGN KEY (risks_id) REFERENCES risk(id);


--
-- Name: project_update_todos fk4n3lppjbpog70k696cuuo7uge; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_update_todos
  ADD CONSTRAINT fk4n3lppjbpog70k696cuuo7uge FOREIGN KEY (project_update_id) REFERENCES project_update(id);


--
-- Name: sub_project_todos fk4u29a2hqqpy04unur716xdbs8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_todos
  ADD CONSTRAINT fk4u29a2hqqpy04unur716xdbs8 FOREIGN KEY (sub_project_id) REFERENCES sub_project(id);


--
-- Name: construction_site_update_risks fk4yq86p409rytee7qf12andccd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_update_risks
  ADD CONSTRAINT fk4yq86p409rytee7qf12andccd FOREIGN KEY (construction_site_update_id) REFERENCES construction_site_update(id);


--
-- Name: construction_site_todos fk52jo7367dqh9yb8yyme04dcnj; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_todos
  ADD CONSTRAINT fk52jo7367dqh9yb8yyme04dcnj FOREIGN KEY (construction_site_id) REFERENCES construction_site(id);


--
-- Name: sub_project_update_change_requests fk53vv8b186mebhx7cwt6uk8ewg; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_update_change_requests
  ADD CONSTRAINT fk53vv8b186mebhx7cwt6uk8ewg FOREIGN KEY (change_requests_id) REFERENCES change_request(id);


--
-- Name: construction_site_update_resources fk57l0nmmrfw0bl4qitt7of18qs; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_update_resources
  ADD CONSTRAINT fk57l0nmmrfw0bl4qitt7of18qs FOREIGN KEY (construction_site_update_id) REFERENCES construction_site_update(id);


--
-- Name: construction_site_update_todos fk58oupwin51dh9rrsqm70y5gq1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_update_todos
  ADD CONSTRAINT fk58oupwin51dh9rrsqm70y5gq1 FOREIGN KEY (todos_id) REFERENCES todo(id);


--
-- Name: project_update_documents fk59l2655g5r7x8je428v4m8t0w; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_update_documents
  ADD CONSTRAINT fk59l2655g5r7x8je428v4m8t0w FOREIGN KEY (documents_id) REFERENCES document(id);


--
-- Name: project_reunion_plannings fk5c984vhuecgj88mdeu2tduosn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_reunion_plannings
  ADD CONSTRAINT fk5c984vhuecgj88mdeu2tduosn FOREIGN KEY (project_id) REFERENCES project(id);


--
-- Name: sub_project_update_milestones fk5ndmmop90wlpequte15yy26aj; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_update_milestones
  ADD CONSTRAINT fk5ndmmop90wlpequte15yy26aj FOREIGN KEY (sub_project_update_id) REFERENCES sub_project_update(id);


--
-- Name: project_actions fk5sfsfijmhs7l0x4yjrdmqw3x9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_actions
  ADD CONSTRAINT fk5sfsfijmhs7l0x4yjrdmqw3x9 FOREIGN KEY (actions_id) REFERENCES action(id);


--
-- Name: project_update_risks fk5t6un078sf0vo2p9oynfx54ov; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_update_risks
  ADD CONSTRAINT fk5t6un078sf0vo2p9oynfx54ov FOREIGN KEY (project_update_id) REFERENCES project_update(id);


--
-- Name: project_update_human_resources fk5uqb7gd6qccvaic5x4ikijptc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_update_human_resources
  ADD CONSTRAINT fk5uqb7gd6qccvaic5x4ikijptc FOREIGN KEY (project_update_id) REFERENCES project_update(id);


--
-- Name: sub_project_risks fk659yxxcntqjck8m1vsu0f5cyf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_risks
  ADD CONSTRAINT fk659yxxcntqjck8m1vsu0f5cyf FOREIGN KEY (risks_id) REFERENCES risk(id);


--
-- Name: sub_project_documents fk6eb6qtn3et6i6g9djsi0kwoqr; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_documents
  ADD CONSTRAINT fk6eb6qtn3et6i6g9djsi0kwoqr FOREIGN KEY (documents_id) REFERENCES document(id);


--
-- Name: project_risks fk6q0da3y1ythfd2e1ulyneplov; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_risks
  ADD CONSTRAINT fk6q0da3y1ythfd2e1ulyneplov FOREIGN KEY (project_id) REFERENCES project(id);


--
-- Name: contribution fk6yldljk6wf209daq8cy937omx; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contribution
  ADD CONSTRAINT fk6yldljk6wf209daq8cy937omx FOREIGN KEY (organization_id) REFERENCES contributor_organization(id);


--
-- Name: project_construction_sites fk74uuom0ivtegoqllqk1t881u8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_construction_sites
  ADD CONSTRAINT fk74uuom0ivtegoqllqk1t881u8 FOREIGN KEY (project_id) REFERENCES project(id);


--
-- Name: project_update_documents fk812a35paspvn21cpt39cdx5pl; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_update_documents
  ADD CONSTRAINT fk812a35paspvn21cpt39cdx5pl FOREIGN KEY (project_update_id) REFERENCES project_update(id);


--
-- Name: project_communication_plans fk87bf69544pfmvs2avh1e4wo4e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_communication_plans
  ADD CONSTRAINT fk87bf69544pfmvs2avh1e4wo4e FOREIGN KEY (communication_plans_id) REFERENCES communication_plan(id);


--
-- Name: sub_project_update_documents fk87bkff25hdhkcskhu29yx8y3y; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_update_documents
  ADD CONSTRAINT fk87bkff25hdhkcskhu29yx8y3y FOREIGN KEY (documents_id) REFERENCES document(id);


--
-- Name: human_resource fk8jf9hup47o5rtyai6623iypoc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY human_resource
  ADD CONSTRAINT fk8jf9hup47o5rtyai6623iypoc FOREIGN KEY (last_version_id) REFERENCES human_resource(id);


--
-- Name: construction_site_milestones fk8nwb198v2mbnsvqmaokyxmiwv; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_milestones
  ADD CONSTRAINT fk8nwb198v2mbnsvqmaokyxmiwv FOREIGN KEY (milestones_id) REFERENCES milestone(id);


--
-- Name: contributor_organization_contributions fk8obwpt914s6lb6ljqih9ndtp0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contributor_organization_contributions
  ADD CONSTRAINT fk8obwpt914s6lb6ljqih9ndtp0 FOREIGN KEY (contributor_organization_id) REFERENCES contributor_organization(id);


--
-- Name: project_communication_plans fk8q813s0t4wraphcgay7xd164w; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_communication_plans
  ADD CONSTRAINT fk8q813s0t4wraphcgay7xd164w FOREIGN KEY (project_id) REFERENCES project(id);


--
-- Name: construction_site_archived_updates fk8vb7qgtiyf0si7071aa5f1qrk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_archived_updates
  ADD CONSTRAINT fk8vb7qgtiyf0si7071aa5f1qrk FOREIGN KEY (construction_site_id) REFERENCES construction_site(id);


--
-- Name: sub_project_construction_sites fk92oimbx13ue3nw2807914su5m; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_construction_sites
  ADD CONSTRAINT fk92oimbx13ue3nw2807914su5m FOREIGN KEY (construction_sites_id) REFERENCES construction_site(id);


--
-- Name: project_update_reunion_plannings fk974ujluy0c4n2r0t9ton3v978; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_update_reunion_plannings
  ADD CONSTRAINT fk974ujluy0c4n2r0t9ton3v978 FOREIGN KEY (reunion_plannings_id) REFERENCES reunion_planning(id);


--
-- Name: milestone fk9k1jmlvehvy8o1d6xjbqp9mi2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY milestone
  ADD CONSTRAINT fk9k1jmlvehvy8o1d6xjbqp9mi2 FOREIGN KEY (last_version_id) REFERENCES milestone(id);


--
-- Name: sub_project_archived_updates fk9kxkwv7k1rv8now8u2r7yryha; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_archived_updates
  ADD CONSTRAINT fk9kxkwv7k1rv8now8u2r7yryha FOREIGN KEY (sub_project_id) REFERENCES sub_project(id);


--
-- Name: project_update_writeups fka3c3g6m3ovhxs305bi7009t6u; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_update_writeups
  ADD CONSTRAINT fka3c3g6m3ovhxs305bi7009t6u FOREIGN KEY (project_update_id) REFERENCES project_update(id);


--
-- Name: sub_project_pending_issues fka6vowf3j8621adpuhe5mkoud2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_pending_issues
  ADD CONSTRAINT fka6vowf3j8621adpuhe5mkoud2 FOREIGN KEY (pending_issues_id) REFERENCES pending_issue(id);


--
-- Name: sub_project_archived_updates fkaf421fi1qbm5k9ycraak3bfak; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_archived_updates
  ADD CONSTRAINT fkaf421fi1qbm5k9ycraak3bfak FOREIGN KEY (archived_updates_id) REFERENCES sub_project_update(id);


--
-- Name: sub_project_milestones fkagpbh1q5axlyq0sbe1qf25aqh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_milestones
  ADD CONSTRAINT fkagpbh1q5axlyq0sbe1qf25aqh FOREIGN KEY (milestones_id) REFERENCES milestone(id);


--
-- Name: contributor_organization_contributions fkal1o1nuf6crjy2h8q0umija97; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contributor_organization_contributions
  ADD CONSTRAINT fkal1o1nuf6crjy2h8q0umija97 FOREIGN KEY (contributions_id) REFERENCES contribution(id);


--
-- Name: construction_site_update_documents fkasta9hc4ub98cys42bp49rbck; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_update_documents
  ADD CONSTRAINT fkasta9hc4ub98cys42bp49rbck FOREIGN KEY (documents_id) REFERENCES document(id);


--
-- Name: construction_site_archived_updates fkb5f0784bj31yagkr9dkmdek16; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_archived_updates
  ADD CONSTRAINT fkb5f0784bj31yagkr9dkmdek16 FOREIGN KEY (archived_updates_id) REFERENCES construction_site_update(id);


--
-- Name: construction_site_update_human_resources fkb74on6gjbf73kiuehro9757bc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_update_human_resources
  ADD CONSTRAINT fkb74on6gjbf73kiuehro9757bc FOREIGN KEY (construction_site_update_id) REFERENCES construction_site_update(id);


--
-- Name: writeup fkbaxdf26dfx8hhufqrhmwx4qmt; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY writeup
  ADD CONSTRAINT fkbaxdf26dfx8hhufqrhmwx4qmt FOREIGN KEY (last_version_id) REFERENCES writeup(id);


--
-- Name: project_update_pending_issues fkbd81qowpvoq83f59q3q6x23gx; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_update_pending_issues
  ADD CONSTRAINT fkbd81qowpvoq83f59q3q6x23gx FOREIGN KEY (pending_issues_id) REFERENCES pending_issue(id);


--
-- Name: project_pending_issues fkbmh6tp7lr2qm0dd8ui23prlad; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_pending_issues
  ADD CONSTRAINT fkbmh6tp7lr2qm0dd8ui23prlad FOREIGN KEY (pending_issues_id) REFERENCES pending_issue(id);


--
-- Name: document fkbpcwjq9iut4jaixq1a58pyuv9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY document
  ADD CONSTRAINT fkbpcwjq9iut4jaixq1a58pyuv9 FOREIGN KEY (last_version_id) REFERENCES document(id);


--
-- Name: project_update_risks fkca17ae8vvb22ptv6l46lrb9gb; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_update_risks
  ADD CONSTRAINT fkca17ae8vvb22ptv6l46lrb9gb FOREIGN KEY (risks_id) REFERENCES risk(id);


--
-- Name: sub_project_human_resources fkcoejq90dwgk3u28m06giegu2p; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_human_resources
  ADD CONSTRAINT fkcoejq90dwgk3u28m06giegu2p FOREIGN KEY (human_resources_id) REFERENCES human_resource(id);


--
-- Name: sub_project_update_documents fkctwdpj40m4yh6m9hwx1evehvg; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_update_documents
  ADD CONSTRAINT fkctwdpj40m4yh6m9hwx1evehvg FOREIGN KEY (sub_project_update_id) REFERENCES sub_project_update(id);


--
-- Name: construction_site_human_resources fkddcct7ys69f0ra04jx62ljcxd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_human_resources
  ADD CONSTRAINT fkddcct7ys69f0ra04jx62ljcxd FOREIGN KEY (construction_site_id) REFERENCES construction_site(id);


--
-- Name: sub_project_change_requests fke762a752pvwc6ar67017hg7w5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_change_requests
  ADD CONSTRAINT fke762a752pvwc6ar67017hg7w5 FOREIGN KEY (sub_project_id) REFERENCES sub_project(id);


--
-- Name: construction_site_human_resources fkeax9icew72r12s8qe8rkcbuf2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_human_resources
  ADD CONSTRAINT fkeax9icew72r12s8qe8rkcbuf2 FOREIGN KEY (human_resources_id) REFERENCES human_resource(id);


--
-- Name: construction_site_update_milestones fkem9qva3l6s389urdbvg5wr3yk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_update_milestones
  ADD CONSTRAINT fkem9qva3l6s389urdbvg5wr3yk FOREIGN KEY (milestones_id) REFERENCES milestone(id);


--
-- Name: action fkeuxis3k71dj1x2sbslrntjyhn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY action
  ADD CONSTRAINT fkeuxis3k71dj1x2sbslrntjyhn FOREIGN KEY (last_version_id) REFERENCES action(id);


--
-- Name: resource fkfc7go0qw7va8b492n4wc227x3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY resource
  ADD CONSTRAINT fkfc7go0qw7va8b492n4wc227x3 FOREIGN KEY (last_version_id) REFERENCES resource(id);


--
-- Name: project_documents fkfifca15akb3vq3ikki43wvf34; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_documents
  ADD CONSTRAINT fkfifca15akb3vq3ikki43wvf34 FOREIGN KEY (documents_id) REFERENCES document(id);


--
-- Name: contribution fkfiq0ecipeu4419esqoktyosfo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contribution
  ADD CONSTRAINT fkfiq0ecipeu4419esqoktyosfo FOREIGN KEY (user_id) REFERENCES user_account(id);


--
-- Name: project_human_resources fkfy7vseahigmy2ui9aj3keys5x; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_human_resources
  ADD CONSTRAINT fkfy7vseahigmy2ui9aj3keys5x FOREIGN KEY (human_resources_id) REFERENCES human_resource(id);


--
-- Name: project_archived_updates fkg24vul7ox93ya5tc7lute72j7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_archived_updates
  ADD CONSTRAINT fkg24vul7ox93ya5tc7lute72j7 FOREIGN KEY (archived_updates_id) REFERENCES project_update(id);


--
-- Name: construction_site_update_resources fkg28gvkhs1vg0ot7368y4lhh4o; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_update_resources
  ADD CONSTRAINT fkg28gvkhs1vg0ot7368y4lhh4o FOREIGN KEY (resources_id) REFERENCES resource(id);


--
-- Name: sub_project_resources fkg2n8i2vs6dxwu0afn5s0j7750; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_resources
  ADD CONSTRAINT fkg2n8i2vs6dxwu0afn5s0j7750 FOREIGN KEY (resources_id) REFERENCES resource(id);


--
-- Name: construction_site_risks fkg6e5lvku6txigmr4y57agkm50; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_risks
  ADD CONSTRAINT fkg6e5lvku6txigmr4y57agkm50 FOREIGN KEY (risks_id) REFERENCES risk(id);


--
-- Name: project_pending_issues fkgg4b3w4dgnps90cdf9b56jak9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_pending_issues
  ADD CONSTRAINT fkgg4b3w4dgnps90cdf9b56jak9 FOREIGN KEY (project_id) REFERENCES project(id);


--
-- Name: project_writeups fkgilosgpfdp9cojbrn3trr73rg; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_writeups
  ADD CONSTRAINT fkgilosgpfdp9cojbrn3trr73rg FOREIGN KEY (writeups_id) REFERENCES writeup(id);


--
-- Name: project_update_writeups fkgvs42jyv21gsk5nbfmatlmrru; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_update_writeups
  ADD CONSTRAINT fkgvs42jyv21gsk5nbfmatlmrru FOREIGN KEY (writeups_id) REFERENCES writeup(id);


--
-- Name: project_resources fkgvyjy4pwnuerf1ojtk71e4edo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_resources
  ADD CONSTRAINT fkgvyjy4pwnuerf1ojtk71e4edo FOREIGN KEY (project_id) REFERENCES project(id);


--
-- Name: sub_project_update_human_resources fkh07x7ns8wqykhal493de1i2tq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_update_human_resources
  ADD CONSTRAINT fkh07x7ns8wqykhal493de1i2tq FOREIGN KEY (sub_project_update_id) REFERENCES sub_project_update(id);


--
-- Name: sub_project_change_requests fkh7hng2nw9xcuov9jti561h1uj; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_change_requests
  ADD CONSTRAINT fkh7hng2nw9xcuov9jti561h1uj FOREIGN KEY (change_requests_id) REFERENCES change_request(id);


--
-- Name: construction_site_risks fkhp7n8j6x04eyybnbgsnlwfgk6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_risks
  ADD CONSTRAINT fkhp7n8j6x04eyybnbgsnlwfgk6 FOREIGN KEY (construction_site_id) REFERENCES construction_site(id);


--
-- Name: construction_site_update_pending_issues fkhrfyudbh8ba5bavqkayuhb85j; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_update_pending_issues
  ADD CONSTRAINT fkhrfyudbh8ba5bavqkayuhb85j FOREIGN KEY (construction_site_update_id) REFERENCES construction_site_update(id);


--
-- Name: construction_site_actions fki4v12ihll6io8hug1f325x5y3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_actions
  ADD CONSTRAINT fki4v12ihll6io8hug1f325x5y3 FOREIGN KEY (actions_id) REFERENCES action(id);


--
-- Name: project_reunion_plannings fkipja5823ydn3hrpvne99bi6sa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_reunion_plannings
  ADD CONSTRAINT fkipja5823ydn3hrpvne99bi6sa FOREIGN KEY (reunion_plannings_id) REFERENCES reunion_planning(id);


--
-- Name: construction_site_update_actions fkiuh74ep09fah4kboxulj81mtl; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_update_actions
  ADD CONSTRAINT fkiuh74ep09fah4kboxulj81mtl FOREIGN KEY (construction_site_update_id) REFERENCES construction_site_update(id);


--
-- Name: sub_project_actions fkj33swpnvrdewavfu6u71s6r4q; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_actions
  ADD CONSTRAINT fkj33swpnvrdewavfu6u71s6r4q FOREIGN KEY (sub_project_id) REFERENCES sub_project(id);


--
-- Name: project_documents fkj3tsnp0x28a8r5vklwp4l85e0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_documents
  ADD CONSTRAINT fkj3tsnp0x28a8r5vklwp4l85e0 FOREIGN KEY (project_id) REFERENCES project(id);


--
-- Name: construction_site_update_risks fkj4rx15s17a2syu9a7t9mjxwg8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_update_risks
  ADD CONSTRAINT fkj4rx15s17a2syu9a7t9mjxwg8 FOREIGN KEY (risks_id) REFERENCES risk(id);


--
-- Name: change_request fkjcojscffeql8rw2re4nyn0khi; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY change_request
  ADD CONSTRAINT fkjcojscffeql8rw2re4nyn0khi FOREIGN KEY (last_version_id) REFERENCES change_request(id);


--
-- Name: action fkjh6y55bxr3tfb3m77d6i1k559; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY action
  ADD CONSTRAINT fkjh6y55bxr3tfb3m77d6i1k559 FOREIGN KEY (supervisor_id) REFERENCES human_resource(id);


--
-- Name: pending_issue fkjuhfb6ec5gc48vksimm5a8l1c; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pending_issue
  ADD CONSTRAINT fkjuhfb6ec5gc48vksimm5a8l1c FOREIGN KEY (last_version_id) REFERENCES pending_issue(id);


--
-- Name: project_risks fkjw81e6752uavx40j2ks5r8m7u; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_risks
  ADD CONSTRAINT fkjw81e6752uavx40j2ks5r8m7u FOREIGN KEY (risks_id) REFERENCES risk(id);


--
-- Name: construction_site_actions fkkdanw9kdjokh0p1ad4iho60ge; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_actions
  ADD CONSTRAINT fkkdanw9kdjokh0p1ad4iho60ge FOREIGN KEY (construction_site_id) REFERENCES construction_site(id);


--
-- Name: construction_site_update_change_requests fkkhlnkm3vlk0y8igu0vbv68ges; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_update_change_requests
  ADD CONSTRAINT fkkhlnkm3vlk0y8igu0vbv68ges FOREIGN KEY (construction_site_update_id) REFERENCES construction_site_update(id);


--
-- Name: construction_site_update_documents fkksagnmdvk5rof23n1df9ctbpq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_update_documents
  ADD CONSTRAINT fkksagnmdvk5rof23n1df9ctbpq FOREIGN KEY (construction_site_update_id) REFERENCES construction_site_update(id);


--
-- Name: sub_project_human_resources fkksuy3moavckp0yu2h2ll91uec; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_human_resources
  ADD CONSTRAINT fkksuy3moavckp0yu2h2ll91uec FOREIGN KEY (sub_project_id) REFERENCES sub_project(id);


--
-- Name: construction_site_update_todos fkkxbv1v1lavq0jfct6im3d9ob6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_update_todos
  ADD CONSTRAINT fkkxbv1v1lavq0jfct6im3d9ob6 FOREIGN KEY (construction_site_update_id) REFERENCES construction_site_update(id);


--
-- Name: sub_project_update_pending_issues fkky1m4avfo1jo8oe451gu620n1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_update_pending_issues
  ADD CONSTRAINT fkky1m4avfo1jo8oe451gu620n1 FOREIGN KEY (pending_issues_id) REFERENCES pending_issue(id);


--
-- Name: sub_project_resources fkkysvakuxjnklan7it9vpbnc9s; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_resources
  ADD CONSTRAINT fkkysvakuxjnklan7it9vpbnc9s FOREIGN KEY (sub_project_id) REFERENCES sub_project(id);


--
-- Name: construction_site_resources fkl49g6qlpa6leq88vjva4njuw4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_resources
  ADD CONSTRAINT fkl49g6qlpa6leq88vjva4njuw4 FOREIGN KEY (resources_id) REFERENCES resource(id);


--
-- Name: project_update_milestones fkl4o1wooxjheamgs4wq0osk070; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_update_milestones
  ADD CONSTRAINT fkl4o1wooxjheamgs4wq0osk070 FOREIGN KEY (milestones_id) REFERENCES milestone(id);


--
-- Name: sub_project_update_risks fkl5a7jgqhwpd7jj18nvda2vgni; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_update_risks
  ADD CONSTRAINT fkl5a7jgqhwpd7jj18nvda2vgni FOREIGN KEY (sub_project_update_id) REFERENCES sub_project_update(id);


--
-- Name: reunion_planning fklbpseghk9f3csw2muqws97dv7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reunion_planning
  ADD CONSTRAINT fklbpseghk9f3csw2muqws97dv7 FOREIGN KEY (last_version_id) REFERENCES reunion_planning(id);


--
-- Name: contribution fklltff3umyak02l0nlov2f14lk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contribution
  ADD CONSTRAINT fklltff3umyak02l0nlov2f14lk FOREIGN KEY (project_id) REFERENCES project(id);


--
-- Name: communication_plan fklmvuvkh22wtt5inny3bstjbrd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY communication_plan
  ADD CONSTRAINT fklmvuvkh22wtt5inny3bstjbrd FOREIGN KEY (supervisor_id) REFERENCES human_resource(id);


--
-- Name: construction_site_update_pending_issues fklr7hc1vkn9ounxhq2cw9f71bo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_update_pending_issues
  ADD CONSTRAINT fklr7hc1vkn9ounxhq2cw9f71bo FOREIGN KEY (pending_issues_id) REFERENCES pending_issue(id);


--
-- Name: project_update_reunion_plannings fklsghhaqcmo84k7xry8bcsm2eo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_update_reunion_plannings
  ADD CONSTRAINT fklsghhaqcmo84k7xry8bcsm2eo FOREIGN KEY (project_update_id) REFERENCES project_update(id);


--
-- Name: communication_plan fklv9u22ankaqjw1ncjd9fj32s2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY communication_plan
  ADD CONSTRAINT fklv9u22ankaqjw1ncjd9fj32s2 FOREIGN KEY (last_version_id) REFERENCES communication_plan(id);


--
-- Name: project_update_actions fklvoxvcfipd44bdk56a6q9w7qk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_update_actions
  ADD CONSTRAINT fklvoxvcfipd44bdk56a6q9w7qk FOREIGN KEY (project_update_id) REFERENCES project_update(id);


--
-- Name: construction_site_change_requests fkmeto1goushtsywhtrtr9ac1qo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_change_requests
  ADD CONSTRAINT fkmeto1goushtsywhtrtr9ac1qo FOREIGN KEY (construction_site_id) REFERENCES construction_site(id);


--
-- Name: sub_project_update_todos fkmilxrp3ukpnss0dt3lijoht60; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_update_todos
  ADD CONSTRAINT fkmilxrp3ukpnss0dt3lijoht60 FOREIGN KEY (sub_project_update_id) REFERENCES sub_project_update(id);


--
-- Name: sub_project_todos fkmirpsbmkqm48f26v0l80heqm5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_todos
  ADD CONSTRAINT fkmirpsbmkqm48f26v0l80heqm5 FOREIGN KEY (todos_id) REFERENCES todo(id);


--
-- Name: project_update_change_requests fkml1jfdd68pijc3fbxe619f8cl; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_update_change_requests
  ADD CONSTRAINT fkml1jfdd68pijc3fbxe619f8cl FOREIGN KEY (project_update_id) REFERENCES project_update(id);


--
-- Name: project_update_resources fkmy8tqej2xq3517cxobyu2pn0v; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_update_resources
  ADD CONSTRAINT fkmy8tqej2xq3517cxobyu2pn0v FOREIGN KEY (resources_id) REFERENCES resource(id);


--
-- Name: project_update_actions fkn1t2xynccu3pf062cpn3u99el; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_update_actions
  ADD CONSTRAINT fkn1t2xynccu3pf062cpn3u99el FOREIGN KEY (actions_id) REFERENCES action(id);


--
-- Name: construction_site_pending_issues fkn65x5uh4lb418xume61qbfesp; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_pending_issues
  ADD CONSTRAINT fkn65x5uh4lb418xume61qbfesp FOREIGN KEY (construction_site_id) REFERENCES construction_site(id);


--
-- Name: project_update_pending_issues fknbm87p0j1k70it67gwsrvttx7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_update_pending_issues
  ADD CONSTRAINT fknbm87p0j1k70it67gwsrvttx7 FOREIGN KEY (project_update_id) REFERENCES project_update(id);


--
-- Name: pending_issue fknd8x8279iec1a5liwxr5sk6l8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pending_issue
  ADD CONSTRAINT fknd8x8279iec1a5liwxr5sk6l8 FOREIGN KEY (supervisor_id) REFERENCES human_resource(id);


--
-- Name: project_archived_updates fkngkj55u24ft7y5cxljjh50j0v; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_archived_updates
  ADD CONSTRAINT fkngkj55u24ft7y5cxljjh50j0v FOREIGN KEY (project_id) REFERENCES project(id);


--
-- Name: sub_project_update_actions fknh139tgil3xcy398wgn11jifi; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_update_actions
  ADD CONSTRAINT fknh139tgil3xcy398wgn11jifi FOREIGN KEY (actions_id) REFERENCES action(id);


--
-- Name: construction_site_update_actions fknsjq19usf4aq9xhvxa7rltmcj; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_update_actions
  ADD CONSTRAINT fknsjq19usf4aq9xhvxa7rltmcj FOREIGN KEY (actions_id) REFERENCES action(id);


--
-- Name: project_todos fknvqiyxcfumw4hoetm9853b06v; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_todos
  ADD CONSTRAINT fknvqiyxcfumw4hoetm9853b06v FOREIGN KEY (todos_id) REFERENCES todo(id);


--
-- Name: project_update_todos fko8g80ek6w33sxblkuik5fn95t; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_update_todos
  ADD CONSTRAINT fko8g80ek6w33sxblkuik5fn95t FOREIGN KEY (todos_id) REFERENCES todo(id);


--
-- Name: project_construction_sites fkoorr4mi01s40npkrcijko392m; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_construction_sites
  ADD CONSTRAINT fkoorr4mi01s40npkrcijko392m FOREIGN KEY (construction_sites_id) REFERENCES construction_site(id);


--
-- Name: sub_project_construction_sites fkp9xpraaykbyjhai3567dnlx76; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_construction_sites
  ADD CONSTRAINT fkp9xpraaykbyjhai3567dnlx76 FOREIGN KEY (sub_project_id) REFERENCES sub_project(id);


--
-- Name: project_update_milestones fkpb4el0skb149l4cdk8xmwx32o; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_update_milestones
  ADD CONSTRAINT fkpb4el0skb149l4cdk8xmwx32o FOREIGN KEY (project_update_id) REFERENCES project_update(id);


--
-- Name: sub_project_risks fkpeayt50ncgqnvgi65gpf9ukep; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_risks
  ADD CONSTRAINT fkpeayt50ncgqnvgi65gpf9ukep FOREIGN KEY (sub_project_id) REFERENCES sub_project(id);


--
-- Name: construction_site_pending_issues fkpixb0djksr80rb97wf8o6ksw2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_pending_issues
  ADD CONSTRAINT fkpixb0djksr80rb97wf8o6ksw2 FOREIGN KEY (pending_issues_id) REFERENCES pending_issue(id);


--
-- Name: project_sub_projects fkppstwi84uvyaqhu4m0jnu7auw; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_sub_projects
  ADD CONSTRAINT fkppstwi84uvyaqhu4m0jnu7auw FOREIGN KEY (project_id) REFERENCES project(id);


--
-- Name: project_update_communication_plans fkqb7i0rnmn3uip2mry97oijw1n; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_update_communication_plans
  ADD CONSTRAINT fkqb7i0rnmn3uip2mry97oijw1n FOREIGN KEY (project_update_id) REFERENCES project_update(id);


--
-- Name: project_resources fkqloe28wnxlwb5plj7msbnf2j3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_resources
  ADD CONSTRAINT fkqloe28wnxlwb5plj7msbnf2j3 FOREIGN KEY (resources_id) REFERENCES resource(id);


--
-- Name: project_writeups fkqt0eeoppotmqqg1b55wy1270s; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_writeups
  ADD CONSTRAINT fkqt0eeoppotmqqg1b55wy1270s FOREIGN KEY (project_id) REFERENCES project(id);


--
-- Name: construction_site_resources fkr0hs38s7p8vvqtjwy8efe06co; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_resources
  ADD CONSTRAINT fkr0hs38s7p8vvqtjwy8efe06co FOREIGN KEY (construction_site_id) REFERENCES construction_site(id);


--
-- Name: sub_project_milestones fkrfgqd9vkwvyjf3ajre7x3g0j2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_milestones
  ADD CONSTRAINT fkrfgqd9vkwvyjf3ajre7x3g0j2 FOREIGN KEY (sub_project_id) REFERENCES sub_project(id);


--
-- Name: sub_project_update_change_requests fks9u8r3m28tv6qx49dyqgu16hk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_update_change_requests
  ADD CONSTRAINT fks9u8r3m28tv6qx49dyqgu16hk FOREIGN KEY (sub_project_update_id) REFERENCES sub_project_update(id);


--
-- Name: project_human_resources fksbx3q2fcbk64vo8gigeaulevb; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_human_resources
  ADD CONSTRAINT fksbx3q2fcbk64vo8gigeaulevb FOREIGN KEY (project_id) REFERENCES project(id);


--
-- Name: sub_project_update_actions fksdob8y5k9jyaiwe75vk2lwwjp; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_update_actions
  ADD CONSTRAINT fksdob8y5k9jyaiwe75vk2lwwjp FOREIGN KEY (sub_project_update_id) REFERENCES sub_project_update(id);


--
-- Name: project_sub_projects fksimevmc4nbcnqj4hyew29c8py; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_sub_projects
  ADD CONSTRAINT fksimevmc4nbcnqj4hyew29c8py FOREIGN KEY (sub_projects_id) REFERENCES sub_project(id);


--
-- Name: project_actions fkswutxdivyd921llq6pynqainv; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_actions
  ADD CONSTRAINT fkswutxdivyd921llq6pynqainv FOREIGN KEY (project_id) REFERENCES project(id);


--
-- Name: project_update_communication_plans fksy2mvcovr4x3v4rywm01rnf1j; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_update_communication_plans
  ADD CONSTRAINT fksy2mvcovr4x3v4rywm01rnf1j FOREIGN KEY (communication_plans_id) REFERENCES communication_plan(id);


--
-- Name: risk fkt0msm0xsc8w6ygtaapnwa64un; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY risk
  ADD CONSTRAINT fkt0msm0xsc8w6ygtaapnwa64un FOREIGN KEY (last_version_id) REFERENCES risk(id);


--
-- Name: project_change_requests fkt6lmm2qiobcystm1qoy8ava4a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_change_requests
  ADD CONSTRAINT fkt6lmm2qiobcystm1qoy8ava4a FOREIGN KEY (change_requests_id) REFERENCES change_request(id);


--
-- Name: project_change_requests fkt8cskwvkngbgurpt039a4vjrp; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_change_requests
  ADD CONSTRAINT fkt8cskwvkngbgurpt039a4vjrp FOREIGN KEY (project_id) REFERENCES project(id);


--
-- Name: project_update_resources fktcocu4n2sfjd9xvi0jvhmi8w1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY project_update_resources
  ADD CONSTRAINT fktcocu4n2sfjd9xvi0jvhmi8w1 FOREIGN KEY (project_update_id) REFERENCES project_update(id);


--
-- Name: sub_project_update_human_resources fkthfvleg00t82rr81l7km4sg2f; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_project_update_human_resources
  ADD CONSTRAINT fkthfvleg00t82rr81l7km4sg2f FOREIGN KEY (human_resources_id) REFERENCES human_resource(id);


--
-- Name: construction_site_update_change_requests fktperqcc1v28maskf5r1ile5fh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY construction_site_update_change_requests
  ADD CONSTRAINT fktperqcc1v28maskf5r1ile5fh FOREIGN KEY (change_requests_id) REFERENCES change_request(id);


--
-- PostgreSQL database dump complete
--


-- populate with user (login:"Frodo"; password:"Baggins")
INSERT INTO user_account (id, login, password_hash, password_salt, first_name, last_name) VALUES
  ('73c60097-4c7c-4c74-a347-5f69fcaef9fb', 'Frodo',
   E'\\x6DB3B66707B40240B7DD71B549AE1536EF6E52C9F0FDCCE781F9AC1EEB574C0B', E'\\x0A2EBE7A87F3504A2C6F8846E7EDA2AF',
   'Frodo', 'Baggins');
-- populate with user (login:"Bilbo"; password:"Baggins")
INSERT INTO user_account (id, login, password_hash, password_salt, first_name, last_name) VALUES
  ('98c60097-4c7c-4c74-a347-5f69fcaef9da', 'Bilbo',
   E'\\x6DB3B66707B40240B7DD71B549AE1536EF6E52C9F0FDCCE781F9AC1EEB574C0B', E'\\x0A2EBE7A87F3504A2C6F8846E7EDA2AF',
   'Bilbo', 'Baggins');

-- Populate with resources
INSERT INTO human_resource (id, reference, name, last_version_id)
VALUES ('13814d0f-4e6c-4739-bac3-a011b8bbfdd3', 'd6b1719c-7a92-4aca-86f8-5129a3b288fc', 'Ali', NULL);
INSERT INTO human_resource (id, reference, name, last_version_id)
VALUES ('724a77b9-8a4d-4d81-9e76-ca87a8534395', '992f27a5-55df-4ab6-b6fe-889378a7efe7', 'Fatma', NULL);

