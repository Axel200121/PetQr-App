<?php

if ($_SERVER['REQUEST_METHOD']=='POST') {

    $correo = $_POST['correo'];
    $password = $_POST['password'];

    require_once 'conexion.php';

    $sql = "SELECT * FROM usuarios WHERE correo='$correo' ";

    $response = mysqli_query($mysql, $sql);

    $result = array();
    $result['login'] = array();

    if ( mysqli_num_rows($response) === 1 ) {

        $row = mysqli_fetch_assoc($response);

        if ( password_verify($password, $row['password']) ) {

            $index['nombre'] = $row['nombre'];
            $index['apellidos'] = $row['apellidos'];
            $index['telefono'] = $row['telefono'];
            $index['direccion'] = $row['direccion'];
            $index['correo'] = $row['correo'];
            $index['id_usuario'] = $row['id_usuario'];

            array_push($result['login'], $index);

            $result['success'] = "1";
            $result['message'] = "success";
            echo json_encode($result);

            mysqli_close($mysql);

        } else {

            $result['success'] = "0";
            $result['message'] = "error";
            echo json_encode($result);

            mysqli_close($mysql);

        }

    }

}

?>