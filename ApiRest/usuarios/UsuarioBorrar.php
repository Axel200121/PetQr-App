<?php
if ($_SERVER["REQUEST_METHOD"]=="POST"){
    require_once "../conexion.php";
    $idUsuario = $_POST['idUsuario'];
    $query ="DELETE FROM usuario WHERE idUsuario='".$idUsuario."'";
    $resultado =$mysql->query($query);
    if ($mysql->affected_rows>0){
        if ($resultado==true){
            echo "Usuario eliminado";
        }
    }else{
        echo "Error al eliminar el usuario";
    }
    $mysql->close();
}
