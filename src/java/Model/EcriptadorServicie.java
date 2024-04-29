/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.ejb.Stateless;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Carlos Solis
 */
@Stateless
public class EcriptadorServicie {

    public String encriptarContrasena(String contrasena) {
        return BCrypt.hashpw(contrasena, BCrypt.gensalt());
    }

    public boolean validarContrasena(String contrasenaPlana, String contrasenaEncriptada) {
        return BCrypt.checkpw(contrasenaPlana, contrasenaEncriptada);
    }
}
