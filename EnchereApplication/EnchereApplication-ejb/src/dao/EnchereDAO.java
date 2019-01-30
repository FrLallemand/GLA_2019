/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Encheres;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author leduc
 */
@Local
public interface EnchereDAO {

    public Encheres create(Encheres e);

    public List<Encheres> getForArticle(Long id);
    public List<Encheres> getForEnchereur(Long id);
    
}
