package com.flx.xs.xsmm;

//import org.springframework.beans.factory.annotation.Value;

public class Bean1 implements IBean1 {

    private String var1;
    private int port;
    private String flxConfigValue1;

	@Override
    public String hello(String name) {
        return var1 + ", " + name;
    }

    @Override
    //@Value("${var1}")
    public void setVar1(String var1) {
        this.var1 = var1;
    }
    
    public void setPort(int port){
    	this.port = port;
    }
    
    public int getPort() {
		return port;
	}

	@Override
	public void setFlxConfigValue1(String flxConfigValue1) {
		this.flxConfigValue1 = flxConfigValue1;
		
	}

	@Override
	public String getFlxConfigValue1() {
		return flxConfigValue1;
		
	}


}
