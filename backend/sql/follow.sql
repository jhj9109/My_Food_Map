﻿DROP TABLE IF EXISTS `follow`;

CREATE TABLE `follow` (
  `no` int auto_increment,
  `followerId` int,
  `followingId` int,
  PRIMARY KEY (no),
  foreign key (followerId) references user(userid),
  foreign key (followingId) references user(userid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;