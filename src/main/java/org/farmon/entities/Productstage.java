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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author sb
 */
@Entity
@Table(name = "productstage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productstage.findAll", query = "SELECT p FROM Productstage p"),
    @NamedQuery(name = "Productstage.findById", query = "SELECT p FROM Productstage p WHERE p.id = :id"),
    @NamedQuery(name = "Productstage.findByCropid", query = "SELECT p FROM Productstage p WHERE p.cropid = :cropid"),
    @NamedQuery(name = "Productstage.findByProductid", query = "SELECT p FROM Productstage p WHERE p.productid = :productid"),
    @NamedQuery(name = "Productstage.findByStageid", query = "SELECT p FROM Productstage p WHERE p.stageid = :stageid"),
    @NamedQuery(name = "Productstage.findByStagename", query = "SELECT p FROM Productstage p WHERE p.stagename = :stagename")})
public class Productstage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "cropid")
    private Integer cropid;
    @Column(name = "productid")
    private Integer productid;
    @Column(name = "stageid")
    private Integer stageid;
    @Size(max = 100)
    @Column(name = "stagename")
    private String stagename;

    public Productstage() {
    }

    public Productstage(Integer id) {
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

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public Integer getStageid() {
        return stageid;
    }

    public void setStageid(Integer stageid) {
        this.stageid = stageid;
    }

    public String getStagename() {
        return stagename;
    }

    public void setStagename(String stagename) {
        this.stagename = stagename;
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
        if (!(object instanceof Productstage)) {
            return false;
        }
        Productstage other = (Productstage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.farmon.entities.Productstage[ id=" + id + " ]";
    }
    
}
