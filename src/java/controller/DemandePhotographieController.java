package controller;

import bean.Client;
import bean.DemandePhotographie;
import bean.DemandeService;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.DemandePhotographieFacade;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.event.SelectEvent;

@Named(value = "demandePhotographieController")
@SessionScoped
public class DemandePhotographieController implements Serializable {

    @EJB
    private service.DemandePhotographieFacade ejbFacade;
    private List<DemandePhotographie> items;
    private DemandePhotographie selected;
    private DemandeService demandeService;
    private Client client;

    public DemandePhotographieController() {
    }

    public String save() {
        ejbFacade.save(selected);
        selected = null;
        return "DemandePhotographieCreate";
    }

    public DemandePhotographie getSelected() {
        if (selected == null) {
            selected = new DemandePhotographie();
        }
        return selected;
    }

    public void setSelected(DemandePhotographie selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DemandePhotographieFacade getFacade() {
        return ejbFacade;
    }

    public DemandePhotographie prepareCreate() {
        selected = new DemandePhotographie();
        initializeEmbeddableKey();
        return selected;
    }

    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

    public void addMessage() {
        String summary = selected.getVideographie() ? "Videographie inclus" : "Videographie non inclus";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DemandePhotographieCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DemandePhotographieUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DemandePhotographieDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<DemandePhotographie> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public DemandePhotographieFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(DemandePhotographieFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public DemandeService getDemandeService() {
        if (demandeService == null) {
            demandeService = new DemandeService();
        }
        return demandeService;
    }

    public void setDemandeService(DemandeService demandeService) {
        this.demandeService = demandeService;
    }

    public Client getClient() {
        if (client == null) {
            client = new Client();
        }
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public DemandePhotographie getDemandePhotographie(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<DemandePhotographie> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<DemandePhotographie> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = DemandePhotographie.class)
    public static class DemandePhotographieControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DemandePhotographieController controller = (DemandePhotographieController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "demandePhotographieController");
            return controller.getDemandePhotographie(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof DemandePhotographie) {
                DemandePhotographie o = (DemandePhotographie) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DemandePhotographie.class.getName()});
                return null;
            }
        }

    }

}
