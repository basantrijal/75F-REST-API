CREATE DATABASE IF NOT EXISTS `75F`
USE `75F`;

CREATE TABLE IF NOT EXISTS `weather_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `device_id` varchar(255) NOT NULL,
  `temperature` decimal(3,1) NOT NULL,
  `timestamp` datetime NOT NULL,
  `humidity` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;


INSERT INTO `weather_data` (`id`, `device_id`, `temperature`, `timestamp`, `humidity`) VALUES
	(1, '1234ABCS', 75.1, '2017-07-12 08:13:00', 37),
	(2, '1234ABCS', 75.1, '2017-07-12 08:14:00', 39),
	(3, '1234ABCS', 75.5, '2017-07-12 08:15:00', 40),
	(4, '1234ABCS', 75.3, '2017-07-12 08:16:00', 45),
	(5, '1234ABCS', 75.2, '2017-07-12 08:17:00', 41),
	(6, '1234ABCS', 74.8, '2017-07-12 08:18:00', 38),
	(7, '1234ABCS', 74.3, '2017-07-12 08:19:00', 39),
	(8, '1234ABCS', 74.6, '2017-07-12 08:20:00', 41),
	(9, '1234ABCS', 74.9, '2017-07-12 08:21:00', 35),
	(10, '1234ABCS', 75.0, '2017-07-12 08:22:00', 30);