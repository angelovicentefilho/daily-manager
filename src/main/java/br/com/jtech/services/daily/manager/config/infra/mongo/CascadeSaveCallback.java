/*
 *  @(#)CascadeSaveCallback.java
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

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Collection;

import static java.util.Objects.nonNull;


/**
 * Callback class for cascade saving.
 *
 * @author angelo.vicente
 * @class CascadeDeleteCallback
 */
@RequiredArgsConstructor
public class CascadeSaveCallback implements ReflectionUtils.FieldCallback {

    private final Object source;
    private final MongoOperations operations;

    /**
     * Method overloading for create cascade on save callback.
     *
     * @param field Field from entity annotated.
     * @throws IllegalArgumentException Argument cannot be verified with cascade.
     * @throws IllegalAccessException   Argument cannot be accessed.
     */
    @Override
    public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {

        ReflectionUtils.makeAccessible(field);

        if (field.isAnnotationPresent(DBRef.class) && field.isAnnotationPresent(Cascade.class)) {
            final var fieldValue = field.get(source);

            if (nonNull(fieldValue)) {
                final var callback = new IdentifyCallback();
                final CascadeType cascadeType = field.getAnnotation(Cascade.class).value();

                if (cascadeType.equals(CascadeType.SAVE) || cascadeType.equals(CascadeType.ALL)) {
                    if (fieldValue instanceof Collection<?>) {
                        ((Collection<?>) fieldValue).forEach(operations::save);
                    } else {
                        ReflectionUtils.doWithFields(fieldValue.getClass(), callback);
                        operations.save(fieldValue);
                    }
                }
            }
        }

    }
}
