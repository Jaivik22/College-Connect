<?php
require "Database.php";
$db = new DataBase();
if($db->dbConnect()){
    $notice = $_REQUEST['notice'];
    $title = $_REQUEST['title'];
    $stmt = mysqli_prepare($db->dbConnect(), "INSERT INTO `circularnotice` (`title`, `notice`) VALUES (?, ?)");
    mysqli_stmt_bind_param($stmt, "ss", $title, $notice);
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

