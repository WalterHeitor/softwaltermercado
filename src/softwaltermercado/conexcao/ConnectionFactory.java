/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaltermercado.conexcao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author walter heitor
 */
public class ConnectionFactory {
    protected static EntityManager entityManager;
    private static  EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("vendas_mercadinho");
    public static EntityManager getEntityManager(){
        if(entityManager == null){
            entityManager = factory.createEntityManager();
        }
        return entityManager;
    }
}
