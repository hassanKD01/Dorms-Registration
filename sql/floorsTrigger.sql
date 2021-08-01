CREATE DEFINER=`root`@`localhost` TRIGGER `floors_AFTER_UPDATE` AFTER UPDATE ON `floors` FOR EACH ROW BEGIN
	declare fullfloors int default 0;
    select count(*) into fullfloors 
    from `floors` 
    where (`isFull` = 'yes') and (`blockName` = new.`blockName`);
    if fullfloors = 4 then
		update `blocks` set `isFull` = 'yes' where `blockName` = new.`blockName`;
	else
		update `blocks` set `isFull` = 'no' where `blockName` = new.`blockName`;
	end if;
END