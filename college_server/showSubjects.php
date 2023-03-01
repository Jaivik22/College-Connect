<?php
require "Database.php";
$db = new DataBase();
$semester = $_REQUEST['semester'];
$branch = $_REQUEST['branch'];

$sql = "SELECT * FROM `subjects` WHERE  branch = '$branch' and semester = '$semester'";
$query = mysqli_query($db->dbConnect(),$sql);
while($res= mysqli_fetch_assoc($query)){
    $data['subjects'][]  = $res;
}
echo json_encode($data);
?>