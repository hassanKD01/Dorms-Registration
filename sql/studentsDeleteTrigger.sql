CREATE DEFINER=`root`@`localhost` TRIGGER `students_AFTER_DELETE` AFTER DELETE ON `students` FOR EACH ROW BEGIN
update `dorms-registration`.`rooms` 
    set numberOfStudents = numberOfStudents -1 
    where roomNb = old.room and floorNb = old.floorNb and blockName = old.blockName;
END