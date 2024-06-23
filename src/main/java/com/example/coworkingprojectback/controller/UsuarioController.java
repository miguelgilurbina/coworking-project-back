//package com.example.coworkingprojectback.controller;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/usuario")
//public class UsuarioController {
//
//    @GetMapping("/info")
//    public Map<String, Object> obtenerInfoUsuario(Authentication authentication) {
//        Map<String, Object> userInfo = new HashMap<>();
//
//        // Obtiene el nombre de usuario y los roles del usuario autenticado
//        String username = authentication.getName();
//        userInfo.put("username", username);
//
//        // Obtiene los roles del usuario
//        userInfo.put("roles", getRoles(authentication));
//
//        return userInfo;
//    }
//
//    // Método auxiliar para obtener los roles del usuario
//    private String getRoles(Authentication authentication) {
//        StringBuilder roles = new StringBuilder();
//        boolean first = true;
//
//        for (GrantedAuthority authority : authentication.getAuthorities()) {
//            if (!first) {
//                roles.append(", ");
//            } else {
//                first = false;
//            }
//            roles.append(authority.getAuthority());
//        }
//
//        return roles.toString();
//    }
//}
