<?php
$hostname='localhost';
$database='pet_qr';
$username='root';
$password='';
$mysql = new mysqli($hostname,$username,$password,$database);
if($mysql->connect_error){
    ///echo "El sitio web está experimentado problemas";
    die("Error de conexion");
}