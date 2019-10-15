package com.mycompany.myapp.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.mycompany.myapp.domain.ImpuestoView;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.ImpuestoViewRepository;
import com.mycompany.myapp.service.dto.ImpuestoViewCriteria;
import com.mycompany.myapp.service.dto.ImpuestoViewDTO;
import com.mycompany.myapp.service.mapper.ImpuestoViewMapper;

/**
 * Service for executing complex queries for {@link ImpuestoView} entities in the database.
 * The main input is a {@link ImpuestoViewCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ImpuestoViewDTO} or a {@link Page} of {@link ImpuestoViewDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ImpuestoViewQueryService extends QueryService<ImpuestoView> {

    private final Logger log = LoggerFactory.getLogger(ImpuestoViewQueryService.class);

    private final ImpuestoViewRepository impuestoViewRepository;

    private final ImpuestoViewMapper impuestoViewMapper;

    public ImpuestoViewQueryService(ImpuestoViewRepository impuestoViewRepository, ImpuestoViewMapper impuestoViewMapper) {
        this.impuestoViewRepository = impuestoViewRepository;
        this.impuestoViewMapper = impuestoViewMapper;
    }

    /**
     * Return a {@link List} of {@link ImpuestoViewDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ImpuestoViewDTO> findByCriteria(ImpuestoViewCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ImpuestoView> specification = createSpecification(criteria);
        return impuestoViewMapper.toDto(impuestoViewRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ImpuestoViewDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ImpuestoViewDTO> findByCriteria(ImpuestoViewCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ImpuestoView> specification = createSpecification(criteria);
        return impuestoViewRepository.findAll(specification, page)
            .map(impuestoViewMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ImpuestoViewCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ImpuestoView> specification = createSpecification(criteria);
        return impuestoViewRepository.count(specification);
    }

    /**
     * Function to convert {@link ImpuestoViewCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ImpuestoView> createSpecification(ImpuestoViewCriteria criteria) {
        Specification<ImpuestoView> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ImpuestoView_.id));
            }
            if (criteria.getcFacturaF() != null) {
                specification = specification.and(buildStringSpecification(criteria.getcFacturaF(), ImpuestoView_.cFacturaF));
            }
            if (criteria.getcReferenciaF() != null) {
                specification = specification.and(buildStringSpecification(criteria.getcReferenciaF(), ImpuestoView_.cReferenciaF));
            }
            if (criteria.getEjercicioF() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEjercicioF(), ImpuestoView_.ejercicioF));
            }
            if (criteria.getDocumentoFiF() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDocumentoFiF(), ImpuestoView_.documentoFiF));
            }
            if (criteria.getZdmsIdF() != null) {
                specification = specification.and(buildStringSpecification(criteria.getZdmsIdF(), ImpuestoView_.zdmsIdF));
            }
            if (criteria.getcProveedorF() != null) {
                specification = specification.and(buildStringSpecification(criteria.getcProveedorF(), ImpuestoView_.cProveedorF));
            }
            if (criteria.getcSociedadF() != null) {
                specification = specification.and(buildStringSpecification(criteria.getcSociedadF(), ImpuestoView_.cSociedadF));
            }
            if (criteria.getIva() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIva(), ImpuestoView_.iva));
            }
            if (criteria.getSumImpoMoneda() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSumImpoMoneda(), ImpuestoView_.sumImpoMoneda));
            }
            if (criteria.getSumImpoImpuesto() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSumImpoImpuesto(), ImpuestoView_.sumImpoImpuesto));
            }
            if (criteria.getSumImporteBase() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSumImporteBase(), ImpuestoView_.sumImporteBase));
            }
            if (criteria.getcMonedaI() != null) {
                specification = specification.and(buildStringSpecification(criteria.getcMonedaI(), ImpuestoView_.cMonedaI));
            }
        }
        return specification;
    }
}
