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
function showTab(tabId) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tab-pane");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].classList.remove("active");
    }
    tablinks = document.getElementsByClassName("nav-link");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].classList.remove("active");
    }
    document.getElementById(tabId).classList.add("active");
}
// Function to update hidden input with selected product IDs
function updateHiddenInput() {
    const checkboxes = document.querySelectorAll('.product-checkbox');
    const selectedProductIds = Array.from(checkboxes)
        .filter(checkbox => checkbox.checked)
        .map(checkbox => checkbox.value);
    document.getElementById('selectedProductIds').value = selectedProductIds.join('-');
}

// Function to mark checkboxes as checked based on hidden input value
function markSelectedCheckboxes() {
    const selectedProductIds = document.getElementById('selectedProductIds').value.split('-');
    const checkboxes = document.querySelectorAll('.product-checkbox');
    checkboxes.forEach(checkbox => {
        if (selectedProductIds.includes(checkbox.value)) {
            checkbox.checked = true;
        }
    });
}

// Function to update hidden input when checkboxes change
document.addEventListener('DOMContentLoaded', () => {
    markSelectedCheckboxes();

    const checkboxes = document.querySelectorAll('.product-checkbox');
    checkboxes.forEach(checkbox => {
        checkbox.addEventListener('change', updateHiddenInput);
    });
});

// Function to submit form with selected product IDs in the URL
function submitFormWithSelectedProducts() {
    const selectedProductIds = document.getElementById('selectedProductIds').value;
    const form = document.getElementById('shoppingCartForm');
    const action = form.getAttribute('action');
    form.setAttribute('action', action + '?productsIds=' + selectedProductIds);
    form.submit();
}