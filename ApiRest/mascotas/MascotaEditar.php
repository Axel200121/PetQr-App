<?php
if ($_SERVER["REQUEST_METHOD"]=="POST"){
    include_once "../conexion.php";
    $idMascota = $_POST['idMascota'];
    $nombre=$_POST['nombre'];
    $tipoMascota=$_POST['tipoMascota'];
    $descripcion=$_POST['descripcion'];
    $idUsuario=$_POST['idUsuario'];
    $query = "UPDATE mascota SET nombre='".$nombre."',tipoMascota='".$tipoMascota."',descripcion='".$descripcion."' WHERE idMascota='".$idMascota."'";
    $resultado = $mysql->query($query);
    if ($mysql->affected_rows>0){
        if ($resultado==true){
            echo "Mascota Actualizada";
        }else{
            echo "Error al actualizar mascota";
        }
    }
    $mysql->close();
}
