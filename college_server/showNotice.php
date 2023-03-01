<?php
require "Database.php";
$db = new DataBase();

$sql = "SELECT * FROM `circularnotice` ORDER BY id DESC ";
$query = mysqli_query($db->dbConnect(),$sql);
while($res= mysqli_fetch_assoc($query)){
    $data['circularnotice'][]  = $res;
}
echo json_encode($data);

?>