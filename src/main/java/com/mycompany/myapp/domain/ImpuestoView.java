package com.mycompany.myapp.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ImpuestoView.
 */
@Entity
@Table(name = "impuesto_view")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ImpuestoView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "c_factura_f")
    private String cFacturaF;

    @Column(name = "c_referencia_f")
    private String cReferenciaF;

    @Column(name = "ejercicio_f")
    private Integer ejercicioF;

    @Column(name = "documento_fi_f")
    private String documentoFiF;

    @Column(name = "zdms_id_f")
    private String zdmsIdF;

    @Column(name = "c_proveedor_f")
    private String cProveedorF;

    @Column(name = "c_sociedad_f")
    private String cSociedadF;

    @Column(name = "iva")
    private String iva;

    @Column(name = "sum_impo_moneda")
    private Double sumImpoMoneda;

    @Column(name = "sum_impo_impuesto")
    private Double sumImpoImpuesto;

    @Column(name = "sum_importe_base")
    private Double sumImporteBase;

    @Column(name = "c_moneda_i")
    private String cMonedaI;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getcFacturaF() {
        return cFacturaF;
    }

    public ImpuestoView cFacturaF(String cFacturaF) {
        this.cFacturaF = cFacturaF;
        return this;
    }

    public void setcFacturaF(String cFacturaF) {
        this.cFacturaF = cFacturaF;
    }

    public String getcReferenciaF() {
        return cReferenciaF;
    }

    public ImpuestoView cReferenciaF(String cReferenciaF) {
        this.cReferenciaF = cReferenciaF;
        return this;
    }

    public void setcReferenciaF(String cReferenciaF) {
        this.cReferenciaF = cReferenciaF;
    }

    public Integer getEjercicioF() {
        return ejercicioF;
    }

    public ImpuestoView ejercicioF(Integer ejercicioF) {
        this.ejercicioF = ejercicioF;
        return this;
    }

    public void setEjercicioF(Integer ejercicioF) {
        this.ejercicioF = ejercicioF;
    }

    public String getDocumentoFiF() {
        return documentoFiF;
    }

    public ImpuestoView documentoFiF(String documentoFiF) {
        this.documentoFiF = documentoFiF;
        return this;
    }

    public void setDocumentoFiF(String documentoFiF) {
        this.documentoFiF = documentoFiF;
    }

    public String getZdmsIdF() {
        return zdmsIdF;
    }

    public ImpuestoView zdmsIdF(String zdmsIdF) {
        this.zdmsIdF = zdmsIdF;
        return this;
    }

    public void setZdmsIdF(String zdmsIdF) {
        this.zdmsIdF = zdmsIdF;
    }

    public String getcProveedorF() {
        return cProveedorF;
    }

    public ImpuestoView cProveedorF(String cProveedorF) {
        this.cProveedorF = cProveedorF;
        return this;
    }

    public void setcProveedorF(String cProveedorF) {
        this.cProveedorF = cProveedorF;
    }

    public String getcSociedadF() {
        return cSociedadF;
    }

    public ImpuestoView cSociedadF(String cSociedadF) {
        this.cSociedadF = cSociedadF;
        return this;
    }

    public void setcSociedadF(String cSociedadF) {
        this.cSociedadF = cSociedadF;
    }

    public String getIva() {
        return iva;
    }

    public ImpuestoView iva(String iva) {
        this.iva = iva;
        return this;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }

    public Double getSumImpoMoneda() {
        return sumImpoMoneda;
    }

    public ImpuestoView sumImpoMoneda(Double sumImpoMoneda) {
        this.sumImpoMoneda = sumImpoMoneda;
        return this;
    }

    public void setSumImpoMoneda(Double sumImpoMoneda) {
        this.sumImpoMoneda = sumImpoMoneda;
    }

    public Double getSumImpoImpuesto() {
        return sumImpoImpuesto;
    }

    public ImpuestoView sumImpoImpuesto(Double sumImpoImpuesto) {
        this.sumImpoImpuesto = sumImpoImpuesto;
        return this;
    }

    public void setSumImpoImpuesto(Double sumImpoImpuesto) {
        this.sumImpoImpuesto = sumImpoImpuesto;
    }

    public Double getSumImporteBase() {
        return sumImporteBase;
    }

    public ImpuestoView sumImporteBase(Double sumImporteBase) {
        this.sumImporteBase = sumImporteBase;
        return this;
    }

    public void setSumImporteBase(Double sumImporteBase) {
        this.sumImporteBase = sumImporteBase;
    }

    public String getcMonedaI() {
        return cMonedaI;
    }

    public ImpuestoView cMonedaI(String cMonedaI) {
        this.cMonedaI = cMonedaI;
        return this;
    }

    public void setcMonedaI(String cMonedaI) {
        this.cMonedaI = cMonedaI;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ImpuestoView)) {
            return false;
        }
        return id != null && id.equals(((ImpuestoView) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ImpuestoView{" +
            "id=" + getId() +
            ", cFacturaF='" + getcFacturaF() + "'" +
            ", cReferenciaF='" + getcReferenciaF() + "'" +
            ", ejercicioF=" + getEjercicioF() +
            ", documentoFiF='" + getDocumentoFiF() + "'" +
            ", zdmsIdF='" + getZdmsIdF() + "'" +
            ", cProveedorF='" + getcProveedorF() + "'" +
            ", cSociedadF='" + getcSociedadF() + "'" +
            ", iva='" + getIva() + "'" +
            ", sumImpoMoneda=" + getSumImpoMoneda() +
            ", sumImpoImpuesto=" + getSumImpoImpuesto() +
            ", sumImporteBase=" + getSumImporteBase() +
            ", cMonedaI='" + getcMonedaI() + "'" +
            "}";
    }
}
