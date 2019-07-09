package com.jstarcraft.ai.search.annotation;

import java.io.Reader;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.lucene.analysis.TokenStream;

/**
 * 检索分词
 * 
 * <pre>
 *  仅作用于{@link Reader},{@link String},{@link TokenStream}类型字段的注解.
 * </pre>
 * 
 * @author Birdy
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface RetrievalAnalyze {

    /** 反向词向量(取决于是否需要查询) */
    RetrievalTerm negative() default @RetrievalTerm(frequency = false, position = false, offset = false);

    /** 正向词向量(取决于是否需要高亮) */
    RetrievalTerm positive() default @RetrievalTerm(frequency = false, position = false, offset = false);

}
