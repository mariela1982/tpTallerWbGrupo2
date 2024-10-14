document.addEventListener("DOMContentLoaded", function() {
    const contrasenaInput = document.getElementById("password");
    const form = document.getElementById("editUserForm");
    let originalPassword = contrasenaInput.value;//guardo la contraseña orginal


    contrasenaInput.addEventListener("focus", function() {
        contrasenaInput.type = "text";
        contrasenaInput.value = contrasenaInput.getAttribute("data-password")|| "";
    });

    contrasenaInput.addEventListener("blur", function() {
        contrasenaInput.type = "password";
        const contrasenaActual = contrasenaInput.value;

        if(contrasenaActual){
            contrasenaInput.setAttribute("data-password",contrasenaActual);
            contrasenaInput.value = "******";
        }
    });

    if(contrasenaInput.value){
        contrasenaInput.setAttribute("data-password",contrasenaInput.value);
        contrasenaInput.value ="******";
        contrasenaInput.type = "password";
    }

    //antes de enviar el formulario por si modifico o no
    form.addEventListener("submit",function (event){
        const currentPassword = contrasenaInput.getAttribute("data-password");

        if (contrasenaInput.value === "******"){
            contrasenaInput.value = originalPassword; // el usuario no cambio nada
        }else {
            contrasenaInput.value = currentPassword;
        }
    });
});