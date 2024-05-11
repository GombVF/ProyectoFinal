// funciones.js
function mostrarOcultarCampo() {
    console.log("entró")
    let select = document.getElementById("tipoPersona");
    console.log(select.value);
    if (select.value === "Física") {
        console.log("entró1")
        document.getElementById("personaFisica").style.display = "block";  // Oculta el campo
        document.getElementById("personaMoral").style.display = "none";  // Oculta el campo
    } else if (select.value === "Moral") {
        console.log("entró2")
        document.getElementById("personaFisica").style.display = "none";  // Oculta el campo
        document.getElementById("personaMoral").style.display = "block"; // Muestra el campo
    }
}
