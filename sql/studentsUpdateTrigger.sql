CREATE DEFINER=`root`@`localhost` TRIGGER `students_AFTER_UPDATE` AFTER UPDATE ON `students` FOR EACH ROW BEGIN
update `dorms-registration`.`rooms` 
    set numberOfStudents = numberOfStudents +1 
    where roomNb = New.room and floorNb = new.floorNb and blockName = new.blockName;
update `dorms-registration`.`rooms` 
	set numberOfStudents = numberOfStudents -1 
    where roomNb = old.room and floorNb = old.floorNb and blockName = old.blockName;
END