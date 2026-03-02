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
@Table(name = "sensordetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sensordetail.findAll", query = "SELECT s FROM Sensordetail s"),
    @NamedQuery(name = "Sensordetail.findById", query = "SELECT s FROM Sensordetail s WHERE s.id = :id"),
    @NamedQuery(name = "Sensordetail.findBySensorid", query = "SELECT s FROM Sensordetail s WHERE s.sensorid = :sensorid"),
    @NamedQuery(name = "Sensordetail.findByParameter", query = "SELECT s FROM Sensordetail s WHERE s.parameter = :parameter")})
public class Sensordetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "sensorid")
    private String sensorid;
    @Size(max = 100)
    @Column(name = "parameter")
    private String parameter;

    public Sensordetail() {
    }

    public Sensordetail(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSensorid() {
        return sensorid;
    }

    public void setSensorid(String sensorid) {
        this.sensorid = sensorid;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
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
        if (!(object instanceof Sensordetail)) {
            return false;
        }
        Sensordetail other = (Sensordetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.farmon.entities.Sensordetail[ id=" + id + " ]";
    }
    
}
