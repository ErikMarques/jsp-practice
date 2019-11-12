--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.8
-- Dumped by pg_dump version 9.2.8
-- Started on 2018-07-24 17:35:32

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

DROP DATABASE "curso-jsp";
--
-- TOC entry 1974 (class 1262 OID 222626)
-- Name: curso-jsp; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "curso-jsp" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Portuguese_Brazil.1252' LC_CTYPE = 'Portuguese_Brazil.1252';


ALTER DATABASE "curso-jsp" OWNER TO postgres;

\connect "curso-jsp"

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 5 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 1975 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 178 (class 3079 OID 11727)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 1977 (class 0 OID 0)
-- Dependencies: 178
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- TOC entry 170 (class 1259 OID 222656)
-- Name: eventosequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE eventosequence
    START WITH 12
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.eventosequence OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 171 (class 1259 OID 222658)
-- Name: eventos; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE eventos (
    id bigint DEFAULT nextval('eventosequence'::regclass) NOT NULL,
    descricao character varying(500),
    dataevento character varying(20)
);


ALTER TABLE public.eventos OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 223229)
-- Name: produtosequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE produtosequence
    START WITH 8
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.produtosequence OWNER TO postgres;

--
-- TOC entry 177 (class 1259 OID 223231)
-- Name: produto; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE produto (
    id bigint DEFAULT nextval('produtosequence'::regclass) NOT NULL,
    nome character varying(500),
    quantidade numeric(10,4),
    valor numeric(10,4)
);


ALTER TABLE public.produto OWNER TO postgres;

--
-- TOC entry 172 (class 1259 OID 222667)
-- Name: projetosequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE projetosequence
    START WITH 20
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.projetosequence OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 222671)
-- Name: projeto; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE projeto (
    id bigint DEFAULT nextval('projetosequence'::regclass) NOT NULL,
    nome character varying(500)
);


ALTER TABLE public.projeto OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 222669)
-- Name: seriesequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE seriesequence
    START WITH 20
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seriesequence OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 222680)
-- Name: series; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE series (
    id bigint DEFAULT nextval('seriesequence'::regclass) NOT NULL,
    nome character varying(500),
    datainicial character varying(500),
    datafinal character varying(500),
    projeto bigint NOT NULL
);


ALTER TABLE public.series OWNER TO postgres;

--
-- TOC entry 168 (class 1259 OID 222627)
-- Name: usersequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE usersequence
    START WITH 8
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usersequence OWNER TO postgres;

--
-- TOC entry 169 (class 1259 OID 222629)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE usuario (
    id bigint DEFAULT nextval('usersequence'::regclass) NOT NULL,
    login character varying(500),
    senha character varying(20),
    nome character varying(500),
    telefone character varying(20)
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 1963 (class 0 OID 222658)
-- Dependencies: 171
-- Data for Name: eventos; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO eventos (id, descricao, dataevento) VALUES (1, 'Reunião de projeto 1', '2018-10-10');
INSERT INTO eventos (id, descricao, dataevento) VALUES (2, 'Reunião de projeto 2', '2018-10-05');
INSERT INTO eventos (id, descricao, dataevento) VALUES (3, 'Reunião de projeto 3', '2018-10-01');


--
-- TOC entry 1978 (class 0 OID 0)
-- Dependencies: 170
-- Name: eventosequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('eventosequence', 12, false);


--
-- TOC entry 1969 (class 0 OID 223231)
-- Dependencies: 177
-- Data for Name: produto; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO produto (id, nome, quantidade, valor) VALUES (14, 'carne', 600.0000, 6.0000);
INSERT INTO produto (id, nome, quantidade, valor) VALUES (15, '89889', 988.0000, 8.0000);
INSERT INTO produto (id, nome, quantidade, valor) VALUES (16, '66', 666.0000, 66.0000);
INSERT INTO produto (id, nome, quantidade, valor) VALUES (17, '444', 44.0000, 44.0000);


--
-- TOC entry 1979 (class 0 OID 0)
-- Dependencies: 176
-- Name: produtosequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('produtosequence', 17, true);


--
-- TOC entry 1966 (class 0 OID 222671)
-- Dependencies: 174
-- Data for Name: projeto; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO projeto (id, nome) VALUES (1, 'Projeto 1');
INSERT INTO projeto (id, nome) VALUES (2, 'Projeto 2');


--
-- TOC entry 1980 (class 0 OID 0)
-- Dependencies: 172
-- Name: projetosequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('projetosequence', 20, false);


--
-- TOC entry 1967 (class 0 OID 222680)
-- Dependencies: 175
-- Data for Name: series; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO series (id, nome, datainicial, datafinal, projeto) VALUES (1, 'Serie 1', '2018-10-05', '2018-10-10', 1);
INSERT INTO series (id, nome, datainicial, datafinal, projeto) VALUES (3, 'Serie 1', '2018-10-05', '2018-10-10', 2);
INSERT INTO series (id, nome, datainicial, datafinal, projeto) VALUES (4, 'Serie 2', '2018-10-08', '2018-10-12', 2);
INSERT INTO series (id, nome, datainicial, datafinal, projeto) VALUES (2, 'Serie 2', '2018-11-14', '2018-11-18', 1);


--
-- TOC entry 1981 (class 0 OID 0)
-- Dependencies: 173
-- Name: seriesequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('seriesequence', 20, false);


--
-- TOC entry 1982 (class 0 OID 0)
-- Dependencies: 168
-- Name: usersequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('usersequence', 55, true);


--
-- TOC entry 1961 (class 0 OID 222629)
-- Dependencies: 169
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO usuario (id, login, senha, nome, telefone) VALUES (54, 'asasassa', '7787897', '889898', '898777');
INSERT INTO usuario (id, login, senha, nome, telefone) VALUES (55, '98989', '9898', '9898', 'sdsdsd');
INSERT INTO usuario (id, login, senha, nome, telefone) VALUES (16, 'admin', 'admin', 'Alex', '8797987987987');


--
-- TOC entry 1847 (class 2606 OID 222666)
-- Name: evento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY eventos
    ADD CONSTRAINT evento_pkey PRIMARY KEY (id);


--
-- TOC entry 1853 (class 2606 OID 223239)
-- Name: produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY produto
    ADD CONSTRAINT produto_pkey PRIMARY KEY (id);


--
-- TOC entry 1849 (class 2606 OID 222679)
-- Name: projeto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY projeto
    ADD CONSTRAINT projeto_pkey PRIMARY KEY (id);


--
-- TOC entry 1851 (class 2606 OID 222688)
-- Name: series_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY series
    ADD CONSTRAINT series_pkey PRIMARY KEY (id);


--
-- TOC entry 1845 (class 2606 OID 222637)
-- Name: usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- TOC entry 1976 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2018-07-24 17:35:33

--
-- PostgreSQL database dump complete
--

