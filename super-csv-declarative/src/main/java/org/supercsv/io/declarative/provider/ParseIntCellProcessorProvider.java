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

import java.lang.reflect.Field;

import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.cellprocessor.ift.LongCellProcessor;
import org.supercsv.io.declarative.annotation.ParseInt;

/**
 * CellProcessorProvider for {@link ParseInt}
 * 
 * @since 2.5
 * @author Dominik Schlosser
 */
public class ParseIntCellProcessorProvider implements CellProcessorByAnnotationProvider<ParseInt>,
	CellProcessorProvider {
	
	/**
	 * {@inheritDoc}
	 */
	public CellProcessor create(ParseInt annotation, CellProcessor next) {
		return new org.supercsv.cellprocessor.ParseInt((LongCellProcessor) next);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public CellProcessor create(Field forField, CellProcessor next) {
		return new org.supercsv.cellprocessor.ParseInt((LongCellProcessor) next);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Class<ParseInt> getType() {
		return ParseInt.class;
	}
	
}
