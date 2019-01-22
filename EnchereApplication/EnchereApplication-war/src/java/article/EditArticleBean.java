/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package article;

import dao.ArticleDAOBean;
import entities.Article;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import navigation.NavigationBean;

/**
 *
 * @author ghaniturismo
 */
@Named(value = "editArticleBean")
@ManagedBean
@RequestScoped
public class EditArticleBean {

    @EJB
    private ArticleDAOBean articleDAOBean;

    //@EJB
    //private UserManagerBean userManager;

    private NavigationBean navigationBean;

    @ManagedProperty(value = "#{param.id}")
    private Long id;
    
    private Article article;

    /**
     * Creates a new instance of editArticleBean
     */
    public EditArticleBean() {
        this.article = new Article();
        this.article.setFin(new Date());
        this.navigationBean = new NavigationBean();
    }

    public String create() {
        articleDAOBean.addToUtilisateur(article, navigationBean.getId());
        return "index?faces-redirect=true";
    }

    public void retrieveArticle(){
        this.article = articleDAOBean.getById(this.id);
    }
    
    public void removeArticle(){
        articleDAOBean.removeById(this.id);
    }


    public String edit() {
        Article a = articleDAOBean.getById(id);
        a.setNom(article.getNom());
        a.setDescription(article.getDescription());
        a.setPrix(article.getPrix());
        a.setCategories(article.getCategories());
        a.setFin(article.getFin());
        
        articleDAOBean.save(a);

        return "index?faces-redirect=true";
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

}
