package com.mygradleproject.publictransportation.common;

public class AccessDeniedException extends RuntimeException{
    
	private static final long serialVersionUID = -4033307254898937394L;

	public AccessDeniedException(String message){
        super(message);
    }
    
}
