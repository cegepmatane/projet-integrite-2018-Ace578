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
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- Name: copierchamps(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION copierchamps() RETURNS void
    LANGUAGE plpgsql
    AS $$

DECLARE
	livreCourant RECORD;
BEGIN
	FOR livreCourant in
    	SELECT livre.* FROM livre
    LOOP
    	UPDATE livre SET annee = cast(mouton.acienneanne as double precision) WHERE id = livreCourant.id;
    
    END LOOP;
    
END
$$;


ALTER FUNCTION public.copierchamps() OWNER TO postgres;

--
-- Name: journaliser(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION journaliser() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE	
	description text;
    objetAvant text;
    objetApres text;
    operation text;
	BEGIN
    objetAvant='';
    objetApres='';
    operation='';
    
    
    IF TG_OP = 'INSERT' THEN
    	objetAvant ='()';
        objetApres:='('||NEW.titre||','||NEW.annee||','||NEW.style||')';
        operation='ajouter';
    END IF;
    
    IF TG_OP = 'UPDATE' THEN
    	objetAvant:='('||OLD.titre||','||OLD.annee||','||OLD.style||')';
        objetApres:='('||NEW.titre||','||NEW.annee||','||NEW.style||')';
        operation='modifier';
    END IF;
    
    IF TG_OP = 'DELETE' THEN
    	objetAvant:='('||OLD.titre||','||OLD.annee||','||OLD.style||')';
        objetApres:='()';
        operation='supprimer';
    END IF;
    
    description:= objetAvant||'->'||objetApres;
    INSERT into journal(moment, operation, objet, description) VALUES (NOW(), TG_OP, 'livre', description);
    return NEW;
  	END
$$;


ALTER FUNCTION public.journaliser() OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: journal; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE journal (
    id integer NOT NULL,
    moment timestamp with time zone,
    operation text,
    description text,
    objet text NOT NULL
);


ALTER TABLE journal OWNER TO postgres;

--
-- Name: journal_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE journal_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE journal_id_seq OWNER TO postgres;

--
-- Name: journal_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE journal_id_seq OWNED BY journal.id;


--
-- Name: livre; Type: TABLE; Schema: public; Owner: postgres
--



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
-- Name: journal id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY journal ALTER COLUMN id SET DEFAULT nextval('journal_id_seq'::regclass);


--
-- Name: livre id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY livre ALTER COLUMN id SET DEFAULT nextval('livre_id_seq'::regclass);


--
-- Name: prix id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prix ALTER COLUMN id SET DEFAULT nextval('prix_id_seq'::regclass);


--
-- Data for Name: journal; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO journal VALUES (1, '2018-09-27 14:56:44.548576-04', 'AJOUTER', '(Harry Potter, 1987)', 'livre');
INSERT INTO journal VALUES (2, '2018-09-27 15:09:16.921779-04', 'AJOUTER', '(Harry Potter, 1987)', 'livre');
INSERT INTO journal VALUES (3, '2018-09-27 15:30:58.299959-04', 'AJOUTER', '(Attrape coeur)', 'livre');
INSERT INTO journal VALUES (4, '2018-09-27 15:45:02.980049-04', 'DELETE', '(Le seigneur des Anneaux,1997,Fantasy)->()', 'livre');
INSERT INTO journal VALUES (5, '2018-09-27 15:46:19.72316-04', 'UPDATE', '(Le vieil homme et la mer,1997,Fiction)->(Le vieil homme et la mer,2002,Fiction)', 'livre');


--
-- Name: journal_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('journal_id_seq', 5, true);


--
-- Data for Name: livre; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO livre VALUES (8, 'Attrape coeur', '1959', 'fiction', NULL);
INSERT INTO livre VALUES (10, 'Attrape coeur', '1959', 'fiction', NULL);
INSERT INTO livre VALUES (3, 'Le vieil homme et la mer', '2002', 'Fiction', NULL);
INSERT INTO livre VALUES (4, 'La Scouine', '1997', 'Anti-Terroir', NULL);
INSERT INTO livre VALUES (5, '', '1997', '', NULL);
INSERT INTO livre VALUES (2, 'La ferme des animaux', '1997', 'Apologue', NULL);
INSERT INTO livre VALUES (1, 'Le seigneur des Anneaux', '1997', 'Fantasy', NULL);
INSERT INTO livre VALUES (6, 'Allo', '1997', 'REACH', NULL);
INSERT INTO livre VALUES (7, '', '1997', '', NULL);


--
-- Name: livre_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('livre_id_seq', 10, true);


--
-- Data for Name: prix; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO prix VALUES (2, 'Livre le mieux écris', '1984', 'WAHOU', 2);
INSERT INTO prix VALUES (1, 'Livre le plus drôle', '1982', 'Trop le fun', 1);
INSERT INTO prix VALUES (3, 'Livre avec la meilleur couverture', '1962', 'Majestueux', 1);
INSERT INTO prix VALUES (4, 'AAAAA', 'AAA', 'null', NULL);
INSERT INTO prix VALUES (5, 'AAAAA', 'AAA', 'null', NULL);
INSERT INTO prix VALUES (6, 'AAAAAB', 'AAAB', 'null', NULL);
INSERT INTO prix VALUES (7, 'sgsg', 'gsggs', 'null', 2);
INSERT INTO prix VALUES (8, 'Livre de Vincent', '2008', 'null', 2);
INSERT INTO prix VALUES (9, 'ALLO', '1998', 'null', 2);
INSERT INTO prix VALUES (10, 'TEst', '1998', 'null', 2);
INSERT INTO prix VALUES (11, 'TEST2', '1990', 'null', 2);
INSERT INTO prix VALUES (12, 'Vince', '1998', 'null', 2);
INSERT INTO prix VALUES (13, 'Youss', '1998', 'Parfum', 2);


--
-- Name: prix_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('prix_id_seq', 13, true);


--
-- Name: journal journal_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY journal
    ADD CONSTRAINT journal_pkey PRIMARY KEY (id);


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
-- Name: livre evenementajoutlivre; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER evenementajoutlivre BEFORE INSERT ON livre FOR EACH ROW EXECUTE PROCEDURE journaliser();


--
-- Name: livre evenementmodificationlivre; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER evenementmodificationlivre BEFORE UPDATE ON livre FOR EACH ROW EXECUTE PROCEDURE journaliser();


--
-- Name: livre evenementsuppressionlivre; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER evenementsuppressionlivre BEFORE DELETE ON livre FOR EACH ROW EXECUTE PROCEDURE journaliser();


--
-- Name: prix one_livre_to_many_prix; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prix
    ADD CONSTRAINT one_livre_to_many_prix FOREIGN KEY (livre) REFERENCES livre(id);


--
-- PostgreSQL database dump complete
--

