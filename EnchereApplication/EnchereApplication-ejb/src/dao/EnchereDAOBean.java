/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import entities.Encheres;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author aceawan
 */
@Stateless
public class EnchereDAOBean {
    @PersistenceContext(unitName = "EnchereApplication-ejbPU")
    private EntityManager em;
    
    public Encheres create(Encheres e){
        em.persist(e);
        return e;
    }
        
    public List<Encheres> getForArticle(Long id){
        return em
                .createNamedQuery("Enchere.findForArticle", Encheres.class)
                .setParameter("searchedArticle", id)
                .getResultList();
    }
}