package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.ImpuestoView;
import com.mycompany.myapp.repository.ImpuestoViewRepository;
import com.mycompany.myapp.service.ImpuestoViewService;
import com.mycompany.myapp.service.dto.ImpuestoViewDTO;
import com.mycompany.myapp.service.mapper.ImpuestoViewMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.ImpuestoViewCriteria;
import com.mycompany.myapp.service.ImpuestoViewQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ImpuestoViewResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ImpuestoViewResourceIT {

    private static final String DEFAULT_C_FACTURA_F = "AAAAAAAAAA";
    private static final String UPDATED_C_FACTURA_F = "BBBBBBBBBB";

    private static final String DEFAULT_C_REFERENCIA_F = "AAAAAAAAAA";
    private static final String UPDATED_C_REFERENCIA_F = "BBBBBBBBBB";

    private static final Integer DEFAULT_EJERCICIO_F = 1;
    private static final Integer UPDATED_EJERCICIO_F = 2;
    private static final Integer SMALLER_EJERCICIO_F = 1 - 1;

    private static final String DEFAULT_DOCUMENTO_FI_F = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENTO_FI_F = "BBBBBBBBBB";

    private static final String DEFAULT_ZDMS_ID_F = "AAAAAAAAAA";
    private static final String UPDATED_ZDMS_ID_F = "BBBBBBBBBB";

    private static final String DEFAULT_C_PROVEEDOR_F = "AAAAAAAAAA";
    private static final String UPDATED_C_PROVEEDOR_F = "BBBBBBBBBB";

    private static final String DEFAULT_C_SOCIEDAD_F = "AAAAAAAAAA";
    private static final String UPDATED_C_SOCIEDAD_F = "BBBBBBBBBB";

    private static final String DEFAULT_IVA = "AAAAAAAAAA";
    private static final String UPDATED_IVA = "BBBBBBBBBB";

    private static final Double DEFAULT_SUM_IMPO_MONEDA = 1D;
    private static final Double UPDATED_SUM_IMPO_MONEDA = 2D;
    private static final Double SMALLER_SUM_IMPO_MONEDA = 1D - 1D;

    private static final Double DEFAULT_SUM_IMPO_IMPUESTO = 1D;
    private static final Double UPDATED_SUM_IMPO_IMPUESTO = 2D;
    private static final Double SMALLER_SUM_IMPO_IMPUESTO = 1D - 1D;

    private static final Double DEFAULT_SUM_IMPORTE_BASE = 1D;
    private static final Double UPDATED_SUM_IMPORTE_BASE = 2D;
    private static final Double SMALLER_SUM_IMPORTE_BASE = 1D - 1D;

    private static final String DEFAULT_C_MONEDA_I = "AAAAAAAAAA";
    private static final String UPDATED_C_MONEDA_I = "BBBBBBBBBB";

    @Autowired
    private ImpuestoViewRepository impuestoViewRepository;

    @Autowired
    private ImpuestoViewMapper impuestoViewMapper;

    @Autowired
    private ImpuestoViewService impuestoViewService;

    @Autowired
    private ImpuestoViewQueryService impuestoViewQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restImpuestoViewMockMvc;

    private ImpuestoView impuestoView;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ImpuestoViewResource impuestoViewResource = new ImpuestoViewResource(impuestoViewService, impuestoViewQueryService);
        this.restImpuestoViewMockMvc = MockMvcBuilders.standaloneSetup(impuestoViewResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ImpuestoView createEntity(EntityManager em) {
        ImpuestoView impuestoView = new ImpuestoView()
            .cFacturaF(DEFAULT_C_FACTURA_F)
            .cReferenciaF(DEFAULT_C_REFERENCIA_F)
            .ejercicioF(DEFAULT_EJERCICIO_F)
            .documentoFiF(DEFAULT_DOCUMENTO_FI_F)
            .zdmsIdF(DEFAULT_ZDMS_ID_F)
            .cProveedorF(DEFAULT_C_PROVEEDOR_F)
            .cSociedadF(DEFAULT_C_SOCIEDAD_F)
            .iva(DEFAULT_IVA)
            .sumImpoMoneda(DEFAULT_SUM_IMPO_MONEDA)
            .sumImpoImpuesto(DEFAULT_SUM_IMPO_IMPUESTO)
            .sumImporteBase(DEFAULT_SUM_IMPORTE_BASE)
            .cMonedaI(DEFAULT_C_MONEDA_I);
        return impuestoView;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ImpuestoView createUpdatedEntity(EntityManager em) {
        ImpuestoView impuestoView = new ImpuestoView()
            .cFacturaF(UPDATED_C_FACTURA_F)
            .cReferenciaF(UPDATED_C_REFERENCIA_F)
            .ejercicioF(UPDATED_EJERCICIO_F)
            .documentoFiF(UPDATED_DOCUMENTO_FI_F)
            .zdmsIdF(UPDATED_ZDMS_ID_F)
            .cProveedorF(UPDATED_C_PROVEEDOR_F)
            .cSociedadF(UPDATED_C_SOCIEDAD_F)
            .iva(UPDATED_IVA)
            .sumImpoMoneda(UPDATED_SUM_IMPO_MONEDA)
            .sumImpoImpuesto(UPDATED_SUM_IMPO_IMPUESTO)
            .sumImporteBase(UPDATED_SUM_IMPORTE_BASE)
            .cMonedaI(UPDATED_C_MONEDA_I);
        return impuestoView;
    }

    @BeforeEach
    public void initTest() {
        impuestoView = createEntity(em);
    }

    @Test
    @Transactional
    public void createImpuestoView() throws Exception {
        int databaseSizeBeforeCreate = impuestoViewRepository.findAll().size();

        // Create the ImpuestoView
        ImpuestoViewDTO impuestoViewDTO = impuestoViewMapper.toDto(impuestoView);
        restImpuestoViewMockMvc.perform(post("/api/impuesto-views")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(impuestoViewDTO)))
            .andExpect(status().isCreated());

        // Validate the ImpuestoView in the database
        List<ImpuestoView> impuestoViewList = impuestoViewRepository.findAll();
        assertThat(impuestoViewList).hasSize(databaseSizeBeforeCreate + 1);
        ImpuestoView testImpuestoView = impuestoViewList.get(impuestoViewList.size() - 1);
        assertThat(testImpuestoView.getcFacturaF()).isEqualTo(DEFAULT_C_FACTURA_F);
        assertThat(testImpuestoView.getcReferenciaF()).isEqualTo(DEFAULT_C_REFERENCIA_F);
        assertThat(testImpuestoView.getEjercicioF()).isEqualTo(DEFAULT_EJERCICIO_F);
        assertThat(testImpuestoView.getDocumentoFiF()).isEqualTo(DEFAULT_DOCUMENTO_FI_F);
        assertThat(testImpuestoView.getZdmsIdF()).isEqualTo(DEFAULT_ZDMS_ID_F);
        assertThat(testImpuestoView.getcProveedorF()).isEqualTo(DEFAULT_C_PROVEEDOR_F);
        assertThat(testImpuestoView.getcSociedadF()).isEqualTo(DEFAULT_C_SOCIEDAD_F);
        assertThat(testImpuestoView.getIva()).isEqualTo(DEFAULT_IVA);
        assertThat(testImpuestoView.getSumImpoMoneda()).isEqualTo(DEFAULT_SUM_IMPO_MONEDA);
        assertThat(testImpuestoView.getSumImpoImpuesto()).isEqualTo(DEFAULT_SUM_IMPO_IMPUESTO);
        assertThat(testImpuestoView.getSumImporteBase()).isEqualTo(DEFAULT_SUM_IMPORTE_BASE);
        assertThat(testImpuestoView.getcMonedaI()).isEqualTo(DEFAULT_C_MONEDA_I);
    }

    @Test
    @Transactional
    public void createImpuestoViewWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = impuestoViewRepository.findAll().size();

        // Create the ImpuestoView with an existing ID
        impuestoView.setId(1L);
        ImpuestoViewDTO impuestoViewDTO = impuestoViewMapper.toDto(impuestoView);

        // An entity with an existing ID cannot be created, so this API call must fail
        restImpuestoViewMockMvc.perform(post("/api/impuesto-views")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(impuestoViewDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ImpuestoView in the database
        List<ImpuestoView> impuestoViewList = impuestoViewRepository.findAll();
        assertThat(impuestoViewList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllImpuestoViews() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList
        restImpuestoViewMockMvc.perform(get("/api/impuesto-views?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(impuestoView.getId().intValue())))
            .andExpect(jsonPath("$.[*].cFacturaF").value(hasItem(DEFAULT_C_FACTURA_F)))
            .andExpect(jsonPath("$.[*].cReferenciaF").value(hasItem(DEFAULT_C_REFERENCIA_F)))
            .andExpect(jsonPath("$.[*].ejercicioF").value(hasItem(DEFAULT_EJERCICIO_F)))
            .andExpect(jsonPath("$.[*].documentoFiF").value(hasItem(DEFAULT_DOCUMENTO_FI_F)))
            .andExpect(jsonPath("$.[*].zdmsIdF").value(hasItem(DEFAULT_ZDMS_ID_F)))
            .andExpect(jsonPath("$.[*].cProveedorF").value(hasItem(DEFAULT_C_PROVEEDOR_F)))
            .andExpect(jsonPath("$.[*].cSociedadF").value(hasItem(DEFAULT_C_SOCIEDAD_F)))
            .andExpect(jsonPath("$.[*].iva").value(hasItem(DEFAULT_IVA)))
            .andExpect(jsonPath("$.[*].sumImpoMoneda").value(hasItem(DEFAULT_SUM_IMPO_MONEDA.doubleValue())))
            .andExpect(jsonPath("$.[*].sumImpoImpuesto").value(hasItem(DEFAULT_SUM_IMPO_IMPUESTO.doubleValue())))
            .andExpect(jsonPath("$.[*].sumImporteBase").value(hasItem(DEFAULT_SUM_IMPORTE_BASE.doubleValue())))
            .andExpect(jsonPath("$.[*].cMonedaI").value(hasItem(DEFAULT_C_MONEDA_I)));
    }
    
    @Test
    @Transactional
    public void getImpuestoView() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get the impuestoView
        restImpuestoViewMockMvc.perform(get("/api/impuesto-views/{id}", impuestoView.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(impuestoView.getId().intValue()))
            .andExpect(jsonPath("$.cFacturaF").value(DEFAULT_C_FACTURA_F))
            .andExpect(jsonPath("$.cReferenciaF").value(DEFAULT_C_REFERENCIA_F))
            .andExpect(jsonPath("$.ejercicioF").value(DEFAULT_EJERCICIO_F))
            .andExpect(jsonPath("$.documentoFiF").value(DEFAULT_DOCUMENTO_FI_F))
            .andExpect(jsonPath("$.zdmsIdF").value(DEFAULT_ZDMS_ID_F))
            .andExpect(jsonPath("$.cProveedorF").value(DEFAULT_C_PROVEEDOR_F))
            .andExpect(jsonPath("$.cSociedadF").value(DEFAULT_C_SOCIEDAD_F))
            .andExpect(jsonPath("$.iva").value(DEFAULT_IVA))
            .andExpect(jsonPath("$.sumImpoMoneda").value(DEFAULT_SUM_IMPO_MONEDA.doubleValue()))
            .andExpect(jsonPath("$.sumImpoImpuesto").value(DEFAULT_SUM_IMPO_IMPUESTO.doubleValue()))
            .andExpect(jsonPath("$.sumImporteBase").value(DEFAULT_SUM_IMPORTE_BASE.doubleValue()))
            .andExpect(jsonPath("$.cMonedaI").value(DEFAULT_C_MONEDA_I));
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBycFacturaFIsEqualToSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where cFacturaF equals to DEFAULT_C_FACTURA_F
        defaultImpuestoViewShouldBeFound("cFacturaF.equals=" + DEFAULT_C_FACTURA_F);

        // Get all the impuestoViewList where cFacturaF equals to UPDATED_C_FACTURA_F
        defaultImpuestoViewShouldNotBeFound("cFacturaF.equals=" + UPDATED_C_FACTURA_F);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBycFacturaFIsNotEqualToSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where cFacturaF not equals to DEFAULT_C_FACTURA_F
        defaultImpuestoViewShouldNotBeFound("cFacturaF.notEquals=" + DEFAULT_C_FACTURA_F);

        // Get all the impuestoViewList where cFacturaF not equals to UPDATED_C_FACTURA_F
        defaultImpuestoViewShouldBeFound("cFacturaF.notEquals=" + UPDATED_C_FACTURA_F);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBycFacturaFIsInShouldWork() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where cFacturaF in DEFAULT_C_FACTURA_F or UPDATED_C_FACTURA_F
        defaultImpuestoViewShouldBeFound("cFacturaF.in=" + DEFAULT_C_FACTURA_F + "," + UPDATED_C_FACTURA_F);

        // Get all the impuestoViewList where cFacturaF equals to UPDATED_C_FACTURA_F
        defaultImpuestoViewShouldNotBeFound("cFacturaF.in=" + UPDATED_C_FACTURA_F);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBycFacturaFIsNullOrNotNull() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where cFacturaF is not null
        defaultImpuestoViewShouldBeFound("cFacturaF.specified=true");

        // Get all the impuestoViewList where cFacturaF is null
        defaultImpuestoViewShouldNotBeFound("cFacturaF.specified=false");
    }
                @Test
    @Transactional
    public void getAllImpuestoViewsBycFacturaFContainsSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where cFacturaF contains DEFAULT_C_FACTURA_F
        defaultImpuestoViewShouldBeFound("cFacturaF.contains=" + DEFAULT_C_FACTURA_F);

        // Get all the impuestoViewList where cFacturaF contains UPDATED_C_FACTURA_F
        defaultImpuestoViewShouldNotBeFound("cFacturaF.contains=" + UPDATED_C_FACTURA_F);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBycFacturaFNotContainsSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where cFacturaF does not contain DEFAULT_C_FACTURA_F
        defaultImpuestoViewShouldNotBeFound("cFacturaF.doesNotContain=" + DEFAULT_C_FACTURA_F);

        // Get all the impuestoViewList where cFacturaF does not contain UPDATED_C_FACTURA_F
        defaultImpuestoViewShouldBeFound("cFacturaF.doesNotContain=" + UPDATED_C_FACTURA_F);
    }


    @Test
    @Transactional
    public void getAllImpuestoViewsBycReferenciaFIsEqualToSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where cReferenciaF equals to DEFAULT_C_REFERENCIA_F
        defaultImpuestoViewShouldBeFound("cReferenciaF.equals=" + DEFAULT_C_REFERENCIA_F);

        // Get all the impuestoViewList where cReferenciaF equals to UPDATED_C_REFERENCIA_F
        defaultImpuestoViewShouldNotBeFound("cReferenciaF.equals=" + UPDATED_C_REFERENCIA_F);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBycReferenciaFIsNotEqualToSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where cReferenciaF not equals to DEFAULT_C_REFERENCIA_F
        defaultImpuestoViewShouldNotBeFound("cReferenciaF.notEquals=" + DEFAULT_C_REFERENCIA_F);

        // Get all the impuestoViewList where cReferenciaF not equals to UPDATED_C_REFERENCIA_F
        defaultImpuestoViewShouldBeFound("cReferenciaF.notEquals=" + UPDATED_C_REFERENCIA_F);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBycReferenciaFIsInShouldWork() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where cReferenciaF in DEFAULT_C_REFERENCIA_F or UPDATED_C_REFERENCIA_F
        defaultImpuestoViewShouldBeFound("cReferenciaF.in=" + DEFAULT_C_REFERENCIA_F + "," + UPDATED_C_REFERENCIA_F);

        // Get all the impuestoViewList where cReferenciaF equals to UPDATED_C_REFERENCIA_F
        defaultImpuestoViewShouldNotBeFound("cReferenciaF.in=" + UPDATED_C_REFERENCIA_F);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBycReferenciaFIsNullOrNotNull() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where cReferenciaF is not null
        defaultImpuestoViewShouldBeFound("cReferenciaF.specified=true");

        // Get all the impuestoViewList where cReferenciaF is null
        defaultImpuestoViewShouldNotBeFound("cReferenciaF.specified=false");
    }
                @Test
    @Transactional
    public void getAllImpuestoViewsBycReferenciaFContainsSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where cReferenciaF contains DEFAULT_C_REFERENCIA_F
        defaultImpuestoViewShouldBeFound("cReferenciaF.contains=" + DEFAULT_C_REFERENCIA_F);

        // Get all the impuestoViewList where cReferenciaF contains UPDATED_C_REFERENCIA_F
        defaultImpuestoViewShouldNotBeFound("cReferenciaF.contains=" + UPDATED_C_REFERENCIA_F);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBycReferenciaFNotContainsSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where cReferenciaF does not contain DEFAULT_C_REFERENCIA_F
        defaultImpuestoViewShouldNotBeFound("cReferenciaF.doesNotContain=" + DEFAULT_C_REFERENCIA_F);

        // Get all the impuestoViewList where cReferenciaF does not contain UPDATED_C_REFERENCIA_F
        defaultImpuestoViewShouldBeFound("cReferenciaF.doesNotContain=" + UPDATED_C_REFERENCIA_F);
    }


    @Test
    @Transactional
    public void getAllImpuestoViewsByEjercicioFIsEqualToSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where ejercicioF equals to DEFAULT_EJERCICIO_F
        defaultImpuestoViewShouldBeFound("ejercicioF.equals=" + DEFAULT_EJERCICIO_F);

        // Get all the impuestoViewList where ejercicioF equals to UPDATED_EJERCICIO_F
        defaultImpuestoViewShouldNotBeFound("ejercicioF.equals=" + UPDATED_EJERCICIO_F);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsByEjercicioFIsNotEqualToSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where ejercicioF not equals to DEFAULT_EJERCICIO_F
        defaultImpuestoViewShouldNotBeFound("ejercicioF.notEquals=" + DEFAULT_EJERCICIO_F);

        // Get all the impuestoViewList where ejercicioF not equals to UPDATED_EJERCICIO_F
        defaultImpuestoViewShouldBeFound("ejercicioF.notEquals=" + UPDATED_EJERCICIO_F);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsByEjercicioFIsInShouldWork() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where ejercicioF in DEFAULT_EJERCICIO_F or UPDATED_EJERCICIO_F
        defaultImpuestoViewShouldBeFound("ejercicioF.in=" + DEFAULT_EJERCICIO_F + "," + UPDATED_EJERCICIO_F);

        // Get all the impuestoViewList where ejercicioF equals to UPDATED_EJERCICIO_F
        defaultImpuestoViewShouldNotBeFound("ejercicioF.in=" + UPDATED_EJERCICIO_F);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsByEjercicioFIsNullOrNotNull() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where ejercicioF is not null
        defaultImpuestoViewShouldBeFound("ejercicioF.specified=true");

        // Get all the impuestoViewList where ejercicioF is null
        defaultImpuestoViewShouldNotBeFound("ejercicioF.specified=false");
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsByEjercicioFIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where ejercicioF is greater than or equal to DEFAULT_EJERCICIO_F
        defaultImpuestoViewShouldBeFound("ejercicioF.greaterThanOrEqual=" + DEFAULT_EJERCICIO_F);

        // Get all the impuestoViewList where ejercicioF is greater than or equal to UPDATED_EJERCICIO_F
        defaultImpuestoViewShouldNotBeFound("ejercicioF.greaterThanOrEqual=" + UPDATED_EJERCICIO_F);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsByEjercicioFIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where ejercicioF is less than or equal to DEFAULT_EJERCICIO_F
        defaultImpuestoViewShouldBeFound("ejercicioF.lessThanOrEqual=" + DEFAULT_EJERCICIO_F);

        // Get all the impuestoViewList where ejercicioF is less than or equal to SMALLER_EJERCICIO_F
        defaultImpuestoViewShouldNotBeFound("ejercicioF.lessThanOrEqual=" + SMALLER_EJERCICIO_F);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsByEjercicioFIsLessThanSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where ejercicioF is less than DEFAULT_EJERCICIO_F
        defaultImpuestoViewShouldNotBeFound("ejercicioF.lessThan=" + DEFAULT_EJERCICIO_F);

        // Get all the impuestoViewList where ejercicioF is less than UPDATED_EJERCICIO_F
        defaultImpuestoViewShouldBeFound("ejercicioF.lessThan=" + UPDATED_EJERCICIO_F);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsByEjercicioFIsGreaterThanSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where ejercicioF is greater than DEFAULT_EJERCICIO_F
        defaultImpuestoViewShouldNotBeFound("ejercicioF.greaterThan=" + DEFAULT_EJERCICIO_F);

        // Get all the impuestoViewList where ejercicioF is greater than SMALLER_EJERCICIO_F
        defaultImpuestoViewShouldBeFound("ejercicioF.greaterThan=" + SMALLER_EJERCICIO_F);
    }


    @Test
    @Transactional
    public void getAllImpuestoViewsByDocumentoFiFIsEqualToSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where documentoFiF equals to DEFAULT_DOCUMENTO_FI_F
        defaultImpuestoViewShouldBeFound("documentoFiF.equals=" + DEFAULT_DOCUMENTO_FI_F);

        // Get all the impuestoViewList where documentoFiF equals to UPDATED_DOCUMENTO_FI_F
        defaultImpuestoViewShouldNotBeFound("documentoFiF.equals=" + UPDATED_DOCUMENTO_FI_F);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsByDocumentoFiFIsNotEqualToSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where documentoFiF not equals to DEFAULT_DOCUMENTO_FI_F
        defaultImpuestoViewShouldNotBeFound("documentoFiF.notEquals=" + DEFAULT_DOCUMENTO_FI_F);

        // Get all the impuestoViewList where documentoFiF not equals to UPDATED_DOCUMENTO_FI_F
        defaultImpuestoViewShouldBeFound("documentoFiF.notEquals=" + UPDATED_DOCUMENTO_FI_F);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsByDocumentoFiFIsInShouldWork() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where documentoFiF in DEFAULT_DOCUMENTO_FI_F or UPDATED_DOCUMENTO_FI_F
        defaultImpuestoViewShouldBeFound("documentoFiF.in=" + DEFAULT_DOCUMENTO_FI_F + "," + UPDATED_DOCUMENTO_FI_F);

        // Get all the impuestoViewList where documentoFiF equals to UPDATED_DOCUMENTO_FI_F
        defaultImpuestoViewShouldNotBeFound("documentoFiF.in=" + UPDATED_DOCUMENTO_FI_F);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsByDocumentoFiFIsNullOrNotNull() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where documentoFiF is not null
        defaultImpuestoViewShouldBeFound("documentoFiF.specified=true");

        // Get all the impuestoViewList where documentoFiF is null
        defaultImpuestoViewShouldNotBeFound("documentoFiF.specified=false");
    }
                @Test
    @Transactional
    public void getAllImpuestoViewsByDocumentoFiFContainsSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where documentoFiF contains DEFAULT_DOCUMENTO_FI_F
        defaultImpuestoViewShouldBeFound("documentoFiF.contains=" + DEFAULT_DOCUMENTO_FI_F);

        // Get all the impuestoViewList where documentoFiF contains UPDATED_DOCUMENTO_FI_F
        defaultImpuestoViewShouldNotBeFound("documentoFiF.contains=" + UPDATED_DOCUMENTO_FI_F);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsByDocumentoFiFNotContainsSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where documentoFiF does not contain DEFAULT_DOCUMENTO_FI_F
        defaultImpuestoViewShouldNotBeFound("documentoFiF.doesNotContain=" + DEFAULT_DOCUMENTO_FI_F);

        // Get all the impuestoViewList where documentoFiF does not contain UPDATED_DOCUMENTO_FI_F
        defaultImpuestoViewShouldBeFound("documentoFiF.doesNotContain=" + UPDATED_DOCUMENTO_FI_F);
    }


    @Test
    @Transactional
    public void getAllImpuestoViewsByZdmsIdFIsEqualToSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where zdmsIdF equals to DEFAULT_ZDMS_ID_F
        defaultImpuestoViewShouldBeFound("zdmsIdF.equals=" + DEFAULT_ZDMS_ID_F);

        // Get all the impuestoViewList where zdmsIdF equals to UPDATED_ZDMS_ID_F
        defaultImpuestoViewShouldNotBeFound("zdmsIdF.equals=" + UPDATED_ZDMS_ID_F);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsByZdmsIdFIsNotEqualToSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where zdmsIdF not equals to DEFAULT_ZDMS_ID_F
        defaultImpuestoViewShouldNotBeFound("zdmsIdF.notEquals=" + DEFAULT_ZDMS_ID_F);

        // Get all the impuestoViewList where zdmsIdF not equals to UPDATED_ZDMS_ID_F
        defaultImpuestoViewShouldBeFound("zdmsIdF.notEquals=" + UPDATED_ZDMS_ID_F);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsByZdmsIdFIsInShouldWork() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where zdmsIdF in DEFAULT_ZDMS_ID_F or UPDATED_ZDMS_ID_F
        defaultImpuestoViewShouldBeFound("zdmsIdF.in=" + DEFAULT_ZDMS_ID_F + "," + UPDATED_ZDMS_ID_F);

        // Get all the impuestoViewList where zdmsIdF equals to UPDATED_ZDMS_ID_F
        defaultImpuestoViewShouldNotBeFound("zdmsIdF.in=" + UPDATED_ZDMS_ID_F);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsByZdmsIdFIsNullOrNotNull() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where zdmsIdF is not null
        defaultImpuestoViewShouldBeFound("zdmsIdF.specified=true");

        // Get all the impuestoViewList where zdmsIdF is null
        defaultImpuestoViewShouldNotBeFound("zdmsIdF.specified=false");
    }
                @Test
    @Transactional
    public void getAllImpuestoViewsByZdmsIdFContainsSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where zdmsIdF contains DEFAULT_ZDMS_ID_F
        defaultImpuestoViewShouldBeFound("zdmsIdF.contains=" + DEFAULT_ZDMS_ID_F);

        // Get all the impuestoViewList where zdmsIdF contains UPDATED_ZDMS_ID_F
        defaultImpuestoViewShouldNotBeFound("zdmsIdF.contains=" + UPDATED_ZDMS_ID_F);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsByZdmsIdFNotContainsSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where zdmsIdF does not contain DEFAULT_ZDMS_ID_F
        defaultImpuestoViewShouldNotBeFound("zdmsIdF.doesNotContain=" + DEFAULT_ZDMS_ID_F);

        // Get all the impuestoViewList where zdmsIdF does not contain UPDATED_ZDMS_ID_F
        defaultImpuestoViewShouldBeFound("zdmsIdF.doesNotContain=" + UPDATED_ZDMS_ID_F);
    }


    @Test
    @Transactional
    public void getAllImpuestoViewsBycProveedorFIsEqualToSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where cProveedorF equals to DEFAULT_C_PROVEEDOR_F
        defaultImpuestoViewShouldBeFound("cProveedorF.equals=" + DEFAULT_C_PROVEEDOR_F);

        // Get all the impuestoViewList where cProveedorF equals to UPDATED_C_PROVEEDOR_F
        defaultImpuestoViewShouldNotBeFound("cProveedorF.equals=" + UPDATED_C_PROVEEDOR_F);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBycProveedorFIsNotEqualToSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where cProveedorF not equals to DEFAULT_C_PROVEEDOR_F
        defaultImpuestoViewShouldNotBeFound("cProveedorF.notEquals=" + DEFAULT_C_PROVEEDOR_F);

        // Get all the impuestoViewList where cProveedorF not equals to UPDATED_C_PROVEEDOR_F
        defaultImpuestoViewShouldBeFound("cProveedorF.notEquals=" + UPDATED_C_PROVEEDOR_F);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBycProveedorFIsInShouldWork() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where cProveedorF in DEFAULT_C_PROVEEDOR_F or UPDATED_C_PROVEEDOR_F
        defaultImpuestoViewShouldBeFound("cProveedorF.in=" + DEFAULT_C_PROVEEDOR_F + "," + UPDATED_C_PROVEEDOR_F);

        // Get all the impuestoViewList where cProveedorF equals to UPDATED_C_PROVEEDOR_F
        defaultImpuestoViewShouldNotBeFound("cProveedorF.in=" + UPDATED_C_PROVEEDOR_F);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBycProveedorFIsNullOrNotNull() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where cProveedorF is not null
        defaultImpuestoViewShouldBeFound("cProveedorF.specified=true");

        // Get all the impuestoViewList where cProveedorF is null
        defaultImpuestoViewShouldNotBeFound("cProveedorF.specified=false");
    }
                @Test
    @Transactional
    public void getAllImpuestoViewsBycProveedorFContainsSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where cProveedorF contains DEFAULT_C_PROVEEDOR_F
        defaultImpuestoViewShouldBeFound("cProveedorF.contains=" + DEFAULT_C_PROVEEDOR_F);

        // Get all the impuestoViewList where cProveedorF contains UPDATED_C_PROVEEDOR_F
        defaultImpuestoViewShouldNotBeFound("cProveedorF.contains=" + UPDATED_C_PROVEEDOR_F);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBycProveedorFNotContainsSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where cProveedorF does not contain DEFAULT_C_PROVEEDOR_F
        defaultImpuestoViewShouldNotBeFound("cProveedorF.doesNotContain=" + DEFAULT_C_PROVEEDOR_F);

        // Get all the impuestoViewList where cProveedorF does not contain UPDATED_C_PROVEEDOR_F
        defaultImpuestoViewShouldBeFound("cProveedorF.doesNotContain=" + UPDATED_C_PROVEEDOR_F);
    }


    @Test
    @Transactional
    public void getAllImpuestoViewsBycSociedadFIsEqualToSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where cSociedadF equals to DEFAULT_C_SOCIEDAD_F
        defaultImpuestoViewShouldBeFound("cSociedadF.equals=" + DEFAULT_C_SOCIEDAD_F);

        // Get all the impuestoViewList where cSociedadF equals to UPDATED_C_SOCIEDAD_F
        defaultImpuestoViewShouldNotBeFound("cSociedadF.equals=" + UPDATED_C_SOCIEDAD_F);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBycSociedadFIsNotEqualToSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where cSociedadF not equals to DEFAULT_C_SOCIEDAD_F
        defaultImpuestoViewShouldNotBeFound("cSociedadF.notEquals=" + DEFAULT_C_SOCIEDAD_F);

        // Get all the impuestoViewList where cSociedadF not equals to UPDATED_C_SOCIEDAD_F
        defaultImpuestoViewShouldBeFound("cSociedadF.notEquals=" + UPDATED_C_SOCIEDAD_F);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBycSociedadFIsInShouldWork() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where cSociedadF in DEFAULT_C_SOCIEDAD_F or UPDATED_C_SOCIEDAD_F
        defaultImpuestoViewShouldBeFound("cSociedadF.in=" + DEFAULT_C_SOCIEDAD_F + "," + UPDATED_C_SOCIEDAD_F);

        // Get all the impuestoViewList where cSociedadF equals to UPDATED_C_SOCIEDAD_F
        defaultImpuestoViewShouldNotBeFound("cSociedadF.in=" + UPDATED_C_SOCIEDAD_F);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBycSociedadFIsNullOrNotNull() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where cSociedadF is not null
        defaultImpuestoViewShouldBeFound("cSociedadF.specified=true");

        // Get all the impuestoViewList where cSociedadF is null
        defaultImpuestoViewShouldNotBeFound("cSociedadF.specified=false");
    }
                @Test
    @Transactional
    public void getAllImpuestoViewsBycSociedadFContainsSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where cSociedadF contains DEFAULT_C_SOCIEDAD_F
        defaultImpuestoViewShouldBeFound("cSociedadF.contains=" + DEFAULT_C_SOCIEDAD_F);

        // Get all the impuestoViewList where cSociedadF contains UPDATED_C_SOCIEDAD_F
        defaultImpuestoViewShouldNotBeFound("cSociedadF.contains=" + UPDATED_C_SOCIEDAD_F);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBycSociedadFNotContainsSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where cSociedadF does not contain DEFAULT_C_SOCIEDAD_F
        defaultImpuestoViewShouldNotBeFound("cSociedadF.doesNotContain=" + DEFAULT_C_SOCIEDAD_F);

        // Get all the impuestoViewList where cSociedadF does not contain UPDATED_C_SOCIEDAD_F
        defaultImpuestoViewShouldBeFound("cSociedadF.doesNotContain=" + UPDATED_C_SOCIEDAD_F);
    }


    @Test
    @Transactional
    public void getAllImpuestoViewsByIvaIsEqualToSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where iva equals to DEFAULT_IVA
        defaultImpuestoViewShouldBeFound("iva.equals=" + DEFAULT_IVA);

        // Get all the impuestoViewList where iva equals to UPDATED_IVA
        defaultImpuestoViewShouldNotBeFound("iva.equals=" + UPDATED_IVA);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsByIvaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where iva not equals to DEFAULT_IVA
        defaultImpuestoViewShouldNotBeFound("iva.notEquals=" + DEFAULT_IVA);

        // Get all the impuestoViewList where iva not equals to UPDATED_IVA
        defaultImpuestoViewShouldBeFound("iva.notEquals=" + UPDATED_IVA);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsByIvaIsInShouldWork() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where iva in DEFAULT_IVA or UPDATED_IVA
        defaultImpuestoViewShouldBeFound("iva.in=" + DEFAULT_IVA + "," + UPDATED_IVA);

        // Get all the impuestoViewList where iva equals to UPDATED_IVA
        defaultImpuestoViewShouldNotBeFound("iva.in=" + UPDATED_IVA);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsByIvaIsNullOrNotNull() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where iva is not null
        defaultImpuestoViewShouldBeFound("iva.specified=true");

        // Get all the impuestoViewList where iva is null
        defaultImpuestoViewShouldNotBeFound("iva.specified=false");
    }
                @Test
    @Transactional
    public void getAllImpuestoViewsByIvaContainsSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where iva contains DEFAULT_IVA
        defaultImpuestoViewShouldBeFound("iva.contains=" + DEFAULT_IVA);

        // Get all the impuestoViewList where iva contains UPDATED_IVA
        defaultImpuestoViewShouldNotBeFound("iva.contains=" + UPDATED_IVA);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsByIvaNotContainsSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where iva does not contain DEFAULT_IVA
        defaultImpuestoViewShouldNotBeFound("iva.doesNotContain=" + DEFAULT_IVA);

        // Get all the impuestoViewList where iva does not contain UPDATED_IVA
        defaultImpuestoViewShouldBeFound("iva.doesNotContain=" + UPDATED_IVA);
    }


    @Test
    @Transactional
    public void getAllImpuestoViewsBySumImpoMonedaIsEqualToSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where sumImpoMoneda equals to DEFAULT_SUM_IMPO_MONEDA
        defaultImpuestoViewShouldBeFound("sumImpoMoneda.equals=" + DEFAULT_SUM_IMPO_MONEDA);

        // Get all the impuestoViewList where sumImpoMoneda equals to UPDATED_SUM_IMPO_MONEDA
        defaultImpuestoViewShouldNotBeFound("sumImpoMoneda.equals=" + UPDATED_SUM_IMPO_MONEDA);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBySumImpoMonedaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where sumImpoMoneda not equals to DEFAULT_SUM_IMPO_MONEDA
        defaultImpuestoViewShouldNotBeFound("sumImpoMoneda.notEquals=" + DEFAULT_SUM_IMPO_MONEDA);

        // Get all the impuestoViewList where sumImpoMoneda not equals to UPDATED_SUM_IMPO_MONEDA
        defaultImpuestoViewShouldBeFound("sumImpoMoneda.notEquals=" + UPDATED_SUM_IMPO_MONEDA);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBySumImpoMonedaIsInShouldWork() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where sumImpoMoneda in DEFAULT_SUM_IMPO_MONEDA or UPDATED_SUM_IMPO_MONEDA
        defaultImpuestoViewShouldBeFound("sumImpoMoneda.in=" + DEFAULT_SUM_IMPO_MONEDA + "," + UPDATED_SUM_IMPO_MONEDA);

        // Get all the impuestoViewList where sumImpoMoneda equals to UPDATED_SUM_IMPO_MONEDA
        defaultImpuestoViewShouldNotBeFound("sumImpoMoneda.in=" + UPDATED_SUM_IMPO_MONEDA);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBySumImpoMonedaIsNullOrNotNull() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where sumImpoMoneda is not null
        defaultImpuestoViewShouldBeFound("sumImpoMoneda.specified=true");

        // Get all the impuestoViewList where sumImpoMoneda is null
        defaultImpuestoViewShouldNotBeFound("sumImpoMoneda.specified=false");
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBySumImpoMonedaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where sumImpoMoneda is greater than or equal to DEFAULT_SUM_IMPO_MONEDA
        defaultImpuestoViewShouldBeFound("sumImpoMoneda.greaterThanOrEqual=" + DEFAULT_SUM_IMPO_MONEDA);

        // Get all the impuestoViewList where sumImpoMoneda is greater than or equal to UPDATED_SUM_IMPO_MONEDA
        defaultImpuestoViewShouldNotBeFound("sumImpoMoneda.greaterThanOrEqual=" + UPDATED_SUM_IMPO_MONEDA);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBySumImpoMonedaIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where sumImpoMoneda is less than or equal to DEFAULT_SUM_IMPO_MONEDA
        defaultImpuestoViewShouldBeFound("sumImpoMoneda.lessThanOrEqual=" + DEFAULT_SUM_IMPO_MONEDA);

        // Get all the impuestoViewList where sumImpoMoneda is less than or equal to SMALLER_SUM_IMPO_MONEDA
        defaultImpuestoViewShouldNotBeFound("sumImpoMoneda.lessThanOrEqual=" + SMALLER_SUM_IMPO_MONEDA);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBySumImpoMonedaIsLessThanSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where sumImpoMoneda is less than DEFAULT_SUM_IMPO_MONEDA
        defaultImpuestoViewShouldNotBeFound("sumImpoMoneda.lessThan=" + DEFAULT_SUM_IMPO_MONEDA);

        // Get all the impuestoViewList where sumImpoMoneda is less than UPDATED_SUM_IMPO_MONEDA
        defaultImpuestoViewShouldBeFound("sumImpoMoneda.lessThan=" + UPDATED_SUM_IMPO_MONEDA);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBySumImpoMonedaIsGreaterThanSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where sumImpoMoneda is greater than DEFAULT_SUM_IMPO_MONEDA
        defaultImpuestoViewShouldNotBeFound("sumImpoMoneda.greaterThan=" + DEFAULT_SUM_IMPO_MONEDA);

        // Get all the impuestoViewList where sumImpoMoneda is greater than SMALLER_SUM_IMPO_MONEDA
        defaultImpuestoViewShouldBeFound("sumImpoMoneda.greaterThan=" + SMALLER_SUM_IMPO_MONEDA);
    }


    @Test
    @Transactional
    public void getAllImpuestoViewsBySumImpoImpuestoIsEqualToSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where sumImpoImpuesto equals to DEFAULT_SUM_IMPO_IMPUESTO
        defaultImpuestoViewShouldBeFound("sumImpoImpuesto.equals=" + DEFAULT_SUM_IMPO_IMPUESTO);

        // Get all the impuestoViewList where sumImpoImpuesto equals to UPDATED_SUM_IMPO_IMPUESTO
        defaultImpuestoViewShouldNotBeFound("sumImpoImpuesto.equals=" + UPDATED_SUM_IMPO_IMPUESTO);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBySumImpoImpuestoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where sumImpoImpuesto not equals to DEFAULT_SUM_IMPO_IMPUESTO
        defaultImpuestoViewShouldNotBeFound("sumImpoImpuesto.notEquals=" + DEFAULT_SUM_IMPO_IMPUESTO);

        // Get all the impuestoViewList where sumImpoImpuesto not equals to UPDATED_SUM_IMPO_IMPUESTO
        defaultImpuestoViewShouldBeFound("sumImpoImpuesto.notEquals=" + UPDATED_SUM_IMPO_IMPUESTO);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBySumImpoImpuestoIsInShouldWork() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where sumImpoImpuesto in DEFAULT_SUM_IMPO_IMPUESTO or UPDATED_SUM_IMPO_IMPUESTO
        defaultImpuestoViewShouldBeFound("sumImpoImpuesto.in=" + DEFAULT_SUM_IMPO_IMPUESTO + "," + UPDATED_SUM_IMPO_IMPUESTO);

        // Get all the impuestoViewList where sumImpoImpuesto equals to UPDATED_SUM_IMPO_IMPUESTO
        defaultImpuestoViewShouldNotBeFound("sumImpoImpuesto.in=" + UPDATED_SUM_IMPO_IMPUESTO);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBySumImpoImpuestoIsNullOrNotNull() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where sumImpoImpuesto is not null
        defaultImpuestoViewShouldBeFound("sumImpoImpuesto.specified=true");

        // Get all the impuestoViewList where sumImpoImpuesto is null
        defaultImpuestoViewShouldNotBeFound("sumImpoImpuesto.specified=false");
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBySumImpoImpuestoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where sumImpoImpuesto is greater than or equal to DEFAULT_SUM_IMPO_IMPUESTO
        defaultImpuestoViewShouldBeFound("sumImpoImpuesto.greaterThanOrEqual=" + DEFAULT_SUM_IMPO_IMPUESTO);

        // Get all the impuestoViewList where sumImpoImpuesto is greater than or equal to UPDATED_SUM_IMPO_IMPUESTO
        defaultImpuestoViewShouldNotBeFound("sumImpoImpuesto.greaterThanOrEqual=" + UPDATED_SUM_IMPO_IMPUESTO);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBySumImpoImpuestoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where sumImpoImpuesto is less than or equal to DEFAULT_SUM_IMPO_IMPUESTO
        defaultImpuestoViewShouldBeFound("sumImpoImpuesto.lessThanOrEqual=" + DEFAULT_SUM_IMPO_IMPUESTO);

        // Get all the impuestoViewList where sumImpoImpuesto is less than or equal to SMALLER_SUM_IMPO_IMPUESTO
        defaultImpuestoViewShouldNotBeFound("sumImpoImpuesto.lessThanOrEqual=" + SMALLER_SUM_IMPO_IMPUESTO);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBySumImpoImpuestoIsLessThanSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where sumImpoImpuesto is less than DEFAULT_SUM_IMPO_IMPUESTO
        defaultImpuestoViewShouldNotBeFound("sumImpoImpuesto.lessThan=" + DEFAULT_SUM_IMPO_IMPUESTO);

        // Get all the impuestoViewList where sumImpoImpuesto is less than UPDATED_SUM_IMPO_IMPUESTO
        defaultImpuestoViewShouldBeFound("sumImpoImpuesto.lessThan=" + UPDATED_SUM_IMPO_IMPUESTO);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBySumImpoImpuestoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where sumImpoImpuesto is greater than DEFAULT_SUM_IMPO_IMPUESTO
        defaultImpuestoViewShouldNotBeFound("sumImpoImpuesto.greaterThan=" + DEFAULT_SUM_IMPO_IMPUESTO);

        // Get all the impuestoViewList where sumImpoImpuesto is greater than SMALLER_SUM_IMPO_IMPUESTO
        defaultImpuestoViewShouldBeFound("sumImpoImpuesto.greaterThan=" + SMALLER_SUM_IMPO_IMPUESTO);
    }


    @Test
    @Transactional
    public void getAllImpuestoViewsBySumImporteBaseIsEqualToSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where sumImporteBase equals to DEFAULT_SUM_IMPORTE_BASE
        defaultImpuestoViewShouldBeFound("sumImporteBase.equals=" + DEFAULT_SUM_IMPORTE_BASE);

        // Get all the impuestoViewList where sumImporteBase equals to UPDATED_SUM_IMPORTE_BASE
        defaultImpuestoViewShouldNotBeFound("sumImporteBase.equals=" + UPDATED_SUM_IMPORTE_BASE);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBySumImporteBaseIsNotEqualToSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where sumImporteBase not equals to DEFAULT_SUM_IMPORTE_BASE
        defaultImpuestoViewShouldNotBeFound("sumImporteBase.notEquals=" + DEFAULT_SUM_IMPORTE_BASE);

        // Get all the impuestoViewList where sumImporteBase not equals to UPDATED_SUM_IMPORTE_BASE
        defaultImpuestoViewShouldBeFound("sumImporteBase.notEquals=" + UPDATED_SUM_IMPORTE_BASE);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBySumImporteBaseIsInShouldWork() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where sumImporteBase in DEFAULT_SUM_IMPORTE_BASE or UPDATED_SUM_IMPORTE_BASE
        defaultImpuestoViewShouldBeFound("sumImporteBase.in=" + DEFAULT_SUM_IMPORTE_BASE + "," + UPDATED_SUM_IMPORTE_BASE);

        // Get all the impuestoViewList where sumImporteBase equals to UPDATED_SUM_IMPORTE_BASE
        defaultImpuestoViewShouldNotBeFound("sumImporteBase.in=" + UPDATED_SUM_IMPORTE_BASE);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBySumImporteBaseIsNullOrNotNull() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where sumImporteBase is not null
        defaultImpuestoViewShouldBeFound("sumImporteBase.specified=true");

        // Get all the impuestoViewList where sumImporteBase is null
        defaultImpuestoViewShouldNotBeFound("sumImporteBase.specified=false");
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBySumImporteBaseIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where sumImporteBase is greater than or equal to DEFAULT_SUM_IMPORTE_BASE
        defaultImpuestoViewShouldBeFound("sumImporteBase.greaterThanOrEqual=" + DEFAULT_SUM_IMPORTE_BASE);

        // Get all the impuestoViewList where sumImporteBase is greater than or equal to UPDATED_SUM_IMPORTE_BASE
        defaultImpuestoViewShouldNotBeFound("sumImporteBase.greaterThanOrEqual=" + UPDATED_SUM_IMPORTE_BASE);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBySumImporteBaseIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where sumImporteBase is less than or equal to DEFAULT_SUM_IMPORTE_BASE
        defaultImpuestoViewShouldBeFound("sumImporteBase.lessThanOrEqual=" + DEFAULT_SUM_IMPORTE_BASE);

        // Get all the impuestoViewList where sumImporteBase is less than or equal to SMALLER_SUM_IMPORTE_BASE
        defaultImpuestoViewShouldNotBeFound("sumImporteBase.lessThanOrEqual=" + SMALLER_SUM_IMPORTE_BASE);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBySumImporteBaseIsLessThanSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where sumImporteBase is less than DEFAULT_SUM_IMPORTE_BASE
        defaultImpuestoViewShouldNotBeFound("sumImporteBase.lessThan=" + DEFAULT_SUM_IMPORTE_BASE);

        // Get all the impuestoViewList where sumImporteBase is less than UPDATED_SUM_IMPORTE_BASE
        defaultImpuestoViewShouldBeFound("sumImporteBase.lessThan=" + UPDATED_SUM_IMPORTE_BASE);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBySumImporteBaseIsGreaterThanSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where sumImporteBase is greater than DEFAULT_SUM_IMPORTE_BASE
        defaultImpuestoViewShouldNotBeFound("sumImporteBase.greaterThan=" + DEFAULT_SUM_IMPORTE_BASE);

        // Get all the impuestoViewList where sumImporteBase is greater than SMALLER_SUM_IMPORTE_BASE
        defaultImpuestoViewShouldBeFound("sumImporteBase.greaterThan=" + SMALLER_SUM_IMPORTE_BASE);
    }


    @Test
    @Transactional
    public void getAllImpuestoViewsBycMonedaIIsEqualToSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where cMonedaI equals to DEFAULT_C_MONEDA_I
        defaultImpuestoViewShouldBeFound("cMonedaI.equals=" + DEFAULT_C_MONEDA_I);

        // Get all the impuestoViewList where cMonedaI equals to UPDATED_C_MONEDA_I
        defaultImpuestoViewShouldNotBeFound("cMonedaI.equals=" + UPDATED_C_MONEDA_I);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBycMonedaIIsNotEqualToSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where cMonedaI not equals to DEFAULT_C_MONEDA_I
        defaultImpuestoViewShouldNotBeFound("cMonedaI.notEquals=" + DEFAULT_C_MONEDA_I);

        // Get all the impuestoViewList where cMonedaI not equals to UPDATED_C_MONEDA_I
        defaultImpuestoViewShouldBeFound("cMonedaI.notEquals=" + UPDATED_C_MONEDA_I);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBycMonedaIIsInShouldWork() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where cMonedaI in DEFAULT_C_MONEDA_I or UPDATED_C_MONEDA_I
        defaultImpuestoViewShouldBeFound("cMonedaI.in=" + DEFAULT_C_MONEDA_I + "," + UPDATED_C_MONEDA_I);

        // Get all the impuestoViewList where cMonedaI equals to UPDATED_C_MONEDA_I
        defaultImpuestoViewShouldNotBeFound("cMonedaI.in=" + UPDATED_C_MONEDA_I);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBycMonedaIIsNullOrNotNull() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where cMonedaI is not null
        defaultImpuestoViewShouldBeFound("cMonedaI.specified=true");

        // Get all the impuestoViewList where cMonedaI is null
        defaultImpuestoViewShouldNotBeFound("cMonedaI.specified=false");
    }
                @Test
    @Transactional
    public void getAllImpuestoViewsBycMonedaIContainsSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where cMonedaI contains DEFAULT_C_MONEDA_I
        defaultImpuestoViewShouldBeFound("cMonedaI.contains=" + DEFAULT_C_MONEDA_I);

        // Get all the impuestoViewList where cMonedaI contains UPDATED_C_MONEDA_I
        defaultImpuestoViewShouldNotBeFound("cMonedaI.contains=" + UPDATED_C_MONEDA_I);
    }

    @Test
    @Transactional
    public void getAllImpuestoViewsBycMonedaINotContainsSomething() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        // Get all the impuestoViewList where cMonedaI does not contain DEFAULT_C_MONEDA_I
        defaultImpuestoViewShouldNotBeFound("cMonedaI.doesNotContain=" + DEFAULT_C_MONEDA_I);

        // Get all the impuestoViewList where cMonedaI does not contain UPDATED_C_MONEDA_I
        defaultImpuestoViewShouldBeFound("cMonedaI.doesNotContain=" + UPDATED_C_MONEDA_I);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultImpuestoViewShouldBeFound(String filter) throws Exception {
        restImpuestoViewMockMvc.perform(get("/api/impuesto-views?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(impuestoView.getId().intValue())))
            .andExpect(jsonPath("$.[*].cFacturaF").value(hasItem(DEFAULT_C_FACTURA_F)))
            .andExpect(jsonPath("$.[*].cReferenciaF").value(hasItem(DEFAULT_C_REFERENCIA_F)))
            .andExpect(jsonPath("$.[*].ejercicioF").value(hasItem(DEFAULT_EJERCICIO_F)))
            .andExpect(jsonPath("$.[*].documentoFiF").value(hasItem(DEFAULT_DOCUMENTO_FI_F)))
            .andExpect(jsonPath("$.[*].zdmsIdF").value(hasItem(DEFAULT_ZDMS_ID_F)))
            .andExpect(jsonPath("$.[*].cProveedorF").value(hasItem(DEFAULT_C_PROVEEDOR_F)))
            .andExpect(jsonPath("$.[*].cSociedadF").value(hasItem(DEFAULT_C_SOCIEDAD_F)))
            .andExpect(jsonPath("$.[*].iva").value(hasItem(DEFAULT_IVA)))
            .andExpect(jsonPath("$.[*].sumImpoMoneda").value(hasItem(DEFAULT_SUM_IMPO_MONEDA.doubleValue())))
            .andExpect(jsonPath("$.[*].sumImpoImpuesto").value(hasItem(DEFAULT_SUM_IMPO_IMPUESTO.doubleValue())))
            .andExpect(jsonPath("$.[*].sumImporteBase").value(hasItem(DEFAULT_SUM_IMPORTE_BASE.doubleValue())))
            .andExpect(jsonPath("$.[*].cMonedaI").value(hasItem(DEFAULT_C_MONEDA_I)));

        // Check, that the count call also returns 1
        restImpuestoViewMockMvc.perform(get("/api/impuesto-views/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultImpuestoViewShouldNotBeFound(String filter) throws Exception {
        restImpuestoViewMockMvc.perform(get("/api/impuesto-views?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restImpuestoViewMockMvc.perform(get("/api/impuesto-views/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingImpuestoView() throws Exception {
        // Get the impuestoView
        restImpuestoViewMockMvc.perform(get("/api/impuesto-views/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateImpuestoView() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        int databaseSizeBeforeUpdate = impuestoViewRepository.findAll().size();

        // Update the impuestoView
        ImpuestoView updatedImpuestoView = impuestoViewRepository.findById(impuestoView.getId()).get();
        // Disconnect from session so that the updates on updatedImpuestoView are not directly saved in db
        em.detach(updatedImpuestoView);
        updatedImpuestoView
            .cFacturaF(UPDATED_C_FACTURA_F)
            .cReferenciaF(UPDATED_C_REFERENCIA_F)
            .ejercicioF(UPDATED_EJERCICIO_F)
            .documentoFiF(UPDATED_DOCUMENTO_FI_F)
            .zdmsIdF(UPDATED_ZDMS_ID_F)
            .cProveedorF(UPDATED_C_PROVEEDOR_F)
            .cSociedadF(UPDATED_C_SOCIEDAD_F)
            .iva(UPDATED_IVA)
            .sumImpoMoneda(UPDATED_SUM_IMPO_MONEDA)
            .sumImpoImpuesto(UPDATED_SUM_IMPO_IMPUESTO)
            .sumImporteBase(UPDATED_SUM_IMPORTE_BASE)
            .cMonedaI(UPDATED_C_MONEDA_I);
        ImpuestoViewDTO impuestoViewDTO = impuestoViewMapper.toDto(updatedImpuestoView);

        restImpuestoViewMockMvc.perform(put("/api/impuesto-views")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(impuestoViewDTO)))
            .andExpect(status().isOk());

        // Validate the ImpuestoView in the database
        List<ImpuestoView> impuestoViewList = impuestoViewRepository.findAll();
        assertThat(impuestoViewList).hasSize(databaseSizeBeforeUpdate);
        ImpuestoView testImpuestoView = impuestoViewList.get(impuestoViewList.size() - 1);
        assertThat(testImpuestoView.getcFacturaF()).isEqualTo(UPDATED_C_FACTURA_F);
        assertThat(testImpuestoView.getcReferenciaF()).isEqualTo(UPDATED_C_REFERENCIA_F);
        assertThat(testImpuestoView.getEjercicioF()).isEqualTo(UPDATED_EJERCICIO_F);
        assertThat(testImpuestoView.getDocumentoFiF()).isEqualTo(UPDATED_DOCUMENTO_FI_F);
        assertThat(testImpuestoView.getZdmsIdF()).isEqualTo(UPDATED_ZDMS_ID_F);
        assertThat(testImpuestoView.getcProveedorF()).isEqualTo(UPDATED_C_PROVEEDOR_F);
        assertThat(testImpuestoView.getcSociedadF()).isEqualTo(UPDATED_C_SOCIEDAD_F);
        assertThat(testImpuestoView.getIva()).isEqualTo(UPDATED_IVA);
        assertThat(testImpuestoView.getSumImpoMoneda()).isEqualTo(UPDATED_SUM_IMPO_MONEDA);
        assertThat(testImpuestoView.getSumImpoImpuesto()).isEqualTo(UPDATED_SUM_IMPO_IMPUESTO);
        assertThat(testImpuestoView.getSumImporteBase()).isEqualTo(UPDATED_SUM_IMPORTE_BASE);
        assertThat(testImpuestoView.getcMonedaI()).isEqualTo(UPDATED_C_MONEDA_I);
    }

    @Test
    @Transactional
    public void updateNonExistingImpuestoView() throws Exception {
        int databaseSizeBeforeUpdate = impuestoViewRepository.findAll().size();

        // Create the ImpuestoView
        ImpuestoViewDTO impuestoViewDTO = impuestoViewMapper.toDto(impuestoView);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restImpuestoViewMockMvc.perform(put("/api/impuesto-views")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(impuestoViewDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ImpuestoView in the database
        List<ImpuestoView> impuestoViewList = impuestoViewRepository.findAll();
        assertThat(impuestoViewList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteImpuestoView() throws Exception {
        // Initialize the database
        impuestoViewRepository.saveAndFlush(impuestoView);

        int databaseSizeBeforeDelete = impuestoViewRepository.findAll().size();

        // Delete the impuestoView
        restImpuestoViewMockMvc.perform(delete("/api/impuesto-views/{id}", impuestoView.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ImpuestoView> impuestoViewList = impuestoViewRepository.findAll();
        assertThat(impuestoViewList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ImpuestoView.class);
        ImpuestoView impuestoView1 = new ImpuestoView();
        impuestoView1.setId(1L);
        ImpuestoView impuestoView2 = new ImpuestoView();
        impuestoView2.setId(impuestoView1.getId());
        assertThat(impuestoView1).isEqualTo(impuestoView2);
        impuestoView2.setId(2L);
        assertThat(impuestoView1).isNotEqualTo(impuestoView2);
        impuestoView1.setId(null);
        assertThat(impuestoView1).isNotEqualTo(impuestoView2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ImpuestoViewDTO.class);
        ImpuestoViewDTO impuestoViewDTO1 = new ImpuestoViewDTO();
        impuestoViewDTO1.setId(1L);
        ImpuestoViewDTO impuestoViewDTO2 = new ImpuestoViewDTO();
        assertThat(impuestoViewDTO1).isNotEqualTo(impuestoViewDTO2);
        impuestoViewDTO2.setId(impuestoViewDTO1.getId());
        assertThat(impuestoViewDTO1).isEqualTo(impuestoViewDTO2);
        impuestoViewDTO2.setId(2L);
        assertThat(impuestoViewDTO1).isNotEqualTo(impuestoViewDTO2);
        impuestoViewDTO1.setId(null);
        assertThat(impuestoViewDTO1).isNotEqualTo(impuestoViewDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(impuestoViewMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(impuestoViewMapper.fromId(null)).isNull();
    }
}
