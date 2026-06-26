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
import java.util.Date;

/**
 *
 * @author sb
 */
@Entity
@Table(name = "growthstage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Growthstage.findAll", query = "SELECT g FROM Growthstage g"),
    @NamedQuery(name = "Growthstage.findById", query = "SELECT g FROM Growthstage g WHERE g.id = :id"),
    @NamedQuery(name = "Growthstage.findByHarvestid", query = "SELECT g FROM Growthstage g WHERE g.harvestid = :harvestid"),
    @NamedQuery(name = "Growthstage.findByCropid", query = "SELECT g FROM Growthstage g WHERE g.cropid = :cropid"),
    @NamedQuery(name = "Growthstage.findByProductid", query = "SELECT g FROM Growthstage g WHERE g.productid = :productid"),
    @NamedQuery(name = "Growthstage.findByCurrentstageid", query = "SELECT g FROM Growthstage g WHERE g.currentstageid = :currentstageid"),
    @NamedQuery(name = "Growthstage.findByCount", query = "SELECT g FROM Growthstage g WHERE g.count = :count"),
    @NamedQuery(name = "Growthstage.findByLifecycleid", query = "SELECT g FROM Growthstage g WHERE g.lifecycleid = :lifecycleid"),
    @NamedQuery(name = "Growthstage.findByDate", query = "SELECT g FROM Growthstage g WHERE g.date = :date")})
public class Growthstage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "harvestid")
    private Integer harvestid;
    @Column(name = "cropid")
    private Integer cropid;
    @Column(name = "productid")
    private Integer productid;
    @Column(name = "currentstageid")
    private Integer currentstageid;
    @Column(name = "count")
    private Integer count;
    @Column(name = "lifecycleid")
    private Integer lifecycleid;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    public Growthstage() {
    }

    public Growthstage(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHarvestid() {
        return harvestid;
    }

    public void setHarvestid(Integer harvestid) {
        this.harvestid = harvestid;
    }

    public Integer getCropid() {
        return cropid;
    }

    public void setCropid(Integer cropid) {
        this.cropid = cropid;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public Integer getCurrentstageid() {
        return currentstageid;
    }

    public void setCurrentstageid(Integer currentstageid) {
        this.currentstageid = currentstageid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getLifecycleid() {
        return lifecycleid;
    }

    public void setLifecycleid(Integer lifecycleid) {
        this.lifecycleid = lifecycleid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        if (!(object instanceof Growthstage)) {
            return false;
        }
        Growthstage other = (Growthstage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.farmon.entities.Growthstage[ id=" + id + " ]";
    }
    
}
