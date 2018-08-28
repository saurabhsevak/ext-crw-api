package com.corpository.ext.crw.api.extcrwapi.bean.Response;

import java.io.Serializable;

public class JSONResponsePagination<T> implements Serializable {
	
	private static final long serialVersionUID = -6027662023461472624L;

	private String status;
	private String message;
	private int recordsTotal;
	private int recordsFiltered;
	T data;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public int getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
