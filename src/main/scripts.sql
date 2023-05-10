-- Cursos 
insert into curso(id,nome,pessoa_instrutora,preco)
values(1001,'teoria das coisas','maria silva',500.00);

insert into curso(id,nome,pessoa_instrutora,preco)
values(1002,'como bolar um baseado','Bob Marley',420.00);

insert into curso(id,nome,pessoa_instrutora,preco)
values(1003,'filosofia','Platao',70.00);

insert into curso(id,nome,pessoa_instrutora,preco)
values(1004,'artes','Picasso',70.00);

-- Alunos

insert into aluno(id,nome,curso_id)
values(1005,'joao',1002);

insert into aluno(id,nome,curso_id)
values(1006,'maria',1002);

insert into aluno(id,nome,curso_id)
values(1007,'jose',1001);

insert into aluno(id,nome,curso_id)
values(1008,'pedro',1004);
