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

--
-- Name: surveillertables(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION surveillertables() RETURNS void
    LANGUAGE plpgsql
    AS $$

DECLARE
	livreCourant RECORD;
    checksum text;
    effectif integer;
BEGIN
	--FOR livreCourant in
    	--SELECT livre.* FROM livre
    --LOOP
    	
    
    --END LOOP;
    
    checksum:=md5(string_agg(livre.titre::text,'' ORDER BY id)) FROM livre;
    effectif:=COUNT(*) FROM livre;
    INSERT into livresecret(checksum, effectif, date) VALUES(checksum,effectif, NOW());
    
END

$$;


ALTER FUNCTION public.surveillertables() OWNER TO postgres;

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

CREATE TABLE livre (
    id integer NOT NULL,
    titre text,
    annee text,
    style text,
    ancienneannee text
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
-- Name: livresecret; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE livresecret (
    id integer NOT NULL,
    checksum text,
    effectif integer,
    date timestamp with time zone
);


ALTER TABLE livresecret OWNER TO postgres;

--
-- Name: livresecret_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE livresecret_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE livresecret_id_seq OWNER TO postgres;

--
-- Name: livresecret_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE livresecret_id_seq OWNED BY livresecret.id;


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
-- Name: journal id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY journal ALTER COLUMN id SET DEFAULT nextval('journal_id_seq'::regclass);


--
-- Name: livre id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY livre ALTER COLUMN id SET DEFAULT nextval('livre_id_seq'::regclass);


--
-- Name: livresecret id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY livresecret ALTER COLUMN id SET DEFAULT nextval('livresecret_id_seq'::regclass);


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
INSERT INTO journal VALUES (6, '2018-09-27 15:54:42.495783-04', 'UPDATE', '(Attrape coeur,1959,fiction)->(Attrape coeurs,1959,fiction)', 'livre');
INSERT INTO journal VALUES (7, '2018-09-27 15:54:49.095518-04', 'UPDATE', '(Attrape coeur,1959,fiction)->(Attrape moi,1959,fiction)', 'livre');
INSERT INTO journal VALUES (8, '2018-09-27 15:57:19.342767-04', 'INSERT', '()->(Le Seigneur des anneaux,1965,Fantasy)', 'livre');
INSERT INTO journal VALUES (9, '2018-09-27 16:01:00.156089-04', 'INSERT', '()->(La Scouine,2004,Terroir)', 'livre');
INSERT INTO journal VALUES (10, '2018-09-27 16:06:48.711069-04', 'UPDATE', '(Le vieil homme et la mer,2002,Fiction)->(Attrape coeur,1959,fiction)', 'livre');
INSERT INTO journal VALUES (11, '2018-09-27 16:06:48.711069-04', 'UPDATE', '(Attrape coeurs,1959,fiction)->(Attrape coeur,1959,fiction)', 'livre');
INSERT INTO journal VALUES (12, '2018-09-27 16:06:48.711069-04', 'UPDATE', '(Attrape moi,1959,fiction)->(Attrape coeur,1959,fiction)', 'livre');
INSERT INTO journal VALUES (13, '2018-09-27 16:06:48.711069-04', 'UPDATE', '(Le Seigneur des anneaux,1965,Fantasy)->(Attrape coeur,1959,fiction)', 'livre');
INSERT INTO journal VALUES (14, '2018-09-27 16:06:48.711069-04', 'UPDATE', '(La Scouine,2004,Terroir)->(Attrape coeur,1959,fiction)', 'livre');
INSERT INTO journal VALUES (15, '2018-09-27 16:06:48.711069-04', 'UPDATE', '(La Scouine,1997,Anti-Terroir)->(Attrape coeur,1959,fiction)', 'livre');
INSERT INTO journal VALUES (16, '2018-09-27 16:06:48.711069-04', 'UPDATE', '(,1997,)->(Attrape coeur,1959,fiction)', 'livre');
INSERT INTO journal VALUES (17, '2018-09-27 16:06:48.711069-04', 'UPDATE', '(La ferme des animaux,1997,Apologue)->(Attrape coeur,1959,fiction)', 'livre');
INSERT INTO journal VALUES (18, '2018-09-27 16:06:48.711069-04', 'UPDATE', '(Le seigneur des Anneaux,1997,Fantasy)->(Attrape coeur,1959,fiction)', 'livre');
INSERT INTO journal VALUES (19, '2018-09-27 16:06:48.711069-04', 'UPDATE', '(Allo,1997,REACH)->(Attrape coeur,1959,fiction)', 'livre');
INSERT INTO journal VALUES (20, '2018-09-27 16:06:48.711069-04', 'UPDATE', '(,1997,)->(Attrape coeur,1959,fiction)', 'livre');
INSERT INTO journal VALUES (21, '2018-09-27 16:07:37.920815-04', 'UPDATE', '(Attrape coeur,1959,fiction)->(Le seigneur des anneaux,1957,fantasy)', 'livre');
INSERT INTO journal VALUES (22, '2018-09-27 16:07:56.947177-04', 'UPDATE', '(Attrape coeur,1959,fiction)->(Harry Potter,1993,Sorcier)', 'livre');
INSERT INTO journal VALUES (23, '2018-09-27 16:14:51.06493-04', 'UPDATE', '(Le seigneur des anneaux,1957,fantasy)->(Le seigneur des anneaux,1957,fantasy)', 'livre');
INSERT INTO journal VALUES (24, '2018-09-27 16:14:52.496315-04', 'UPDATE', '(Attrape coeur,1959,fiction)->(Attrape coeur,1959,fiction)', 'livre');
INSERT INTO journal VALUES (25, '2018-09-27 16:14:53.536015-04', 'UPDATE', '(Attrape coeur,1959,fiction)->(Attrape coeur,1959,fiction)', 'livre');
INSERT INTO journal VALUES (26, '2018-09-27 16:14:54.944155-04', 'UPDATE', '(Attrape coeur,1959,fiction)->(Attrape coeur,1959,fiction)', 'livre');
INSERT INTO journal VALUES (27, '2018-09-27 16:14:58.071997-04', 'UPDATE', '(Le seigneur des anneaux,1957,fantasy)->(Le seigneur des anneaux,1957,fantasy)', 'livre');
INSERT INTO journal VALUES (28, '2018-09-27 16:35:09.825309-04', 'UPDATE', '(Attrape coeur,1959,fiction)->(Attrape coeurs,1959,fiction)', 'livre');
INSERT INTO journal VALUES (29, '2018-09-27 17:11:53.880878-04', 'UPDATE', '(Le seigneur des anneaux,1957,fantasy)->(Le seigneur des anneaux,2002,fantasy)', 'livre');
INSERT INTO journal VALUES (30, '2018-10-02 23:30:36.945403-04', 'UPDATE', '(Attrape coeur,1959,fiction)->(Attrape coeursss,1959,fiction)', 'livre');
INSERT INTO journal VALUES (31, '2018-10-02 23:32:26.430109-04', 'UPDATE', '(Le seigneur des anneaux,2002,fantasy)->(Le seigneur des anneaux,2002,fantasy)', 'livre');
INSERT INTO journal VALUES (32, '2018-10-02 23:32:32.333067-04', 'UPDATE', '(Attrape coeursss,1959,fiction)->(Attrape coeursss,1959,fiction)', 'livre');
INSERT INTO journal VALUES (33, '2018-10-02 23:32:33.885065-04', 'UPDATE', '(Attrape coeur,1959,fiction)->(Attrape coeur,1959,fiction)', 'livre');
INSERT INTO journal VALUES (34, '2018-10-02 23:42:46.711126-04', 'UPDATE', '(Attrape coeur,1959,fiction)->(Attrape coeur,1959,fiction)', 'livre');
INSERT INTO journal VALUES (35, '2018-10-02 23:46:59.820723-04', 'UPDATE', '(Attrape coeur,1959,fiction)->(Attrape coeur,1959,fiction)', 'livre');
INSERT INTO journal VALUES (36, '2018-10-02 23:47:04.747339-04', 'UPDATE', '(Attrape coeursss,1959,fiction)->(Attrape c,1959,fiction)', 'livre');
INSERT INTO journal VALUES (37, '2018-10-03 08:56:26.263167-04', 'DELETE', '(Attrape c,1959,fiction)->()', 'livre');
INSERT INTO journal VALUES (38, '2018-10-03 08:56:34.372001-04', 'DELETE', '(Attrape c,1959,fiction)->()', 'livre');


--
-- Name: journal_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('journal_id_seq', 38, true);


--
-- Data for Name: livre; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO livre VALUES (1, 'Attrape coeur', '1959', 'fiction', NULL);
INSERT INTO livre VALUES (4, 'Harry Potter', '1993', 'Sorcier', NULL);
INSERT INTO livre VALUES (8, 'Attrape coeur', '1959', 'fiction', NULL);
INSERT INTO livre VALUES (5, 'Attrape coeur', '1959', 'fiction', NULL);
INSERT INTO livre VALUES (6, 'Attrape coeur', '1959', 'fiction', NULL);
INSERT INTO livre VALUES (10, 'Attrape coeurs', '1959', 'fiction', NULL);
INSERT INTO livre VALUES (3, 'Le seigneur des anneaux', '2002', 'fantasy', NULL);
INSERT INTO livre VALUES (12, 'Attrape coeur', '1959', 'fiction', NULL);
INSERT INTO livre VALUES (7, 'Attrape coeur', '1959', 'fiction', NULL);
INSERT INTO livre VALUES (2, 'Attrape coeur', '1959', 'fiction', NULL);
INSERT INTO livre VALUES (11, 'Attrape c', '1959', 'fiction', NULL);


--
-- Name: livre_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('livre_id_seq', 12, true);


--
-- Data for Name: livresecret; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO livresecret VALUES (1, 'b6ef172d4c2e56797e2843eb848b0752', 3, '2018-10-03 09:13:20.919548-04');
INSERT INTO livresecret VALUES (2, 'b6ef172d4c2e56797e2843eb848b0752', 3, '2018-10-03 09:14:37.250294-04');
INSERT INTO livresecret VALUES (3, 'b6ef172d4c2e56797e2843eb848b0752', 11, '2018-10-03 09:14:48.35981-04');


--
-- Name: livresecret_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('livresecret_id_seq', 3, true);


--
-- Data for Name: prix; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO prix VALUES (2, 'Livre le mieux écris', '1984', 'WAHOU', 2);
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
INSERT INTO prix VALUES (14, 'Le plus gentillet', '2567', 'sympa', 2);
INSERT INTO prix VALUES (15, 'Epouvantable', '2009', 'AAAAh', NULL);
INSERT INTO prix VALUES (1, 'Livre le plus drôlesss', '1982', 'Trop le fun', 1);


--
-- Name: prix_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('prix_id_seq', 15, true);


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
-- Name: livresecret livresecret_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY livresecret
    ADD CONSTRAINT livresecret_pkey PRIMARY KEY (id);


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
-- Name: prix one_livre_to_many_key; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prix
    ADD CONSTRAINT one_livre_to_many_key FOREIGN KEY (livre) REFERENCES livre(id) ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

