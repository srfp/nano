// Generated by xsd compiler for android/java
// DO NOT CHANGE!
package com.ebay.trading.api;

import java.io.Serializable;
import com.leansoft.nano.annotation.*;

public class BasicAmountType implements Serializable {

    private static final long serialVersionUID = -1L;

	@Value
	@Order(value=0)
	public String value;	
	
	@Attribute  
	@Order(value=1)
	public CurrencyCodeType currencyID;	
	
    
}