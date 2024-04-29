/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ReaderFacadeLocal;
import entities.Reader;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Carlos Solis
 */
@ManagedBean(name = "readerBean")
@SessionScoped
public class ReaderController {

    @EJB
    private ReaderFacadeLocal readerFacadeLocal;
    private List<Reader> listaReaders;
    private Reader reader;

    @PostConstruct
    public void init() {
        this.reader = new Reader();
    }

    public List<Reader> getListaReaders() {
        listaReaders = readerFacadeLocal.findAll();
        return listaReaders;
    }

    public void setListaReaders(List<Reader> listaReaders) {
        this.listaReaders = listaReaders;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public void crearReader() {
        if (validarCampos()) {
            try {
                readerFacadeLocal.create(reader);
                reader.setName("");
                this.reader = new Reader();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Lector creado correctamente"));
                RequestContext.getCurrentInstance().execute("PF('dialogoAgregarReader').hide()");
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al crear lector"));
            }
        }
    }

    private boolean validarCampos() {
        if (reader.getName() == null || reader.getName().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El nombre del lector es obligatorio"));
            return false;
        }
        return true;
    }

    public void editarReader() {
        if (validarCampos()) {
            try {
                readerFacadeLocal.edit(reader);
                reader = new Reader();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Lector editado correctamente"));
                RequestContext.getCurrentInstance().execute("PF('dialogoEditarReader').hide()");
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al editar lector"));
            }
        }
    }

    public void eliminarReader(Reader readerEliminar) {
        readerFacadeLocal.remove(readerEliminar);
        reader = new Reader();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Lector eliminado correctamente"));
    }
}
