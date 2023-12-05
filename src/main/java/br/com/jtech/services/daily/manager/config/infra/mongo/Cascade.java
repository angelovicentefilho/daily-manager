/*
 *  @(#)Cascade.java
 *
 *  Copyright (c) J-Tech Solucoes em Informatica.
 *  All Rights Reserved.
 *
 *  This software is the confidential and proprietary information of J-Tech.
 *  ("Confidential Information"). You shall not disclose such Confidential
 *  Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with J-Tech.
 *
 */
package br.com.jtech.services.daily.manager.config.infra.mongo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used for create cascade in entity fields.
 *
 * @author angelo.vicente
 * @annotation Cascade
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Cascade {

    /**
     * Cascade typed with {@link CascadeType}
     *
     * @return Return a cascade type.
     */
    CascadeType value() default CascadeType.ALL;
}
