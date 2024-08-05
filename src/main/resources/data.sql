-- Entidades
--DROP TABLE Consulta;
--DROP TABLE Paciente;
--DROP TABLE Nutricionista;
--DROP TABLE Funcionario;
--DROP TABLE Endereco;

CREATE TABLE Endereco (
	id serial PRIMARY KEY,
	logradouro varchar,
	estado varchar,
	cidade varchar,
	numero int,
	cep varchar
);

CREATE TABLE Funcionario (
	id serial PRIMARY KEY,
	matricula varchar,
	tempoExp int,
	enderecoId int REFERENCES Endereco(id)
);

CREATE TABLE Nutricionista (
	id serial PRIMARY KEY,
	crn varchar,
	especialidade varchar
) INHERITS (Funcionario);

CREATE TABLE Paciente (
	id serial PRIMARY KEY,
	nome varchar,
	dataNascimento date,
	cpf varchar,
	telefone varchar,
	email varchar,
	enderecoId int REFERENCES Endereco(Id)
);

CREATE TABLE Consulta (
	id serial PRIMARY KEY,
	nutricionistaId int REFERENCES Nutricionista(id),
	pacienteId int REFERENCES Paciente(id),
	dataConsulta date,
	observacoes varchar
);

-- Registros
INSERT INTO Endereco (logradouro, estado, cidade, numero, cep)
VALUES
    ('Rua Exemplo', 'SP', 'São Paulo', 123, '01234-567'),
    ('Avenida Teste', 'RJ', 'Rio de Janeiro', 456, '12345-678'),
    ('Praça Exemplar', 'MG', 'Belo Horizonte', 789, '23456-789'),
    ('Serv. Ruazinha', 'SC', 'Joinville', 56, '88899-678');


INSERT INTO Paciente (nome, dataNascimento, cpf, telefone, email, enderecoId)
VALUES
	('João Silva','1999-01-25', '012.345.678-90', '(11)99999-8888', 'joaosilva233@email.com', 1),
	('Maria Ribeiro', '1966-07-13', '14536598764', '+55(51)987456321', 'maria_ribeiro_@gmail.com', 2),
	('Valdemir Rocha', '1944-05-09', '987.654.321-66', '(48) 988856666', 'valdemirRocha@hotmail.com', 3),
	('Marco Santos','2000-01-27', '210.345.678-09', '(22)98887-1234', 'marco_santos_2000@gmail.com', 4);

INSERT INTO Nutricionista (matricula, tempoExp, enderecoId, crn, especialidade)
VALUES
	('MAT123', 5, 1, 'CRN-5/1234', 'Atletas'),
	('MAT231', 2, 2, 'CRN-5/9874/S', 'Vegetarianismo'),
	('MAT321', 1, 3, 'CRN-5/5555', 'Clinica');

INSERT INTO Consulta (pacienteId, nutricionistaId, dataConsulta, observacoes)
VALUES
    (2, 3, '2024-05-30', 'Paciente obeso'),
    (1, 1, '2022-10-01', 'Pós cirurgico'),
    (3, 2, '2024-05-30', 'Paciente quer ganhar massa muscular');

-- Alteracoes

UPDATE Paciente
	SET telefone = '(48)988856611'
	WHERE id = 2;

DELETE FROM Paciente WHERE nome='Marco Santos';

