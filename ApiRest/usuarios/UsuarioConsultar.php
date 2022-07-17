<?php
if ($_SERVER["REQUEST_METHOD"]==["GET"]){
    require_once "../conexion.php";
    $idUsuario = $_GET["idUsuario"];
    //hacemos la consulta para traer el registro
    $query = "SELECT * FROM usuario WHERE idUsuario='".$idUsuario."'";
    $resultado = $mysql->query($query);
    if ($mysql->affected_rows > 0){
        //va jalando cada uno de los registro
        while ($row=$resultado->fetch_assoc()){
            $array = $row;
        }
        //nos devuelve la informacion en JSON
        echo json_encode($array);
    }else{
        echo "Usuario no encontrado";
    }
    $resultado->close();
    $mysql->close();
}