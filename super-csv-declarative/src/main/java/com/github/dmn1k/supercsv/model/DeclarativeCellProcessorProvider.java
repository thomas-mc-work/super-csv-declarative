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
package com.github.dmn1k.supercsv.model;

import java.lang.annotation.Annotation;

import org.supercsv.cellprocessor.ift.CellProcessor;

/**
 * Responsible for creation of {@link CellProcessor}s from Annotations
 *
 * @since 2.5
 * @author Dominik Schlosser
 */
public interface DeclarativeCellProcessorProvider<T extends Annotation> {

    /**
     * Creates the cell processor from the given annotation
     *
     * @param metadata the metadata for this processing-step
     * @return a CellProcessor based on the information in the given annotation
     */
    CellProcessorFactory create(ProcessingMetadata<T> metadata);

    /**
     * @return the annotation-type (necessary because of Java type-erasure)
     */
    Class<T> getType();
}
