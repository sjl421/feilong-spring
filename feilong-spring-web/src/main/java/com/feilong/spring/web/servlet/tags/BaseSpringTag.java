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
package com.feilong.spring.web.servlet.tags;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.feilong.core.io.UncheckedIOException;

/**
 * 自定义标签的父类,需要和spring控制的业务层交互的请使用这个基类.
 *
 * @author feilong
 * @version 1.0.0 2009年10月28日 10:50:06
 * @version 1.2.0 2015年5月24日 上午4:20:05
 * @see org.springframework.web.servlet.tags.RequestContextAwareTag
 * @since 1.2.0
 */
public class BaseSpringTag extends RequestContextAwareTag{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 5289127954140428690L;

    /**
     * 标签开始.
     *
     * @return the int
     * @throws UncheckedIOException
     *             the unchecked io exception
     */
    @Override
    public int doStartTagInternal() throws UncheckedIOException{
        JspWriter jspWriter = pageContext.getOut();// 重要
        try{
            jspWriter.println(this.writeContent());
        }catch (IOException e){
            throw new UncheckedIOException(e);
        }
        return SKIP_BODY;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
     */
    @Override
    public int doEndTag(){
        return EVAL_PAGE;// 处理标签后，继续处理JSP后面的内容
    }

    /**
     * 获得spring管理的实体bean.
     *
     * @param <T>
     *            the generic type
     * @param beanName
     *            实体bean名称
     * @return the bean
     */
    @SuppressWarnings("unchecked")
    protected <T> T getBean(String beanName){
        WebApplicationContext webApplicationContext = this.getRequestContext().getWebApplicationContext();
        return (T) webApplicationContext.getBean(beanName);
    }

    /**
     * 显示.
     *
     * @return the object
     */
    protected Object writeContent(){
        return "";
    }

    // [start] 公用方法
    /**
     * 获得HttpServletRequest.
     *
     * @return the http servlet request
     */
    protected HttpServletRequest getHttpServletRequest(){
        return (HttpServletRequest) getServletRequest();
    }

    /**
     * 获得ServletRequest.
     *
     * @return the servlet request
     */
    protected ServletRequest getServletRequest(){
        return this.pageContext.getRequest();
    }

    /**
     * 获得 HttpSession.
     *
     * @return HttpSession
     */
    protected HttpSession getHttpSession(){
        return this.pageContext.getSession();
    }

    /**
     * 获得HttpServletResponse.
     *
     * @return the http servlet response
     */
    protected HttpServletResponse getHttpServletResponse(){
        return (HttpServletResponse) pageContext.getResponse();
    }
    // [end]
}