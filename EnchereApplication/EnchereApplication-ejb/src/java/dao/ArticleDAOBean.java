/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Article;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author francoislallemand
 */
@Stateless
public class ArticleDAOBean {
    
    @PersistenceContext(unitName = "EnchereApplication-ejbPU")
    private EntityManager em;
 
    public Article create(Article a){
        em.persist(a);
        return a;
    }
    
    public Article addToUtilisateur(Article a, Long id) {
        return null;
    }
    
    
}
