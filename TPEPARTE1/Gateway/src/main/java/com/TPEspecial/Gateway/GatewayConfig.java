package com.TPEspecial.Gateway;

import com.TPEspecial.Gateway.security.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {

        return builder.routes()

                // ============================
                //   RUTA PARA MS-MONOPATIN
                // ============================

                .route("msMonopatin",r -> r
                        .path(
                                "/api/monopatin/**",
                                "/api/parada/**"
                        )
                        .filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthFilter.Config())))
                        .uri("http://localhost:9001")
                )

                // ============================
                //   RUTA PARA MS-VIAJE
                // ============================

                .route("msViaje",r -> r
                        .path(
                                "/api/pausa/**",
                                "/api/viaje/**"
                        )
                        .filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthFilter.Config())))
                        .uri("http://localhost:9002")
                )

                // ============================
                //   RUTA PARA MS-USUARIO
                // ============================
                .route("msUsuario", r -> r
                        .path(
                                "/api/usuario/**",
                                "/api/cuenta/**"
                        )
                        .filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthFilter.Config())))
                        .uri("http://localhost:9003")
                )

                // ============================
                //   RUTA PARA MS-FACTURA
                // ============================

                .route("msFactura",r -> r
                        .path(
                                "/api/factura/**",
                                "/api/detallefactura/**",
                                "/api/tarifa/**"
                        )
                        .filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthFilter.Config())))
                        .uri("http://localhost:9004")
                )


                // ============================
                //   RUTA PARA MS-ADMINISTRADOR
                // ============================

                .route("msAdministrador",r -> r
                        .path(
                                "/api/admin/**",
                                "/api/mantenimiento/**"
                        )
                        .filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthFilter.Config())))
                        .uri("http://localhost:9005")
                )


                .build();
    }
}
