package com.orbitalyards.landwar.mvc.model.body;

import org.springframework.http.HttpStatus;

/***
 * Top-level response class for sending information back out from server.
 * Note: this by itself is not JSON serializable, you must implement that
 * in the implementation class you write using annotations. 
 * @author roohr
 */
public abstract class AppResponse {

	protected int statusCode;
	protected String msg;
	
	protected HttpStatus httpStatus;
	protected Exception exception;
	
	public AppResponse() {}

	public abstract int getStatusCode();

	public abstract void setStatusCode(int statusCode);

	public abstract String getMsg();

	public abstract void setMsg(String msg);
	
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}
}
