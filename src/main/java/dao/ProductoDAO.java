package dao;

import csur.adrian.crudobjectdb.ObjectDBUtil;
import models.Producto;

import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class ProductoDAO {

    public void add(Producto pro) {
        if(pro == null) return;
        try{
            var em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            em.persist(pro);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            System.out.println("Error al añadir producto: " + e.getMessage());
        }

    }

    public ArrayList<Producto> getAll(){
        ArrayList<Producto> salida;
        var em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<Producto> q = em.createQuery("SELECT pro from Producto pro",Producto.class);
        salida = (ArrayList<Producto>) q.getResultList();
        em.close();
        return salida;
    }

    public Producto get(Integer id){
        var em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<Producto> q = em.createQuery("SELECT pro from Producto pro WHERE id = :id",Producto.class)
                .setParameter("id", id);
        Producto salida =  q.getSingleResult();
        em.close();
        return salida;
    }

}
