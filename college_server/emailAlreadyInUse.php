<?php
require "Database.php";

$db = new DataBase();

$EmailID = $_POST['EmailID'];
$sql = "SELECT * FROM userdata WHERE EmailID = '$EmailID'";

$query = mysqli_query($db->dbConnect(),$sql);
if(mysqli_num_rows($query)==1){
    echo "email already exists";
}
else{
    echo "does not exists"; 
}
?>