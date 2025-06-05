package com.Arteria.ArteriaBackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "detalle_compra")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
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
    @JsonBackReference
    private Compra compra;


    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "FK_id_obra", unique = true)
    private Obra obra;

}
