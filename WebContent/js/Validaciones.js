function prueba() {
    //Se verifica si alguno de los checks esta seleccionado
    if ($('input[name="viajediario"]').is(':checked')) {
        alert('Campo correcto');
         }
    else {
        alert('Se debe seleccionar por lo menos un color');
    }
    return false;
}