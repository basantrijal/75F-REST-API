
CREATE DATABASE IF NOT EXISTS `75F`
USE `75F`;

CREATE TABLE IF NOT EXISTS `weather_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `device_id` varchar(255) NOT NULL,
  `percentage` int(11) NOT NULL,
  `temperature` decimal(3,1) NOT NULL,
  `timestamp` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

INSERT INTO `weather_data` (`id`, `device_id`, `percentage`, `temperature`, `timestamp`) VALUES
	(1, '1234ABCS', 37, 75.1, '2017-07-12 08:13:00'),
	(2, '1234ABCS', 39, 75.1, '2017-07-12 08:14:00'),
	(3, '1234ABCS', 40, 75.5, '2017-07-12 08:15:00'),
	(4, '1234ABCS', 45, 75.3, '2017-07-12 08:16:00'),
	(5, '1234ABCS', 41, 75.2, '2017-07-12 08:17:00'),
	(6, '1234ABCS', 38, 74.8, '2017-07-12 08:18:00'),
	(7, '1234ABCS', 39, 74.3, '2017-07-12 08:19:00'),
	(8, '1234ABCS', 41, 74.6, '2017-07-12 08:20:00'),
	(9, '1234ABCS', 35, 74.9, '2017-07-12 08:21:00'),
	(10, '1234ABCS', 30, 75.0, '2017-07-12 08:22:00');
