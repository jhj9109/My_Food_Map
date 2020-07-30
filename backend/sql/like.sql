﻿﻿DROP TABLE IF EXISTS `like`;

CREATE TABLE `like` (
  `no` int auto_increment,
  `userid` int,
  `reviewid` int,
  PRIMARY KEY (`no`),
  foreign key (userid) references user(userid),
  foreign key (reviewid) references review(no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;