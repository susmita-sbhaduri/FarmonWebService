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
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author sb
 */
@Entity
@Table(name = "crop")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Crop.findAll", query = "SELECT c FROM Crop c"),
    @NamedQuery(name = "Crop.findByCropid", query = "SELECT c FROM Crop c WHERE c.cropid = :cropid"),
    @NamedQuery(name = "Crop.findByCropname", query = "SELECT c FROM Crop c WHERE c.cropname = :cropname"),
    @NamedQuery(name = "Crop.findByTotalstock", query = "SELECT c FROM Crop c WHERE c.totalstock = :totalstock"),
    @NamedQuery(name = "Crop.findByStartdate", query = "SELECT c FROM Crop c WHERE c.startdate = :startdate"),
    @NamedQuery(name = "Crop.findByEnddate", query = "SELECT c FROM Crop c WHERE c.enddate = :enddate")})
public class Crop implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cropid")
    private Integer cropid;
    @Size(max = 100)
    @Column(name = "cropname")
    private String cropname;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "totalstock")
    private BigDecimal totalstock;
    @Column(name = "startdate")
    @Temporal(TemporalType.DATE)
    private Date startdate;
    @Column(name = "enddate")
    @Temporal(TemporalType.DATE)
    private Date enddate;

    public Crop() {
    }

    public Crop(Integer cropid) {
        this.cropid = cropid;
    }

    public Integer getCropid() {
        return cropid;
    }

    public void setCropid(Integer cropid) {
        this.cropid = cropid;
    }

    public String getCropname() {
        return cropname;
    }

    public void setCropname(String cropname) {
        this.cropname = cropname;
    }

    public BigDecimal getTotalstock() {
        return totalstock;
    }

    public void setTotalstock(BigDecimal totalstock) {
        this.totalstock = totalstock;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cropid != null ? cropid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crop)) {
            return false;
        }
        Crop other = (Crop) object;
        if ((this.cropid == null && other.cropid != null) || (this.cropid != null && !this.cropid.equals(other.cropid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.farmon.entities.Crop[ cropid=" + cropid + " ]";
    }
    
}
