<?php
if ($_SERVER["REQUEST_METHOD"]=="GET"){

    require_once "../conexion.php";

    $idMascota = $_GET['idMascota'];

    $query = "SELECT mascota.nombre AS nombreMascota,tipoMascota,descripcion,
    concat_ws('',usuario.nombre,' ',usuario.apellidoPaterno,'',usuario.apellidoMaterno) AS NombreCompleto ,
    usuario.direccion,usuario.telefono
     FROM mascota INNER JOIN usuario ON mascota.idUsuario=usuario.idUsuario WHERE idMascota='".$idMascota."'";
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