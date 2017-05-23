--
-- PostgreSQL database dump
--

-- SET THE <tenant_schema_name> HERE  !
SET search_path = "43d44eae-cd10-4ffb-97e1-c3e189119659";

-- Dumped from database version 9.6.2
-- Dumped by pg_dump version 9.6.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = ON;
SET check_function_bodies = FALSE;
SET client_min_messages = WARNING;
SET row_security = OFF;

SET default_tablespace = '';

SET default_with_oids = FALSE;

--
-- Name: action; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE action (
  id                   UUID                        NOT NULL,
  reference            UUID                        NOT NULL,
  advancement          INTEGER                     NOT NULL,
  closure_date         TIMESTAMP WITHOUT TIME ZONE,
  closure_planned_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  comment              CHARACTER VARYING(255),
  creation_date        TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  description          CHARACTER VARYING(255)      NOT NULL,
  priority             INTEGER                     NOT NULL,
  status               INTEGER                     NOT NULL,
  last_version_id      UUID,
  supervisor_id        UUID                        NOT NULL
);


ALTER TABLE action
  OWNER TO postgres;

--
-- Name: change_request; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE change_request (
  id                    UUID                        NOT NULL,
  reference             UUID                        NOT NULL,
  decision_date         TIMESTAMP WITHOUT TIME ZONE,
  decision_planned_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  description           CHARACTER VARYING(255)      NOT NULL,
  impacts               CHARACTER VARYING(255),
  priority              INTEGER                     NOT NULL,
  request_date          TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  requester             CHARACTER VARYING(255)      NOT NULL,
  status                INTEGER                     NOT NULL,
  last_version_id       UUID
);


ALTER TABLE change_request
  OWNER TO postgres;

--
-- Name: communication_plan; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE communication_plan (
  id              UUID                   NOT NULL,
  reference       UUID                   NOT NULL,
  name            CHARACTER VARYING(255) NOT NULL,
  last_version_id UUID,
  supervisor_id   UUID                   NOT NULL
);


ALTER TABLE communication_plan
  OWNER TO postgres;

--
-- Name: construction_site; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE construction_site (
  dtype                  CHARACTER VARYING(31)       NOT NULL,
  id                     UUID                        NOT NULL,
  advancement            INTEGER                     NOT NULL,
  budget_consumed        NUMERIC(19, 2),
  budget_initial         NUMERIC(19, 2),
  budget_to_consume      NUMERIC(19, 2),
  charge_consumed        NUMERIC(10, 0),
  charge_prevision       NUMERIC(10, 0),
  status                 INTEGER                     NOT NULL,
  comment                CHARACTER VARYING(255),
  end_date               TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  goal                   CHARACTER VARYING(255),
  name                   CHARACTER VARYING(255)      NOT NULL,
  start_date             TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  final_client           CHARACTER VARYING(255),
  history_decisions      CHARACTER VARYING(255),
  hypotheses_constraints CHARACTER VARYING(255),
  main_contact           CHARACTER VARYING(255),
  sponsors               CHARACTER VARYING(255),
  entity_id              UUID
);


ALTER TABLE construction_site
  OWNER TO postgres;

--
-- Name: construction_site_actions; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE construction_site_actions (
  construction_site_id UUID NOT NULL,
  actions_id           UUID NOT NULL
);


ALTER TABLE construction_site_actions
  OWNER TO postgres;

--
-- Name: construction_site_archived_updates; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE construction_site_archived_updates (
  construction_site_id UUID NOT NULL,
  archived_updates_id  UUID NOT NULL
);


ALTER TABLE construction_site_archived_updates
  OWNER TO postgres;

--
-- Name: construction_site_change_requests; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE construction_site_change_requests (
  construction_site_id UUID NOT NULL,
  change_requests_id   UUID NOT NULL
);


ALTER TABLE construction_site_change_requests
  OWNER TO postgres;

--
-- Name: construction_site_communication_plans; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE construction_site_communication_plans (
  construction_site_id   UUID NOT NULL,
  communication_plans_id UUID NOT NULL
);


ALTER TABLE construction_site_communication_plans
  OWNER TO postgres;

--
-- Name: construction_site_construction_sites; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE construction_site_construction_sites (
  sub_project_id        UUID NOT NULL,
  construction_sites_id UUID NOT NULL
);


ALTER TABLE construction_site_construction_sites
  OWNER TO postgres;

--
-- Name: construction_site_documents; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE construction_site_documents (
  construction_site_id UUID NOT NULL,
  documents_id         UUID NOT NULL
);


ALTER TABLE construction_site_documents
  OWNER TO postgres;

--
-- Name: construction_site_human_resources; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE construction_site_human_resources (
  construction_site_id UUID NOT NULL,
  human_resources_id   UUID NOT NULL
);


ALTER TABLE construction_site_human_resources
  OWNER TO postgres;

--
-- Name: construction_site_milestones; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE construction_site_milestones (
  construction_site_id UUID NOT NULL,
  milestones_id        UUID NOT NULL
);


ALTER TABLE construction_site_milestones
  OWNER TO postgres;

--
-- Name: construction_site_pending_issues; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE construction_site_pending_issues (
  construction_site_id UUID NOT NULL,
  pending_issues_id    UUID NOT NULL
);


ALTER TABLE construction_site_pending_issues
  OWNER TO postgres;

--
-- Name: construction_site_resources; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE construction_site_resources (
  construction_site_id UUID NOT NULL,
  resources_id         UUID NOT NULL
);


ALTER TABLE construction_site_resources
  OWNER TO postgres;

--
-- Name: construction_site_reunion_plannings; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE construction_site_reunion_plannings (
  construction_site_id UUID NOT NULL,
  reunion_plannings_id UUID NOT NULL
);


ALTER TABLE construction_site_reunion_plannings
  OWNER TO postgres;

--
-- Name: construction_site_risks; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE construction_site_risks (
  construction_site_id UUID NOT NULL,
  risks_id             UUID NOT NULL
);


ALTER TABLE construction_site_risks
  OWNER TO postgres;

--
-- Name: construction_site_sub_projects; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE construction_site_sub_projects (
  project_id      UUID NOT NULL,
  sub_projects_id UUID NOT NULL
);


ALTER TABLE construction_site_sub_projects
  OWNER TO postgres;

--
-- Name: construction_site_todos; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE construction_site_todos (
  construction_site_id UUID NOT NULL,
  todos_id             UUID NOT NULL
);


ALTER TABLE construction_site_todos
  OWNER TO postgres;

--
-- Name: construction_site_writeups; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE construction_site_writeups (
  construction_site_id UUID NOT NULL,
  writeups_id          UUID NOT NULL
);


ALTER TABLE construction_site_writeups
  OWNER TO postgres;

--
-- Name: contribution; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE contribution (
  id              UUID    NOT NULL,
  role            INTEGER NOT NULL,
  user_id         UUID    NOT NULL,
  organization_id UUID    NOT NULL,
  project_id      UUID    NOT NULL
);


ALTER TABLE contribution
  OWNER TO postgres;

--
-- Name: contributor_organization; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE contributor_organization (
  id                 UUID                   NOT NULL,
  address            CHARACTER VARYING(255),
  complementary_info CHARACTER VARYING(255),
  email              CHARACTER VARYING(254) NOT NULL,
  fax_numbers        CHARACTER VARYING(150),
  full_name          CHARACTER VARYING(100) NOT NULL,
  legal_type         INTEGER,
  role               INTEGER                NOT NULL,
  telephone_numbers  CHARACTER VARYING(150),
  trade_register     CHARACTER VARYING(20)
);


ALTER TABLE contributor_organization
  OWNER TO postgres;

--
-- Name: contributor_organization_contributions; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE contributor_organization_contributions (
  contributor_organization_id UUID NOT NULL,
  contributions_id            UUID NOT NULL
);


ALTER TABLE contributor_organization_contributions
  OWNER TO postgres;

--
-- Name: document; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE document (
  id              UUID                   NOT NULL,
  reference       UUID                   NOT NULL,
  name            CHARACTER VARYING(255) NOT NULL,
  os_path         CHARACTER VARYING(260) NOT NULL,
  last_version_id UUID
);


ALTER TABLE document
  OWNER TO postgres;

--
-- Name: human_resource; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE human_resource (
  id              UUID                   NOT NULL,
  reference       UUID                   NOT NULL,
  name            CHARACTER VARYING(255) NOT NULL,
  last_version_id UUID
);


ALTER TABLE human_resource
  OWNER TO postgres;

--
-- Name: milestone; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE milestone (
  id              UUID                        NOT NULL,
  reference       UUID                        NOT NULL,
  description     CHARACTER VARYING(255),
  due_date        TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  name            CHARACTER VARYING(255)      NOT NULL,
  last_version_id UUID
);


ALTER TABLE milestone
  OWNER TO postgres;

--
-- Name: pending_issue; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE pending_issue (
  id                      UUID                        NOT NULL,
  reference               UUID                        NOT NULL,
  creation_date           TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  decisions               CHARACTER VARYING(255),
  description             CHARACTER VARYING(255)      NOT NULL,
  impacts                 CHARACTER VARYING(255),
  priority                INTEGER                     NOT NULL,
  resolution_date         TIMESTAMP WITHOUT TIME ZONE,
  resolution_planned_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  status                  INTEGER                     NOT NULL,
  last_version_id         UUID,
  supervisor_id           UUID                        NOT NULL
);


ALTER TABLE pending_issue
  OWNER TO postgres;

--
-- Name: project_level_update; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE project_level_update (
  id                UUID                        NOT NULL,
  advancement       INTEGER                     NOT NULL,
  budget_consumed   NUMERIC(19, 2),
  budget_initial    NUMERIC(19, 2),
  budget_to_consume NUMERIC(19, 2),
  charge_consumed   NUMERIC(10, 0),
  charge_prevision  NUMERIC(10, 0),
  status            INTEGER                     NOT NULL,
  update_time       TIMESTAMP WITHOUT TIME ZONE NOT NULL
);


ALTER TABLE project_level_update
  OWNER TO postgres;

--
-- Name: project_level_update_actions; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE project_level_update_actions (
  project_level_update_id UUID NOT NULL,
  actions_id              UUID NOT NULL
);


ALTER TABLE project_level_update_actions
  OWNER TO postgres;

--
-- Name: project_level_update_change_requests; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE project_level_update_change_requests (
  project_level_update_id UUID NOT NULL,
  change_requests_id      UUID NOT NULL
);


ALTER TABLE project_level_update_change_requests
  OWNER TO postgres;

--
-- Name: project_level_update_communication_plans; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE project_level_update_communication_plans (
  project_level_update_id UUID NOT NULL,
  communication_plans_id  UUID NOT NULL
);


ALTER TABLE project_level_update_communication_plans
  OWNER TO postgres;

--
-- Name: project_level_update_documents; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE project_level_update_documents (
  project_level_update_id UUID NOT NULL,
  documents_id            UUID NOT NULL
);


ALTER TABLE project_level_update_documents
  OWNER TO postgres;

--
-- Name: project_level_update_human_resources; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE project_level_update_human_resources (
  project_level_update_id UUID NOT NULL,
  human_resources_id      UUID NOT NULL
);


ALTER TABLE project_level_update_human_resources
  OWNER TO postgres;

--
-- Name: project_level_update_milestones; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE project_level_update_milestones (
  project_level_update_id UUID NOT NULL,
  milestones_id           UUID NOT NULL
);


ALTER TABLE project_level_update_milestones
  OWNER TO postgres;

--
-- Name: project_level_update_pending_issues; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE project_level_update_pending_issues (
  project_level_update_id UUID NOT NULL,
  pending_issues_id       UUID NOT NULL
);


ALTER TABLE project_level_update_pending_issues
  OWNER TO postgres;

--
-- Name: project_level_update_resources; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE project_level_update_resources (
  project_level_update_id UUID NOT NULL,
  resources_id            UUID NOT NULL
);


ALTER TABLE project_level_update_resources
  OWNER TO postgres;

--
-- Name: project_level_update_reunion_plannings; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE project_level_update_reunion_plannings (
  project_level_update_id UUID NOT NULL,
  reunion_plannings_id    UUID NOT NULL
);


ALTER TABLE project_level_update_reunion_plannings
  OWNER TO postgres;

--
-- Name: project_level_update_risks; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE project_level_update_risks (
  project_level_update_id UUID NOT NULL,
  risks_id                UUID NOT NULL
);


ALTER TABLE project_level_update_risks
  OWNER TO postgres;

--
-- Name: project_level_update_todos; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE project_level_update_todos (
  project_level_update_id UUID NOT NULL,
  todos_id                UUID NOT NULL
);


ALTER TABLE project_level_update_todos
  OWNER TO postgres;

--
-- Name: project_level_update_writeups; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE project_level_update_writeups (
  project_level_update_id UUID NOT NULL,
  writeups_id             UUID NOT NULL
);


ALTER TABLE project_level_update_writeups
  OWNER TO postgres;

--
-- Name: projects_entity; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE projects_entity (
  id   UUID                   NOT NULL,
  name CHARACTER VARYING(100) NOT NULL
);


ALTER TABLE projects_entity
  OWNER TO postgres;

--
-- Name: resource; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE resource (
  id              UUID                   NOT NULL,
  reference       UUID                   NOT NULL,
  name            CHARACTER VARYING(100) NOT NULL,
  type            INTEGER                NOT NULL,
  last_version_id UUID
);


ALTER TABLE resource
  OWNER TO postgres;

--
-- Name: reunion_planning; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE reunion_planning (
  id              UUID                   NOT NULL,
  reference       UUID                   NOT NULL,
  date            TIMESTAMP WITHOUT TIME ZONE,
  location        CHARACTER VARYING(255),
  name            CHARACTER VARYING(255) NOT NULL,
  status          INTEGER                NOT NULL,
  last_version_id UUID
);


ALTER TABLE reunion_planning
  OWNER TO postgres;

--
-- Name: risk; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE risk (
  id                 UUID                        NOT NULL,
  reference          UUID                        NOT NULL,
  action_plan        CHARACTER VARYING(255)      NOT NULL,
  closure_date       TIMESTAMP WITHOUT TIME ZONE,
  comment            CHARACTER VARYING(255),
  decision           CHARACTER VARYING(255),
  description        CHARACTER VARYING(255)      NOT NULL,
  detection_date     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  impact             INTEGER                     NOT NULL,
  probability        INTEGER                     NOT NULL,
  qualification_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  status             INTEGER                     NOT NULL,
  last_version_id    UUID
);


ALTER TABLE risk
  OWNER TO postgres;

--
-- Name: tenant; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE tenant (
  id     UUID                   NOT NULL,
  pseudo CHARACTER VARYING(100) NOT NULL
);


ALTER TABLE tenant
  OWNER TO postgres;

--
-- Name: tenant_details; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE tenant_details (
  id                        UUID                   NOT NULL,
  address                   CHARACTER VARYING(255),
  client_code               CHARACTER VARYING(255) NOT NULL,
  complementary_info        CHARACTER VARYING(255),
  contact_email             CHARACTER VARYING(254),
  contact_name              CHARACTER VARYING(100),
  contact_telephone_numbers CHARACTER VARYING(100),
  email                     CHARACTER VARYING(254) NOT NULL,
  fax_numbers               CHARACTER VARYING(150),
  full_name                 CHARACTER VARYING(100) NOT NULL,
  telephone_numbers         CHARACTER VARYING(150),
  website_url               CHARACTER VARYING(2000)
);


ALTER TABLE tenant_details
  OWNER TO postgres;

--
-- Name: todo; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE todo (
  id              UUID                        NOT NULL,
  reference       UUID                        NOT NULL,
  charge          INTEGER                     NOT NULL,
  description     CHARACTER VARYING(255)      NOT NULL,
  estimation_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  last_version_id UUID
);


ALTER TABLE todo
  OWNER TO postgres;

--
-- Name: user_account; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE user_account (
  id            UUID                  NOT NULL,
  first_name    CHARACTER VARYING(40) NOT NULL,
  last_name     CHARACTER VARYING(40) NOT NULL,
  login         CHARACTER VARYING(10) NOT NULL,
  password_hash BYTEA                 NOT NULL,
  password_salt BYTEA                 NOT NULL
);


ALTER TABLE user_account
  OWNER TO postgres;

--
-- Name: writeup; Type: TABLE; Schema: <tenant_schema_name>; Owner: postgres
--

CREATE TABLE writeup (
  id              UUID                   NOT NULL,
  reference       UUID                   NOT NULL,
  description     CHARACTER VARYING(255) NOT NULL,
  name            CHARACTER VARYING(255) NOT NULL,
  last_version_id UUID
);


ALTER TABLE writeup
  OWNER TO postgres;

--
-- Name: action action_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY action
  ADD CONSTRAINT action_pkey PRIMARY KEY (id);

--
-- Name: change_request change_request_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY change_request
  ADD CONSTRAINT change_request_pkey PRIMARY KEY (id);

--
-- Name: communication_plan communication_plan_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY communication_plan
  ADD CONSTRAINT communication_plan_pkey PRIMARY KEY (id);

--
-- Name: construction_site_actions construction_site_actions_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_actions
  ADD CONSTRAINT construction_site_actions_pkey PRIMARY KEY (construction_site_id, actions_id);

--
-- Name: construction_site_archived_updates construction_site_archived_updates_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_archived_updates
  ADD CONSTRAINT construction_site_archived_updates_pkey PRIMARY KEY (construction_site_id, archived_updates_id);

--
-- Name: construction_site_change_requests construction_site_change_requests_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_change_requests
  ADD CONSTRAINT construction_site_change_requests_pkey PRIMARY KEY (construction_site_id, change_requests_id);

--
-- Name: construction_site_communication_plans construction_site_communication_plans_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_communication_plans
  ADD CONSTRAINT construction_site_communication_plans_pkey PRIMARY KEY (construction_site_id, communication_plans_id);

--
-- Name: construction_site_construction_sites construction_site_construction_sites_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_construction_sites
  ADD CONSTRAINT construction_site_construction_sites_pkey PRIMARY KEY (sub_project_id, construction_sites_id);

--
-- Name: construction_site_documents construction_site_documents_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_documents
  ADD CONSTRAINT construction_site_documents_pkey PRIMARY KEY (construction_site_id, documents_id);

--
-- Name: construction_site_human_resources construction_site_human_resources_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_human_resources
  ADD CONSTRAINT construction_site_human_resources_pkey PRIMARY KEY (construction_site_id, human_resources_id);

--
-- Name: construction_site_milestones construction_site_milestones_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_milestones
  ADD CONSTRAINT construction_site_milestones_pkey PRIMARY KEY (construction_site_id, milestones_id);

--
-- Name: construction_site_pending_issues construction_site_pending_issues_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_pending_issues
  ADD CONSTRAINT construction_site_pending_issues_pkey PRIMARY KEY (construction_site_id, pending_issues_id);

--
-- Name: construction_site construction_site_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site
  ADD CONSTRAINT construction_site_pkey PRIMARY KEY (id);

--
-- Name: construction_site_resources construction_site_resources_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_resources
  ADD CONSTRAINT construction_site_resources_pkey PRIMARY KEY (construction_site_id, resources_id);

--
-- Name: construction_site_reunion_plannings construction_site_reunion_plannings_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_reunion_plannings
  ADD CONSTRAINT construction_site_reunion_plannings_pkey PRIMARY KEY (construction_site_id, reunion_plannings_id);

--
-- Name: construction_site_risks construction_site_risks_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_risks
  ADD CONSTRAINT construction_site_risks_pkey PRIMARY KEY (construction_site_id, risks_id);

--
-- Name: construction_site_sub_projects construction_site_sub_projects_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_sub_projects
  ADD CONSTRAINT construction_site_sub_projects_pkey PRIMARY KEY (project_id, sub_projects_id);

--
-- Name: construction_site_todos construction_site_todos_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_todos
  ADD CONSTRAINT construction_site_todos_pkey PRIMARY KEY (construction_site_id, todos_id);

--
-- Name: construction_site_writeups construction_site_writeups_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_writeups
  ADD CONSTRAINT construction_site_writeups_pkey PRIMARY KEY (construction_site_id, writeups_id);

--
-- Name: contribution contribution_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY contribution
  ADD CONSTRAINT contribution_pkey PRIMARY KEY (id);

--
-- Name: contributor_organization contributor_organization_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY contributor_organization
  ADD CONSTRAINT contributor_organization_pkey PRIMARY KEY (id);

--
-- Name: document document_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY document
  ADD CONSTRAINT document_pkey PRIMARY KEY (id);

--
-- Name: human_resource human_resource_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY human_resource
  ADD CONSTRAINT human_resource_pkey PRIMARY KEY (id);

--
-- Name: milestone milestone_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY milestone
  ADD CONSTRAINT milestone_pkey PRIMARY KEY (id);

--
-- Name: pending_issue pending_issue_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY pending_issue
  ADD CONSTRAINT pending_issue_pkey PRIMARY KEY (id);

--
-- Name: project_level_update_actions project_level_update_actions_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY project_level_update_actions
  ADD CONSTRAINT project_level_update_actions_pkey PRIMARY KEY (project_level_update_id, actions_id);

--
-- Name: project_level_update_change_requests project_level_update_change_requests_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY project_level_update_change_requests
  ADD CONSTRAINT project_level_update_change_requests_pkey PRIMARY KEY (project_level_update_id, change_requests_id);

--
-- Name: project_level_update_communication_plans project_level_update_communication_plans_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY project_level_update_communication_plans
  ADD CONSTRAINT project_level_update_communication_plans_pkey PRIMARY KEY (project_level_update_id, communication_plans_id);

--
-- Name: project_level_update_documents project_level_update_documents_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY project_level_update_documents
  ADD CONSTRAINT project_level_update_documents_pkey PRIMARY KEY (project_level_update_id, documents_id);

--
-- Name: project_level_update_human_resources project_level_update_human_resources_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY project_level_update_human_resources
  ADD CONSTRAINT project_level_update_human_resources_pkey PRIMARY KEY (project_level_update_id, human_resources_id);

--
-- Name: project_level_update_milestones project_level_update_milestones_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY project_level_update_milestones
  ADD CONSTRAINT project_level_update_milestones_pkey PRIMARY KEY (project_level_update_id, milestones_id);

--
-- Name: project_level_update_pending_issues project_level_update_pending_issues_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY project_level_update_pending_issues
  ADD CONSTRAINT project_level_update_pending_issues_pkey PRIMARY KEY (project_level_update_id, pending_issues_id);

--
-- Name: project_level_update project_level_update_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY project_level_update
  ADD CONSTRAINT project_level_update_pkey PRIMARY KEY (id);

--
-- Name: project_level_update_resources project_level_update_resources_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY project_level_update_resources
  ADD CONSTRAINT project_level_update_resources_pkey PRIMARY KEY (project_level_update_id, resources_id);

--
-- Name: project_level_update_reunion_plannings project_level_update_reunion_plannings_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY project_level_update_reunion_plannings
  ADD CONSTRAINT project_level_update_reunion_plannings_pkey PRIMARY KEY (project_level_update_id, reunion_plannings_id);

--
-- Name: project_level_update_risks project_level_update_risks_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY project_level_update_risks
  ADD CONSTRAINT project_level_update_risks_pkey PRIMARY KEY (project_level_update_id, risks_id);

--
-- Name: project_level_update_todos project_level_update_todos_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY project_level_update_todos
  ADD CONSTRAINT project_level_update_todos_pkey PRIMARY KEY (project_level_update_id, todos_id);

--
-- Name: project_level_update_writeups project_level_update_writeups_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY project_level_update_writeups
  ADD CONSTRAINT project_level_update_writeups_pkey PRIMARY KEY (project_level_update_id, writeups_id);

--
-- Name: projects_entity projects_entity_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY projects_entity
  ADD CONSTRAINT projects_entity_pkey PRIMARY KEY (id);

--
-- Name: resource resource_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY resource
  ADD CONSTRAINT resource_pkey PRIMARY KEY (id);

--
-- Name: reunion_planning reunion_planning_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY reunion_planning
  ADD CONSTRAINT reunion_planning_pkey PRIMARY KEY (id);

--
-- Name: risk risk_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY risk
  ADD CONSTRAINT risk_pkey PRIMARY KEY (id);

--
-- Name: tenant_details tenant_details_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY tenant_details
  ADD CONSTRAINT tenant_details_pkey PRIMARY KEY (id);

--
-- Name: tenant tenant_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY tenant
  ADD CONSTRAINT tenant_pkey PRIMARY KEY (id);

--
-- Name: todo todo_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY todo
  ADD CONSTRAINT todo_pkey PRIMARY KEY (id);

--
-- Name: tenant_details uk_7uh73xcewr7s2qad802ml6ctq; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY tenant_details
  ADD CONSTRAINT uk_7uh73xcewr7s2qad802ml6ctq UNIQUE (client_code);

--
-- Name: contributor_organization_contributions uk_9yoop07gt6w8r4foc7vfelhjr; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY contributor_organization_contributions
  ADD CONSTRAINT uk_9yoop07gt6w8r4foc7vfelhjr UNIQUE (contributions_id);

--
-- Name: construction_site_archived_updates uk_es2vsv49ne0gbi1dspt03q2fd; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_archived_updates
  ADD CONSTRAINT uk_es2vsv49ne0gbi1dspt03q2fd UNIQUE (archived_updates_id);

--
-- Name: tenant uk_gn6vtsavd1ajpbieirvoiyup5; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY tenant
  ADD CONSTRAINT uk_gn6vtsavd1ajpbieirvoiyup5 UNIQUE (pseudo);

--
-- Name: construction_site_sub_projects uk_nu18994urp2ouilfie5u107y1; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_sub_projects
  ADD CONSTRAINT uk_nu18994urp2ouilfie5u107y1 UNIQUE (sub_projects_id);

--
-- Name: construction_site_construction_sites uk_ogmrx3ge7thcyud5vlpsndru6; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_construction_sites
  ADD CONSTRAINT uk_ogmrx3ge7thcyud5vlpsndru6 UNIQUE (construction_sites_id);

--
-- Name: user_account uk_plpggm55i6uhyv404q6pyu0ub; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY user_account
  ADD CONSTRAINT uk_plpggm55i6uhyv404q6pyu0ub UNIQUE (login);

--
-- Name: contribution ukemf5t25e81c8jldhois4tofpp; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY contribution
  ADD CONSTRAINT ukemf5t25e81c8jldhois4tofpp UNIQUE (user_id, project_id, role);

--
-- Name: user_account user_account_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY user_account
  ADD CONSTRAINT user_account_pkey PRIMARY KEY (id);

--
-- Name: writeup writeup_pkey; Type: CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY writeup
  ADD CONSTRAINT writeup_pkey PRIMARY KEY (id);

--
-- Name: construction_site_change_requests fk18sqaerap633595nd5836f3pc; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_change_requests
  ADD CONSTRAINT fk18sqaerap633595nd5836f3pc FOREIGN KEY (change_requests_id) REFERENCES change_request (id);

--
-- Name: construction_site_documents fk1gy4fgkiop0ewy7pm9msidfnq; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_documents
  ADD CONSTRAINT fk1gy4fgkiop0ewy7pm9msidfnq FOREIGN KEY (construction_site_id) REFERENCES construction_site (id);

--
-- Name: construction_site_documents fk1qrym9edj7fcowf7m46uiwigm; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_documents
  ADD CONSTRAINT fk1qrym9edj7fcowf7m46uiwigm FOREIGN KEY (documents_id) REFERENCES document (id);

--
-- Name: todo fk1y0y2r8kix9ont9i6md9qra8a; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY todo
  ADD CONSTRAINT fk1y0y2r8kix9ont9i6md9qra8a FOREIGN KEY (last_version_id) REFERENCES todo (id);

--
-- Name: construction_site_milestones fk237q06rxe1x14par0l79jftcm; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_milestones
  ADD CONSTRAINT fk237q06rxe1x14par0l79jftcm FOREIGN KEY (construction_site_id) REFERENCES construction_site (id);

--
-- Name: project_level_update_change_requests fk2hjp8n98xc4e4c5dr3k7uh5m5; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY project_level_update_change_requests
  ADD CONSTRAINT fk2hjp8n98xc4e4c5dr3k7uh5m5 FOREIGN KEY (project_level_update_id) REFERENCES project_level_update (id);

--
-- Name: construction_site_todos fk2klcqesn1dksw4t8mhk8btq8v; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_todos
  ADD CONSTRAINT fk2klcqesn1dksw4t8mhk8btq8v FOREIGN KEY (todos_id) REFERENCES todo (id);

--
-- Name: construction_site_construction_sites fk2vqx6g43l3eeu0fid0qvn06if; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_construction_sites
  ADD CONSTRAINT fk2vqx6g43l3eeu0fid0qvn06if FOREIGN KEY (construction_sites_id) REFERENCES construction_site (id);

--
-- Name: project_level_update_todos fk308rafb2w2glfvbwp8kmieg9k; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY project_level_update_todos
  ADD CONSTRAINT fk308rafb2w2glfvbwp8kmieg9k FOREIGN KEY (project_level_update_id) REFERENCES project_level_update (id);

--
-- Name: project_level_update_communication_plans fk40p8w8ar0e2tcat0k98r89s12; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY project_level_update_communication_plans
  ADD CONSTRAINT fk40p8w8ar0e2tcat0k98r89s12 FOREIGN KEY (project_level_update_id) REFERENCES project_level_update (id);

--
-- Name: construction_site_sub_projects fk421em0k8y9ba6vl5pmkhy43di; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_sub_projects
  ADD CONSTRAINT fk421em0k8y9ba6vl5pmkhy43di FOREIGN KEY (project_id) REFERENCES construction_site (id);

--
-- Name: construction_site_todos fk52jo7367dqh9yb8yyme04dcnj; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_todos
  ADD CONSTRAINT fk52jo7367dqh9yb8yyme04dcnj FOREIGN KEY (construction_site_id) REFERENCES construction_site (id);

--
-- Name: project_level_update_human_resources fk5k70o490u0ulutpga79x8y3f2; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY project_level_update_human_resources
  ADD CONSTRAINT fk5k70o490u0ulutpga79x8y3f2 FOREIGN KEY (human_resources_id) REFERENCES human_resource (id);

--
-- Name: project_level_update_pending_issues fk61qol6r4eenbcenl3odhfmsay; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY project_level_update_pending_issues
  ADD CONSTRAINT fk61qol6r4eenbcenl3odhfmsay FOREIGN KEY (project_level_update_id) REFERENCES project_level_update (id);

--
-- Name: contribution fk6djepkfe92sasp4em1bvewixh; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY contribution
  ADD CONSTRAINT fk6djepkfe92sasp4em1bvewixh FOREIGN KEY (project_id) REFERENCES construction_site (id);

--
-- Name: contribution fk6yldljk6wf209daq8cy937omx; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY contribution
  ADD CONSTRAINT fk6yldljk6wf209daq8cy937omx FOREIGN KEY (organization_id) REFERENCES contributor_organization (id);

--
-- Name: project_level_update_risks fk7b41mt5oa4xkcem8tcimynbai; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY project_level_update_risks
  ADD CONSTRAINT fk7b41mt5oa4xkcem8tcimynbai FOREIGN KEY (project_level_update_id) REFERENCES project_level_update (id);

--
-- Name: communication_plan fk7ghff83ibuitib8wm13ilf96c; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY communication_plan
  ADD CONSTRAINT fk7ghff83ibuitib8wm13ilf96c FOREIGN KEY (supervisor_id) REFERENCES resource (id);

--
-- Name: project_level_update_writeups fk807fh1ct8jvbj3e4gu2k7joj1; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY project_level_update_writeups
  ADD CONSTRAINT fk807fh1ct8jvbj3e4gu2k7joj1 FOREIGN KEY (project_level_update_id) REFERENCES project_level_update (id);

--
-- Name: construction_site_reunion_plannings fk81qou6rok2q5kg5qk4pchm064; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_reunion_plannings
  ADD CONSTRAINT fk81qou6rok2q5kg5qk4pchm064 FOREIGN KEY (construction_site_id) REFERENCES construction_site (id);

--
-- Name: human_resource fk8jf9hup47o5rtyai6623iypoc; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY human_resource
  ADD CONSTRAINT fk8jf9hup47o5rtyai6623iypoc FOREIGN KEY (last_version_id) REFERENCES human_resource (id);

--
-- Name: project_level_update_milestones fk8lqdul43nwnb11g9q2ohe1ysr; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY project_level_update_milestones
  ADD CONSTRAINT fk8lqdul43nwnb11g9q2ohe1ysr FOREIGN KEY (milestones_id) REFERENCES milestone (id);

--
-- Name: construction_site_milestones fk8nwb198v2mbnsvqmaokyxmiwv; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_milestones
  ADD CONSTRAINT fk8nwb198v2mbnsvqmaokyxmiwv FOREIGN KEY (milestones_id) REFERENCES milestone (id);

--
-- Name: contributor_organization_contributions fk8obwpt914s6lb6ljqih9ndtp0; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY contributor_organization_contributions
  ADD CONSTRAINT fk8obwpt914s6lb6ljqih9ndtp0 FOREIGN KEY (contributor_organization_id) REFERENCES contributor_organization (id);

--
-- Name: construction_site_archived_updates fk8vb7qgtiyf0si7071aa5f1qrk; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_archived_updates
  ADD CONSTRAINT fk8vb7qgtiyf0si7071aa5f1qrk FOREIGN KEY (construction_site_id) REFERENCES construction_site (id);

--
-- Name: project_level_update_resources fk8yon5u6h1ufnwc4vkwk4rpqo0; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY project_level_update_resources
  ADD CONSTRAINT fk8yon5u6h1ufnwc4vkwk4rpqo0 FOREIGN KEY (project_level_update_id) REFERENCES project_level_update (id);

--
-- Name: project_level_update_pending_issues fk9421g0d5ixdat3q0p91e9r0kd; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY project_level_update_pending_issues
  ADD CONSTRAINT fk9421g0d5ixdat3q0p91e9r0kd FOREIGN KEY (pending_issues_id) REFERENCES pending_issue (id);

--
-- Name: milestone fk9k1jmlvehvy8o1d6xjbqp9mi2; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY milestone
  ADD CONSTRAINT fk9k1jmlvehvy8o1d6xjbqp9mi2 FOREIGN KEY (last_version_id) REFERENCES milestone (id);

--
-- Name: project_level_update_actions fk9t7pksvxgd86tvvmx2cwsnwcf; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY project_level_update_actions
  ADD CONSTRAINT fk9t7pksvxgd86tvvmx2cwsnwcf FOREIGN KEY (project_level_update_id) REFERENCES project_level_update (id);

--
-- Name: construction_site_writeups fka01xues17kf8tf93ko7inic38; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_writeups
  ADD CONSTRAINT fka01xues17kf8tf93ko7inic38 FOREIGN KEY (writeups_id) REFERENCES writeup (id);

--
-- Name: contributor_organization_contributions fkal1o1nuf6crjy2h8q0umija97; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY contributor_organization_contributions
  ADD CONSTRAINT fkal1o1nuf6crjy2h8q0umija97 FOREIGN KEY (contributions_id) REFERENCES contribution (id);

--
-- Name: construction_site_sub_projects fkapby75m445sh70jlt30n8aqgl; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_sub_projects
  ADD CONSTRAINT fkapby75m445sh70jlt30n8aqgl FOREIGN KEY (sub_projects_id) REFERENCES construction_site (id);

--
-- Name: construction_site fkbavtoengmtratfcc9vff9acd8; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site
  ADD CONSTRAINT fkbavtoengmtratfcc9vff9acd8 FOREIGN KEY (entity_id) REFERENCES projects_entity (id);

--
-- Name: writeup fkbaxdf26dfx8hhufqrhmwx4qmt; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY writeup
  ADD CONSTRAINT fkbaxdf26dfx8hhufqrhmwx4qmt FOREIGN KEY (last_version_id) REFERENCES writeup (id);

--
-- Name: project_level_update_reunion_plannings fkbh0ch2jpr6dsjwjad1t9xitw4; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY project_level_update_reunion_plannings
  ADD CONSTRAINT fkbh0ch2jpr6dsjwjad1t9xitw4 FOREIGN KEY (project_level_update_id) REFERENCES project_level_update (id);

--
-- Name: document fkbpcwjq9iut4jaixq1a58pyuv9; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY document
  ADD CONSTRAINT fkbpcwjq9iut4jaixq1a58pyuv9 FOREIGN KEY (last_version_id) REFERENCES document (id);

--
-- Name: pending_issue fkbqf4to6tk25hymldg9tsxgtrr; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY pending_issue
  ADD CONSTRAINT fkbqf4to6tk25hymldg9tsxgtrr FOREIGN KEY (supervisor_id) REFERENCES resource (id);

--
-- Name: construction_site_communication_plans fkdbn00gnsvgk6ohhu3a8dbqm31; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_communication_plans
  ADD CONSTRAINT fkdbn00gnsvgk6ohhu3a8dbqm31 FOREIGN KEY (communication_plans_id) REFERENCES communication_plan (id);

--
-- Name: construction_site_human_resources fkddcct7ys69f0ra04jx62ljcxd; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_human_resources
  ADD CONSTRAINT fkddcct7ys69f0ra04jx62ljcxd FOREIGN KEY (construction_site_id) REFERENCES construction_site (id);

--
-- Name: construction_site_human_resources fkeax9icew72r12s8qe8rkcbuf2; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_human_resources
  ADD CONSTRAINT fkeax9icew72r12s8qe8rkcbuf2 FOREIGN KEY (human_resources_id) REFERENCES human_resource (id);

--
-- Name: construction_site_communication_plans fkeesfmy558msgccbkpf58kbwjh; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_communication_plans
  ADD CONSTRAINT fkeesfmy558msgccbkpf58kbwjh FOREIGN KEY (construction_site_id) REFERENCES construction_site (id);

--
-- Name: project_level_update_human_resources fkeh7k9g7n7fp3e405nl5gsq41l; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY project_level_update_human_resources
  ADD CONSTRAINT fkeh7k9g7n7fp3e405nl5gsq41l FOREIGN KEY (project_level_update_id) REFERENCES project_level_update (id);

--
-- Name: action fkeuxis3k71dj1x2sbslrntjyhn; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY action
  ADD CONSTRAINT fkeuxis3k71dj1x2sbslrntjyhn FOREIGN KEY (last_version_id) REFERENCES action (id);

--
-- Name: resource fkfc7go0qw7va8b492n4wc227x3; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY resource
  ADD CONSTRAINT fkfc7go0qw7va8b492n4wc227x3 FOREIGN KEY (last_version_id) REFERENCES resource (id);

--
-- Name: contribution fkfiq0ecipeu4419esqoktyosfo; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY contribution
  ADD CONSTRAINT fkfiq0ecipeu4419esqoktyosfo FOREIGN KEY (user_id) REFERENCES user_account (id);

--
-- Name: construction_site_risks fkg6e5lvku6txigmr4y57agkm50; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_risks
  ADD CONSTRAINT fkg6e5lvku6txigmr4y57agkm50 FOREIGN KEY (risks_id) REFERENCES risk (id);

--
-- Name: construction_site_construction_sites fkhjqo6fo2q0r4odb8kliqkfo6q; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_construction_sites
  ADD CONSTRAINT fkhjqo6fo2q0r4odb8kliqkfo6q FOREIGN KEY (sub_project_id) REFERENCES construction_site (id);

--
-- Name: project_level_update_documents fkhnk902ifpu870b43jd3di50e3; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY project_level_update_documents
  ADD CONSTRAINT fkhnk902ifpu870b43jd3di50e3 FOREIGN KEY (documents_id) REFERENCES document (id);

--
-- Name: construction_site_risks fkhp7n8j6x04eyybnbgsnlwfgk6; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_risks
  ADD CONSTRAINT fkhp7n8j6x04eyybnbgsnlwfgk6 FOREIGN KEY (construction_site_id) REFERENCES construction_site (id);

--
-- Name: project_level_update_writeups fkht9k97lpser6nyvoh39g0task; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY project_level_update_writeups
  ADD CONSTRAINT fkht9k97lpser6nyvoh39g0task FOREIGN KEY (writeups_id) REFERENCES writeup (id);

--
-- Name: construction_site_actions fki4v12ihll6io8hug1f325x5y3; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_actions
  ADD CONSTRAINT fki4v12ihll6io8hug1f325x5y3 FOREIGN KEY (actions_id) REFERENCES action (id);

--
-- Name: change_request fkjcojscffeql8rw2re4nyn0khi; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY change_request
  ADD CONSTRAINT fkjcojscffeql8rw2re4nyn0khi FOREIGN KEY (last_version_id) REFERENCES change_request (id);

--
-- Name: pending_issue fkjuhfb6ec5gc48vksimm5a8l1c; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY pending_issue
  ADD CONSTRAINT fkjuhfb6ec5gc48vksimm5a8l1c FOREIGN KEY (last_version_id) REFERENCES pending_issue (id);

--
-- Name: construction_site_reunion_plannings fkk8i08llvitp55v1234fv4l3so; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_reunion_plannings
  ADD CONSTRAINT fkk8i08llvitp55v1234fv4l3so FOREIGN KEY (reunion_plannings_id) REFERENCES reunion_planning (id);

--
-- Name: construction_site_actions fkkdanw9kdjokh0p1ad4iho60ge; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_actions
  ADD CONSTRAINT fkkdanw9kdjokh0p1ad4iho60ge FOREIGN KEY (construction_site_id) REFERENCES construction_site (id);

--
-- Name: project_level_update_documents fkkie6jen7hpt0sqc9q7qc4iswr; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY project_level_update_documents
  ADD CONSTRAINT fkkie6jen7hpt0sqc9q7qc4iswr FOREIGN KEY (project_level_update_id) REFERENCES project_level_update (id);

--
-- Name: construction_site_resources fkl49g6qlpa6leq88vjva4njuw4; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_resources
  ADD CONSTRAINT fkl49g6qlpa6leq88vjva4njuw4 FOREIGN KEY (resources_id) REFERENCES resource (id);

--
-- Name: reunion_planning fklbpseghk9f3csw2muqws97dv7; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY reunion_planning
  ADD CONSTRAINT fklbpseghk9f3csw2muqws97dv7 FOREIGN KEY (last_version_id) REFERENCES reunion_planning (id);

--
-- Name: project_level_update_milestones fklkqskaxqxh87fxdq2n7qelce2; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY project_level_update_milestones
  ADD CONSTRAINT fklkqskaxqxh87fxdq2n7qelce2 FOREIGN KEY (project_level_update_id) REFERENCES project_level_update (id);

--
-- Name: project_level_update_todos fkll1gc4d5rivuh08or03ghemjj; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY project_level_update_todos
  ADD CONSTRAINT fkll1gc4d5rivuh08or03ghemjj FOREIGN KEY (todos_id) REFERENCES todo (id);

--
-- Name: communication_plan fklv9u22ankaqjw1ncjd9fj32s2; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY communication_plan
  ADD CONSTRAINT fklv9u22ankaqjw1ncjd9fj32s2 FOREIGN KEY (last_version_id) REFERENCES communication_plan (id);

--
-- Name: project_level_update_reunion_plannings fkm1ai03ecsnkccat1t0kyqj6k8; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY project_level_update_reunion_plannings
  ADD CONSTRAINT fkm1ai03ecsnkccat1t0kyqj6k8 FOREIGN KEY (reunion_plannings_id) REFERENCES reunion_planning (id);

--
-- Name: construction_site_change_requests fkmeto1goushtsywhtrtr9ac1qo; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_change_requests
  ADD CONSTRAINT fkmeto1goushtsywhtrtr9ac1qo FOREIGN KEY (construction_site_id) REFERENCES construction_site (id);

--
-- Name: project_level_update_communication_plans fkn1t1hte8jpd6mkbivwh8mcr8; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY project_level_update_communication_plans
  ADD CONSTRAINT fkn1t1hte8jpd6mkbivwh8mcr8 FOREIGN KEY (communication_plans_id) REFERENCES communication_plan (id);

--
-- Name: construction_site_pending_issues fkn65x5uh4lb418xume61qbfesp; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_pending_issues
  ADD CONSTRAINT fkn65x5uh4lb418xume61qbfesp FOREIGN KEY (construction_site_id) REFERENCES construction_site (id);

--
-- Name: project_level_update_actions fko9w4rcb8es38hi685c2x6j7nk; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY project_level_update_actions
  ADD CONSTRAINT fko9w4rcb8es38hi685c2x6j7nk FOREIGN KEY (actions_id) REFERENCES action (id);

--
-- Name: construction_site_writeups fkocfi8jfme3l5ym3og0mqr5cwc; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_writeups
  ADD CONSTRAINT fkocfi8jfme3l5ym3og0mqr5cwc FOREIGN KEY (construction_site_id) REFERENCES construction_site (id);

--
-- Name: project_level_update_risks fkonbxpy3ia1ejqt0mnc4n42iog; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY project_level_update_risks
  ADD CONSTRAINT fkonbxpy3ia1ejqt0mnc4n42iog FOREIGN KEY (risks_id) REFERENCES risk (id);

--
-- Name: action fkotmi03cjcqnpdadtfivkmjq1i; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY action
  ADD CONSTRAINT fkotmi03cjcqnpdadtfivkmjq1i FOREIGN KEY (supervisor_id) REFERENCES resource (id);

--
-- Name: construction_site_pending_issues fkpixb0djksr80rb97wf8o6ksw2; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_pending_issues
  ADD CONSTRAINT fkpixb0djksr80rb97wf8o6ksw2 FOREIGN KEY (pending_issues_id) REFERENCES pending_issue (id);

--
-- Name: construction_site_resources fkr0hs38s7p8vvqtjwy8efe06co; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_resources
  ADD CONSTRAINT fkr0hs38s7p8vvqtjwy8efe06co FOREIGN KEY (construction_site_id) REFERENCES construction_site (id);

--
-- Name: project_level_update_change_requests fksdujnani6jop6g60jife6frc1; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY project_level_update_change_requests
  ADD CONSTRAINT fksdujnani6jop6g60jife6frc1 FOREIGN KEY (change_requests_id) REFERENCES change_request (id);

--
-- Name: risk fkt0msm0xsc8w6ygtaapnwa64un; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY risk
  ADD CONSTRAINT fkt0msm0xsc8w6ygtaapnwa64un FOREIGN KEY (last_version_id) REFERENCES risk (id);

--
-- Name: project_level_update_resources fktihh42kyh48l2hjnf2x10sxvn; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY project_level_update_resources
  ADD CONSTRAINT fktihh42kyh48l2hjnf2x10sxvn FOREIGN KEY (resources_id) REFERENCES resource (id);

--
-- Name: construction_site_archived_updates fkuwaguqoibs84n3gf2rt23rds; Type: FK CONSTRAINT; Schema: <tenant_schema_name>; Owner: postgres
--

ALTER TABLE ONLY construction_site_archived_updates
  ADD CONSTRAINT fkuwaguqoibs84n3gf2rt23rds FOREIGN KEY (archived_updates_id) REFERENCES project_level_update (id);

--
-- PostgreSQL database dump complete
--


-- Populate with resources
INSERT INTO resource (id, reference, name, type, last_version_id)
VALUES ('13814d0f-4e6c-4739-bac3-a011b8bbfdd3', 'd6b1719c-7a92-4aca-86f8-5129a3b288fc', 'Ali', 0, NULL);
INSERT INTO resource (id, reference, name, type, last_version_id)
VALUES ('724a77b9-8a4d-4d81-9e76-ca87a8534395', '992f27a5-55df-4ab6-b6fe-889378a7efe7', 'Fatma', 0, NULL);

