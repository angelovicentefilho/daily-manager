package br.com.jtech.services.daily.manager.config.infra.annotations;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestController
@RequestMapping
public @interface JtechRestController {

    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] value() default {};

}
