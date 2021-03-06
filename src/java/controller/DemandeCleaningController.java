package controller;

import bean.DemandeCleaning;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.DemandeCleaningFacade;

import java.io.Serializable;
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

@Named("demandeCleaningController")
@SessionScoped
public class DemandeCleaningController implements Serializable {

    @EJB
    private service.DemandeCleaningFacade ejbFacade;
    private List<DemandeCleaning> items = null;
    private DemandeCleaning selected;

    public DemandeCleaningController() {
    }

    public DemandeCleaning getSelected() {
        if (selected == null) {
            selected = new DemandeCleaning();
        }
        return selected;
    }

    public void setSelected(DemandeCleaning selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    public void addMessage() {
        String summary = selected.getBringEquipement()? "Equipement inclus" : "Equipement non inclus";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
    }

    protected void initializeEmbeddableKey() {
    }

    private DemandeCleaningFacade getFacade() {
        return ejbFacade;
    }

    public DemandeCleaning prepareCreate() {
        selected = new DemandeCleaning();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DemandeCleaningCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DemandeCleaningUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DemandeCleaningDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<DemandeCleaning> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
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

    public DemandeCleaning getDemandeCleaning(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<DemandeCleaning> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<DemandeCleaning> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = DemandeCleaning.class)
    public static class DemandeCleaningControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DemandeCleaningController controller = (DemandeCleaningController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "demandeCleaningController");
            return controller.getDemandeCleaning(getKey(value));
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
            if (object instanceof DemandeCleaning) {
                DemandeCleaning o = (DemandeCleaning) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DemandeCleaning.class.getName()});
                return null;
            }
        }

    }

}
