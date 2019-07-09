package com.jstarcraft.ai.search.converter;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedList;

import org.apache.lucene.document.DoublePoint;
import org.apache.lucene.document.FloatPoint;
import org.apache.lucene.document.IntPoint;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.NumericDocValuesField;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.util.NumericUtils;

import com.jstarcraft.ai.search.annotation.RetrievalAnalyze;
import com.jstarcraft.ai.search.annotation.RetrievalIndex;
import com.jstarcraft.ai.search.annotation.RetrievalSort;
import com.jstarcraft.ai.search.annotation.RetrievalStore;
import com.jstarcraft.ai.search.exception.RetrievalException;
import com.jstarcraft.core.common.reflection.TypeUtility;
import com.jstarcraft.core.utility.ClassUtility;

/**
 * 数值转换器
 * 
 * @author Birdy
 *
 */
public class NumberConverter implements RetrievalConverter {

    @Override
    public Collection<IndexableField> convert(String name, Type type, Object data, RetrievalAnalyze analyze, RetrievalIndex index, RetrievalSort sort, RetrievalStore store) {
        Class<?> clazz = TypeUtility.getRawType(type, null);
        clazz = ClassUtility.primitiveToWrapper(clazz);
        if (Byte.class == clazz) {

        }
        if (Short.class == clazz) {

        }
        if (Integer.class == clazz) {
            Collection<IndexableField> fields = new LinkedList<>();
            if (index != null) {
                fields.add(new IntPoint(name, (Integer) data));
            }
            if (sort != null) {
                fields.add(new NumericDocValuesField(name, (Integer) data));
            }
            if (store != null) {
                fields.add(new StoredField(name, (Integer) data));
            }
            return fields;
        }
        if (Long.class == clazz) {
            Collection<IndexableField> fields = new LinkedList<>();
            if (index != null) {
                fields.add(new LongPoint(name, (Long) data));
            }
            if (sort != null) {
                fields.add(new NumericDocValuesField(name, (Long) data));
            }
            if (store != null) {
                fields.add(new StoredField(name, (Long) data));
            }
            return fields;
        }
        if (Float.class == clazz) {
            Collection<IndexableField> fields = new LinkedList<>();
            if (index != null) {
                fields.add(new FloatPoint(name, (Float) data));
            }
            if (sort != null) {
                fields.add(new NumericDocValuesField(name, NumericUtils.floatToSortableInt((Float) data)));
            }
            if (store != null) {
                fields.add(new StoredField(name, (Float) data));
            }
            return fields;
        }
        if (Double.class == clazz) {
            Collection<IndexableField> fields = new LinkedList<>();
            if (index != null) {
                fields.add(new DoublePoint(name, (Double) data));
            }
            if (sort != null) {
                fields.add(new NumericDocValuesField(name, NumericUtils.doubleToSortableLong((Double) data)));
            }
            if (store != null) {
                fields.add(new StoredField(name, (Double) data));
            }
            return fields;
        }
        throw new RetrievalException();
    }

}
