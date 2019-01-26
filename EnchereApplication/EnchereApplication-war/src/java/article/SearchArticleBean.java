/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package article;

import cookies.CookieJar;
import dao.ArticleDAOBean;
import entities.Article;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.Cookie;

/**
 *
 * @author francoislallemand
 */

@Named("searchArticleBean")
@ManagedBean
@RequestScoped
public class SearchArticleBean {

    @EJB
    private ArticleDAOBean articleDAO;
   
    private List<Article> articles;    
   
    private String nom;
    
    public SearchArticleBean(){
        this.articles = new ArrayList<Article>();        
    }

    public void search(){
       articles = articleDAO.searchArticleNamed(this.nom);        
    }
    
    public List<Article> getAll(){
       return articleDAO.getAll();        
    }
    
    public List<Article> getAllMine(){
       Cookie idCookie = CookieJar.getInstance().getIdCookie();
       return articleDAO.getAllMine(Long.parseLong(idCookie.getValue()));        
    }
    
    public List<Article> getPanier(){
       Cookie idCookie = CookieJar.getInstance().getIdCookie();
       return articleDAO.getPanier(Long.parseLong(idCookie.getValue()));        
       
    }
    
    public ArticleDAOBean getArticleDAO() {
        return articleDAO;
    }

    public void setArticleDAO(ArticleDAOBean articleDAO) {
        this.articleDAO = articleDAO;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public double getActualPrice(Article elem){
        return articleDAO.getActualPrice(elem);
    }

}
