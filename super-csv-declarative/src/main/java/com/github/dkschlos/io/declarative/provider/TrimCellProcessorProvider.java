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
package com.github.dkschlos.io.declarative.provider;

import org.supercsv.cellprocessor.Trim;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;

/**
 * CellProcessorProvider for Trim
 * 
 * @since 2.5
 * @author Dominik Schlosser
 */
public class TrimCellProcessorProvider implements
	DeclarativeCellProcessorProvider<com.github.dkschlos.io.declarative.annotation.Trim> {
	
	/**
	 * {@inheritDoc}
	 */
	public CellProcessorFactory create(final com.github.dkschlos.io.declarative.annotation.Trim annotation) {
		return new CellProcessorFactory() {
			
			public int getOrder() {
				return annotation.order();
			}
			
			public CellProcessor create(CellProcessor next) {
				return new Trim((StringCellProcessor) next);
			}
		};
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Class<com.github.dkschlos.io.declarative.annotation.Trim> getType() {
		return com.github.dkschlos.io.declarative.annotation.Trim.class;
	}
	
}