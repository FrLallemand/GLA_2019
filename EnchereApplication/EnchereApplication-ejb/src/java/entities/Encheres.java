/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ladeveze2u
 */
@Entity
@Table(name = "ENCHERES")
public class Encheres {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private double montant;

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Utilisateurs getEnchereur() {
        return enchereur;
    }

    public void setEnchereur(Utilisateurs enchereur) {
        this.enchereur = enchereur;
    }

    public Articles getArticle() {
        return article;
    }

    public void setArticle(Articles article) {
        this.article = article;
    }
            
    @Embedded
    Utilisateurs enchereur;
    
    @Embedded
    Articles article;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
