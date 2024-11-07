package com.tallerwebi.presentacion;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import com.tallerwebi.dominio.entidades.UserBuyer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ControladorApi {

   // @Value("${codigo.mercadolibre}")
    private String mercadolibreToken = "TEST-7140903790380993-110617-8652aa7ce1f131e0392c0b988b38e13b-85944015";

    @RequestMapping(value = "api/mp/crear_preferencia", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> getList(@RequestBody UserBuyer userBayer){
//        if(userBayer == null){
//            return "error Json";}
        String title = userBayer.getTitle();
        int quantity = userBayer.getQuantity();
        int price = userBayer.getPrice();

        try {
            MercadoPagoConfig.setAccessToken(mercadolibreToken);
//creo la preferencia que es la venta del torneo
            PreferenceItemRequest itemRequest = PreferenceItemRequest
                    .builder()
                    .title(title)
                    .quantity(quantity)
                    .unitPrice(new BigDecimal(price))
                    .currencyId("ARS")
                    .build();
            List<PreferenceItemRequest> items = new ArrayList<>();
            items.add(itemRequest);

            // 2 - preferencia a donde volvemos

            PreferenceBackUrlsRequest backUrlsRequest = PreferenceBackUrlsRequest
                    .builder() //comienza con builder y termina con build
                    .success("http://localhost:8080/spring/inscripcion")
                    .pending("http://localhost:8080/spring/home")
                    .failure("http://localhost:8080/spring/home")
                    .build();

            // unimos las preferencias que creamos
            PreferenceRequest preferenceRequest = PreferenceRequest
                    .builder()
                    .items(items)
                    .backUrls(backUrlsRequest)
                    .build();

            //creo un objeto de tipo cliente para comunicarme con mp
            PreferenceClient cliente = new PreferenceClient();
            //creo una nueva preferencia que sera igual a la respuesta de nuestro cliente a partir de la informacion que le enviamos
            Preference preference = cliente.create(preferenceRequest);

            // Devolver la ID de la preferencia en formato JSON
            Map<String, String> response = new HashMap<>();
            response.put("id", preference.getId());

            return ResponseEntity.ok(response); // Devuelve un JSON con la clave "id"
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Error al crear la preferencia"));
        }


    }

}
