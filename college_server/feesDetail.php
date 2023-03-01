<?php
require "Database.php";
$db = new DataBase();
if($db->dbConnect()){
    $username = $_REQUEST['username'];
    $ennumber = $_REQUEST['ennumber'];
    $stmt = mysqli_prepare($db->dbConnect(), "INSERT INTO `feesdetail` (`username`, `e.n.number`) VALUES (?, ?)");
    mysqli_stmt_bind_param($stmt, "ss", $username, $ennumber);
    if(mysqli_stmt_execute($stmt)){
        echo "Upload Successful";
    }
    else{
        echo "Failed to upload";
    }
}else{
    echo "database connection failed..";
}
?>

