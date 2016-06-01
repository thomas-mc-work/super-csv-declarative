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
package org.supercsv.io.declarative.constraint.provider;

import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.declarative.constraint.annotation.Unique;
import org.supercsv.io.declarative.provider.CellProcessorFactory;
import org.supercsv.io.declarative.provider.DeclarativeCellProcessorProvider;

/**
 * CellProcessorProvider for Unique
 * 
 * @since 2.5
 * @author Dominik Schlosser
 */
public class UniqueCellProcessorProvider implements DeclarativeCellProcessorProvider<Unique> {
	
	/**
	 * {@inheritDoc}
	 */
	public CellProcessorFactory create(final Unique annotation) {
		return new CellProcessorFactory() {
			
			public int getOrder() {
				return annotation.order();
			}
			
			public CellProcessor create(CellProcessor next) {
				return new org.supercsv.cellprocessor.constraint.Unique(next);
			}
		};
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Class<Unique> getType() {
		return Unique.class;
	}
	
}
