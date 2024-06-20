//package com.example.coworkingprojectback;
//
//import com.example.coworkingprojectback.entity.Sala;
//import com.example.coworkingprojectback.service.impl.SalaService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@SpringBootTest
//class CoworkingProjectBackApplicationTests {
//    @Autowired
//    private SalaService salaService;
//
//
//
//    @Test
//    void contextLoads() {
//    }
//    @Test
//    void testRegistrarSala() {
//        // Crea un objeto Sala para la prueba
//       Sala sala = new Sala("Sala 1", "Descripción de la Sala", 10, 100.0);
//
//        // Registra la sala utilizando el servicio de SalaService
//        Sala salaRegistrada = salaService.registrarSala(sala);
//
//        // Verifica si la sala se ha registrado correctamente
//        assertNotNull(salaRegistrada);
//        assertNotNull(salaRegistrada.getId());
//        assertEquals("Sala 1", salaRegistrada.getNombre());
//        assertEquals("Descripción de la Sala", salaRegistrada.getDescripcion());
//        assertEquals(10, salaRegistrada.getCapacidad());
//        assertEquals(100.0, salaRegistrada.getPrecio());
//    }
//}