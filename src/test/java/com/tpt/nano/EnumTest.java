package com.tpt.nano;

import java.util.List;

import com.tpt.nano.annotation.Element;
import com.tpt.nano.annotation.RootElement;

import junit.framework.TestCase;

public class EnumTest extends TestCase {
	
    private static final String SOURCE = 
	    "<enumBug>\n"+
	    "  <type>A</type>\n"+
	    "</enumBug>";
        
    private static final String INVALID_SOURCE = 
        "<enumBug>\n"+
        "  <type>C</type>\n"+
        "</enumBug>";
        
    private static final String LIST = 
	    "<enumVariableArgumentsBug>\n"+
	    "  <types>A</types>\n"+
	    "  <types>B</types>\n"+
	    "  <types>A</types>\n"+
	    "  <types>A</types>\n"+
	    "</enumVariableArgumentsBug>";
    
    public enum PartType {
        A,
        B;
    }
    
    @RootElement
    public static class EnumVariableArgumentsBug {
    	
    	@Element
    	private List<PartType> types;
    	
    	public List<PartType> getTypes() {
    		return types;
    	}
    }
    
    @RootElement
    public static class EnumBug {
    	
    	@Element
    	private PartType type;
    	
    	public PartType getType() {
    		return type;
    	}
    }
    
    public void testEnum() throws Exception {
    	IReader xmlReader = NanoFactory.getXMLReader();
    	EnumBug bug = xmlReader.read(EnumBug.class, SOURCE);
    
    	assertEquals(PartType.A, bug.getType());
    }
    
    public void testInvalidEnum() throws Exception {
    	IReader xmlReader = NanoFactory.getXMLReader();
    	EnumBug bug = xmlReader.read(EnumBug.class, INVALID_SOURCE);
    	
    	assertNull(bug.getType());
    }
    
    public void testVargsEnum() throws Exception {
    	IReader xmlReader = NanoFactory.getXMLReader();
    	EnumVariableArgumentsBug bug = xmlReader.read(EnumVariableArgumentsBug.class, LIST);
    	
        assertEquals(PartType.A, bug.getTypes().get(0));
        assertEquals(PartType.B, bug.getTypes().get(1));
        assertEquals(PartType.A, bug.getTypes().get(2));
        assertEquals(PartType.A, bug.getTypes().get(3));
    }

}
