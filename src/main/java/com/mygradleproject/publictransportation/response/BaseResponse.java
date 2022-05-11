package com.mygradleproject.publictransportation.response;

public class BaseResponse {
	
	public static final String SUCCESS = "success";
	public static final String UNSUCCESS = "unsuccess";

    private Integer status;
    private Object data;
    private String operationMessage;
    private String additionalInfo;
    
    public Integer getStatus() {
        return status;
    }

	public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

	public String getOperationMessage() {
		return operationMessage;
	}

	public void setOperationMessage(String operationMessage) {
		this.operationMessage = operationMessage;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
}