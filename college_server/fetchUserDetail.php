<?php
require "Database.php";
$db = new DataBase();
$username = $_REQUEST['username'];

$sql = "SELECT * FROM `userdata` WHERE username = '$username' ";
$query = mysqli_query($db->dbConnect(),$sql);
while($res= mysqli_fetch_assoc($query)){
    $data['userDetails'][]  = $res;
}
echo json_encode($data);
?>