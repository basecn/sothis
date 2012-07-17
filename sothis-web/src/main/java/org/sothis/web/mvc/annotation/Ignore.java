package org.sothis.web.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ���ע����set�����ϣ����Ӧ��request�������ᱻset<br>
 * ���ע����action�����ϣ����action�������ᱻע�ᵽsothis��<br>
 * ���ע����controller���ϣ����controller�е�����action�����ᱻע����sothis��<br>
 * ���ע����package�ϣ����package�е�����controller�����ᱻע����sothis��<br>
 * 
 * @author velna
 * 
 */
@Target( { ElementType.METHOD, ElementType.TYPE, ElementType.PACKAGE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Ignore {

}
