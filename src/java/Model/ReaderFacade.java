/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import entities.Reader;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Carlos Solis
 */
@Stateless
public class ReaderFacade extends AbstractFacade<Reader> implements ReaderFacadeLocal {

    @PersistenceContext(unitName = "ForoWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReaderFacade() {
        super(Reader.class);
    }
    
}
