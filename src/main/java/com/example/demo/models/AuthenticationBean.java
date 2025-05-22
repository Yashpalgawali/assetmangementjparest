package com.example.demo.models;

public class AuthenticationBean {

	private String message;

	public AuthenticationBean(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public AuthenticationBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "AuthenticationBean [message=" + message + "]";
	}
	
	

//    @Value("${spring.jackson.time-zone}")
//    private StringBuffer jacksonTimeZone;
//
//    @PostConstruct
//    public void checkTimeZone() {
//        System.out.println("Jackson Timezone: " + jacksonTimeZone);
//    }
}
