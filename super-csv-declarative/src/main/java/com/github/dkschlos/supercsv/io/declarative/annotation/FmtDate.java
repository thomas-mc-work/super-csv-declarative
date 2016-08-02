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
package com.github.dkschlos.supercsv.io.declarative.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.text.SimpleDateFormat;

import com.github.dkschlos.supercsv.io.declarative.CellProcessorAnnotationDescriptor;
import com.github.dkschlos.supercsv.io.declarative.ProcessorOrder;
import com.github.dkschlos.supercsv.io.declarative.StandardCsvContexts;
import com.github.dkschlos.supercsv.io.declarative.provider.FmtDateCellProcessorProvider;

/**
 * Annotation for the {@link org.supercsv.cellprocessor.FmtDate}-cell processor
 *
 * @since 2.5
 * @author Dominik Schlosser
 */
@CellProcessorAnnotationDescriptor(provider = FmtDateCellProcessorProvider.class, contexts = {StandardCsvContexts.WRITE})
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface FmtDate {

    /**
     * @return the date format String (see {@link SimpleDateFormat})
     */
    String format() default "";

    int order() default ProcessorOrder.UNDEFINED;
}
