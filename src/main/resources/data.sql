# table user
CREATE TABLE IF NOT EXISTS  `springboot`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  `age` INT NOT NULL,
  `passwd` VARCHAR(20) NULL,
  `roles` VARCHAR(256) NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`));
# init user
insert into user(name, age, passwd, roles) values('kevin',18,'1234','ADMIN') ;
insert into user(name, age, passwd, roles) values('zhang',28,'1234','USER') ;


# table jpa_entity
CREATE TABLE IF NOT EXISTS `springboot`.`jpa_entity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `f_a` varchar(20) NOT NULL,
  `f_b` varchar(20) NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `field` (`f_a`,`f_b`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

# init jpa_entity
INSERT INTO jpa_entity (f_a, f_b) VALUES("field A","field B") ON DUPLICATE KEY UPDATE
f_a="field A",f_b="field B";
