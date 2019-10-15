package com.mycompany.myapp.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.ImpuestoView} entity.
 */
public class ImpuestoViewDTO implements Serializable {

    private Long id;

    private String cFacturaF;

    private String cReferenciaF;

    private Integer ejercicioF;

    private String documentoFiF;

    private String zdmsIdF;

    private String cProveedorF;

    private String cSociedadF;

    private String iva;

    private Double sumImpoMoneda;

    private Double sumImpoImpuesto;

    private Double sumImporteBase;

    private String cMonedaI;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getcFacturaF() {
        return cFacturaF;
    }

    public void setcFacturaF(String cFacturaF) {
        this.cFacturaF = cFacturaF;
    }

    public String getcReferenciaF() {
        return cReferenciaF;
    }

    public void setcReferenciaF(String cReferenciaF) {
        this.cReferenciaF = cReferenciaF;
    }

    public Integer getEjercicioF() {
        return ejercicioF;
    }

    public void setEjercicioF(Integer ejercicioF) {
        this.ejercicioF = ejercicioF;
    }

    public String getDocumentoFiF() {
        return documentoFiF;
    }

    public void setDocumentoFiF(String documentoFiF) {
        this.documentoFiF = documentoFiF;
    }

    public String getZdmsIdF() {
        return zdmsIdF;
    }

    public void setZdmsIdF(String zdmsIdF) {
        this.zdmsIdF = zdmsIdF;
    }

    public String getcProveedorF() {
        return cProveedorF;
    }

    public void setcProveedorF(String cProveedorF) {
        this.cProveedorF = cProveedorF;
    }

    public String getcSociedadF() {
        return cSociedadF;
    }

    public void setcSociedadF(String cSociedadF) {
        this.cSociedadF = cSociedadF;
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }

    public Double getSumImpoMoneda() {
        return sumImpoMoneda;
    }

    public void setSumImpoMoneda(Double sumImpoMoneda) {
        this.sumImpoMoneda = sumImpoMoneda;
    }

    public Double getSumImpoImpuesto() {
        return sumImpoImpuesto;
    }

    public void setSumImpoImpuesto(Double sumImpoImpuesto) {
        this.sumImpoImpuesto = sumImpoImpuesto;
    }

    public Double getSumImporteBase() {
        return sumImporteBase;
    }

    public void setSumImporteBase(Double sumImporteBase) {
        this.sumImporteBase = sumImporteBase;
    }

    public String getcMonedaI() {
        return cMonedaI;
    }

    public void setcMonedaI(String cMonedaI) {
        this.cMonedaI = cMonedaI;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ImpuestoViewDTO impuestoViewDTO = (ImpuestoViewDTO) o;
        if (impuestoViewDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), impuestoViewDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ImpuestoViewDTO{" +
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
