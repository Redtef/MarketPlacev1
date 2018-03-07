package controller;

import bean.DemandeService;
import bean.Planning;
import bean.Secteur;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.DemandeServiceFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import service.PlanningFacade;
import service.SecteurFacade;

@Named("demandeServiceController")
@SessionScoped
public class DemandeServiceController implements Serializable {

    @EJB
    private SecteurFacade secteurFacade;

    @EJB
    private service.DemandeServiceFacade ejbFacade;
    private List<DemandeService> items;
    private DemandeService selected;
    private Planning planning;
    @EJB
    private PlanningFacade planningFacade;

    public DemandeServiceController() {
    }

    public void save() {
        selected.setPlanning(planning);
        ejbFacade.save(selected);

    }

    public List<Secteur> getAllSecteur() {
        return secteurFacade.findAll();
    }

    public DemandeService getSelected() {
        if (selected == null) {
            selected = new DemandeService();
        }
        return selected;
    }

    public void setSelected(DemandeService selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DemandeServiceFacade getFacade() {
        return ejbFacade;
    }

    public DemandeService prepareCreate() {
        selected = new DemandeService();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DemandeServiceCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DemandeServiceUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DemandeServiceDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<DemandeService> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public SecteurFacade getSecteurFacade() {
        return secteurFacade;
    }

    public void setSecteurFacade(SecteurFacade secteurFacade) {
        this.secteurFacade = secteurFacade;
    }

    public DemandeServiceFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(DemandeServiceFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public Planning getPlanning() {
        if (planning == null) {
            planning = new Planning();
        }
        return planning;
    }

    public void setPlanning(Planning planning) {
        this.planning = planning;
    }

    public PlanningFacade getPlanningFacade() {
        return planningFacade;
    }

    public void setPlanningFacade(PlanningFacade planningFacade) {
        this.planningFacade = planningFacade;
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

    public DemandeService getDemandeService(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<DemandeService> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<DemandeService> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = DemandeService.class)
    public static class DemandeServiceControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DemandeServiceController controller = (DemandeServiceController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "demandeServiceController");
            return controller.getDemandeService(getKey(value));
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
            if (object instanceof DemandeService) {
                DemandeService o = (DemandeService) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DemandeService.class.getName()});
                return null;
            }
        }

    }

}
