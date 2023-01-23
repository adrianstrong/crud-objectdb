package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue
    private Integer id;
    private String fecha;
    private String cliente;
    private String estado;
    @ManyToOne
    @JoinColumn(name="producto")
    private Producto producto;

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", fecha='" + fecha + '\'' +
                ", cliente='" + cliente + '\'' +
                ", estado='" + estado + '\'' +
                ", producto=" + producto +
                '}';
    }
}

