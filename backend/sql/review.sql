DROP TABLE IF EXISTS `review`;

CREATE TABLE `review` (
  `no` int auto_increment,
  userid int,
  `content` varchar(255) DEFAULT NULL,
  `rank` int NOT NULL,
  `create_date` datetime DEFAULT current_timestamp(),
  image varchar(128) DEFAULT NULL,
  PRIMARY KEY (`no`),
  foreign key (userid) references user(userid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;