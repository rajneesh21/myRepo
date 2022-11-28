package com.rk;

public class FunnyException extends Throwable{
 String msg="Ha Ha So funny";

@Override
public String getMessage() {

	return msg;
}

public FunnyException() {
	
	super();

}

public FunnyException(String message) {
	super(message);

}


}
