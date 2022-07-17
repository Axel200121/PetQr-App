<?php
if ($_SERVER["REQUEST_METHOD"]=="POST"){
    require_once '../conexion.php';
    $nombre=$_POST["nombre"];
    $tipoMascota=$_POST["tipoMascota"];
    $descripcion=$_POST["descripcion"];
    $idUsuario=$_POST["idUsuario"];

    $query="INSERT INTO mascota 
            (nombre, tipoMascota, descripcion, idUsuario) 
            VALUES('".$nombre."','".$tipoMascota."','".$descripcion."','".$idUsuario."')";
    $resultado = $mysql->query($query);

    if ($resultado==true){
        echo "Mascota registrada";
    }else{
        echo "Las mascota no se pudo registrar";
    }
}
