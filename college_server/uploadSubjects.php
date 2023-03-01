
<?php
require "Database.php";
$db = new DataBase();
$semester = $_REQUEST['semester'];
$branch = $_REQUEST['branch'];
$subject = $_REQUEST['subject'];

$sql = "INSERT INTO `subjects`(`Branch`,`Semester`,`Subject`) VALUES('$branch','$semester','$subject')";
if($query = mysqli_query($db->dbConnect(),$sql)){
    echo "Upload Successful";
}
else{
    echo "Failed to upload";
}

?>
