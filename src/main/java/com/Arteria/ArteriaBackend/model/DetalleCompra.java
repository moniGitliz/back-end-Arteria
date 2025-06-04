package com.Arteria.ArteriaBackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "detalle_compra")
public class DetalleCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_compra")
    private Integer id_detalle_compra;
    @Column(name = "precio_unitario_obra", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio_unitario_obra;

    /* ---------- Relaciones ---------- */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_id_compra")
    private Compra compra;


    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "FK_id_obra", unique = true)
    private Obra obra;

}
