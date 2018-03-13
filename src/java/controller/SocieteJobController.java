package controller;

import bean.Societe;
import bean.SocieteJob;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.SocieteJobFacade;

import java.io.Serializable;
import java.util.ArrayList;
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

@Named("societeJobController")
@SessionScoped
public class SocieteJobController implements Serializable {

    @EJB
    private service.SocieteJobFacade ejbFacade;
    private List<SocieteJob> items = new ArrayList<>();
    private SocieteJob selected;
    private Societe societe;

    public SocieteJobController() {
    }

    public SocieteJob getSelected() {
        if (selected == null) {
            selected = new SocieteJob();
        }
        return selected;
    }

    public void add() {
        System.out.println(selected);
        items.add(ejbFacade.clone(selected));
        System.out.println(ejbFacade.clone(selected));
        //items.add(new SocieteJob());
    }

    public void remove(SocieteJob societeJob) {
        items.remove(societeJob);
    }

    public void save() {
//        societe = (Societe) SessionUtil.getAttribute("societe");
//        ejbFacade.save(items, societe);
//        items = null;
        ejbFacade.save(items,selected.getSociete());
    }

    public void setSelected(SocieteJob selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SocieteJobFacade getFacade() {
        return ejbFacade;
    }

    public SocieteJobFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(SocieteJobFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    public SocieteJob prepareCreate() {
        selected = new SocieteJob();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SocieteJobCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SocieteJobUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("SocieteJobDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<SocieteJob> getItems() {
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

    public SocieteJob getSocieteJob(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<SocieteJob> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<SocieteJob> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = SocieteJob.class)
    public static class SocieteJobControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SocieteJobController controller = (SocieteJobController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "societeJobController");
            return controller.getSocieteJob(getKey(value));
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
            if (object instanceof SocieteJob) {
                SocieteJob o = (SocieteJob) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), SocieteJob.class.getName()});
                return null;
            }
        }

    }

}
