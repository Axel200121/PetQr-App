<?php
include_once "../conexion.php";
$correo=$_POST['correo'];
$psw=$_POST['psw'];
//$correo="angeloroncal@developeru.net";
//$psw="12345678";
$sentencia=$mysql->prepare("SELECT * FROM usuario WHERE correo=? AND psw=?");
$sentencia->bind_param('ss',$correo,$psw);
$sentencia->execute();

$resultado = $sentencia->get_result();
if ($fila = $resultado->fetch_assoc()) {
    echo json_encode($fila,JSON_UNESCAPED_UNICODE);
}
$sentencia->close();
$mysql->close();
?>