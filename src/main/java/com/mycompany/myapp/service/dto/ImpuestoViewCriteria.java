package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.ImpuestoView} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.ImpuestoViewResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /impuesto-views?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ImpuestoViewCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter cFacturaF;

    private StringFilter cReferenciaF;

    private IntegerFilter ejercicioF;

    private StringFilter documentoFiF;

    private StringFilter zdmsIdF;

    private StringFilter cProveedorF;

    private StringFilter cSociedadF;

    private StringFilter iva;

    private DoubleFilter sumImpoMoneda;

    private DoubleFilter sumImpoImpuesto;

    private DoubleFilter sumImporteBase;

    private StringFilter cMonedaI;

    public ImpuestoViewCriteria(){
    }

    public ImpuestoViewCriteria(ImpuestoViewCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.cFacturaF = other.cFacturaF == null ? null : other.cFacturaF.copy();
        this.cReferenciaF = other.cReferenciaF == null ? null : other.cReferenciaF.copy();
        this.ejercicioF = other.ejercicioF == null ? null : other.ejercicioF.copy();
        this.documentoFiF = other.documentoFiF == null ? null : other.documentoFiF.copy();
        this.zdmsIdF = other.zdmsIdF == null ? null : other.zdmsIdF.copy();
        this.cProveedorF = other.cProveedorF == null ? null : other.cProveedorF.copy();
        this.cSociedadF = other.cSociedadF == null ? null : other.cSociedadF.copy();
        this.iva = other.iva == null ? null : other.iva.copy();
        this.sumImpoMoneda = other.sumImpoMoneda == null ? null : other.sumImpoMoneda.copy();
        this.sumImpoImpuesto = other.sumImpoImpuesto == null ? null : other.sumImpoImpuesto.copy();
        this.sumImporteBase = other.sumImporteBase == null ? null : other.sumImporteBase.copy();
        this.cMonedaI = other.cMonedaI == null ? null : other.cMonedaI.copy();
    }

    @Override
    public ImpuestoViewCriteria copy() {
        return new ImpuestoViewCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getcFacturaF() {
        return cFacturaF;
    }

    public void setcFacturaF(StringFilter cFacturaF) {
        this.cFacturaF = cFacturaF;
    }

    public StringFilter getcReferenciaF() {
        return cReferenciaF;
    }

    public void setcReferenciaF(StringFilter cReferenciaF) {
        this.cReferenciaF = cReferenciaF;
    }

    public IntegerFilter getEjercicioF() {
        return ejercicioF;
    }

    public void setEjercicioF(IntegerFilter ejercicioF) {
        this.ejercicioF = ejercicioF;
    }

    public StringFilter getDocumentoFiF() {
        return documentoFiF;
    }

    public void setDocumentoFiF(StringFilter documentoFiF) {
        this.documentoFiF = documentoFiF;
    }

    public StringFilter getZdmsIdF() {
        return zdmsIdF;
    }

    public void setZdmsIdF(StringFilter zdmsIdF) {
        this.zdmsIdF = zdmsIdF;
    }

    public StringFilter getcProveedorF() {
        return cProveedorF;
    }

    public void setcProveedorF(StringFilter cProveedorF) {
        this.cProveedorF = cProveedorF;
    }

    public StringFilter getcSociedadF() {
        return cSociedadF;
    }

    public void setcSociedadF(StringFilter cSociedadF) {
        this.cSociedadF = cSociedadF;
    }

    public StringFilter getIva() {
        return iva;
    }

    public void setIva(StringFilter iva) {
        this.iva = iva;
    }

    public DoubleFilter getSumImpoMoneda() {
        return sumImpoMoneda;
    }

    public void setSumImpoMoneda(DoubleFilter sumImpoMoneda) {
        this.sumImpoMoneda = sumImpoMoneda;
    }

    public DoubleFilter getSumImpoImpuesto() {
        return sumImpoImpuesto;
    }

    public void setSumImpoImpuesto(DoubleFilter sumImpoImpuesto) {
        this.sumImpoImpuesto = sumImpoImpuesto;
    }

    public DoubleFilter getSumImporteBase() {
        return sumImporteBase;
    }

    public void setSumImporteBase(DoubleFilter sumImporteBase) {
        this.sumImporteBase = sumImporteBase;
    }

    public StringFilter getcMonedaI() {
        return cMonedaI;
    }

    public void setcMonedaI(StringFilter cMonedaI) {
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
        final ImpuestoViewCriteria that = (ImpuestoViewCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(cFacturaF, that.cFacturaF) &&
            Objects.equals(cReferenciaF, that.cReferenciaF) &&
            Objects.equals(ejercicioF, that.ejercicioF) &&
            Objects.equals(documentoFiF, that.documentoFiF) &&
            Objects.equals(zdmsIdF, that.zdmsIdF) &&
            Objects.equals(cProveedorF, that.cProveedorF) &&
            Objects.equals(cSociedadF, that.cSociedadF) &&
            Objects.equals(iva, that.iva) &&
            Objects.equals(sumImpoMoneda, that.sumImpoMoneda) &&
            Objects.equals(sumImpoImpuesto, that.sumImpoImpuesto) &&
            Objects.equals(sumImporteBase, that.sumImporteBase) &&
            Objects.equals(cMonedaI, that.cMonedaI);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        cFacturaF,
        cReferenciaF,
        ejercicioF,
        documentoFiF,
        zdmsIdF,
        cProveedorF,
        cSociedadF,
        iva,
        sumImpoMoneda,
        sumImpoImpuesto,
        sumImporteBase,
        cMonedaI
        );
    }

    @Override
    public String toString() {
        return "ImpuestoViewCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (cFacturaF != null ? "cFacturaF=" + cFacturaF + ", " : "") +
                (cReferenciaF != null ? "cReferenciaF=" + cReferenciaF + ", " : "") +
                (ejercicioF != null ? "ejercicioF=" + ejercicioF + ", " : "") +
                (documentoFiF != null ? "documentoFiF=" + documentoFiF + ", " : "") +
                (zdmsIdF != null ? "zdmsIdF=" + zdmsIdF + ", " : "") +
                (cProveedorF != null ? "cProveedorF=" + cProveedorF + ", " : "") +
                (cSociedadF != null ? "cSociedadF=" + cSociedadF + ", " : "") +
                (iva != null ? "iva=" + iva + ", " : "") +
                (sumImpoMoneda != null ? "sumImpoMoneda=" + sumImpoMoneda + ", " : "") +
                (sumImpoImpuesto != null ? "sumImpoImpuesto=" + sumImpoImpuesto + ", " : "") +
                (sumImporteBase != null ? "sumImporteBase=" + sumImporteBase + ", " : "") +
                (cMonedaI != null ? "cMonedaI=" + cMonedaI + ", " : "") +
            "}";
    }

}
