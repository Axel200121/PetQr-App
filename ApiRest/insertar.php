<?php
if($_SERVER["REQUEST_METHOD"]=="POST"){
    require_once 'conexion.php';
    $nombre=$_POST["nombre"];
    $apellidoPaterno=$_POST["apellidoPaterno"];
    $apellidoMaterno=$_POST["apellidoMaterno"];
    $telefono=$_POST["telefono"];
    $direccion=$_POST["direccion"];
    $correo=$_POST["correo"];
    $psw=$_POST["psw"];

    $query="INSERT INTO usuario 
            (nombre, apellidoPaterno, apellidoMaterno, telefono, direccion, correo, psw)
            VALUES('".$nombre."','".$apellidoPaterno."','".$apellidoMaterno."','".$telefono."','".$direccion."','".$correo."','".$psw."');";
    $resultado=$mysql->query($query);

    if($resultado==true){
        echo "El usuario se inserto de forma exitosa";
    }else{
        echo "Error al insertar el usuario";
    }
}