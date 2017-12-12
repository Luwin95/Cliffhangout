
CREATE SEQUENCE public.quotation_quotation_difficulty_seq;

CREATE TABLE public.quotation (
  quotation_difficulty INTEGER NOT NULL DEFAULT nextval('public.quotation_quotation_difficulty_seq'),
  quotation_name VARCHAR(50) NOT NULL,
  CONSTRAINT quotation_pk PRIMARY KEY (quotation_difficulty)
);


ALTER SEQUENCE public.quotation_quotation_difficulty_seq OWNED BY public.quotation.quotation_difficulty;

CREATE SEQUENCE public.region_region_id_seq;

CREATE TABLE public.region (
  region_id INTEGER NOT NULL DEFAULT nextval('public.region_region_id_seq'),
  region_name VARCHAR NOT NULL,
  CONSTRAINT region_pk PRIMARY KEY (region_id)
);


ALTER SEQUENCE public.region_region_id_seq OWNED BY public.region.region_id;

CREATE TABLE public.departement (
  departement_code VARCHAR NOT NULL,
  region_id INTEGER NOT NULL,
  departement_name VARCHAR NOT NULL,
  CONSTRAINT departement_pk PRIMARY KEY (departement_code)
);


CREATE SEQUENCE public.image_id_seq;

CREATE TABLE public.image (
  image_id INTEGER NOT NULL DEFAULT nextval('public.image_id_seq'),
  alt VARCHAR(255) NOT NULL,
  title VARCHAR(255) NOT NULL,
  path VARCHAR(255) NOT NULL,
  CONSTRAINT image_pk PRIMARY KEY (image_id)
);


ALTER SEQUENCE public.image_id_seq OWNED BY public.image.image_id;

CREATE UNIQUE INDEX image_idx
  ON public.image
  ( path );

CREATE SEQUENCE public.user_account_id_seq;

CREATE TABLE public.user_account (
  user_account_id INTEGER NOT NULL DEFAULT nextval('public.user_account_id_seq'),
  login VARCHAR(255) NOT NULL,
  password VARCHAR(1000) NOT NULL,
  email VARCHAR(255) NOT NULL,
  role VARCHAR(255) NOT NULL,
  image_id INTEGER,
  active BOOLEAN NOT NULL,
  CONSTRAINT user_account_pk PRIMARY KEY (user_account_id)
);


ALTER SEQUENCE public.user_account_id_seq OWNED BY public.user_account.user_account_id;

CREATE UNIQUE INDEX user_account_idx
  ON public.user_account
  ( login, email );

CREATE SEQUENCE public.comment_comment_id_seq;

CREATE TABLE public.comment (
  comment_id INTEGER NOT NULL DEFAULT nextval('public.comment_comment_id_seq'),
  content VARCHAR(1000) NOT NULL,
  author_id INTEGER NOT NULL,
  parent_id INTEGER,
  reported BOOLEAN NOT NULL,
  CONSTRAINT comment_pk PRIMARY KEY (comment_id)
);


ALTER SEQUENCE public.comment_comment_id_seq OWNED BY public.comment.comment_id;

CREATE SEQUENCE public.topo_id_seq;

CREATE TABLE public.topo (
  topo_id INTEGER NOT NULL DEFAULT nextval('public.topo_id_seq'),
  name VARCHAR(255) NOT NULL,
  description VARCHAR(1000) NOT NULL,
  file VARCHAR(255),
  borrowed BOOLEAN NOT NULL,
  user_account_id INTEGER NOT NULL,
  CONSTRAINT topo_pk PRIMARY KEY (topo_id)
);


ALTER SEQUENCE public.topo_id_seq OWNED BY public.topo.topo_id;

CREATE TABLE public.comment_topo (
  comment_id INTEGER NOT NULL,
  topo_id INTEGER NOT NULL,
  CONSTRAINT comment_topo_pk PRIMARY KEY (comment_id)
);


CREATE TABLE public.topo_borrowing (
  topo_id INTEGER NOT NULL,
  user_account_id INTEGER NOT NULL,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  CONSTRAINT topo_borrowing_pk PRIMARY KEY (topo_id, user_account_id)
);


CREATE SEQUENCE public.site_id_seq;

CREATE TABLE public.site (
  site_id INTEGER NOT NULL DEFAULT nextval('public.site_id_seq'),
  name VARCHAR(255) NOT NULL,
  description VARCHAR(1000) NOT NULL,
  location VARCHAR(100) NOT NULL,
  postcode VARCHAR(50) NOT NULL,
  latitude REAL,
  longitude REAL,
  user_account_id INTEGER NOT NULL,
  departement_code VARCHAR NOT NULL,
  region_id INTEGER NOT NULL,
  CONSTRAINT site_pk PRIMARY KEY (site_id)
);


ALTER SEQUENCE public.site_id_seq OWNED BY public.site.site_id;

CREATE TABLE public.site_topo (
  site_id INTEGER NOT NULL,
  topo_id INTEGER NOT NULL,
  CONSTRAINT site_topo_pk PRIMARY KEY (site_id, topo_id)
);


CREATE TABLE public.comment_site (
  comment_id INTEGER NOT NULL,
  site_id INTEGER NOT NULL,
  CONSTRAINT comment_site_pk PRIMARY KEY (comment_id)
);


CREATE SEQUENCE public.sector_id_seq;

CREATE TABLE public.sector (
  sector_id INTEGER NOT NULL DEFAULT nextval('public.sector_id_seq'),
  name VARCHAR(255) NOT NULL,
  description VARCHAR(1000) NOT NULL,
  site_id INTEGER NOT NULL,
  CONSTRAINT sector_pk PRIMARY KEY (sector_id)
);


ALTER SEQUENCE public.sector_id_seq OWNED BY public.sector.sector_id;

CREATE TABLE public.sector_image (
  image_id INTEGER NOT NULL,
  sector_id INTEGER NOT NULL,
  CONSTRAINT sector_image_pk PRIMARY KEY (image_id)
);


CREATE SEQUENCE public.way_id_seq;

CREATE TABLE public.way (
  way_id INTEGER NOT NULL DEFAULT nextval('public.way_id_seq'),
  name VARCHAR(255) NOT NULL,
  height REAL NOT NULL,
  points_nb INTEGER NOT NULL,
  sector_id INTEGER NOT NULL,
  quotation_difficulty INTEGER NOT NULL,
  CONSTRAINT way_pk PRIMARY KEY (way_id)
);


ALTER SEQUENCE public.way_id_seq OWNED BY public.way.way_id;

CREATE SEQUENCE public.length_id_seq;

CREATE TABLE public.length (
  length_id INTEGER NOT NULL DEFAULT nextval('public.length_id_seq'),
  name VARCHAR(255) NOT NULL,
  description VARCHAR(1000) NOT NULL,
  way_id INTEGER NOT NULL,
  CONSTRAINT length_pk PRIMARY KEY (length_id)
);


ALTER SEQUENCE public.length_id_seq OWNED BY public.length.length_id;

CREATE SEQUENCE public.point_point_id_seq;

CREATE TABLE public.point (
  point_id INTEGER NOT NULL DEFAULT nextval('public.point_point_id_seq'),
  name VARCHAR(255) NOT NULL,
  description VARCHAR(1000) NOT NULL,
  length_id INTEGER NOT NULL,
  CONSTRAINT point_pk PRIMARY KEY (point_id)
);


ALTER SEQUENCE public.point_point_id_seq OWNED BY public.point.point_id;

CREATE TABLE public.site_image (
  image_id INTEGER NOT NULL,
  site_id INTEGER NOT NULL,
  CONSTRAINT site_image_pk PRIMARY KEY (image_id)
);


ALTER TABLE public.way ADD CONSTRAINT quotation_way_fk
FOREIGN KEY (quotation_difficulty)
REFERENCES public.quotation (quotation_difficulty)
ON DELETE NO ACTION
ON UPDATE NO ACTION
  NOT DEFERRABLE;

ALTER TABLE public.site ADD CONSTRAINT region_site_fk
FOREIGN KEY (region_id)
REFERENCES public.region (region_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
  NOT DEFERRABLE;

ALTER TABLE public.departement ADD CONSTRAINT region_departement_fk
FOREIGN KEY (region_id)
REFERENCES public.region (region_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
  NOT DEFERRABLE;

ALTER TABLE public.site ADD CONSTRAINT departement_site_fk
FOREIGN KEY (departement_code)
REFERENCES public.departement (departement_code)
ON DELETE NO ACTION
ON UPDATE NO ACTION
  NOT DEFERRABLE;

ALTER TABLE public.site_image ADD CONSTRAINT image_site_image_fk
FOREIGN KEY (image_id)
REFERENCES public.image (image_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
  NOT DEFERRABLE;

ALTER TABLE public.sector_image ADD CONSTRAINT image_sector_image_fk
FOREIGN KEY (image_id)
REFERENCES public.image (image_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
  NOT DEFERRABLE;

ALTER TABLE public.user_account ADD CONSTRAINT image_user_account_fk
FOREIGN KEY (image_id)
REFERENCES public.image (image_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
  NOT DEFERRABLE;

ALTER TABLE public.topo_borrowing ADD CONSTRAINT user_topo_borrowing_fk
FOREIGN KEY (user_account_id)
REFERENCES public.user_account (user_account_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
  NOT DEFERRABLE;

ALTER TABLE public.topo ADD CONSTRAINT user_topo_fk
FOREIGN KEY (user_account_id)
REFERENCES public.user_account (user_account_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
  NOT DEFERRABLE;

ALTER TABLE public.comment ADD CONSTRAINT user_comment_fk
FOREIGN KEY (author_id)
REFERENCES public.user_account (user_account_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
  NOT DEFERRABLE;

ALTER TABLE public.site ADD CONSTRAINT user_account_site_fk
FOREIGN KEY (user_account_id)
REFERENCES public.user_account (user_account_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
  NOT DEFERRABLE;

ALTER TABLE public.comment ADD CONSTRAINT comment_comment_fk
FOREIGN KEY (parent_id)
REFERENCES public.comment (comment_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
  NOT DEFERRABLE;

ALTER TABLE public.comment_topo ADD CONSTRAINT comment_comment_topo_fk
FOREIGN KEY (comment_id)
REFERENCES public.comment (comment_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
  NOT DEFERRABLE;

ALTER TABLE public.comment_site ADD CONSTRAINT comment_comment_site_fk
FOREIGN KEY (comment_id)
REFERENCES public.comment (comment_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
  NOT DEFERRABLE;

ALTER TABLE public.topo_borrowing ADD CONSTRAINT topo_topo_borrowing_fk
FOREIGN KEY (topo_id)
REFERENCES public.topo (topo_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
  NOT DEFERRABLE;

ALTER TABLE public.comment_topo ADD CONSTRAINT topo_comment_topo_fk
FOREIGN KEY (topo_id)
REFERENCES public.topo (topo_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
  NOT DEFERRABLE;

ALTER TABLE public.site_topo ADD CONSTRAINT topo_site_topo_fk
FOREIGN KEY (topo_id)
REFERENCES public.topo (topo_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
  NOT DEFERRABLE;

ALTER TABLE public.site_image ADD CONSTRAINT site_site_image_fk
FOREIGN KEY (site_id)
REFERENCES public.site (site_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
  NOT DEFERRABLE;

ALTER TABLE public.sector ADD CONSTRAINT site_sector_fk
FOREIGN KEY (site_id)
REFERENCES public.site (site_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
  NOT DEFERRABLE;

ALTER TABLE public.comment_site ADD CONSTRAINT site_comment_site_fk
FOREIGN KEY (site_id)
REFERENCES public.site (site_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
  NOT DEFERRABLE;

ALTER TABLE public.site_topo ADD CONSTRAINT site_site_topo_fk
FOREIGN KEY (site_id)
REFERENCES public.site (site_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
  NOT DEFERRABLE;

ALTER TABLE public.way ADD CONSTRAINT sector_way_fk
FOREIGN KEY (sector_id)
REFERENCES public.sector (sector_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
  NOT DEFERRABLE;

ALTER TABLE public.sector_image ADD CONSTRAINT sector_sector_image_fk
FOREIGN KEY (sector_id)
REFERENCES public.sector (sector_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
  NOT DEFERRABLE;

ALTER TABLE public.length ADD CONSTRAINT way_length_fk
FOREIGN KEY (way_id)
REFERENCES public.way (way_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
  NOT DEFERRABLE;

ALTER TABLE public.point ADD CONSTRAINT length_point_fk
FOREIGN KEY (length_id)
REFERENCES public.length (length_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
  NOT DEFERRABLE;