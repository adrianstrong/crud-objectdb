package models;

import lombok.*;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Producto implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    @Column
    private String nombre;
    @Column
    private String tipo;
    @Column
    private Float precio;
    @Column
    private Integer disponibilidad;

    @OneToMany(mappedBy = "producto", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Pedido> pedidos;

    @Override
    public String toString() {
        return nombre;
    }
}
