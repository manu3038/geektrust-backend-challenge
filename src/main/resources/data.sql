insert into person values(1,'M','Raja',null);
insert into person values(2,'F','Rani',null);

insert into parent values(1,1,2);

insert into person values(3,'M','chit',1);
insert into person values(4,'M','ish',1);
insert into person values(5,'M','vich',1);
insert into person values(6,'M','aras',1);
insert into person values(7,'F','satya',1);
insert into person values(8,'M','vyan',null);

insert into parent values(2,8,7);

insert into person values(9,'M','asva',2);
insert into person values(10,'M','vyas',2);
insert into person values(11,'F','atya',2);

insert into person values(12,'F','satvy',null);
insert into person values(13,'F','krpi',null);

insert into parent values(3,9,12);
insert into parent values(4,10,13);

insert into person values(14,'M','vasa',3);
insert into person values(15,'M','kriya',4);
insert into person values(16,'F','krithi',4);

insert into person values(17,'F','chitra',null);

--id and father_id and mother_id
insert into parent values(5,6,17);

insert into person values(18,'F','jnki',5);
insert into person values(19,'M','ahit',5);

insert into person values(20,'M','arit',null);

insert into parent values(6,20,18);

insert into person values(21,'F','lavnya',6);
insert into person values(30,'M','laki',6);

insert into person values(31,'F','lika',null);

insert into parent values(7,5,31);

insert into person values(22,'F','chika',7);
insert into person values(23,'F','vila',7);

insert into person values(24,'F','amba',null);

insert into parent values(8,3,24);

insert into person values(25,'F','dritha',8);
insert into person values(26,'F','tritha',8);
insert into person values(27,'M','vritha',8);

insert into person values(28,'M','jaya',null);

insert into parent values(9,28,25);

insert into person values(29,'M','yodhan',9);

