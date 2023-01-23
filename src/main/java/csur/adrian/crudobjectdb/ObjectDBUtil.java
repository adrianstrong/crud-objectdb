/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package csur.adrian.crudobjectdb;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ObjectDBUtil {
    private static EntityManagerFactory entityManagerFactory;

    static{
        try{
            entityManagerFactory = Persistence.createEntityManagerFactory("data.odb");
            System.out.println("Conexión relizada correctamente!");
        }catch(Exception ex){
            System.out.println("Error al iniciar entityManager");
            System.out.println(ex);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

}
