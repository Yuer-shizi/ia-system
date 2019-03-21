INSERT INTO `ia_system`.`roles` (`id`, `role`) VALUES ('1', 'admin');
INSERT INTO `ia_system`.`roles` (`id`, `role`) VALUES ('2', 'sakuya');

INSERT INTO `ia_system`.`users` (`number`, `username`, `avatar`, `introduction`, `password`, `token`, `age`, `department`, `sex`, `speciality`, `type`, `email`) VALUES ('001', 'Admin', 'https://i.loli.net/2018/08/18/5b7819891bab1.jpg', 'i am admin', '123456', 'admin', '18', '计算机学院', '1', '计算机科学与技术（D）', '0', '123@qq.com');
INSERT INTO `ia_system`.`users` (`number`, `username`, `avatar`, `introduction`, `password`, `token`, `age`, `department`, `sex`, `speciality`, `type`, `email`) VALUES ('002', 'Sakuya', 'https://i.loli.net/2018/08/18/5b7819891bab1.jpg', 'i am sakuya', '123456', 'sakuya', '17', '计算机学院', '0', '计算机科学与技术（D）', '0', '521@gmail.com');


INSERT INTO `ia_system`.`roles_users` (`role_id`, `user_id`) VALUES (1, 1);
INSERT INTO `ia_system`.`roles_users` (`role_id`, `user_id`) VALUES (2, 2);