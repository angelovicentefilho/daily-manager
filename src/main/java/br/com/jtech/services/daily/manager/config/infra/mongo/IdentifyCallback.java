/*
 *  @(#)IdentifyCallback.java
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

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * Class used for verify identifier from class.
 *
 * @author angelo.vicente
 * @class IdentifyCallback
 */
public class IdentifyCallback implements ReflectionUtils.FieldCallback {

    @Getter
    private boolean idFound;

    /**
     * The Method verifies if a class contains the identifier.
     *
     * @param field Field used with identifier annotation.
     * @throws IllegalArgumentException Argument cannot be verified with cascade.
     * @throws IllegalAccessException   Argument cannot be accessed.
     */
    @Override
    public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
        ReflectionUtils.makeAccessible(field);

        if (field.isAnnotationPresent(Id.class)) {
            idFound = true;
        }
    }
}
