package com.jstarcraft.ai.search.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 检索词向量
 * 
 * @author Birdy
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RetrievalTerm {

    /** 词频 */
    boolean frequency();

    /** 位置 */
    boolean position();

    /** 偏移 */
    boolean offset();

}
