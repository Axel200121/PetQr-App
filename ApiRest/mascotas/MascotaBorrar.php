<?php
if ($_SERVER["REQUEST_METHOD"]=="POST"){
    include_once "../conexion.php";
    $idMascota = $_POST['idMascota'];

    $query ="DELETE FROM mascota WHERE idMascota='".$idMascota."'";
    $resultado = $mysql->query($query);
    if ($mysql->affected_rows>0){
        if ($resultado==true){
            echo "Mascota eliminada";
        }else{
            echo "Erroo al eliminar la mascota";
        }
    }
    $mysql->close();
}
