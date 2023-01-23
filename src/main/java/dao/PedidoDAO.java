package dao;

import csur.adrian.crudobjectdb.ObjectDBUtil;
import java.util.ArrayList;
import javax.persistence.TypedQuery;

import csur.adrian.crudobjectdb.Utils;
import models.Pedido;

public class PedidoDAO {
    final String HOY = Utils.getToday();

    public void add(Pedido p){
        if(p == null) return;
        try{
            var em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e){
            System.out.println("Error al añadir pedido: " + e.getMessage());
        }

    }

    public void update(Pedido u) {
        if (u == null) return;
        try {
            var em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            TypedQuery<Pedido> query = em.createQuery(
                    "UPDATE Pedido SET estado = :estado, fecha = :fecha WHERE id = :id", Pedido.class);
            query.setParameter("id", u.getId());
            query.setParameter("fecha", u.getFecha());
            query.setParameter("estado", u.getEstado());
            query.executeUpdate();
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            System.out.println("Error al actualizar pedido: " + e.getMessage());
        }
    }

    public ArrayList<Pedido> getAll(){
        ArrayList<Pedido> salida;
        var em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<Pedido> q = em.createQuery("SELECT pe from Pedido pe",Pedido.class);
        salida = (ArrayList<Pedido>) q.getResultList();
        em.close();
        return salida;
    }

    public void delete(Pedido u){
        try {
            var em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            Pedido pedido = em.find(Pedido.class, u.getId());
            em.remove(pedido);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e){
            System.out.println("Error al eliminar pedido: " + e.getMessage());
        }
    }

    public ArrayList<Pedido> getTodayPedidos(){
        ArrayList<Pedido> salida;
        var em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<Pedido> q = em.createQuery("SELECT pe from Pedido pe WHERE pe.fecha = :fecha",Pedido.class)
                .setParameter("fecha", HOY);
        salida = (ArrayList<Pedido>) q.getResultList();
        em.close();
        return salida;
    }


}
