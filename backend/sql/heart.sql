<<<<<<< HEAD
﻿﻿DROP TABLE IF EXISTS `heart`;
=======


use ssafysk;
>>>>>>> 9a9d8c97f8d5907ee3e1fa294c63fe7c7bfb439e

CREATE TABLE `heart` (
  `no` int auto_increment,
  `userid` int,
  `reviewid` int,
  PRIMARY KEY (`no`),
  foreign key (userid) references user(userid),
  foreign key (reviewid) references review(no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;