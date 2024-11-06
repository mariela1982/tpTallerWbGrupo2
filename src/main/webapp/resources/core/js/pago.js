

const mp = new MercadoPago('YOUR_PUBLIC_KEY', {locale: "es-AR"});

document.getElementById("mercadoPago").addEventListener('click', async () => {
   try {
       const orderData = {
           title: document.getElementById("nombreTorneo").innerText,
           quanty: 1,
           price: document.getElementById("valorTorneo").value
       };
            //hago el fecht para el controlador
       const response = await fetch(mp.url + "/crear_preferencia", {
           method: "POST",
           headers: {
               "Content-Type": "application/json",
           },
           body: JSON.stringify({orderData}),
       });
       const preference = await response.json()
       //creo una funcion para el boton
       createCheckoutButton(preference.id);
   } catch (error){
       alert(error.message);
   }
})
const createCheckoutButton = (preferenceId) => {
    const bricksBuilder = mp.bricks();

    const renderComponent = async () => {
        if(window.checkoutButton) window.checkoutButton.unmount();
       await bricksBuilder.create("wallet", "wallet_container", {
            initialization: {
                preferenceId: preferenceId,
            },


        });
    };
};

