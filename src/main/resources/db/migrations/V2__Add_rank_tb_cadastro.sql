-- V2: Migrations para adicionar a coluna de LOCAL na tabela de cadastros

ALTER TABLE tb_cadastro
ADD COLUMN local VARCHAR(255);