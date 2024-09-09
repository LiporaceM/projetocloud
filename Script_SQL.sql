CREATE TABLE Cliente (
CPF VARCHAR(11) PRIMARY KEY,
Nome VARCHAR(100) NOT NULL,
Email VARCHAR(100),
Celular INT,
Endereco VARCHAR(100),
DataNascimento DATE
);
    
CREATE TABLE Cartao (
Numero VARCHAR(16) PRIMARY KEY,
CVV INT,
NomeCartao VARCHAR(100),
Validade VARCHAR(5),
Limite DECIMAL,
Extrato DECIMAL
);
    
CREATE TABLE Comerciante (
CNPJ VARCHAR(14) PRIMARY KEY,
Nome VARCHAR(100),
Email VARCHAR(100),
Celular VARCHAR(11),
Documentos VARCHAR(100),
DataNascimento DATE,
Logradouro VARCHAR(100)
);
    
CREATE TABLE Transacao (
IDTransacao VARCHAR(100) PRIMARY KEY,
Autorizacao VARCHAR(100),
DataHora DATETIME,
Valor DECIMAL
);
    
CREATE TABLE Notificacao (
Field VARCHAR(100) PRIMERY KEY
);
    
    
    
    