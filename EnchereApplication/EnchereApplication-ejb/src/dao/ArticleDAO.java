/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Article;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author leduc
 */
@Local
public interface ArticleDAO {

    public Article addToUtilisateur(Article a, Long id);

    public Article create(Article a);

    public double getActualPrice(Article article);

    public List<Article> getAll();

    public List<Article> getAllMine(Long id);

    public Article getById(long id);

    public List<Article> getPanier(Long id);

    public Boolean isVendeur(Article a, long id);

    public void removeById(long id);

    public Article save(Article a);

    public List<Article> searchArticleNamed(String nom);

    public void sendPanier(Long id);
    
}
