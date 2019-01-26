/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package article;

import cookies.CookieJar;
import dao.ArticleDAOBean;
import dao.EnchereDAOBean;
import entities.Article;
import entities.Encheres;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.Cookie;

/**
 *
 * @author francoislallemand
 */
@Named(value = "displayArticleBean")
@ManagedBean
@RequestScoped
public class DisplayArticleBean {

    @ManagedProperty(value = "#{param.id}")
    private Long id;
    
    @EJB
    private ArticleDAOBean articleDAOBean;
    
    @EJB
    private EnchereDAOBean enchereDAOBean;

    private Article article;
    
    public DisplayArticleBean() {
        this.article = null;
    }
    
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public double getPrix(){
        List<Encheres> encheres = enchereDAOBean.getForArticle(article.getId());
        
        System.out.println("size of the list " + encheres.size());
        
        if(encheres.isEmpty()){
            return this.article.getPrix();
        }
        else{
            double highestBid = 0;
            
            for(Encheres e : encheres){
                if(e.getMontant() > highestBid){
                    highestBid = e.getMontant();
                }
            }
            
            return highestBid;
        }
    }

    public void retrieveArticle(){
        this.article = articleDAOBean.getById(this.id);
    }
    
    public void removeArticle(){
        this.article = articleDAOBean.getById(this.id);
    }

    public double getActualPrice(){
        return articleDAOBean.getActualPrice(this.article);
    }
    
    public Boolean isVendeur(){
        Long myID = Long.parseLong(CookieJar.getInstance().getIdCookie().getValue());
        return this.articleDAOBean.isVendeur(article, myID);
    }
}
