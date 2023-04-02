/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fees_tracking_system;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "SIGNUP", catalog = "", schema = "ROOT")
@NamedQueries({
    @NamedQuery(name = "Signup_1.findAll", query = "SELECT s FROM Signup_1 s")
    , @NamedQuery(name = "Signup_1.findById", query = "SELECT s FROM Signup_1 s WHERE s.id = :id")
    , @NamedQuery(name = "Signup_1.findByFirstname", query = "SELECT s FROM Signup_1 s WHERE s.firstname = :firstname")
    , @NamedQuery(name = "Signup_1.findByLastname", query = "SELECT s FROM Signup_1 s WHERE s.lastname = :lastname")
    , @NamedQuery(name = "Signup_1.findByUsername", query = "SELECT s FROM Signup_1 s WHERE s.username = :username")
    , @NamedQuery(name = "Signup_1.findByPassword", query = "SELECT s FROM Signup_1 s WHERE s.password = :password")
    , @NamedQuery(name = "Signup_1.findByDob", query = "SELECT s FROM Signup_1 s WHERE s.dob = :dob")
    , @NamedQuery(name = "Signup_1.findByContactNo", query = "SELECT s FROM Signup_1 s WHERE s.contactNo = :contactNo")})
public class Signup_1 implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Column(name = "LASTNAME")
    private String lastname;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Column(name = "CONTACT_NO")
    private String contactNo;

    public Signup_1() {
    }

    public Signup_1(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        String oldFirstname = this.firstname;
        this.firstname = firstname;
        changeSupport.firePropertyChange("firstname", oldFirstname, firstname);
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        String oldLastname = this.lastname;
        this.lastname = lastname;
        changeSupport.firePropertyChange("lastname", oldLastname, lastname);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        String oldUsername = this.username;
        this.username = username;
        changeSupport.firePropertyChange("username", oldUsername, username);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        String oldPassword = this.password;
        this.password = password;
        changeSupport.firePropertyChange("password", oldPassword, password);
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        Date oldDob = this.dob;
        this.dob = dob;
        changeSupport.firePropertyChange("dob", oldDob, dob);
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        String oldContactNo = this.contactNo;
        this.contactNo = contactNo;
        changeSupport.firePropertyChange("contactNo", oldContactNo, contactNo);
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
        if (!(object instanceof Signup_1)) {
            return false;
        }
        Signup_1 other = (Signup_1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fees_tracking_system.Signup_1[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
