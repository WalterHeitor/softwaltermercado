/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaltermercado.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import softwaltermercado.model.Cliente;

/**
 *
 * @author walter heitor
 */
public class ClienteDAO {
    
    private static ClienteDAO instance;
    
    protected EntityManager entityManager;
    
    public static ClienteDAO getInstance(){
        if(instance == null){
            instance = new ClienteDAO();
        }
        return instance;
    }
    private EntityManager getEntityManager(){
        EntityManagerFactory factory = 
                Persistence.createEntityManagerFactory("vendas_mercadinho");
        if(entityManager == null){
            entityManager = factory.createEntityManager();
        }
        return entityManager;
    }
    
    
    private ClienteDAO(){
        entityManager = getEntityManager();
    }
    
    public Cliente getById(final Integer id){
        return entityManager.find(Cliente.class, id);
    }
    public List<Cliente> findAll(){
        return entityManager.createQuery("FROM"+ Cliente.class.getName())
                .getResultList();
    }
    public void persist(Cliente cliente){
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(cliente);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
    public void merge(Cliente cliente){
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(cliente);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
    public void remove(Cliente cliente){
        try {
            entityManager.getTransaction().begin();
            cliente = entityManager.find(Cliente.class, cliente.getId());
            entityManager.remove(cliente);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
    public void removeById(final Integer id){
        try {
            Cliente cliente = getById(id);
            remove(cliente);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
