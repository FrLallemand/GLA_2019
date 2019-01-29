/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import cookies.CookieJar;
import dao.UtilisateurDAO;
import dao.UtilisateurDAOBean;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import entities.Utilisateur;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author francoislallemand
 */
@Named("connectionBean")
@ManagedBean
@RequestScoped
public class ConnectionBean {
    
     @EJB
    private UtilisateurDAO utilisateurDAO;
    private String login;
    private String mdp;

    public ConnectionBean() {
        this.login = "";
        this.mdp = "";
    }

    public String connect() {
        if(this.formIsValid()){
            Utilisateur utilisateur = utilisateurDAO.authenticate(login, mdp);
            CookieJar.getInstance().addCookie("id", String.valueOf(utilisateur.getId()), 900 /*15 minutes*/);
            CookieJar.getInstance().addCookie("login", String.valueOf(utilisateur.getLogin()), 900 /*15 minutes*/);
            CookieJar.getInstance().addCookie("nom", String.valueOf(utilisateur.getNom()), 900 /*15 minutes*/);
            CookieJar.getInstance().addCookie("prenom", String.valueOf(utilisateur.getPrenom()), 900 /*15 minutes*/);
        }
        return "index?faces-redirect=true";
    }
    
    public String disconnect() {
        System.out.println("disconnect");
        CookieJar.getInstance().removeCookie("id");
        CookieJar.getInstance().removeCookie("login");
        CookieJar.getInstance().removeCookie("nom");
        CookieJar.getInstance().removeCookie("prenom"); 
        return "index?faces-redirect=true";
    }

    private boolean formIsValid() {
        if(login.equals("") ||
                mdp.equals("") ) {
            return false;
        }
        return true;
    }
    
    public UtilisateurDAO getUtilisateurDAO() {
        return utilisateurDAO;
    }

    public void setUtilisateurDAO(UtilisateurDAOBean utilisateurDAO) {
        this.utilisateurDAO = utilisateurDAO;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

}
