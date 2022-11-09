package curso.java.junit.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class CuentaTestNew {

	@Test
	void testNombreCuenta() {
		Cuenta cuenta = new Cuenta("Cuenta1", new BigDecimal("10000.12345"));
		String esperado = "Cuenta1";
		String real = cuenta.getPersona();
		
		assertNotNull(real);
		assertEquals(esperado, real);
		assertTrue(real.equals("Cuenta1"));
		//assertEquals(esperado, real, "El nombre de la cuenta no es el que se esperaba");
		//assertTrue(real.equals("Cuenta1"),"Nombre de cuenta debe ser igual a la real");
		//De la forma de las lineas anteriores aunque no falle se genera el string
		
		//assertEquals(esperado, real, ()->"El nombre de la cuenta no es el que se esperaba");
		//assertTrue(real.equals("Cuenta1"), ()-> "Nombre de cuenta debe ser igual a la real");
	}

	@Test
	void testSaldoCuenta() {
		Cuenta cuenta = new Cuenta("Andres", new BigDecimal("1000.12345"));
		assertEquals(1000.12345, cuenta.getSaldo().doubleValue());
	}

	@Test
	void testReferenciaCuenta() {
		Cuenta cuenta = new Cuenta("Andres", new BigDecimal("1000.12345"));
		Cuenta cuenta2 = new Cuenta("Andres", new BigDecimal("1000.12345"));

		assertNotEquals(cuenta, cuenta2);
		// Ver método equals de Cuenta
		// assertEquals(cuenta,cuenta2);
	}

	

}
