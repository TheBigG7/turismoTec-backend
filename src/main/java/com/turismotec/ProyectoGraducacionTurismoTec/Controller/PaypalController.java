package com.turismotec.ProyectoGraducacionTurismoTec.Controller;

import com.turismotec.ProyectoGraducacionTurismoTec.model.service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/paypal")
public class PaypalController {

    @Autowired
    private PaypalService paypalService;

    @PostMapping("/crearOrden/{amount}/{currency}")
    public ResponseEntity<String> createOrder(@PathVariable Double amount, @PathVariable String currency) {
        if (amount <= 0 || currency == null || currency.isEmpty()) {
            return ResponseEntity.badRequest().body("Monto o moneda inválidos");
        }
        String response = paypalService.createOrder(amount, currency);
        return ResponseEntity.ok(response);
    }

}
