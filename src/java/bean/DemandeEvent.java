/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Boss
 */
@Entity
public class DemandeEvent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private EventType eventType;
    private int nbrInvites;
    private String detail;
    @ManyToOne
    private DemandeService demandeService;
    @OneToMany(mappedBy = "demandeEvent")
    private List<SupplementDemandeEvent> supplementDemandeEvents;
    @OneToMany(mappedBy = "demandeEvent")
    private List<CuisineDemandeEvent> cuisineDemandeEvents;

    public DemandeService getDemandeService() {
        return demandeService;
    }

    public void setDemandeService(DemandeService demandeService) {
        this.demandeService = demandeService;
    }

    //budget???
    public DemandeEvent() {
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getNbrInvites() {
        return nbrInvites;
    }

    public void setNbrInvites(int nbrInvites) {
        this.nbrInvites = nbrInvites;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CuisineDemandeEvent> getCuisineDemandeEvents() {
        return cuisineDemandeEvents;
    }

    public void setCuisineDemandeEvents(List<CuisineDemandeEvent> cuisineDemandeEvents) {
        this.cuisineDemandeEvents = cuisineDemandeEvents;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public List<SupplementDemandeEvent> getSupplementDemandeEvents() {
        return supplementDemandeEvents;
    }

    public void setSupplementDemandeEvents(List<SupplementDemandeEvent> supplementDemandeEvents) {
        this.supplementDemandeEvents = supplementDemandeEvents;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DemandeEvent)) {
            return false;
        }
        DemandeEvent other = (DemandeEvent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.DemandeEvent[ id=" + id + " ]";
    }

}
