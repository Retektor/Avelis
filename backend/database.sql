--
-- PostgreSQL database cluster dump
--

-- Started on 2026-04-27 10:07:04 +04

\restrict hVdcMy3BQo9gqVyfeDwtq3zFggectes6bY9SO2tfE4KOunzs3hhbiFDYlHMXNMG

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Roles
--

CREATE ROLE postgres;
ALTER ROLE postgres WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS;
CREATE ROLE retektor;
ALTER ROLE retektor WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN NOREPLICATION NOBYPASSRLS;

--
-- User Configurations
--








\unrestrict hVdcMy3BQo9gqVyfeDwtq3zFggectes6bY9SO2tfE4KOunzs3hhbiFDYlHMXNMG

--
-- Databases
--

--
-- Database "template1" dump
--

\connect template1

--
-- PostgreSQL database dump
--

\restrict Pa8zaUtZi6g58PQ9ci28FnaOCql6TglbMpW3aEULlBPF3k2X5fWd6DaX5G6S0Fu

-- Dumped from database version 18.3
-- Dumped by pg_dump version 18.3

-- Started on 2026-04-27 10:07:04 +04

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

-- Completed on 2026-04-27 10:07:04 +04

--
-- PostgreSQL database dump complete
--

\unrestrict Pa8zaUtZi6g58PQ9ci28FnaOCql6TglbMpW3aEULlBPF3k2X5fWd6DaX5G6S0Fu

--
-- Database "avelis" dump
--

--
-- PostgreSQL database dump
--

\restrict rZKpgEH4rUGT10Z5vPnR5l5ZUFYcpy0rj3C2yME5lfnUYavfoEIAyF7zAuNHUuY

-- Dumped from database version 18.3
-- Dumped by pg_dump version 18.3

-- Started on 2026-04-27 10:07:04 +04

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3508 (class 1262 OID 16389)
-- Name: avelis; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE avelis WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'ru_RU.UTF-8';


ALTER DATABASE avelis OWNER TO postgres;

\unrestrict rZKpgEH4rUGT10Z5vPnR5l5ZUFYcpy0rj3C2yME5lfnUYavfoEIAyF7zAuNHUuY
\connect avelis
\restrict rZKpgEH4rUGT10Z5vPnR5l5ZUFYcpy0rj3C2yME5lfnUYavfoEIAyF7zAuNHUuY

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 861 (class 1247 OID 16522)
-- Name: preferred_tutoring_format; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.preferred_tutoring_format AS ENUM (
    'online',
    'offline',
    'any'
);


ALTER TYPE public.preferred_tutoring_format OWNER TO postgres;

--
-- TOC entry 855 (class 1247 OID 16504)
-- Name: user_role; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.user_role AS ENUM (
    'student',
    'tutor',
    'parent',
    'admin'
);


ALTER TYPE public.user_role OWNER TO postgres;

--
-- TOC entry 858 (class 1247 OID 16514)
-- Name: user_status; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.user_status AS ENUM (
    'active',
    'pending',
    'blocked'
);


ALTER TYPE public.user_status OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 222 (class 1259 OID 16607)
-- Name: tutors; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tutors (
    tutor_id bigint NOT NULL,
    user_id integer NOT NULL,
    headline character varying,
    about character varying,
    education_text character varying,
    experience_in_years character varying,
    teaching_methodology character varying,
    tutoring_format public.preferred_tutoring_format NOT NULL,
    hourly_rate_rubles double precision NOT NULL,
    is_published boolean NOT NULL,
    is_verified boolean NOT NULL,
    is_accepting_students boolean NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.tutors OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16606)
-- Name: tutors_tutor_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tutors_tutor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tutors_tutor_id_seq OWNER TO postgres;

--
-- TOC entry 3509 (class 0 OID 0)
-- Dependencies: 221
-- Name: tutors_tutor_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tutors_tutor_id_seq OWNED BY public.tutors.tutor_id;


--
-- TOC entry 220 (class 1259 OID 16582)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    user_id bigint NOT NULL,
    email character varying NOT NULL,
    phone character varying(11) NOT NULL,
    password_hash character varying NOT NULL,
    last_name character varying NOT NULL,
    first_name character varying NOT NULL,
    middle_name character varying,
    birthday date,
    city character varying,
    timezone character varying,
    avatar_url character varying,
    cover_url character varying,
    role public.user_role NOT NULL,
    status public.user_status NOT NULL,
    is_email_verified boolean NOT NULL,
    is_phone_verified boolean NOT NULL,
    last_login_at timestamp without time zone,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16581)
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_user_id_seq OWNER TO postgres;

--
-- TOC entry 3510 (class 0 OID 0)
-- Dependencies: 219
-- Name: users_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_user_id_seq OWNED BY public.users.user_id;


--
-- TOC entry 3343 (class 2604 OID 16610)
-- Name: tutors tutor_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tutors ALTER COLUMN tutor_id SET DEFAULT nextval('public.tutors_tutor_id_seq'::regclass);


--
-- TOC entry 3342 (class 2604 OID 16585)
-- Name: users user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN user_id SET DEFAULT nextval('public.users_user_id_seq'::regclass);


--
-- TOC entry 3502 (class 0 OID 16607)
-- Dependencies: 222
-- Data for Name: tutors; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tutors (tutor_id, user_id, headline, about, education_text, experience_in_years, teaching_methodology, tutoring_format, hourly_rate_rubles, is_published, is_verified, is_accepting_students, created_at, updated_at) FROM stdin;
\.


--
-- TOC entry 3500 (class 0 OID 16582)
-- Dependencies: 220
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (user_id, email, phone, password_hash, last_name, first_name, middle_name, birthday, city, timezone, avatar_url, cover_url, role, status, is_email_verified, is_phone_verified, last_login_at, created_at, updated_at) FROM stdin;
\.


--
-- TOC entry 3511 (class 0 OID 0)
-- Dependencies: 221
-- Name: tutors_tutor_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tutors_tutor_id_seq', 1, false);


--
-- TOC entry 3512 (class 0 OID 0)
-- Dependencies: 219
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_user_id_seq', 1, false);


--
-- TOC entry 3351 (class 2606 OID 16623)
-- Name: tutors tutors_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tutors
    ADD CONSTRAINT tutors_pkey PRIMARY KEY (tutor_id);


--
-- TOC entry 3345 (class 2606 OID 16603)
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- TOC entry 3347 (class 2606 OID 16605)
-- Name: users users_phone_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_phone_key UNIQUE (phone);


--
-- TOC entry 3349 (class 2606 OID 16601)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


-- Completed on 2026-04-27 10:07:05 +04

--
-- PostgreSQL database dump complete
--

\unrestrict rZKpgEH4rUGT10Z5vPnR5l5ZUFYcpy0rj3C2yME5lfnUYavfoEIAyF7zAuNHUuY

--
-- Database "postgres" dump
--

\connect postgres

--
-- PostgreSQL database dump
--

\restrict QoIFhddQnuBbzYdNfoRFph6YRHcPLqhOJkZ2SeKydnfNVbblWBxOBqWflvba2bl

-- Dumped from database version 18.3
-- Dumped by pg_dump version 18.3

-- Started on 2026-04-27 10:07:05 +04

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

-- Completed on 2026-04-27 10:07:06 +04

--
-- PostgreSQL database dump complete
--

\unrestrict QoIFhddQnuBbzYdNfoRFph6YRHcPLqhOJkZ2SeKydnfNVbblWBxOBqWflvba2bl

-- Completed on 2026-04-27 10:07:06 +04

--
-- PostgreSQL database cluster dump complete
--

