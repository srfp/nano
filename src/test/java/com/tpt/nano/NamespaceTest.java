package com.tpt.nano;

import com.tpt.nano.annotation.Element;
import com.tpt.nano.annotation.RootElement;

import junit.framework.TestCase;

public class NamespaceTest extends TestCase {
	
	@RootElement(namespace="namespace1")
	private static class Aaa {
		@Element(name="bbb", namespace="namespace2")
		public Bbb bbb;
	}
	
	//@XmlRootElement(namespace="namespace2")
	private static class Bbb {
		@Element(name="aaa", namespace="namespace1")
		public Aaa aaa;
	}
	
	public void testNamespace() throws Exception {
		Aaa parent = new Aaa();
		Bbb child = new Bbb();
		parent.bbb = child;
		Aaa grandChild = new Aaa();
		child.aaa = grandChild;
		grandChild.bbb = new Bbb();
		
		IWriter xmlWriter = NanoFactory.getXMLWriter();
		String str1 = xmlWriter.write(parent);
		
		IReader xmlReader = NanoFactory.getXMLReader();
		Aaa target = xmlReader.read(Aaa.class, str1);
		String str2 = xmlWriter.write(target);
		
		assertEquals(str2, str1);
	}

}
