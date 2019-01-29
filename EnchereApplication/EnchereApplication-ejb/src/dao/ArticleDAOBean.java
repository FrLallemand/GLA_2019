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
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.Cookie;

/**
 *
 * @author francoislallemand
 */
@Stateless
public class ArticleDAOBean implements ArticleDAO {
    
    @PersistenceContext(unitName = "EnchereApplication-ejbPU")
    private EntityManager em;
 
    @Override
    public Article create(Article a){
        em.persist(a);
        return a;
    }
    
    @Override
    public Article addToUtilisateur(Article a, Long id) {
        Utilisateur utilisateur = em.find(Utilisateur.class, id);
        
        a.addVendeur(utilisateur);        
        em.merge(a);
        return a;
    }
    
    @Override
    public List<Article> getAll() {
        return em
                .createNamedQuery("Article.findAll", Article.class)
                .getResultList();                
    }
    
    @Override
    public List<Article> getAllMine(Long id) {
        Utilisateur utilisateur = em.find(Utilisateur.class, id);
        return em
                .createNamedQuery("Article.findAllOf", Article.class)
                .setParameter("user",utilisateur)
                .getResultList();                
    }
    
    @Override
    public List<Article> getPanier(Long id) {
        Utilisateur utilisateur = em.find(Utilisateur.class, id);
        return em
                .createNamedQuery("Article.findAllBuyedNotSended", Article.class)
                .setParameter("user",utilisateur)
                .getResultList();                
    }
    
    @Override
    public void sendPanier(Long id) {
        Utilisateur utilisateur = em.find(Utilisateur.class, id);
        List<Article> listToSend= em.createNamedQuery("Article.findAllBuyedNotSended", Article.class)
                .setParameter("user",utilisateur)
                .getResultList();
        for(Article art:listToSend){
            art.setSended(true);
            em.merge(art);
            //TODO envoyer l'article
        }
    }

    @Override
    public List<Article> searchArticleNamed(String nom) {
        return em.createNamedQuery("Article.findAllAvailableNamed", Article.class)
                .setParameter("searchedDate", new Date())
                .setParameter("nom", nom)
                .getResultList();
    }
    
    @Override
    public double getActualPrice(Article article){
        Double prixTable = em.createNamedQuery("Encheres.priceOfAnArticle",Double.class)
                    .setParameter("mArticle",article)
                    .getSingleResult();
        if(prixTable>article.getPrix())
            return prixTable;
        return article.getPrix();
        
    }
    
    @Override
    public Article getById(long id) {
        System.out.println("ARTICLE FETCHED");
        return em.find(Article.class, id);
    }
    
    @Override
    public void removeById(long id) {
        //TODO : correct relations in database
        
        em.remove(em.find(Article.class, id));        
    }
    
    @Override
    public Article save(Article a) {
        em.merge(a);
        return a;
    }
    
    @Override
    public Boolean isVendeur(Article a,long id){
        //System.out.println("error:"+a.getAcheteur().getId());
        Utilisateur vendeur = em.merge(a.getVendeur());
        return vendeur.getId()==id;//a.getAcheteur().getId()==id;
    }
}
