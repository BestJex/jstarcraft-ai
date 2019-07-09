package com.jstarcraft.ai.search.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 检索排序
 * 
 * <pre>
 * 注意:分词字段存储DocValues没有意义
 * </pre>
 * 
 * @author Birdy
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface RetrievalSort {

}
