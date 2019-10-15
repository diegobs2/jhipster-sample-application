package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ImpuestoViewDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ImpuestoView} and its DTO {@link ImpuestoViewDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ImpuestoViewMapper extends EntityMapper<ImpuestoViewDTO, ImpuestoView> {



    default ImpuestoView fromId(Long id) {
        if (id == null) {
            return null;
        }
        ImpuestoView impuestoView = new ImpuestoView();
        impuestoView.setId(id);
        return impuestoView;
    }
}
