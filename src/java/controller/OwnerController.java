package controller;

import bean.Owner;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.OwnerFacade;

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
import org.primefaces.context.RequestContext;

@Named("ownerController")
@SessionScoped
public class OwnerController implements Serializable {

    @EJB
    private service.OwnerFacade ejbFacade;
    private List<Owner> items = null;
    private Owner selected;

    public OwnerController() {
    }

    public String login() {
        int connected = ejbFacade.loginId(selected);
        if (connected == -1) {
            showMessage("Compte introuvable", "Verifiez votre email et mot de passe");
        } else if (connected == -2) {
            showMessage("Votre compte est blocké", "Veuillez contacter un manager");
        } else if (connected == -3) {
            showMessage("Compte introuvable", "Verifiez votre email et mot de passe");
        }
        return "/owner/profile";
    }

//    public String loginId() {
//        int conected = ejbFacade.loginId(selected);
//        if (conected == 0) {
//            SessionUtil.setAttribute("connectedManager", selected);
////            Device dev = deviceFacade.getManagerDevice(selected);
////            deviceFacade.create(dev);
//        } else if (conected == 1) {
//            Device device = deviceFacade.verifDevice(selected);
//            if (device == null) {
//                return "/manager/question";
//            } else {
//                selected = managerFacade.find(selected.getId());
//                SessionUtil.setAttribute("connectedManager", selected);
//            }
//        }
//        return "/manager/Profile";
//    }
//    public String verifRepons() {
//        int verifier = 0;
//        String conex = DateUtil.formateDate("yyyy-MM-dd", dernierConex);
//        verifier += managerFacade.RepDernConx(selected, conex);
//        verifier += managerFacade.RepDernAction(selected, action);
//        conex = DateUtil.formateDate("yyyy-MM-dd", dernierConfirmation);
//        verifier += managerFacade.RepDernConfir(selected, conex);
//        if (verifier < 2) {
//            return "/login";
//        } else {
//            Device dev = deviceFacade.getManagerDevice(selected);
//            deviceFacade.creerDevice(dev);
//            SessionUtil.setAttribute("device", dev);
//            SessionUtil.setAttribute("connectedManager", selected);
//            return "/manager/Profile";
//        }
//    }
    public void showMessage(String titre, String contenu) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, titre, contenu);
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public Owner getSelected() {
        if (selected == null) {
            selected = new Owner();
        }
        return selected;
    }

    public void setSelected(Owner selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private OwnerFacade getFacade() {
        return ejbFacade;
    }

    public Owner prepareCreate() {
        selected = new Owner();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("OwnerCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("OwnerUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("OwnerDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Owner> getItems() {
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

    public Owner getOwner(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Owner> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Owner> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Owner.class)
    public static class OwnerControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            OwnerController controller = (OwnerController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ownerController");
            return controller.getOwner(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Owner) {
                Owner o = (Owner) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Owner.class.getName()});
                return null;
            }
        }

    }

}
