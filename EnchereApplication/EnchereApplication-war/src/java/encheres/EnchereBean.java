/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package encheres;

import cookies.CookieJar;
import dao.ArticleDAO;
import dao.ArticleDAOBean;
import dao.EnchereDAO;
import dao.UtilisateurDAOBean;
import entities.Article;
import entities.Encheres;
import entities.Utilisateur;
import dao.EnchereDAOBean;
import dao.UtilisateurDAO;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author aceawan
 */
@Named(value = "EnchereBean")
@ManagedBean
@RequestScoped
public class EnchereBean {
    public boolean validValue;
    public Double enchereValue;
    
    @EJB
    ArticleDAO articleDAOBean;
    
    @EJB
    EnchereDAO enchereDAOBean;
    
    @EJB
    UtilisateurDAO utilisateurDAOBean;
    
    @ManagedProperty(value = "#{param.id}")
    public Long articleId;
    
    public Article article;
    
    public Utilisateur user;
    
    public EnchereBean(){
        enchereValue = 0.0;
    }
    
    public void setUp(){
        //System.out.println(articleId);
        article = articleDAOBean.getById(articleId);
        //System.out.println(article.getNom());
        user = utilisateurDAOBean.getById(CookieJar.getInstance().getIdCookie().getValue());
    }
    
    public void setEnchereValue(String svalue){
        Double value = Double.parseDouble(svalue);
        
        List<Encheres> encheres = enchereDAOBean.getForArticle(this.articleId);
        
        validValue = true;
        
        if(!encheres.isEmpty()){
            for(Encheres e : encheres){
                if(e.getMontant() >= value){
                    validValue = false;
                    break;
                }
            }
        }
        
        if(validValue){
            this.enchereValue = value;
        }
    }
    
    public String getEnchereValue(){
        return this.enchereValue.toString();
    }
    
    public String putEnchere(){
        this.setUp();
        Encheres enchereExistante = enchereDAOBean.getForEnchereurAndArticle(user.getId(), article.getId());
        if(validValue){
            if(enchereExistante==null){
                Encheres e = new Encheres();
                //System.out.println("article = " + this.article.getNom());
                e.setArticle(article);
                e.setMontant(this.enchereValue);
                e.setEnchereur(user);
                //System.out.println("article = " + this.user.getNom());
                enchereDAOBean.create(e);
            } else {
                enchereExistante.setMontant(this.enchereValue);
                enchereDAOBean.update(enchereExistante);
            }
        }
        
        return "list";
    }
    
    public Long getArticleId(){
        return this.articleId;
    }
    
    public void setArticleId(Long id){
        this.articleId = id;
    }
    
    public void retrieveEnchereValue() {
        Encheres enchereExistante = enchereDAOBean.getForEnchereurAndArticle(user.getId(), article.getId());
        if(enchereExistante==null){
            this.enchereValue = 0.0;
        } else {
            this.enchereValue = enchereExistante.getMontant();
        }
    }

}
