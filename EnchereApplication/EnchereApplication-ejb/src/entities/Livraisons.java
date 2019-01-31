/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author frlallemand
 */
@Entity
@Table(name = "LIVRAISONS")
public class Livraisons implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "COMMAND_ID")
    private Integer commandId;
    
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @OneToOne
    private Utilisateur userId;

    @JoinColumn(name = "ARTICLE_ID", referencedColumnName = "ID")    
    @OneToOne
    private Article articleId;

    @Column(name = "CREATED")
    @Temporal(TemporalType.DATE)
    private Date created;
    
    @Column(name = "ETAT_LIVRAISON")
    @NotNull
    private Boolean etatLivraison;
    
    @Column(name = "ETAT_FACTURATION")
    @NotNull
    private Boolean etatFacturation;

    @Column(name = "ETAT_CONFIRMATION")
    @NotNull
    private Boolean etatConfirmation;

    public Integer getCommandId() {
        return commandId;
    }

    public void setCommandId(Integer commandId) {
        this.commandId = commandId;
    }

    public Utilisateur getUserId() {
        return userId;
    }

    public void setUserId(Utilisateur userId) {
        this.userId = userId;
    }

    public Article getArticleId() {
        return articleId;
    }

    public void setArticleId(Article articleId) {
        this.articleId = articleId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Boolean getEtatLivraison() {
        return etatLivraison;
    }

    public void setEtatLivraison(Boolean etatLivraison) {
        this.etatLivraison = etatLivraison;
    }

    public Boolean getEtatFacturation() {
        return etatFacturation;
    }

    public void setEtatFacturation(Boolean etatFacturation) {
        this.etatFacturation = etatFacturation;
    }

    public Boolean getEtatConfirmation() {
        return etatConfirmation;
    }

    public void setEtatConfirmation(Boolean etatConfirmation) {
        this.etatConfirmation = etatConfirmation;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commandId != null ? commandId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Livraisons)) {
            return false;
        }
        Livraisons other = (Livraisons) object;
        if ((this.commandId == null && other.commandId != null) || (this.commandId != null && !this.commandId.equals(other.commandId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Livraisons[ commandId=" + commandId + " ]";
    }
    
}
