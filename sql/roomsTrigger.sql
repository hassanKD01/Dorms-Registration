CREATE DEFINER=`root`@`localhost` TRIGGER `rooms_AFTER_UPDATE` AFTER UPDATE ON `rooms` FOR EACH ROW BEGIN
	declare fullRooms int default 0;
    select count(*) into fullRooms from `rooms` 
    where (`numberOfStudents` = 2) and (`floorNb` = old.`floorNb`) and (`blockName` = old.`blockName`);
    if fullRooms = 36 then
		update `floors` set `isFull` = 'yes' where (`floorNb` = old.`floorNb`) and (`blockName` = old.`blockName`);
	else
		update `floors` set `isFull` = 'no' where (`floorNb` = old.`floorNb`) and (`blockName` = old.`blockName`);
	end if;
END