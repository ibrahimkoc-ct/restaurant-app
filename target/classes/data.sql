INSERT INTO CATEGORY VALUES (1,'No image','Güzel el yapımı pizzalar','Pizzalar');
INSERT INTO CATEGORY VALUES (2,'No image','Güzel el yapımı içini ısıtacak çorbalar','Çorbalar');
INSERT INTO CATEGORY VALUES (3,'No image','Günlük taze et yemekler,','Et yemekleri');
INSERT INTO CATEGORY VALUES (4,'No image','Atıştırmalıklar','Atıştırmalıklar');

INSERT INTO PRODUCT VALUES(1,'Pizzalar','pizza','25','4 Mevsim Pizza','https://i.hizliresim.com/hFRUbL.jpg',1);
INSERT INTO PRODUCT VALUES(2,'Pizzalar','pizza','25','Akdeniz','https://i.hizliresim.com/hFRUbL.jpg',1);
INSERT INTO PRODUCT VALUES(3,'Pizzalar','pizza','25','Bol acılı','https://i.hizliresim.com/hFRUbL.jpg',1);
INSERT INTO PRODUCT VALUES(4,'Pizzalar','pizza','25','Sucuklu','https://i.hizliresim.com/hFRUbL.jpg',1);
INSERT INTO PRODUCT VALUES(5,'Pizzalar','pizza','25','Karışık','https://i.hizliresim.com/hFRUbL.jpg',1);
INSERT INTO PRODUCT VALUES(6,'Pizzalar','pizza','25','Bol malzemos','https://i.hizliresim.com/hFRUbL.jpg',1);
INSERT INTO PRODUCT VALUES(7,'Pizzalar','pizza','25','pitost','https://i.hizliresim.com/hFRUbL.jpg',1);
INSERT INTO PRODUCT VALUES(8,'Çorbalar','Çorbalar','10','Mercimek Çorbası','No Image',2);
INSERT INTO PRODUCT VALUES(9,'Çorbalar','Çorbalar','10','Tarhana Çorbası','No Image',2);
INSERT INTO PRODUCT VALUES(10,'Çorbalar','Çorbalar','10','Yayla Çorbası','No Image',2);
INSERT INTO PRODUCT VALUES(11,'Et yemekleri','kebap','30','Kebap Çeşitleri','No Image',3);
INSERT INTO PRODUCT VALUES(12,'Et yemekleri','iskender','40','Iskender','No Image',3);
INSERT INTO PRODUCT VALUES(13,'Et yemekleri','döner','50','Döner','No Image',3);
INSERT INTO PRODUCT VALUES(14,'Et Atıştırmalıklar','sosisli','20','Sosisli','No Image',4);
INSERT INTO PRODUCT VALUES(15,'Et Atıştırmalıklar','goralı','20','Goralı','No Image',4);
INSERT INTO PRODUCT VALUES(16,'Et Atıştırmalıklar','patates kızartması','20','Patates Kızartması','No Image',4);


INSERT INTO CATEGORY_TABLE  VALUES (1,'200 masalık geniş salon','No Image','Salon',200);
INSERT INTO CATEGORY_TABLE  VALUES (2,'70 masalık geniş bahçe','No Image','Bahçe',70);
INSERT INTO CATEGORY_TABLE  VALUES (3,'50 masalık geniş balkon','No Image','Balkon',50);
INSERT INTO CATEGORY_TABLE  VALUES (4,'50 masalık geniş teras','No Image','Teras',50);
INSERT INTO CATEGORY_TABLE  VALUES (5,'20 masalık geniş iç bahçe','No Image','Iç bahçe',20);

INSERT INTO USERS  VALUES ('admin',true,'{noop}pass1');
INSERT INTO Authorities  VALUES ('admin','ROLE_ADMIN');
INSERT INTO USERS  VALUES ('user',true,'{noop}12345');
INSERT INTO Authorities  VALUES ('user','ROLE_USER');