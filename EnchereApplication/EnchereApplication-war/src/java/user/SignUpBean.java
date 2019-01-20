/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

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
@Named("signUpBean")
@ManagedBean
@RequestScoped
public class SignUpBean {
    
     @EJB
    private UtilisateurDAOBean utilisateurDAO;
    private String login;
    private String mdp;
    private String nom;
    private String prenom;

    public SignUpBean() {
        this.login = "";
        this.mdp = "";
        this.nom = "";
        this.prenom = "";
    }

    public String submit() {
        if(this.formIsValid()){
            Utilisateur utilisateur = utilisateurDAO.create(new Utilisateur(login, mdp, nom, prenom));
        }
        return "index?faces-redirect=true";
    }

    private boolean formIsValid() {
        if(login.equals("") ||
                mdp.equals("") ||
                nom.equals("") ||
                prenom.equals("")) {
            return false;
        }
        return true;
    }
    
    public UtilisateurDAOBean getUtilisateurDAO() {
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

}
