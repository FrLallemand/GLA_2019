/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ladeveze2u
 */
@Entity
@Table(name = "ENCHERES")
@NamedQueries({
        @NamedQuery(name="Encheres.priceOfAnArticle",
                query="SELECT MAX(e.montant) FROM Encheres e WHERE e.article=:mArticle")
                ,
        @NamedQuery(name="Encheres.findForArticle",
                query="select e from Encheres e where e.article.id = :searchedArticle"),
        @NamedQuery(name= "Encheres.listOfBuyerOrdered",
                query="SELECT e FROM Encheres e WHERE e.article=:mArticle ORDER BY e.montant DESC"),
        @NamedQuery(name= "Encheres.allForEnchereur",
                query="SELECT e FROM Encheres e WHERE e.enchereur.id=:id"),
        @NamedQuery(name= "Encheres.forEnchereurAndArticle",
                query="SELECT e FROM Encheres e WHERE e.enchereur.id=:idEnchereur AND e.article.id=:idArticle")
})

public class Encheres implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private double montant;
                
    @ManyToOne
    Utilisateur enchereur;
    
    @ManyToOne
    Article article;
    
    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Utilisateur getEnchereur() {
        return enchereur;
    }

    public void setEnchereur(Utilisateur enchereur) {
        this.enchereur = enchereur;
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
