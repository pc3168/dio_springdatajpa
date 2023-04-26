insert into cliente (id, email, cep, cidade, logradouro, numero, telefone) values (1, "renato@gmail.com","37120-000","Paraguaçu","Rua Jair de Souza Dias",300, "3532673030");
insert into cliente (id, email, cep, cidade, logradouro, numero, telefone) values (2, "paulo@gmail.com","35200-000","Varginha","Rua Celestino Caproni de Morais",190, "3532983050");
insert into cliente (id, email, cep, cidade, logradouro, numero, telefone) values (3, "ana@gmail.com","33098-000","Alfenas","Rua Salvador Dias Tavares",100, "3532563080");

insert into produto (id, nome, preco) values (1, "pepino", 23.50);
insert into produto (id, nome, preco) values (2, "arroz", 21.80);
insert into produto (id, nome, preco) values (3, "feijão", 12.50);
insert into produto (id, nome, preco) values (4, "macarrão", 8.40);
insert into produto (id, nome, preco) values (5, "carne", 35.20);
insert into produto (id, nome, preco) values (6, "banana", 12.00);

insert into pedido (id,data, cliente_id, total) values (1,now(),1,57.80);
insert into pedido (id,data, cliente_id, total) values (2,now(),2,20.90);

insert into pedido_produtos (pedido_id, produtos_id) values (1,1);
insert into pedido_produtos (pedido_id, produtos_id) values (1,2);
insert into pedido_produtos (pedido_id, produtos_id) values (1,3);

insert into pedido_produtos (pedido_id, produtos_id) values (2,3);
insert into pedido_produtos (pedido_id, produtos_id) values (2,4);