/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Article;
import entities.Encheres;
import entities.Utilisateur;
import java.util.Date;
import java.util.List;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author leduc
 */
@Singleton
public class TimerBean {

    @PersistenceContext(unitName = "EnchereApplication-ejbPU")
    private EntityManager em;
    
    @Schedule(second = "0", minute = "0", hour = "0", dayOfMonth = "*", month = "*", year = "*")
    public void MAJEncheres(){
        System.out.println("MAJ du jour");
        List<Article> listArticleOfToday = em.createNamedQuery("Article.findAllOfTheDay", Article.class)
                .setParameter("searchedDate",new Date())
                .getResultList();
        
        for(Article art : listArticleOfToday){
            art = em.merge(art);
            List<Encheres> listEnch = em.createNamedQuery("Encheres.listOfBuyerOrdered",Encheres.class)
                    .setParameter("mArticle",art)
                    .getResultList();
            if(listEnch.isEmpty()){
                System.out.println(em.merge(art.getVendeur()).getLogin()+", personne n'a voulu de votre enchere ");
            }else{
                System.out.println("nb enchereur"+listEnch.size());
                Encheres winner = listEnch.get(0);
                em.merge(winner);
                art.setAcheteur(winner.getEnchereur());
                em.merge(art);
                System.out.println(em.merge(winner.getEnchereur()).getLogin()+" a gagné l'enchere");
                for(int i=1;i<listEnch.size();i++){
                    Utilisateur loser = em.merge(em.merge(listEnch.get(i)).getEnchereur());
                    System.out.println(loser.getLogin()+" a perdu l'enchere");
                }
            }
        }
    }
    
    
}
