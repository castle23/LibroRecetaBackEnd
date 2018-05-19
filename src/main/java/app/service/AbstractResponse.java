package app.service;

public class AbstractResponse {

	public static final String ESTATUS_OK = "Ok";
	public static final String CODE_OK = "200";
	public static final String CODE_ERROR_INTERNO = "500";

	private String code;
	private String description;
	private Object data;

	public String getCode() {
		return code;	
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
