package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.ImpuestoView;
import com.mycompany.myapp.repository.ImpuestoViewRepository;
import com.mycompany.myapp.service.dto.ImpuestoViewDTO;
import com.mycompany.myapp.service.mapper.ImpuestoViewMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ImpuestoView}.
 */
@Service
@Transactional
public class ImpuestoViewService {

    private final Logger log = LoggerFactory.getLogger(ImpuestoViewService.class);

    private final ImpuestoViewRepository impuestoViewRepository;

    private final ImpuestoViewMapper impuestoViewMapper;

    public ImpuestoViewService(ImpuestoViewRepository impuestoViewRepository, ImpuestoViewMapper impuestoViewMapper) {
        this.impuestoViewRepository = impuestoViewRepository;
        this.impuestoViewMapper = impuestoViewMapper;
    }

    /**
     * Save a impuestoView.
     *
     * @param impuestoViewDTO the entity to save.
     * @return the persisted entity.
     */
    public ImpuestoViewDTO save(ImpuestoViewDTO impuestoViewDTO) {
        log.debug("Request to save ImpuestoView : {}", impuestoViewDTO);
        ImpuestoView impuestoView = impuestoViewMapper.toEntity(impuestoViewDTO);
        impuestoView = impuestoViewRepository.save(impuestoView);
        return impuestoViewMapper.toDto(impuestoView);
    }

    /**
     * Get all the impuestoViews.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ImpuestoViewDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ImpuestoViews");
        return impuestoViewRepository.findAll(pageable)
            .map(impuestoViewMapper::toDto);
    }


    /**
     * Get one impuestoView by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ImpuestoViewDTO> findOne(Long id) {
        log.debug("Request to get ImpuestoView : {}", id);
        return impuestoViewRepository.findById(id)
            .map(impuestoViewMapper::toDto);
    }

    /**
     * Delete the impuestoView by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ImpuestoView : {}", id);
        impuestoViewRepository.deleteById(id);
    }
}
