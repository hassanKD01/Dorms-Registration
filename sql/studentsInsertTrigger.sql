CREATE DEFINER=`root`@`localhost` TRIGGER `students_AFTER_INSERT` AFTER INSERT ON `students` FOR EACH ROW BEGIN
	update `dorms-registration`.`rooms` 
    set numberOfStudents = numberOfStudents +1 
    where roomNb = New.room and floorNb = new.floorNb and blockName = new.blockName;
END