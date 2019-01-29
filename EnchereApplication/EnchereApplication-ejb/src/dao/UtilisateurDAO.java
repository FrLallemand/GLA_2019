/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Utilisateur;
import javax.ejb.Local;

/**
 *
 * @author leduc
 */
@Local
public interface UtilisateurDAO {

    public Utilisateur authenticate(String login, String mdp);

    public Utilisateur create(Utilisateur u);

    public Utilisateur getById(String id);
    
}
