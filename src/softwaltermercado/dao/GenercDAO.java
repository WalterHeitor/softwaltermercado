/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaltermercado.dao;

import java.lang.reflect.ParameterizedType;
import javax.persistence.EntityManager;
import softwaltermercado.conexcao.ConnectionFactory;

/**
 *
 * @author walter heitor
 */
public class GenercDAO <Entidade>{
    
    private static EntityManager entityManager =
            ConnectionFactory.getEntityManager();
    private Class<Entidade> classe;
    
    public void GenericDAO(){
        this.classe = (Class<Entidade>) ((ParameterizedType)getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
    public void save(Entidade entidade){
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entidade);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
    
    
}
