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
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author francoislallemand
 */
@Named("signUpBean")
@ManagedBean
@RequestScoped
public class SignUpBean {
    
    @EJB
    private UtilisateurDAO utilisateurDAO;
    private String login;
    private String mdp;
    private String nom;
    private String prenom;
    private String adrFacturation;

    public SignUpBean() {
        this.login = "";
        this.mdp = "";
        this.nom = "";
        this.prenom = "";
        this.adrFacturation = "";
    }

    public String submit() {
        try{
            if(this.formIsValid()){
                Utilisateur utilisateur = utilisateurDAO.create(new Utilisateur(login, mdp, nom, prenom, adrFacturation));
                CookieJar.getInstance().addCookie("id", String.valueOf(utilisateur.getId()), 900 /*15 minutes*/);
                CookieJar.getInstance().addCookie("login", String.valueOf(utilisateur.getLogin()), 900 /*15 minutes*/);
                CookieJar.getInstance().addCookie("nom", String.valueOf(utilisateur.getNom()), 900 /*15 minutes*/);
                CookieJar.getInstance().addCookie("prenom", String.valueOf(utilisateur.getPrenom()), 900 /*15 minutes*/);
                CookieJar.getInstance().addCookie("adrFacturation", String.valueOf(utilisateur.getAdrFacturation()), 900 /*15 minutes*/);
            }
            return "index?faces-redirect=true";
        }catch(EJBException e){
                return "signup?faces-redirect=false";
        }
    }

    private boolean formIsValid() {
        if(login.equals("") ||
                mdp.equals("") ||
                nom.equals("") ||
                prenom.equals("") ||
                adrFacturation.equals("")) {
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

    public String getAdrFacturation() {
        return adrFacturation;
    }

    public void setAdrFacturation(String adrFacturation) {
        this.adrFacturation = adrFacturation;
    }

}
