/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import entities.User;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Carlos Solis
 */
@Stateless
public class UserFacade extends AbstractFacade<User> implements UserFacadeLocal {

    @PersistenceContext(unitName = "ForoWebPU")
    private EntityManager em;
    
    @EJB
    EcriptadorServicie ecriptadorServicie;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    @Override
    public User validar(String nombreUsuario, String contrasena) {
        TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.user = :user",
                User.class);
        query.setParameter("user", nombreUsuario);
        try {
            User usuario = query.getSingleResult();
            if (ecriptadorServicie.validarContrasena(contrasena, usuario.getPassword())) {
                return usuario;
            } else {
                return null; // Contraseña incorrecta
            }
        } catch (NoResultException e) {
            return null; // Usuario no encontrado
        }
    }

}
