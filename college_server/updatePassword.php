<?php
require "Database.php";
$db = new DataBase();

$EmailID = $_POST['EmailID'];
$Password = $_POST['Password'];
$Password = password_hash($Password, PASSWORD_DEFAULT);

$sql = "UPDATE userdata SET Password = '$Password' WHERE EmailID = '$EmailID'";
if($query = mysqli_query($db->dbConnect(),$sql))
{
    echo "Password updated";
}
else{
    echo "error in updating password"; 
}





?>