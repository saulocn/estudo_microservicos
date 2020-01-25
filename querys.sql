SELECT * FROM `fornecedor`.`info_fornecedor` LIMIT 1000;

select * from `fornecedor`.`produto`;

SELECT * FROM `loja`.`compra` LIMIT 1000;

begin;

insert into fornecedor.info_fornecedor 
(endereco, estado, nome)
values
('Endereco Fornecedor 1', 'SP', 'fornecedor-SP');


insert into fornecedor.info_fornecedor 
(endereco, estado, nome)
values
('Endereco Fornecedor 2', 'MG', 'fornecedor-MG');


insert into fornecedor.produto 
(descricao, estado, nome, preco)
values
('Flor 1', 'SP', 'Flor 1', 15.9);

insert into fornecedor.produto 
(descricao, estado, nome, preco)
values
('Flor 2', 'SP', 'Flor 2', 19.8);


insert into fornecedor.produto 
(descricao, estado, nome, preco)
values
('Flor 3', 'MG', 'Flor 3', 19.8);

insert into fornecedor.produto 
(descricao, estado, nome, preco)
values
('Flor 4', 'MG', 'Flor 4', 15.2);

commit;