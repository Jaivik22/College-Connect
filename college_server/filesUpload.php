<?php
require "Database.php";
$db = new DataBase();
$branch = $_REQUEST['branch'];
$semester = $_REQUEST['semester'];
$subject = $_REQUEST['subject'];
$fileName = $_FILES['fileName']["name"];
$_uploadPath = "materialUpload/".$fileName;
// $path = "http://172.20.10.4/college_connect/materialUpload/".$fileName;
$path = "college_connect/materialUpload/".$fileName;


if(move_uploaded_file($_FILES["fileName"]["tmp_name"],$_uploadPath)){

    $sql = "INSERT INTO `materialfiles` (`Name`  ,  `NotesPath` , `subjectName`,`branch`,`semester`) VALUES ('$fileName','$path','$subject','$branch','$semester')";
    if($query = mysqli_query($db->dbConnect(),$sql)){
        echo "upload suceesful...";
    }
    else{
        echo "upload Failed";
    }
}else{
    echo "upload Failed";
}
    







?>