/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Article;
import entities.Utilisateur;
import java.util.Date;
import java.util.List;
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
        Utilisateur utilisateur = em.find(Utilisateur.class, id);
        
        a.addVendeur(utilisateur);        
        em.merge(a);
        return a;
    }
    
    public List<Article> getAll() {
        return em
                .createNamedQuery("Article.findAll", Article.class)
                .getResultList();                
    }

    public List<Article> searchArticleNamed(String nom) {
        return em.createNamedQuery("Article.findAllAvailableNamed", Article.class)
                .setParameter("searchedDate", new Date())
                .setParameter("nom", nom)
                .getResultList();
    }
    
    public double getActualPrice(Article article){
        try{
            return em.createNamedQuery("priceOfAnArticle",Double.class)
                    .setParameter("mArticle",article)
                    .getSingleResult();
        }catch(Exception e){
            return article.getPrix();
        }
    }
    
    public Article getById(long id) {
        System.out.println("ARTICLE FETCHED");
        return em.find(Article.class, id);
    }
    
    public void removeById(long id) {
        //TODO : correct relations in database
        
        em.remove(em.find(Article.class, id));        
    }
    
    public Article save(Article a) {
        em.merge(a);
        return a;
    }
}
