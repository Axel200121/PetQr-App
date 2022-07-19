<?php
if ($_SERVER["REQUEST_METHOD"]=="POST"){
    require_once "../conexion.php";
    $idUsuario = $_GET['idUsuario'];
    $query = "SELECT mascota.nombre,tipoMascota,descripcion 
                FROM mascota INNER JOIN usuario ON mascota.idUsuario=usuario.idUsuario 
                WHERE usuario.idUsuario ='".$idUsuario."'";
    $resultado = $mysql->query($query);
    if ($mysql->affected_rows > 0){
        //va jalando cada uno de los registro
        while ($row=$resultado->fetch_assoc()){
            $array = $row;
        }
        //nos devuelve la informacion en JSON
        echo json_encode($array);
    }else{
        echo "Mascotas no encontrado";
    }
}