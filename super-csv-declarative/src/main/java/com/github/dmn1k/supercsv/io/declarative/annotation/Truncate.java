/*
 * Copyright 2007 Kasper B. Graversen
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.dmn1k.supercsv.io.declarative.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.dmn1k.supercsv.io.declarative.CellProcessorAnnotationDescriptor;
import com.github.dmn1k.supercsv.io.declarative.ProcessorOrder;
import com.github.dmn1k.supercsv.io.declarative.provider.TruncateCellProcessorProvider;
import java.lang.annotation.Repeatable;

/**
 * Annotation for the {@link org.supercsv.cellprocessor.Truncate}-cell processor
 *
 * @since 2.5
 * @author Dominik Schlosser
 */
@Repeatable(Truncate.Container.class)
@CellProcessorAnnotationDescriptor(provider = TruncateCellProcessorProvider.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Truncate {

    /**
     * @return the maximum size of the String
     */
    int maxSize();

    /**
     * @return the String to append if the input is truncated (e.g. "...")
     */
    String suffix() default "";

    int order() default ProcessorOrder.UNDEFINED;
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD})
    @interface Container {
        Truncate[] value();
    }
}
