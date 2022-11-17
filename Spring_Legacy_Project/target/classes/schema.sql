CREATE SCHEMA IF NOT EXISTS `ssafy_shoppingmall2` DEFAULT CHARACTER SET utf8 ;
USE `ssafy_shoppingmall2` ;


CREATE TABLE IF NOT EXISTS `product` (
  `pCode` VARCHAR(30) NOT NULL,
  `pName` VARCHAR(45) NOT NULL,
  `price` INT NOT NULL,
  `quantity` INT NOT NULL,
  `brand` VARCHAR(45) NOT NULL,
  `pDesc` VARCHAR(300) NOT NULL,
  `fileName` VARCHAR(20) NULL,
  `fileUri` VARCHAR(200) NULL,
  PRIMARY KEY (`pCode`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `user` (
  `userId` VARCHAR(30) NOT NULL,
  `userName` VARCHAR(45) NOT NULL,
  `userPass` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`userId`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `review` (
  `reviewId` INT NOT NULL AUTO_INCREMENT,
  `pCode` VARCHAR(45) NOT NULL,
  `writer` VARCHAR(45) NOT NULL,
  `content` VARCHAR(300) NULL,
  PRIMARY KEY (`reviewId`)
) ENGINE = InnoDB;


INSERT INTO product
VALUES
('R00001', 'S냉장고', 1000000, 20, '삼성', '삼성 스마트 냉장고', NULL, NULL),
('R00002', 'L냉장고', 100000, 10, '엘리스', '엘리스 저가형 냉장고', NULL, NULL),
('R00003', 'T냉장고', 3000000, 200, '로보', '로보 AI 냉장고', NULL, NULL),
('N00001', 'S노트북', 900000, 50, '삼성', '삼성 파워노트북', NULL, NULL),
('N00002', 'L노트북', 700000, 10, '엘리스', '엘리스 슬림노트북', NULL, NULL),
('P00001', '우주폰S', 500000, 100, '삼성', '삼성 우주폰S', NULL, NULL);

INSERT INTO review
VALUES
(1, 'R00001', '김싸피','성능이 아주 좋습니다'),
(2, 'R00001', '최싸피', '스마트냉장고라 편리합니다.'),
(3, 'P00001', '최싸피', '역시 폰은 우주폰이 제일 좋아요~!!');

INSERT INTO user
VALUES
("ssafy", "김싸피", "1234"),
("park", "박싸피", "1357"),
("lee", "이싸피", "2468"),
("choi", "최싸피", "4321");


SELECT * FROM product;
SELECT * FROM review;
SELECT * FROM user;
