/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fees_tracking_system;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "FEES", catalog = "", schema = "ROOT")
@NamedQueries({
    @NamedQuery(name = "Fees.findAll", query = "SELECT f FROM Fees f")
    , @NamedQuery(name = "Fees.findById", query = "SELECT f FROM Fees f WHERE f.id = :id")
    , @NamedQuery(name = "Fees.findByFname", query = "SELECT f FROM Fees f WHERE f.fname = :fname")
    , @NamedQuery(name = "Fees.findByCost", query = "SELECT f FROM Fees f WHERE f.cost = :cost")})
public class Fees implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "FNAME")
    private String fname;
    @Basic(optional = false)
    @Column(name = "COST")
    private String cost;

    public Fees() {
    }

    public Fees(Integer id) {
        this.id = id;
    }

    public Fees(Integer id, String fname, String cost) {
        this.id = id;
        this.fname = fname;
        this.cost = cost;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        String oldFname = this.fname;
        this.fname = fname;
        changeSupport.firePropertyChange("fname", oldFname, fname);
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        String oldCost = this.cost;
        this.cost = cost;
        changeSupport.firePropertyChange("cost", oldCost, cost);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fees)) {
            return false;
        }
        Fees other = (Fees) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fees_tracking_system.Fees[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
