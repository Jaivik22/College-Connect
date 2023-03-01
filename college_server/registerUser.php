<?php
require "DataBase.php";
$db = new DataBase();
$response = null;
if (isset($_POST['Fullname']) && isset($_POST['EmailID']) && isset($_POST['Username']) && isset($_POST['Password'])) {
    if ($db->dbConnect()) {
        if ($db->signUp("userdata", $_POST['Fullname'], $_POST['EmailID'], $_POST['Username'], $_POST['Password'])) {
           echo "Sign Up Success";
        } else{
         echo "Sign up Failed";
        }
    } else echo  "Error: Database connection";
} else echo  "All fields are required";

?>
