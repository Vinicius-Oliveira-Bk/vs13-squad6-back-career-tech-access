ALTER SESSION SET CURRENT_SCHEMA = VS_13_EQUIPE_6;

-- https://pt.stackoverflow.com/questions/4170/quais-s%c3%a3o-os-tipos-de-dados-apropriados-para-colunas-como-endere%c3%a7o-e-mail-tele

-- USUARIO
/*
    TipoUsuarioEnum
    1 - ESTUDANTE,
    2 - PROFISSIONAL_REALOCACAO,
    3 - PCD,
    4 - MENTOR
*/
INSERT INTO USUARIO (ID, NOME, DATA_NASCIMENTO, CPF, EMAIL, SENHA, ACESSO_PCD, TIPO_USUARIO, INTERESSES, IMAGEM_DOCUMENTO)
VALUES (1, 'Administrador', TO_DATE('1989-08-18', 'YYYY-MM-DD'), '99369419004', 'admin@admin.com', 'admin', 'N', 2, 'Interesse de exemplo', 'https://google.com');

INSERT INTO USUARIO (ID, NOME, CPF, DATA_NASCIMENTO, EMAIL, SENHA, ACESSO_PCD, TIPO_USUARIO, INTERESSES, IMAGEM_DOCUMENTO)
VALUES (2, 'Nathanial Frankel', '03194176030', TO_DATE('2009-07-13', 'YYYY-MM-DD'), 'nathanial@gmail.com', '$2a$04$auV/G2xUXXMHrA9I/tCpOe1sER0JcYKvumt9LKIDJNOXFhnE5pMvS', 'N', 1, 'Donec diam neque', 'https://google.com');

INSERT INTO USUARIO (ID, NOME, CPF, DATA_NASCIMENTO, EMAIL, SENHA, ACESSO_PCD, TIPO_USUARIO, INTERESSES, IMAGEM_DOCUMENTO)
VALUES (3, 'Carolina Rayssa Betina Farias', '87764807044', TO_DATE('1998-12-09', 'YYYY-MM-DD'), 'carolina@gmail.com', '$2a$04$U8HVnrTjjrejq6zKWO0p2.vXsrRLyMHn3nq7WNre0W3N3WqwfH6hu', 'S', 3, 'Suspendisse potenti.', 'https://google.com');

INSERT INTO USUARIO (ID, NOME, DATA_NASCIMENTO, CPF, EMAIL, SENHA, ACESSO_PCD, TIPO_USUARIO, INTERESSES, IMAGEM_DOCUMENTO)
VALUES (4, 'Larissa', TO_DATE('1998-12-09', 'YYYY-MM-DD'), '87764807044', 'larissa@gmail.com', 'mentor', 'N', 4, 'asdsada', 'asdasdsd');

-- CLIENTE
/* OBS:
   TipoPlanoEnum
    1 - GRATUITO
    2 - BASICO
    3 - PREMIUM
*/
INSERT INTO CLIENTE (ID, ID_USUARIO, TIPO_PLANO, CONTROLE_PARENTAL)
VALUES (1, 1, 3, 'N');

INSERT INTO CLIENTE (ID, ID_USUARIO, TIPO_PLANO, CONTROLE_PARENTAL)
VALUES (2, 2, 1, 'S');

INSERT INTO CLIENTE (ID, ID_USUARIO, TIPO_PLANO, CONTROLE_PARENTAL)
VALUES (3, 3, 2, 'N');

INSERT INTO CLIENTE (ID, ID_USUARIO, TIPO_PLANO, CONTROLE_PARENTAL)
VALUES (4, 4, 2, 'N');


-- ENDERECO
/* OBS:
   TipoEnderecoEnum
    1 - RESIDENCIAL,
    2 - COMERCIAL;
*/
INSERT INTO ENDERECO (ID, LOGRADOURO, NUMERO, COMPLEMENTO, CEP, CIDADE, ESTADO, PAIS, TIPO)
VALUES (1, 'Rua das Nascentes', 979, 'Sala comercial', '69022526', 'Manaus', 'Amazonas', 'Brasil', 2);

INSERT INTO ENDERECO (ID, LOGRADOURO, NUMERO, COMPLEMENTO, CEP, CIDADE, ESTADO, PAIS, TIPO)
VALUES (2, 'Rua Apóstolo Matheus', 470, 'Casa', '97037162', 'Santa Maria', 'Rio Grande do Sul', 'Brasil', 1);

INSERT INTO ENDERECO (ID, LOGRADOURO, NUMERO, COMPLEMENTO, CEP, CIDADE, ESTADO, PAIS, TIPO)
VALUES (3, 'Rua 44', 313, 'Condomínio', '77815630', 'Araguaína', 'Tocantins', 'Brasil', 1);

INSERT INTO ENDERECO (ID, LOGRADOURO, NUMERO, COMPLEMENTO, CEP, CIDADE, ESTADO, PAIS, TIPO)
VALUES (4, 'Rua São Jerônimo', 313, 'Casa', '77815630', 'São Paulo', 'São Paulo', 'Brasil', 1);


-- USUARIO_ENDERECO
INSERT INTO USUARIO_ENDERECO (ID_USUARIO, ID_ENDERECO)
VALUES (1, 1);

INSERT INTO USUARIO_ENDERECO (ID_USUARIO, ID_ENDERECO)
VALUES (2, 2);

INSERT INTO USUARIO_ENDERECO (ID_USUARIO, ID_ENDERECO)
VALUES (3, 3);

INSERT INTO USUARIO_ENDERECO (ID_USUARIO, ID_ENDERECO)
VALUES (4, 4);


-- CONTATO
INSERT INTO CONTATO (ID, DESCRICAO, TELEFONE, TIPO)
VALUES (1, 'Telefone Residencial', '9235240576', 1);

INSERT INTO CONTATO (ID, DESCRICAO, TELEFONE, TIPO)
VALUES (2, 'Telefone Comercial', '5134004000', 2);

INSERT INTO CONTATO (ID, DESCRICAO, TELEFONE, TIPO)
VALUES (3, 'Telefone Celular', '6329878205', 1);

INSERT INTO CONTATO (ID, DESCRICAO, TELEFONE, TIPO)
VALUES (4, 'Telefone Celular', '114576205', 1);


-- USUARIO_CONTATO
INSERT INTO USUARIO_CONTATO (ID_USUARIO, ID_CONTATO)
VALUES (1, 1);

INSERT INTO USUARIO_CONTATO (ID_USUARIO, ID_CONTATO)
VALUES (2, 2);

INSERT INTO USUARIO_CONTATO (ID_USUARIO, ID_CONTATO)
VALUES (3, 3);

INSERT INTO USUARIO_CONTATO (ID_USUARIO, ID_CONTATO)
VALUES (4, 4);


-- Cadastro de um usuário para cada tipo de usuário
/*
    AreaAtuacaoEnum
    1 - TI,
    2 - SAUDE,
    3 - EDUCACAO,
    4 - FINANCAS,
    5 - MARKETING,
    6 - JURIDICO,
    7 - ENGENHARIA,
    8 - DESIGN,
    9 - COMERCIO,
    10 - MEIO_AMBIENTE,
    11 - CONSULTORIA,
    12 - RH,
    13 - OUTROS;

    NivelExperienciaEnum
    1 - JUNIOR,
    2 - PLENO,
    3 - SENIOR;
*/
INSERT INTO PROFISSIONAL_REALOCACAO (ID, ID_CLIENTE, PROFISSAO, OBJETIVO_PROFISSIONAL)
VALUES (1, 1, 'Desenvolvedor', 'Desenvolver software');

INSERT INTO ESTUDANTE (ID, ID_CLIENTE, MATRICULA, COMPROVANTE_MATRICULA, INSTITUICAO, CURSO, DATA_INICIO, DATA_TERMINO)
VALUES (1, 2, '1234567890', 'https://comprovante.com.br', 'IFRS', 'Análise e Desenvolvimento de Sistemas', TO_DATE('2022-02-19', 'YYYY-MM-DD'), TO_DATE('2026-02-19', 'YYYY-MM-DD'));

INSERT INTO PCD (ID, ID_CLIENTE, CERTIFICADO_DEFICIENCIA_GOV, TIPO_DEFICIENCIA)
VALUES (1, 3, 'https://certificado.com.br', 'FISICA');

INSERT INTO PROFISSIONAL_MENTOR (ID, ID_USUARIO, AREA_ATUACAO, NIVEL_EXPERIENCIA, CARTEIRA_TRABALHO, DOCUMENTOS_VALIDADOS)
VALUES (1, 4, 1, 3, '12345678912345678912', 'S');


-- Agendamentos de exemplo
/*
    StatusAgendaEnum
    1 - DISPONIVEL,
    2 - AGENDADO,
    3 - CANCELADO,
    4 - PRESENTE;
*/
INSERT INTO AGENDA (ID, ID_MENTOR, ID_CLIENTE, DATA_INICIO, DATA_FIM, STATUS)
VALUES (1, 1, 2, TO_DATE('2021-08-19 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-08-19 11:00:00', 'YYYY-MM-DD HH24:MI:SS'), 2);

COMMIT;