package org.is.webApp;

import static org.junit.Assert.*;

import org.junit.Test;

public class UsuarioTest {

	@Test
	public void testGetPassword() {
		Usuario u = new Usuario ("admin", "admin");
		assertEquals("admin", u.getPassword());
	}

	@Test
	public void testSetPassword() {
		assertTrue(true); //
			}

}
