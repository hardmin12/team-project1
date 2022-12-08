insert into dog_user(id, username, addr, email, password, authority, admin) values (doguser_seq.nextval, 'son','서울','son0@google.com','1234','1','1');
insert into dog_user(id, username, addr, email, password, authority, admin) values (doguser_seq.nextval, 'son2','부산','son2@google.com','1234','1','0');
insert into dog_user(id, username, addr, email, password, authority, admin) values (doguser_seq.nextval, 'son3','전주','son3@google.com','1234','1','0');

insert into pet(id, dogname, dogage, doggender, kind, dogsize, per) values (pet_seq.nextval, '골디', '5', '수컷','골든리트리버','대형견','적응적');
insert into pet(id, dogname, dogage, doggender, kind, dogsize, per) values (pet_seq.nextval, '초코', '1', '수컷','치와와','소형견','소심함');
insert into pet(id, dogname, dogage, doggender, kind, dogsize, per) values (pet_seq.nextval, '민트', '3', '암컷','푸들','중형견','리더형');
insert into pet(id, dogname, dogage, doggender, kind, dogsize, per) values (pet_seq.nextval, '우유', '5', '수컷','골든리트리버','대형견','긍정적');
insert into pet(id, dogname, dogage, doggender, kind, dogsize, per) values (pet_seq.nextval, '초코', '2', '수컷','요크셔테리어','소형견','리더형');
insert into pet(id, dogname, dogage, doggender, kind, dogsize, per) values (pet_seq.nextval, '설탕', '3', '암컷','푸들','중형견','적응적');
insert into pet(id, dogname, dogage, doggender, kind, dogsize, per) values (pet_seq.nextval, '까망이', '3', '암컷','푸들','대형견','리더형');
insert into pet(id, dogname, dogage, doggender, kind, dogsize, per) values (pet_seq.nextval, '렉스', '3', '암컷','푸들','중형견','소심함');
insert into pet(id, dogname, dogage, doggender, kind, dogsize, per) values (pet_seq.nextval, '익순', '4', '중성','푸들','중형견','소심함');
insert into pet(id, dogname, dogage, doggender, kind, dogsize, per) values (pet_seq.nextval, '익준', '3', '암컷','진돗개','중형견','독립적');
insert into pet(id, dogname, dogage, doggender, kind, dogsize, per) values (pet_seq.nextval, '슈가', '5', '암컷','푸들','중형견','소심함');
insert into pet(id, dogname, dogage, doggender, kind, dogsize, per) values (pet_seq.nextval, '메로', '2', '중성','푸들','소형견','독립적');
insert into pet(id, dogname, dogage, doggender, kind, dogsize, per) values (pet_seq.nextval, '슈슈', '2', '암컷','푸들','중형견','적응적');
insert into pet(id, dogname, dogage, doggender, kind, dogsize, per) values (pet_seq.nextval, '빙고', '1', '암컷','푸들','중형견','독립적');
insert into pet(id, dogname, dogage, doggender, kind, dogsize, per) values (pet_seq.nextval, '흰둥이', '10', '암컷','푸들','중형견','적응적');


insert into profile(id, heart, pet_id, doguser_id) values (profile_seq.nextval, '1','1','1');

