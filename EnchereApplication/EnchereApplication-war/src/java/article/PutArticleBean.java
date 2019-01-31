/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package article;

import cookies.CookieJar;
import dao.ArticleDAO;
import dao.ArticleDAOBean;
import entities.Article;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.Cookie;

/**
 *
 * @author francoislallemand
 */
@Named("putArticleBean")
@ManagedBean
@RequestScoped
public class PutArticleBean {
    
    @EJB
    private ArticleDAO articleDAO;
    
    private Article article;
    
    
    public PutArticleBean() {
        this.article = new Article();
    } 

    public String putArticle(){
        Cookie idCookie = CookieJar.getInstance().getIdCookie();
        if(article.getFin() == null){
            article.setFin(new Date());
        }
        articleDAO.addToUtilisateur(article, Long.parseLong(idCookie.getValue()));
        return "index?faces-redirect=true";
     }
    
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

}
