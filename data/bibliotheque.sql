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
-- Name: livre_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE livre_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE livre_id_seq OWNER TO postgres;

--
-- Name: livre_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE livre_id_seq OWNED BY livre.id;


--
-- Name: prix; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE prix (
    id integer NOT NULL,
    nom text,
    promotion text,
    description text,
    livre integer
);


ALTER TABLE prix OWNER TO postgres;

--
-- Name: prix_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE prix_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE prix_id_seq OWNER TO postgres;

--
-- Name: prix_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE prix_id_seq OWNED BY prix.id;


--
-- Name: livre id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY livre ALTER COLUMN id SET DEFAULT nextval('livre_id_seq'::regclass);


--
-- Name: prix id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prix ALTER COLUMN id SET DEFAULT nextval('prix_id_seq'::regclass);


--
-- Data for Name: livre; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO livre VALUES (1, 'Le seigneur des Anneaux', '1954', 'Fantasy');
INSERT INTO livre VALUES (3, 'Le vieil homme et la mer', '1952', 'Fiction');
INSERT INTO livre VALUES (2, 'La ferme des animaux', '1945', 'Apologue');


--
-- Name: livre_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('livre_id_seq', 1, false);


--
-- Data for Name: prix; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Name: prix_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('prix_id_seq', 1, false);


--
-- Name: livre livre_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY livre
    ADD CONSTRAINT livre_pkey PRIMARY KEY (id);


--
-- Name: prix prix_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prix
    ADD CONSTRAINT prix_pkey PRIMARY KEY (id);


--
-- Name: fki_one_livre_to_many_prix; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_one_livre_to_many_prix ON prix USING btree (livre);


--
-- Name: prix one_livre_to_many_prix; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prix
    ADD CONSTRAINT one_livre_to_many_prix FOREIGN KEY (livre) REFERENCES livre(id);


--
-- PostgreSQL database dump complete
--

