# create table
CREATE TABLE IF NOT EXISTS `springboot`.`jpa_entity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `f_a` varchar(20) NOT NULL,
  `f_b` varchar(20) NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `field` (`f_a`,`f_b`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

# init data
INSERT INTO jpa_entity (f_a, f_b) VALUES("field A","field B") ON DUPLICATE KEY UPDATE
f_a="field A",f_b="field B";
