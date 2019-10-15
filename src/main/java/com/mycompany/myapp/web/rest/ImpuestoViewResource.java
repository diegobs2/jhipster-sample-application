package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.ImpuestoViewService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.ImpuestoViewDTO;
import com.mycompany.myapp.service.dto.ImpuestoViewCriteria;
import com.mycompany.myapp.service.ImpuestoViewQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.ImpuestoView}.
 */
@RestController
@RequestMapping("/api")
public class ImpuestoViewResource {

    private final Logger log = LoggerFactory.getLogger(ImpuestoViewResource.class);

    private static final String ENTITY_NAME = "jhipsterSampleApplicationImpuestoView";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ImpuestoViewService impuestoViewService;

    private final ImpuestoViewQueryService impuestoViewQueryService;

    public ImpuestoViewResource(ImpuestoViewService impuestoViewService, ImpuestoViewQueryService impuestoViewQueryService) {
        this.impuestoViewService = impuestoViewService;
        this.impuestoViewQueryService = impuestoViewQueryService;
    }

    /**
     * {@code POST  /impuesto-views} : Create a new impuestoView.
     *
     * @param impuestoViewDTO the impuestoViewDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new impuestoViewDTO, or with status {@code 400 (Bad Request)} if the impuestoView has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/impuesto-views")
    public ResponseEntity<ImpuestoViewDTO> createImpuestoView(@RequestBody ImpuestoViewDTO impuestoViewDTO) throws URISyntaxException {
        log.debug("REST request to save ImpuestoView : {}", impuestoViewDTO);
        if (impuestoViewDTO.getId() != null) {
            throw new BadRequestAlertException("A new impuestoView cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ImpuestoViewDTO result = impuestoViewService.save(impuestoViewDTO);
        return ResponseEntity.created(new URI("/api/impuesto-views/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /impuesto-views} : Updates an existing impuestoView.
     *
     * @param impuestoViewDTO the impuestoViewDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated impuestoViewDTO,
     * or with status {@code 400 (Bad Request)} if the impuestoViewDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the impuestoViewDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/impuesto-views")
    public ResponseEntity<ImpuestoViewDTO> updateImpuestoView(@RequestBody ImpuestoViewDTO impuestoViewDTO) throws URISyntaxException {
        log.debug("REST request to update ImpuestoView : {}", impuestoViewDTO);
        if (impuestoViewDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ImpuestoViewDTO result = impuestoViewService.save(impuestoViewDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, impuestoViewDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /impuesto-views} : get all the impuestoViews.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of impuestoViews in body.
     */
    @GetMapping("/impuesto-views")
    public ResponseEntity<List<ImpuestoViewDTO>> getAllImpuestoViews(ImpuestoViewCriteria criteria, Pageable pageable) {
        log.debug("REST request to get ImpuestoViews by criteria: {}", criteria);
        Page<ImpuestoViewDTO> page = impuestoViewQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /impuesto-views/count} : count all the impuestoViews.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/impuesto-views/count")
    public ResponseEntity<Long> countImpuestoViews(ImpuestoViewCriteria criteria) {
        log.debug("REST request to count ImpuestoViews by criteria: {}", criteria);
        return ResponseEntity.ok().body(impuestoViewQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /impuesto-views/:id} : get the "id" impuestoView.
     *
     * @param id the id of the impuestoViewDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the impuestoViewDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/impuesto-views/{id}")
    public ResponseEntity<ImpuestoViewDTO> getImpuestoView(@PathVariable Long id) {
        log.debug("REST request to get ImpuestoView : {}", id);
        Optional<ImpuestoViewDTO> impuestoViewDTO = impuestoViewService.findOne(id);
        return ResponseUtil.wrapOrNotFound(impuestoViewDTO);
    }

    /**
     * {@code DELETE  /impuesto-views/:id} : delete the "id" impuestoView.
     *
     * @param id the id of the impuestoViewDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/impuesto-views/{id}")
    public ResponseEntity<Void> deleteImpuestoView(@PathVariable Long id) {
        log.debug("REST request to delete ImpuestoView : {}", id);
        impuestoViewService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
