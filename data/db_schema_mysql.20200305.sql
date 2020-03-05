-- --------------------------------------------------------
-- 호스트:                          wession.com
-- 서버 버전:                        5.5.38-0ubuntu0.12.04.1 - (Ubuntu)
-- 서버 OS:                        debian-linux-gnu
-- HeidiSQL 버전:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 테이블 ws_scim_v1.WS_ADMIN_USER 구조 내보내기
CREATE TABLE IF NOT EXISTS `WS_ADMIN_USER` (
  `id` varchar(64) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  `passwd` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 ws_scim_v1.WS_GROUP_USER 구조 내보내기
CREATE TABLE IF NOT EXISTS `WS_GROUP_USER` (
  `groupId` varchar(64) NOT NULL,
  `userId` varchar(64) NOT NULL,
  PRIMARY KEY (`userId`,`groupId`),
  KEY `FK6b6jefyhbjaip83qcgg6sveb1` (`groupId`),
  CONSTRAINT `FK6b6jefyhbjaip83qcgg6sveb1` FOREIGN KEY (`groupId`) REFERENCES `WS_SCIM_GROUP` (`id`),
  CONSTRAINT `FKo1pucj55s424iapufn1qdthl5` FOREIGN KEY (`userId`) REFERENCES `WS_SCIM_USER` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 ws_scim_v1.WS_POLICY_ATTR 구조 내보내기
CREATE TABLE IF NOT EXISTS `WS_POLICY_ATTR` (
  `id` varchar(64) NOT NULL,
  `jobOp` varchar(255) DEFAULT NULL,
  `opr_job` varchar(255) DEFAULT NULL,
  `orgOp` varchar(255) DEFAULT NULL,
  `org_pos` varchar(255) DEFAULT NULL,
  `posOp` varchar(255) DEFAULT NULL,
  `job` varchar(64) DEFAULT NULL,
  `org` varchar(64) DEFAULT NULL,
  `pos` varchar(64) DEFAULT NULL,
  `policy` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKi8jdcecmp1s89lpvaqpfh038b` (`job`),
  KEY `FKftg347y6cd1khjgt3yrno50qy` (`org`),
  KEY `FKqjahpltyft8e3dumkt7380eve` (`pos`),
  KEY `FK8l9b7inlr3hc5uip1cd5fry14` (`policy`),
  CONSTRAINT `FK8l9b7inlr3hc5uip1cd5fry14` FOREIGN KEY (`policy`) REFERENCES `WS_SCIM_POLICY` (`id`),
  CONSTRAINT `FKftg347y6cd1khjgt3yrno50qy` FOREIGN KEY (`org`) REFERENCES `WS_SCIM_ORG` (`id`),
  CONSTRAINT `FKi8jdcecmp1s89lpvaqpfh038b` FOREIGN KEY (`job`) REFERENCES `WS_SCIM_JOB` (`id`),
  CONSTRAINT `FKqjahpltyft8e3dumkt7380eve` FOREIGN KEY (`pos`) REFERENCES `WS_SCIM_POS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 ws_scim_v1.WS_POLICY_GROUP 구조 내보내기
CREATE TABLE IF NOT EXISTS `WS_POLICY_GROUP` (
  `id` varchar(64) NOT NULL,
  `groupId` varchar(64) DEFAULT NULL,
  `policyId` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn59awor1duxv034adllk2dxmc` (`groupId`),
  KEY `FKlhss0rxsmuaq191r4i1lmu3gj` (`policyId`),
  CONSTRAINT `FKlhss0rxsmuaq191r4i1lmu3gj` FOREIGN KEY (`policyId`) REFERENCES `WS_SCIM_POLICY` (`id`),
  CONSTRAINT `FKn59awor1duxv034adllk2dxmc` FOREIGN KEY (`groupId`) REFERENCES `WS_SCIM_GROUP` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 ws_scim_v1.WS_POLICY_USER 구조 내보내기
CREATE TABLE IF NOT EXISTS `WS_POLICY_USER` (
  `id` varchar(64) NOT NULL,
  `policyId` varchar(64) DEFAULT NULL,
  `userId` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6ps54mwhlaqmvt2gldfl9klpe` (`policyId`),
  KEY `FKr90rygduvk0colm2ya24ldxjm` (`userId`),
  CONSTRAINT `FKr90rygduvk0colm2ya24ldxjm` FOREIGN KEY (`userId`) REFERENCES `WS_SCIM_USER` (`id`),
  CONSTRAINT `FK6ps54mwhlaqmvt2gldfl9klpe` FOREIGN KEY (`policyId`) REFERENCES `WS_SCIM_POLICY` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 ws_scim_v1.WS_RESOURCE_HISTORY 구조 내보내기
CREATE TABLE IF NOT EXISTS `WS_RESOURCE_HISTORY` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `changed` varchar(1024) DEFAULT NULL,
  `resId` varchar(64) NOT NULL,
  `resType` varchar(64) NOT NULL,
  `writeTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 ws_scim_v1.WS_SCIM_GROUP 구조 내보내기
CREATE TABLE IF NOT EXISTS `WS_SCIM_GROUP` (
  `id` varchar(64) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `createTime` datetime NOT NULL,
  `expireTime` datetime DEFAULT NULL,
  `modifyTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 ws_scim_v1.WS_SCIM_JOB 구조 내보내기
CREATE TABLE IF NOT EXISTS `WS_SCIM_JOB` (
  `id` varchar(64) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `createTime` datetime NOT NULL,
  `expireTime` datetime DEFAULT NULL,
  `modifyTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 ws_scim_v1.WS_SCIM_ORG 구조 내보내기
CREATE TABLE IF NOT EXISTS `WS_SCIM_ORG` (
  `id` varchar(64) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `createTime` datetime NOT NULL,
  `expireTime` datetime DEFAULT NULL,
  `modifyTime` datetime NOT NULL,
  `parent` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ORG_PARENT` (`parent`),
  CONSTRAINT `FK_ORG_PARENT` FOREIGN KEY (`parent`) REFERENCES `WS_SCIM_ORG` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 ws_scim_v1.WS_SCIM_PASSWORD 구조 내보내기
CREATE TABLE IF NOT EXISTS `WS_SCIM_PASSWORD` (
  `userId` varchar(64) NOT NULL,
  `password` varchar(128) NOT NULL,
  PRIMARY KEY (`userId`),
  CONSTRAINT `FKhm8ounv34n66u3gxagffp2y4x` FOREIGN KEY (`userId`) REFERENCES `WS_SCIM_USER` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 ws_scim_v1.WS_SCIM_POLICY 구조 내보내기
CREATE TABLE IF NOT EXISTS `WS_SCIM_POLICY` (
  `id` varchar(64) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 ws_scim_v1.WS_SCIM_POS 구조 내보내기
CREATE TABLE IF NOT EXISTS `WS_SCIM_POS` (
  `id` varchar(64) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `createTime` datetime NOT NULL,
  `expireTime` datetime DEFAULT NULL,
  `modifyTime` datetime NOT NULL,
  `rank` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 ws_scim_v1.WS_SCIM_SYSTEM 구조 내보내기
CREATE TABLE IF NOT EXISTS `WS_SCIM_SYSTEM` (
  `id` varchar(64) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 ws_scim_v1.WS_SCIM_USER 구조 내보내기
CREATE TABLE IF NOT EXISTS `WS_SCIM_USER` (
  `id` varchar(64) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `createTime` datetime NOT NULL,
  `expireTime` datetime DEFAULT NULL,
  `modifyTime` datetime NOT NULL,
  `job` varchar(64) DEFAULT NULL,
  `org` varchar(64) DEFAULT NULL,
  `pos` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtna2qmny9ti9ykaylmu2i1k13` (`job`),
  KEY `FK55le6c9f46ss1s7sby8pcglw` (`org`),
  KEY `FK8kvt6pjf142cv7753ro4gikb4` (`pos`),
  CONSTRAINT `FK8kvt6pjf142cv7753ro4gikb4` FOREIGN KEY (`pos`) REFERENCES `WS_SCIM_POS` (`id`),
  CONSTRAINT `FK55le6c9f46ss1s7sby8pcglw` FOREIGN KEY (`org`) REFERENCES `WS_SCIM_ORG` (`id`),
  CONSTRAINT `FKtna2qmny9ti9ykaylmu2i1k13` FOREIGN KEY (`job`) REFERENCES `WS_SCIM_JOB` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 ws_scim_v1.WS_SYSTEM_PASSWORD 구조 내보내기
CREATE TABLE IF NOT EXISTS `WS_SYSTEM_PASSWORD` (
  `systemId` varchar(255) NOT NULL,
  `userId` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `sycnTime` datetime DEFAULT NULL,
  PRIMARY KEY (`systemId`,`userId`),
  KEY `FKm841unjm26l6vlpsmg3mivaxs` (`userId`),
  CONSTRAINT `FKm841unjm26l6vlpsmg3mivaxs` FOREIGN KEY (`userId`) REFERENCES `WS_SCIM_USER` (`id`),
  CONSTRAINT `FK36xda2h1hgeafrcxyo4sdjxil` FOREIGN KEY (`systemId`) REFERENCES `WS_SCIM_SYSTEM` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 ws_scim_v1.WS_SYSTEM_POLICY 구조 내보내기
CREATE TABLE IF NOT EXISTS `WS_SYSTEM_POLICY` (
  `systemId` varchar(64) NOT NULL,
  `policyId` varchar(64) NOT NULL,
  PRIMARY KEY (`policyId`,`systemId`),
  KEY `FKhaa2j3chrdqvqxps66cl84tl1` (`systemId`),
  CONSTRAINT `FKhaa2j3chrdqvqxps66cl84tl1` FOREIGN KEY (`systemId`) REFERENCES `WS_SCIM_SYSTEM` (`id`),
  CONSTRAINT `FKrcamjjsxtsbj6bakjxn1xjms1` FOREIGN KEY (`policyId`) REFERENCES `WS_SCIM_POLICY` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 ws_scim_v1.WS_SYSTEM_USER 구조 내보내기
CREATE TABLE IF NOT EXISTS `WS_SYSTEM_USER` (
  `id` varchar(64) NOT NULL,
  `provisionedTime` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `systemId` varchar(64) DEFAULT NULL,
  `userId` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1s99hqkno6mykerfmuyoogkdg` (`systemId`),
  KEY `FKnuapegbwudwyk33ymkexdce81` (`userId`),
  CONSTRAINT `FKnuapegbwudwyk33ymkexdce81` FOREIGN KEY (`userId`) REFERENCES `WS_SCIM_USER` (`id`),
  CONSTRAINT `FK1s99hqkno6mykerfmuyoogkdg` FOREIGN KEY (`systemId`) REFERENCES `WS_SCIM_SYSTEM` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 ws_scim_v1.WS_WORK_JOB 구조 내보내기
CREATE TABLE IF NOT EXISTS `WS_WORK_JOB` (
  `id` varchar(64) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 ws_scim_v1.WS_WORK_SCHEDULER 구조 내보내기
CREATE TABLE IF NOT EXISTS `WS_WORK_SCHEDULER` (
  `id` varchar(64) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  `job` varchar(64) DEFAULT NULL,
  `system` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3od8pwvb327hd2bh10rq0mig1` (`job`),
  KEY `FKkg315n86v2pnvhtfuloed2swy` (`system`),
  CONSTRAINT `FKkg315n86v2pnvhtfuloed2swy` FOREIGN KEY (`system`) REFERENCES `WS_SCIM_SYSTEM` (`id`),
  CONSTRAINT `FK3od8pwvb327hd2bh10rq0mig1` FOREIGN KEY (`job`) REFERENCES `WS_WORK_JOB` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 ws_scim_v1.WS_WORK_TASK 구조 내보내기
CREATE TABLE IF NOT EXISTS `WS_WORK_TASK` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resId` varchar(255) DEFAULT NULL,
  `resType` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `workType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
