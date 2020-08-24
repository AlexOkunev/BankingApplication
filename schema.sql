CREATE TABLE public.clients (
	full_name varchar NOT NULL,
	id int4 NOT NULL,
	short_name varchar(40) NOT NULL,
	address varchar NOT NULL,
	client_type varchar(10) NOT NULL,
	CONSTRAINT clients_pk PRIMARY KEY (id)
);

CREATE SEQUENCE public.client_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
	
CREATE TABLE public.banks (
	id int4 NOT NULL,
	bank_name varchar NOT NULL,
	bik varchar NOT NULL,
	CONSTRAINT banks_pk PRIMARY KEY (id),
	CONSTRAINT banks_un_bik UNIQUE (bik),
	CONSTRAINT banks_un_name UNIQUE (bank_name)
);

CREATE SEQUENCE public.banks_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
	
CREATE TABLE public.deposits (
	id int4 NOT NULL,
	bank_id int4 NOT NULL,
	client_id int4 NOT NULL,
	create_date date NOT NULL,
	"percent" float4 NOT NULL,
	term int4 NOT NULL,
	CONSTRAINT deposits_pk PRIMARY KEY (id),
	CONSTRAINT deposits_fk FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE RESTRICT,
	CONSTRAINT deposits_fk_1 FOREIGN KEY (bank_id) REFERENCES banks(id) ON DELETE RESTRICT
);

CREATE SEQUENCE public.deposit_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;