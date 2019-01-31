/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package article;

import cookies.CookieJar;
import dao.ArticleDAO;
import dao.EnchereDAO;
import entities.Article;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

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
    private ArticleDAO articleDAOBean;

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
        if(this.article==null)
            return false;
        Long myID = Long.parseLong(CookieJar.getInstance().getIdCookie().getValue());
        System.out.println(this.articleDAOBean.isVendeur(article, myID));
        return this.articleDAOBean.isVendeur(article, myID);
    }
}
