<?php
require "Database.php";

$db = new DataBase();

$Username = $_POST['Username'];
$sql = "SELECT * FROM userdata WHERE Username = '$Username'";

$query = mysqli_query($db->dbConnect(),$sql);
if(mysqli_num_rows($query)==1){
    echo "email already exists";
}
else{
    echo "does not exists"; 
}
?>