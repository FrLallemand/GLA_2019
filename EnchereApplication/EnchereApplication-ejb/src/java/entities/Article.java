/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author frlallemand
 */
@Entity
@Table(name = "ARTICLES")
public class Article implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "NOM")
    private String nom;
    @Column(name = "DESCRIPTION")   
    private String description;
    @Column(name = "CATEGORIES")
    private String categories;
    @Column(name = "PRIX")
    private double prix;
    
    @Column(name = "FIN")
    @Temporal(TemporalType.DATE)
    private Date fin;
    
    //@Embedded
    @OneToOne   
    Utilisateur vendeur;
    
    //@Embedded
    @OneToOne
    Utilisateur acheteur;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(String n){
        this.nom = n;
    }
    
    public double getPrix(){
        return this.prix;
    }
    
    public void setPrix(double prix){
        this.prix = prix;
    }
    
    public Utilisateur getVendeur(){
        return this.vendeur;
    }
    
    public void setVendeur(Utilisateur u){
        this.vendeur = u;
    }
    
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public Utilisateur getAcheteur() {
        return acheteur;
    }

    public void setAcheteur(Utilisateur acheteur) {
        this.acheteur = acheteur;
    }
    
    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Article)) {
            return false;
        }
        Article other = (Article) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Articles[ id=" + id + " ]";
    }
    
}
