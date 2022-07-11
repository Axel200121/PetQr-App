<?php
function validate($data) {
    // Elimine los caracteres innecesarios, por ejemplo, espacio adicional, tabulador, nueva línea de la entrada del usuario
    $data = trim($data);
    // Eliminar barras invertidas
    $data = stripslashes($data);
    // Convierta caracteres especiales en entidades HTML, evitando así que los atacantes exploten el código
    $data = htmlspecialchars($data);
    // Devolver datos validados
    return $data;
}
?>
