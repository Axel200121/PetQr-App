<?php
$mysql=new mysqli("localhost","root","","pet_qr");
if($mysql->connect_error){
    die("Error de conexion");
}
else{
    echo "Conexion exitosa";
}