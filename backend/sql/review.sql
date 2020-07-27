DROP TABLE IF EXISTS `review`;

CREATE TABLE `review` (
  `no` int auto_increment,
  `email` varchar(128) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `rank`float DEFAULT 0,
  `create_date` datetime DEFAULT current_timestamp(),
  image varchar(128) DEFAULT NULL,
  userid int,
  PRIMARY KEY (`no`),
  foreign key (userid) references user(userid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;