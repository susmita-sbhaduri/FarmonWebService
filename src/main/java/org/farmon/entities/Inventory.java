/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.farmon.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author sb
 */
@Entity
@Table(name = "inventory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inventory.findAll", query = "SELECT i FROM Inventory i"),
    @NamedQuery(name = "Inventory.findById", query = "SELECT i FROM Inventory i WHERE i.id = :id"),
    @NamedQuery(name = "Inventory.findByCropid", query = "SELECT i FROM Inventory i WHERE i.cropid = :cropid"),
    @NamedQuery(name = "Inventory.findByHasvestid", query = "SELECT i FROM Inventory i WHERE i.hasvestid = :hasvestid"),
    @NamedQuery(name = "Inventory.findByCurrentqty", query = "SELECT i FROM Inventory i WHERE i.currentqty = :currentqty"),
    @NamedQuery(name = "Inventory.findByLastupdatedate", query = "SELECT i FROM Inventory i WHERE i.lastupdatedate = :lastupdatedate")})
public class Inventory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "cropid")
    private Integer cropid;
    @Column(name = "hasvestid")
    private Integer hasvestid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "currentqty")
    private BigDecimal currentqty;
    @Column(name = "lastupdatedate")
    @Temporal(TemporalType.DATE)
    private Date lastupdatedate;

    public Inventory() {
    }

    public Inventory(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCropid() {
        return cropid;
    }

    public void setCropid(Integer cropid) {
        this.cropid = cropid;
    }

    public Integer getHasvestid() {
        return hasvestid;
    }

    public void setHasvestid(Integer hasvestid) {
        this.hasvestid = hasvestid;
    }

    public BigDecimal getCurrentqty() {
        return currentqty;
    }

    public void setCurrentqty(BigDecimal currentqty) {
        this.currentqty = currentqty;
    }

    public Date getLastupdatedate() {
        return lastupdatedate;
    }

    public void setLastupdatedate(Date lastupdatedate) {
        this.lastupdatedate = lastupdatedate;
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
        if (!(object instanceof Inventory)) {
            return false;
        }
        Inventory other = (Inventory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.farmon.entities.Inventory[ id=" + id + " ]";
    }
    
}
