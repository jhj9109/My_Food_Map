DROP TABLE IF EXISTS `store`;

CREATE TABLE store(
	id INT NOT NULL AUTO_INCREMENT,
    code INT NOT NULL,
    store_name VARCHAR(255) NOT NULL,
    dolo VARCHAR(255),
    jibun VARCHAR(255),
    store_number VARCHAR(30),
    type VARCHAR(30),
    menu VARCHAR(10),
    deal_date VARCHAR(10),
    PRIMARY KEY(id)
);