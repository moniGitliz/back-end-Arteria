package com.Arteria.ArteriaBackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class DetalleCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_compra")
    private Integer id_detalle_compra;
    @Column(name = "FK_id_compra", nullable = false)
    private Integer compraId;
    @Column(name = "FK_id_obra", nullable = false)
    private Integer obraId;
    @Column(name = "precio_unitario_obra", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio_unitario_obra;
}
