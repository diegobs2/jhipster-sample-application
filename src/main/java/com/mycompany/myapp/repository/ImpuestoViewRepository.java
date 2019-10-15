package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.ImpuestoView;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ImpuestoView entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImpuestoViewRepository extends JpaRepository<ImpuestoView, Long>, JpaSpecificationExecutor<ImpuestoView> {

}
