/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package navigation;

import cookies.CookieJar;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.servlet.http.Cookie;

/**
 *
 * @author francoislallemand
 */
@Named("navigationBean")
@ManagedBean
public class NavigationBean {
    
    private List<NavigationElement> list; 
    private Long id;
    private String login;
    private String nom;
    private String prenom;

    public NavigationBean() {
        this.list = new ArrayList<>();
        Cookie idCookie = CookieJar.getInstance().getIdCookie();
        Cookie nomCookie = CookieJar.getInstance().getNomCookie();
        Cookie prenomCookie = CookieJar.getInstance().getPrenomCookie();
        Cookie loginCookie = CookieJar.getInstance().getLoginCookie();

        if(idCookie == null) {
            this.list.add(new NavigationElement("Inscription", "signup.xhtml"));
            this.list.add(new NavigationElement("Connexion", "connection.xhtml"));  
        } else {
            this.list.add(new NavigationElement("Nouvel Article", "putArticle.xhtml"));
            this.list.add(new NavigationElement("Mes Articles", "list.xhtml"));
            this.list.add(new NavigationElement("Rechercher", "searchArticle.xhtml"));
            this.list.add(new NavigationElement("Mon Panier", "panier.xhtml"));
            this.list.add(new NavigationElement("Mes enchères", "displayEncheres.xhtml"));
            id = Long.parseLong(idCookie.getValue());
            nom = nomCookie.getValue();
            prenom = prenomCookie.getValue();
            login = loginCookie.getValue();            
        }
    }
    
    public List<NavigationElement> getList() {
        return list;
    }

    public void setList(List<NavigationElement> list) {
        this.list = list;
    }    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

}
