package com.Arteria.ArteriaBackend.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "compra")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Aqui se establece como un AUTOINCREMENT
    @Column(name = "id_compra")
    private Integer idCompra;
    @Column(name = "fecha_compra", nullable = false)
    private LocalDateTime fecha_compra = LocalDateTime.now();
    @Column(name = "valor_total_compra", nullable = false, precision = 10, scale = 2)
    private BigDecimal valor_total_compra;

    /* ──────────── Relaciones ──────────── */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_id_usuario", nullable = false)
    //@JsonBackReference
    private Usuario usuario;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<DetalleCompra> detalles = new ArrayList<>();


}
