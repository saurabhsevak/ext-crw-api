/*
 * Corpository web-api version - 1.0
 * 
 * Created By : Vijay Gajera
 */
package com.corpository.ext.crw.api.extcrwapi.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class WebApiCommonInputBean.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebApiCommonInputBean implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The request. */
	@JsonProperty("request")
	String request;
	
	/** The common input bean object. */
	@JsonProperty("para")
	Object commonInputBeanObject;

	/**
	 * Gets the common input bean object.
	 *
	 * @return the common input bean object
	 */
	public Object getCommonInputBeanObject() {
		return commonInputBeanObject;
	}
	
	/**
	 * Sets the common input bean object.
	 *
	 * @param commonInputBeanObject the new common input bean object
	 */
	public void setCommonInputBeanObject(Object commonInputBeanObject) {
		this.commonInputBeanObject = commonInputBeanObject;
	}
	
	/**
	 * Gets the request.
	 *
	 * @return the request
	 */
	public String getRequest() {
		return request;
	}
	
	/**
	 * Sets the request.
	 *
	 * @param request the new request
	 */
	public void setRequest(String request) {
		this.request = request;
	}
}
