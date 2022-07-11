<?php
// Comprobar si el correo electrónico y la contraseña están configurados
if(isset($_POST['correo']) && isset($_POST['password'])){
    // Incluir los archivos necesarios
    require_once "conexion.phpp";
    require_once "validacion.php";
    // Llame a validar, pase los datos del formulario como parámetro y almacene el valor devuelto
    $correo = validate($_POST['correo']);
    $password = validate($_POST['password']);
    // Crear la cadena de consulta SQL
    $sql = "select * from usuarios where correo='$correo' and password='" . md5($password) . "'";
    // Ejecutar la consulta
    $result = $mysql->query($sql);
    // Si el número de filas devueltas es mayor que 0 (es decir, si se encuentra el registro), imprimiremos "éxito", de lo contrario, "fracaso"
    if($result->num_rows > 0){
        echo "exito";
    } else{
        // Si no se encuentra ningún registro, imprima "falla"
        echo "fracaso";
    }
}
?>