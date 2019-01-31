/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package encheres;

import cookies.CookieJar;
import dao.ArticleDAO;
import dao.EnchereDAO;
import entities.Article;
import entities.Encheres;
import entities.Utilisateur;
import dao.UtilisateurDAO;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.Cookie;

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
        article = articleDAOBean.getById(articleId);
        user = utilisateurDAOBean.getById(CookieJar.getInstance().getIdCookie().getValue());
    }
    
    public String removeEncheres(){
        Cookie idCookie = CookieJar.getInstance().getIdCookie();
        enchereDAOBean.removeForEnchereurAndArticle(Long.parseLong(idCookie.getValue()), this.articleId);
        return "list";
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
                e.setArticle(article);
                e.setMontant(this.enchereValue);
                e.setEnchereur(user);
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
