package com.bodegas.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bodegas")
@Getter
@NoArgsConstructor
public class Bodega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false, length = 120)
    private String nombre;

    @Setter
    @Column(nullable = false, length = 200)
    private String ubicacion;

    @OneToMany(mappedBy = "bodega", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @OrderBy("id ASC")
    private Set<Producto> productos = new HashSet<>();

    public Bodega(String nombre, String ubicacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
        producto.asignarBodega(this);
    }
}
