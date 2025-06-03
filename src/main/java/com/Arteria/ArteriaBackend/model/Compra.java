package com.Arteria.ArteriaBackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Aqui se establece como un AUTOINCREMENT
    @Column(name = "id_compra")
    private Integer id_compra;
    @Column(name = "fecha_compra", nullable = false)
    private LocalDateTime fecha_compra = LocalDateTime.now();
    @Column(name = "valor_total_compra", nullable = false, precision = 10, scale = 2)
    private BigDecimal valor_total_compra;
    @Column(name = "FK_id_usuario", nullable = false)
    private Integer usuarioId;
}
