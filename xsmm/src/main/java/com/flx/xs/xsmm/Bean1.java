package com.flx.xs.xsmm;

import org.springframework.beans.factory.annotation.Value;

public class Bean1 implements IBean1 {

    private String var1;
    private int port;
    

	@Override
    public String hello(String name) {
        return var1 + ", " + name;
    }

    @Override
    @Value("${var1}")
    public void setVar1(String var1) {
        this.var1 = var1;
    }
    
    public void setPort(int port){
    	this.port = port;
    }
    
    public int getPort() {
		return port;
	}

}
