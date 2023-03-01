<?php
require "Database.php";
$db = new DataBase();
$semester = $_REQUEST['semester'];
$branch = $_REQUEST['branch'];
$subject = $_REQUEST['subject'];

$sql = "SELECT * FROM `materialfiles` WHERE subjectName = '$subject' and branch = '$branch' and semester = '$semester' ORDER BY id DESC";
$query = mysqli_query($db->dbConnect(),$sql);
while($res= mysqli_fetch_assoc($query)){
    $data['materialfiles'][]  = $res;
}
echo json_encode($data);
?>