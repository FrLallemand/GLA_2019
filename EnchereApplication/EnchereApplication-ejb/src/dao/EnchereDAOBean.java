/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import entities.Article;
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
public class EnchereDAOBean implements EnchereDAO {
    @PersistenceContext(unitName = "EnchereApplication-ejbPU")
    private EntityManager em;
    
    @Override
    public Encheres create(Encheres e){
        em.persist(e);
        return e;
    }

    
    @Override
    public Encheres update(Encheres e){
        em.merge(e);
        return e;
    }
    
    @Override
    public void removeById(long id) {
        em.remove(em.find(Encheres.class, id));        
    }
    
    @Override
    public List<Encheres> getForArticle(Long id){
        return em
                .createNamedQuery("Encheres.findForArticle", Encheres.class)
                .setParameter("searchedArticle", id)
                .getResultList();
    }
    
    @Override
    public List<Encheres> getForEnchereur(Long id){
        return em
                .createNamedQuery("Encheres.allForEnchereur", Encheres.class)
                .setParameter("id", id)
                .getResultList();
    }
    
    @Override
    public Encheres getForEnchereurAndArticle(Long idEnchereur, Long idArticle){
        return em
                .createNamedQuery("Encheres.forEnchereurAndArticle", Encheres.class)
                .setParameter("idEnchereur", idEnchereur)
                .setParameter("idArticle", idArticle)
                .getSingleResult();
    }

}
