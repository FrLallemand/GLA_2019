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
@Named(value = "displayEncheresBean")
@ManagedBean
@RequestScoped
public class DisplayEncheresBean {
        
    @ManagedProperty(value = "#{param.id}")
    private Long id;
        
    @EJB
    private ArticleDAO articleDAO;
    
    @EJB
    private EnchereDAO enchereDAO;

    private List<Encheres> encheres;        

    
    public void retrieveAll(){
        Cookie idCookie = CookieJar.getInstance().getIdCookie();
        encheres =  enchereDAO.getForEnchereur(Long.parseLong(idCookie.getValue()));
    }
    
    public ArticleDAO getArticleDAO() {
        return articleDAO;
    }

    public void setArticleDAO(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    public EnchereDAO getEnchereDAO() {
        return enchereDAO;
    }

    public void setEnchereDAO(EnchereDAO enchereDAO) {
        this.enchereDAO = enchereDAO;
    }

    public List<Encheres> getEncheres() {
        return encheres;
    }

    public void setEncheres(List<Encheres> encheres) {
        this.encheres = encheres;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
}
