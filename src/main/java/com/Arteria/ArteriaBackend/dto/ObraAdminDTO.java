package com.Arteria.ArteriaBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ObraAdminDTO {

    private String nombre_obra;
    private String descripcion_obra;
    private BigDecimal precio_obra;
    private String nombre_artista;
    private String categoria;


    private String imagen_principal_url;
    private String miniatura_1_url;
    private String miniatura_2_url;
    private String miniatura_3_url;


}
