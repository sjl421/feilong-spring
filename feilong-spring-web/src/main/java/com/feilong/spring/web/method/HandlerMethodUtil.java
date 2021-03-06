/*
 * Copyright (C) 2008 feilong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feilong.spring.web.method;

import java.lang.reflect.Method;

import org.apache.commons.lang3.Validate;
import org.springframework.web.method.HandlerMethod;

/**
 * {@link HandlerMethod} 的工具类.
 *
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 * @since 1.4.0
 */
public final class HandlerMethodUtil{

    /** Don't let anyone instantiate this class. */
    private HandlerMethodUtil(){
        //AssertionError不是必须的. 但它可以避免不小心在类的内部调用构造器. 保证该类在任何情况下都不会被实例化.
        //see 《Effective Java》 2nd
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }

    //---------------------------------------------------------------

    /**
     * 获得方法名称.
     *
     * @param handlerMethod
     *            the handler method
     * @return 如果 <code>handlerMethod</code> 是null,抛出 {@link NullPointerException}<br>
     */
    public static String getHandlerMethodName(HandlerMethod handlerMethod){
        Validate.notNull(handlerMethod, "handlerMethod can't be null!");

        Method method = handlerMethod.getMethod();
        return method.getName();
    }

    /**
     * 获得类型的名称.
     *
     * @param handlerMethod
     *            the handler method
     * @return 如果 <code>handlerMethod</code> 是null,抛出 {@link NullPointerException}<br>
     */
    public static String getDeclaringClassSimpleName(HandlerMethod handlerMethod){
        Validate.notNull(handlerMethod, "handlerMethod can't be null!");

        Method method = handlerMethod.getMethod();
        return method.getDeclaringClass().getSimpleName();
    }
}
