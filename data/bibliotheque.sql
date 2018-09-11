-- Table: public.livre

-- DROP TABLE public.livre;

CREATE TABLE public.livre
(
    id integer NOT NULL,
    titre text COLLATE pg_catalog."default",
    annee text COLLATE pg_catalog."default",
    style text COLLATE pg_catalog."default",
    CONSTRAINT livre_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.livre
    OWNER to postgres;