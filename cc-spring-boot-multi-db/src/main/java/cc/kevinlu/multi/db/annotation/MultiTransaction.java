package cc.kevinlu.multi.db.annotation;

import java.lang.annotation.*;

/**
 * @author chuan
 */
@Target({ ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MultiTransaction {

    String[] values();

}
