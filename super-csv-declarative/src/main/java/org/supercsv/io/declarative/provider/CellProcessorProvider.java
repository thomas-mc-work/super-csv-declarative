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
package org.supercsv.io.declarative.provider;

import java.lang.annotation.Annotation;

import org.supercsv.cellprocessor.ift.CellProcessor;

/**
 * Responsible for creation of {@link CellProcessor}s from Annotations
 * 
 * @since 2.5
 * @author Dominik Schlosser
 */
public interface CellProcessorProvider<T extends Annotation> {
	/**
	 * Creates the cell processor from the given annotation
	 * 
	 * @param annotation
	 *            the given annotation
	 * @return a CellProcessor based on the information in the given annotation
	 */
	CellProcessor create(T annotation, CellProcessor next);
	
	/**
	 * @return the annotation-type (necessary because of Java type-erasure)
	 */
	Class<T> getType();
}
