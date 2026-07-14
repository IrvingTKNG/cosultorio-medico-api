--
-- PostgreSQL database dump
--

\restrict tgeoCPtlQgFH9I34z1mHFr2drquzFAzJdPicTEkCJOjBvOsCqwyyXglJV9WFuar

-- Dumped from database version 16.13
-- Dumped by pg_dump version 16.13

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: cita_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cita_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.cita_id_seq OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: cita; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cita (
    id_cita integer DEFAULT nextval('public.cita_id_seq'::regclass) NOT NULL,
    fk_id_paciente integer NOT NULL,
    fk_id_doctor integer,
    fk_id_estado integer NOT NULL,
    fh_cita date,
    tm_inicio time without time zone,
    tm_fin time without time zone
);


ALTER TABLE public.cita OWNER TO postgres;

--
-- Name: estado_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.estado_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.estado_id_seq OWNER TO postgres;

--
-- Name: cita_estado; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cita_estado (
    id_estado integer DEFAULT nextval('public.estado_id_seq'::regclass) NOT NULL,
    tx_nombre character varying
);


ALTER TABLE public.cita_estado OWNER TO postgres;

--
-- Name: consulta_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.consulta_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.consulta_id_seq OWNER TO postgres;

--
-- Name: consulta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.consulta (
    id_consulta integer DEFAULT nextval('public.consulta_id_seq'::regclass) NOT NULL,
    fk_id_doctor integer,
    fk_id_cita integer NOT NULL,
    fh_consulta date,
    tx_motivo character varying,
    tx_observaciones character varying,
    tx_diagnostico character varying
);


ALTER TABLE public.consulta OWNER TO postgres;

--
-- Name: doctor_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.doctor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.doctor_id_seq OWNER TO postgres;

--
-- Name: doctor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.doctor (
    id_doctor integer DEFAULT nextval('public.doctor_id_seq'::regclass) NOT NULL,
    tx_nombre character varying NOT NULL,
    tx_apellido_paterno character varying NOT NULL,
    tx_apellido_materno character varying,
    tx_correo character varying NOT NULL,
    tx_cedula character varying NOT NULL,
    tx_num_telefono character varying NOT NULL,
    tx_num_tel_alter character varying,
    tx_especialidad character varying,
    tm_entrada time without time zone,
    tm_salida time without time zone
);


ALTER TABLE public.doctor OWNER TO postgres;

--
-- Name: paciente_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.paciente_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.paciente_id_seq OWNER TO postgres;

--
-- Name: paciente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.paciente (
    id_paciente integer DEFAULT nextval('public.paciente_id_seq'::regclass) NOT NULL,
    tx_nombre character varying NOT NULL,
    tx_apellido_paterno character varying NOT NULL,
    tx_apellido_materno character varying,
    tx_num_telefono character varying NOT NULL,
    tx_num_tel_alter character varying,
    tx_num_expediente character varying,
    fh_registro timestamp with time zone,
    tx_correo character varying,
    fh_nacimiento date
);


ALTER TABLE public.paciente OWNER TO postgres;

--
-- Name: receta_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.receta_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.receta_id_seq OWNER TO postgres;

--
-- Name: receta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.receta (
    id_receta integer DEFAULT nextval('public.receta_id_seq'::regclass) NOT NULL,
    fk_id_consulta integer NOT NULL,
    fh_receta date,
    tx_indicaciones character varying,
    json_detalles jsonb
);


ALTER TABLE public.receta OWNER TO postgres;

--
-- Data for Name: cita; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cita (id_cita, fk_id_paciente, fk_id_doctor, fk_id_estado, fh_cita, tm_inicio, tm_fin) FROM stdin;
\.


--
-- Data for Name: cita_estado; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cita_estado (id_estado, tx_nombre) FROM stdin;
1	Creada
2	Editada
3	Cancelada
4	Confirmada
5	Finalizada
\.


--
-- Data for Name: consulta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.consulta (id_consulta, fk_id_doctor, fk_id_cita, fh_consulta, tx_motivo, tx_observaciones, tx_diagnostico) FROM stdin;
\.


--
-- Data for Name: doctor; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.doctor (id_doctor, tx_nombre, tx_apellido_paterno, tx_apellido_materno, tx_correo, tx_cedula, tx_num_telefono, tx_num_tel_alter, tx_especialidad, tm_entrada, tm_salida) FROM stdin;
\.


--
-- Data for Name: paciente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.paciente (id_paciente, tx_nombre, tx_apellido_paterno, tx_apellido_materno, tx_num_telefono, tx_num_tel_alter, tx_num_expediente, fh_registro, tx_correo, fh_nacimiento) FROM stdin;
\.


--
-- Data for Name: receta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.receta (id_receta, fk_id_consulta, fh_receta, tx_indicaciones, json_detalles) FROM stdin;
\.


--
-- Name: cita_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cita_id_seq', 1, false);


--
-- Name: consulta_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.consulta_id_seq', 1, false);


--
-- Name: doctor_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.doctor_id_seq', 1, false);


--
-- Name: estado_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.estado_id_seq', 5, true);


--
-- Name: paciente_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.paciente_id_seq', 1, false);


--
-- Name: receta_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.receta_id_seq', 1, false);


--
-- Name: cita_estado cita_estado_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cita_estado
    ADD CONSTRAINT cita_estado_pkey PRIMARY KEY (id_estado);


--
-- Name: cita cita_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cita
    ADD CONSTRAINT cita_pkey PRIMARY KEY (id_cita);


--
-- Name: consulta consulta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.consulta
    ADD CONSTRAINT consulta_pkey PRIMARY KEY (id_consulta);


--
-- Name: doctor doctor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doctor
    ADD CONSTRAINT doctor_pkey PRIMARY KEY (id_doctor);


--
-- Name: paciente paciente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.paciente
    ADD CONSTRAINT paciente_pkey PRIMARY KEY (id_paciente);


--
-- Name: receta receta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.receta
    ADD CONSTRAINT receta_pkey PRIMARY KEY (id_receta);


--
-- Name: cita fk_cita_doctor; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cita
    ADD CONSTRAINT fk_cita_doctor FOREIGN KEY (fk_id_doctor) REFERENCES public.doctor(id_doctor);


--
-- Name: cita fk_cita_paciente; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cita
    ADD CONSTRAINT fk_cita_paciente FOREIGN KEY (fk_id_paciente) REFERENCES public.paciente(id_paciente);


--
-- Name: consulta fk_id_cita; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.consulta
    ADD CONSTRAINT fk_id_cita FOREIGN KEY (fk_id_cita) REFERENCES public.cita(id_cita);


--
-- Name: consulta fk_id_doctor; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.consulta
    ADD CONSTRAINT fk_id_doctor FOREIGN KEY (fk_id_doctor) REFERENCES public.doctor(id_doctor);


--
-- Name: cita fk_id_estado; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cita
    ADD CONSTRAINT fk_id_estado FOREIGN KEY (fk_id_estado) REFERENCES public.cita_estado(id_estado);


--
-- Name: receta fk_receta_consulta; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.receta
    ADD CONSTRAINT fk_receta_consulta FOREIGN KEY (fk_id_consulta) REFERENCES public.consulta(id_consulta);


--
-- PostgreSQL database dump complete
--

\unrestrict tgeoCPtlQgFH9I34z1mHFr2drquzFAzJdPicTEkCJOjBvOsCqwyyXglJV9WFuar

