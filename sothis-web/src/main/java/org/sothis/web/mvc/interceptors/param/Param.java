package org.sothis.web.mvc.interceptors.param;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Action�����Ĳ���ע��ע��<br>
 * 
 * <pre>
 * class UserController{
 *   public void loginAction(@Param(name="username") String username, @Param(name="password") password){
 *   	User user = dao.findByUsername(username);
 *   	if(user.password.equals(password)){
 *   		...
 *   	}else{
 *   		...
 *   	}
 *   }
 * }
 * </pre>
 * 
 * @author velna
 * 
 */
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Param {
	/**
	 * �����еĲ�����
	 * 
	 * @return
	 */
	String name() default "";

	/**
	 * ������ʽƥ�䣬ֻ��ƥ��Ż���в���ע�롣����{@link Date}�������Ӧ{@link SimpleDateFormat}��pattern
	 * 
	 * @return
	 */
	String pattern() default "";
}
