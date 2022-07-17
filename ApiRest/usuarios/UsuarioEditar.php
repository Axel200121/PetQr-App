<?php
if ($_SERVER["REQUEST_METHOD"]=="POST"){
    include_once "../conexion.php";
    $idUsuario = $_POST['idUsuario'];
    $nombre = $_POST['nombre'];
    $apellidoPaterno = $_POST['apellidoPaterno'];
    $apellidoMaterno = $_POST['apellidoMaterno'];
    $telefono = $_POST['telefono'];
    $direccion = $_POST['direccion'];
    $correo = $_POST['correo'];
    $psw = $_POST['psw'];
    $query = "UPDATE usuario SET nombre='".$nombre."',apellidoPaterno='".$apellidoPaterno."',apellidoMaterno='".$apellidoMaterno."',telefono='".$telefono."',direccion='".$direccion."',correo='".$correo."',psw='".$psw."' WHERE idUsuario='".$idUsuario."'";
    $resultado=$mysql->query($query);
    if ($mysql->affected_rows>0){
        if ($resultado==true){
            echo "Usuario actualizado";
        }
    }else{
        echo "Error al actualizar usuario";
    }
    $mysql->close();

}