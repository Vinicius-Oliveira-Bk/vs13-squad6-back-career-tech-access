ALTER SESSION SET CURRENT_SCHEMA = VS_13_EQUIPE_6;

------------------------------------
-- CRIAÇÃO DA TABELA DE USUARIO
CREATE TABLE USUARIO (
	id NUMBER,
	nome VARCHAR2(80) NOT NULL,
	data_nascimento DATE NOT NULL,
	cpf CHAR(11) NOT NULL,
	email VARCHAR2(255),
	senha VARCHAR2(100) NOT NULL,
	eh_pcd CHAR(1) NOT NULL CHECK (eh_pcd IN ('S', 'N')),
	tipo_deficiencia VARCHAR2(255),
	certificado_deficiencia_gov VARCHAR2 (255),
	imagem_documento VARCHAR2(255),
	ativo CHAR(1) NOT NULL,
	CONSTRAINT PK_USUARIO PRIMARY KEY(ID),
	CONSTRAINT UK_USUARIO_CPF UNIQUE (CPF),
	CONSTRAINT UK_USUARIO_EMAIL UNIQUE (EMAIL)
);

CREATE SEQUENCE SEQ_USUARIO
    START WITH 1
    INCREMENT BY 1
    NOCACHE NOCYCLE;


------------------------------------
-- CRIAÇÃO DA TABELA DE CLIENTE
CREATE TABLE CLIENTE (
	id NUMBER,
	id_usuario NUMBER,
	tipo_plano NUMBER NOT NULL,
	controle_parental CHAR(1) NOT NULL CHECK (controle_parental IN ('S', 'N')),
	eh_estudante CHAR(1) NOT NULL CHECK (eh_estudante IN ('S', 'N')),
	eh_profissional_realocacao CHAR(1) NOT NULL CHECK (eh_profissional_realocacao IN ('S', 'N')),
	profissao VARCHAR2(255),
	objetivo_profissional VARCHAR2(255),
	matricula VARCHAR2(20),
	comprovante_matricula VARCHAR2(255),
	instituicao VARCHAR2(255),
	curso VARCHAR2(255),
	data_inicio DATE,
	data_termino DATE,
	CONSTRAINT PK_CLIENTE PRIMARY KEY (ID),
	CONSTRAINT PK_CLIENTE_USUARIO FOREIGN KEY (id_usuario) REFERENCES USUARIO(id)
);

CREATE SEQUENCE SEQ_CLIENTE
	START WITH 1
	INCREMENT BY 1
	NOCACHE NOCYCLE;



------------------------------------
-- CRIAÇÃO DE TABELA DE MENTORES
CREATE TABLE PROFISSIONAL_MENTOR (
    id NUMBER,
    id_usuario NUMBER NOT NULL,
    nivel_experiencia NUMBER NOT NULL,
    carteira_trabalho VARCHAR2(20) NOT NULL,
    CONSTRAINT PK_PROFISSIONAL_MENTOR PRIMARY KEY (ID),
    CONSTRAINT FK_PROFISSIONAL_CLIENTE FOREIGN KEY (id_usuario) REFERENCES USUARIO(id)
);

CREATE SEQUENCE SEQ_PROFISSIONAL_MENTOR
	START WITH 1
	INCREMENT BY 1
	NOCACHE NOCYCLE;

------------------------------------
-- CRIAÇÃO DE TABELA DE AREA_INTERESSE
CREATE TABLE AREA_INTERESSE(
	id NUMBER,
	id_cliente NUMBER NOT NULL,
	interesse NUMBER NOT NULL,
	CONSTRAINT PK_AREA_INTERESSE PRIMARY KEY (ID),
	CONSTRAINT FK_AREA_INT_CLIENTE FOREIGN KEY (id_cliente) REFERENCES CLIENTE(id)
);

CREATE SEQUENCE SEQ_AREA_INTERESSE
	START WITH 1
	INCREMENT BY 1
	NOCACHE NOCYCLE;


------------------------------------
-- CRIAÇÃO DE TABELA DE AREA_ATUACAO
CREATE TABLE AREA_ATUACAO(
	id NUMBER,
	id_profissional NUMBER NOT NULL,
	interesse NUMBER NOT NULL,
	CONSTRAINT PK_AREA_ATUACAO PRIMARY KEY (ID),
	CONSTRAINT FK_AREA_ATU_PROFISSIONAL FOREIGN KEY (id_profissional) REFERENCES PROFISSIONAL_MENTOR(id)
);

CREATE SEQUENCE SEQ_AREA_ATUACAO
	START WITH 1
	INCREMENT BY 1
	NOCACHE NOCYCLE;

------------------------------------
-- CRIAÇÃO DE TABELA DE ENDERECOS
CREATE TABLE ENDERECO (
    id NUMBER NOT NULL,
    logradouro VARCHAR2(255) NOT NULL,
    numero VARCHAR2(5) NOT NULL,
    complemento VARCHAR2(255),
    cep VARCHAR2(8) NOT NULL,
    cidade VARCHAR2(255) NOT NULL,
    estado VARCHAR2(255) NOT NULL,
    pais VARCHAR2(255) NOT NULL,
    tipo NUMBER(1) NOT NULL,
    CONSTRAINT PK_ENDERECO PRIMARY KEY(id)
);

CREATE SEQUENCE SEQ_ENDERECO
	START WITH 1
	INCREMENT BY 1
	NOCACHE NOCYCLE;


------------------------------------
-- CRIAÇÃO DE TABELA DE USUARIO_ENDERECO
CREATE TABLE USUARIO_ENDERECO (
	id_usuario NUMBER NOT NULL,
	id_endereco NUMBER NOT NULL,
	CONSTRAINT PK_USU_END PRIMARY KEY (id_usuario, id_endereco),
	CONSTRAINT FK_CLI_END_USUARIO FOREIGN KEY (id_usuario) REFERENCES USUARIO(ID),
	CONSTRAINT FK_CLI_END_ENDERECO FOREIGN KEY (id_endereco) REFERENCES ENDERECO(ID)
);


------------------------------------
-- CRIACÃO DA TABELA CONTATO
CREATE TABLE CONTATO (
    id NUMBER NOT NULL,
    id_usuario NUMBER NOT NULL,
    descricao VARCHAR2(255) NOT NULL,
    telefone VARCHAR2(13) NOT NULL,
    tipo NUMBER(1) NOT NULL,
    CONSTRAINT PK_CONTATO PRIMARY KEY(id),
    CONSTRAINT FK_CONTATO_USUARIO FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO(ID)
);

CREATE SEQUENCE SEQ_CONTATO	START WITH 1
    INCREMENT BY 1
    NOCACHE NOCYCLE;



------------------------------------
-- Agendamentos (AGENDA)
CREATE TABLE AGENDA (
    id NUMBER NOT NULL,
    id_mentor NUMBER NOT NULL,
    id_cliente NUMBER,
    data_inicio TIMESTAMP(6) NOT NULL,
    data_fim TIMESTAMP(6) NOT NULL,
    status NUMBER NOT NULL,
    CONSTRAINT PK_AGENDA PRIMARY KEY (id),
    CONSTRAINT FK_AGENDA_PROFISSIONAL FOREIGN KEY (id_mentor) REFERENCES PROFISSIONAL_MENTOR(id),
    CONSTRAINT FK_AGENDA_CLIENTE FOREIGN KEY (id_cliente) REFERENCES CLIENTE(id)
);

CREATE SEQUENCE SEQ_AGENDA START WITH 1
    INCREMENT BY 1
    NOCACHE NOCYCLE;


CREATE TABLE CARGO (
	id NUMBER,
	nome VARCHAR2(50) NOT NULL,
	CONSTRAINT PK_CARGO PRIMARY KEY (id)
);

CREATE SEQUENCE SEQ_CARGO START WITH 1
    INCREMENT BY 1
    NOCACHE NOCYCLE;


CREATE TABLE USUARIO_CARGO (
	id_usuario NUMBER NOT NULL,
	id_cargo NUMBER NOT NULL,
	CONSTRAINT PK_USUARIO_CARGO PRIMARY KEY (id_usuario, id_cargo),
	CONSTRAINT FK_USU_CAR_USUARIO FOREIGN KEY (id_usuario) REFERENCES USUARIO(id),
	CONSTRAINT FK_USU_CAR_CARGO FOREIGN KEY (id_cargo) REFERENCES CARGO(id)
);


INSERT INTO CARGO (id, nome) VALUES (seq_cargo.nextval, 'ROLE_ADMIN');
INSERT INTO CARGO (id, nome) VALUES (seq_cargo.nextval, 'ROLE_USUARIO');
INSERT INTO CARGO (id, nome) VALUES (seq_cargo.nextval, 'ROLE_CLIENTE');
INSERT INTO CARGO (id, nome) VALUES (seq_cargo.nextval, 'ROLE_PROFISSIONAL');


COMMIT;