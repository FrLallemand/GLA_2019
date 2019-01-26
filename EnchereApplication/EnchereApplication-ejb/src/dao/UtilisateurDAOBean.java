/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Utilisateur;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author francoislallemand
 */
@Stateless
public class UtilisateurDAOBean {
    
    @PersistenceContext(unitName = "EnchereApplication-ejbPU")
    private EntityManager em;
    
    
    public Utilisateur create(Utilisateur u){
        em.persist(u);
        return u;
    }
    
    public Utilisateur authenticate(String login, String mdp) {
        Utilisateur u = em
                .createNamedQuery("Utilisateur.authenticate", Utilisateur.class)
                .setParameter("login", login)
                .getSingleResult();

        if(u != null && u.getMdp().equals(mdp)) {
            return u;
        } else {
            return null;
        }
    }
}
