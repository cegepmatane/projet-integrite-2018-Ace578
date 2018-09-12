--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.4
-- Dumped by pg_dump version 9.6.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: bibliotheque; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE bibliotheque WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'French_Canada.1252' LC_CTYPE = 'French_Canada.1252';


ALTER DATABASE bibliotheque OWNER TO postgres;

\connect bibliotheque

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: livre; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE livre (
    id integer NOT NULL,
    titre text,
    annee text,
    style text
);


ALTER TABLE livre OWNER TO postgres;

--
-- Data for Name: livre; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO livre VALUES (2, 'La ferme des animaux', '1945', 'Apologue');
INSERT INTO livre VALUES (1, 'Le Seigneur des Anneaux', '1954', 'Fantasy');
INSERT INTO livre VALUES (3, 'Le vieil homme et la mer', '1952', 'Fiction');


--
-- Name: livre livre_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY livre
    ADD CONSTRAINT livre_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

