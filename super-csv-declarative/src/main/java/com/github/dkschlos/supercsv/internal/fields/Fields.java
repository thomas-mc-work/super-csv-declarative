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
package com.github.dkschlos.supercsv.internal.fields;

import com.github.dkschlos.supercsv.internal.util.Form;
import com.github.dkschlos.supercsv.io.declarative.CsvField;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.exception.SuperCsvException;

public final class Fields {

    private static final Map<CacheKey, Fields> FIELD_CACHE = new HashMap<CacheKey, Fields>();
    private final Map<Integer, FieldWrapper> mappedFields;

    private Fields(Map<Integer, FieldWrapper> mappedFields) {
        this.mappedFields = mappedFields;
    }
    /**
     * Returns all fields of the given class including those of superclasses.
     *
     * @param clazz the class to get the fields of
     * @return all fields of the class and its hierarchy
     */
    public static Fields getFields(Class<?> clazz, String context) {
        CacheKey cacheKey = new CacheKey(clazz, context);
        if (FIELD_CACHE.containsKey(cacheKey)) {
            return FIELD_CACHE.get(cacheKey);
        }
        FieldExtractor fieldExtractor = new FieldExtractor(clazz);
        List<Field> fields = fieldExtractor.getFields();

        Fields result = null;
        Map<Integer, FieldWrapper> fieldsByExplicitIndex = getFieldsByExplicitIndex(fields, context);
        if (fieldsByExplicitIndex.isEmpty()) {
            Map<Integer, FieldWrapper> fieldsByImplicitIndex = getFieldsByImplicitIndex(fields, context);
            result = new Fields(fieldsByImplicitIndex);
        } else {
            result = new Fields(fieldsByExplicitIndex);
        }

        FIELD_CACHE.put(cacheKey, result);
        return result;
    }

    public FieldWrapper getField(int index) {
        FieldWrapper mapped = mappedFields.get(index);
        if (mapped != null) {
            return mapped;
        }

        NullFieldWrapper nullFieldWrapper = new NullFieldWrapper();
        mappedFields.put(index, nullFieldWrapper);

        return nullFieldWrapper;
    }

    public List<FieldWrapper> getAll() {
        return new ArrayList<FieldWrapper>(mappedFields.values());
    }
    private static Map<Integer, FieldWrapper> getFieldsByExplicitIndex(List<Field> fields, String context) {
        Map<Integer, FieldWrapper> result = new HashMap<Integer, FieldWrapper>();
        for (Field field : fields) {
            CsvField fieldAnnotation = field.getAnnotation(CsvField.class);
            if (fieldAnnotation != null) {
                if (result.containsKey(fieldAnnotation.index())) {
                    throw new SuperCsvException(Form.at("Explicit order-index {} was declared twice (Field: {}", fieldAnnotation.index(),
                            field.getName()));
                }

                CellProcessor cellProcessor = BeanCellProcessorExtractor.createCellProcessorFor(field, context);
                result.put(fieldAnnotation.index(), new BeanFieldWrapper(field, cellProcessor));
            }
        }

        return result;
    }

    private static Map<Integer, FieldWrapper> getFieldsByImplicitIndex(List<Field> fields, String context) {
        Map<Integer, FieldWrapper> result = new HashMap<Integer, FieldWrapper>();
        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);
            CellProcessor cellProcessor = BeanCellProcessorExtractor.createCellProcessorFor(field, context);
            result.put(i, new BeanFieldWrapper(field, cellProcessor));
        }

        return result;
    }
    private static class CacheKey {

        private final Class<?> fieldClass;
        private final String context;

        public CacheKey(Class<?> fieldClass, String context) {
            this.fieldClass = fieldClass;
            this.context = context;
        }

        public Class<?> getFieldClass() {
            return fieldClass;
        }

        public String getContext() {
            return context;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((context == null) ? 0 : context.hashCode());
            result = prime * result + ((fieldClass == null) ? 0 : fieldClass.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            CacheKey other = (CacheKey) obj;
            if (context == null) {
                if (other.context != null) {
                    return false;
                }
            } else if (!context.equals(other.context)) {
                return false;
            }
            if (fieldClass == null) {
                if (other.fieldClass != null) {
                    return false;
                }
            } else if (!fieldClass.equals(other.fieldClass)) {
                return false;
            }
            return true;
        }

    }

}
