<?php
if($_SERVER["REQUEST_METHOD"]=="POST"){
    require_once 'conexion.php';
    $nombre=$_POST["nombre"];
    $apellidos=$_POST["apellidos"];
    $telefono=$_POST["telefono"];
    $direccion=$_POST["direccion"];
    $correo=$_POST["correo"];
    $password=$_POST["password"];

    $query="INSERT INTO usuarios (nombre,apellidos,telefono,direccion,correo,password) 

    VALUES('".$nombre."','".$apellidos."','".$telefono."','".$direccion."','".$correo."','".$password."')";
    $resultado=$mysql->query($query);

    if($resultado==true){
        echo "El usuario se inserto de forma exitosa";
    }else{
        echo "Error al insertar el usuario";
    }
}