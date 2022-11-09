package curso.java.junit.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_METHOD)
public class CuentaTestNew {

	@Test
	void testNombreCuenta() {
		Cuenta cuenta = new Cuenta("Persona1", new BigDecimal("10000.12345"));
		String esperado = "Persona1";
		String real = cuenta.getPersona();
		
		assertNotNull(null);
		assertEquals(esperado, real);
//		assertTrue(real.equals("Persona1"));
		//assertEquals(esperado, real, "El nombre de la persona no es el que se esperaba");
		//assertTrue(real.equals("Cuenta1"),"Nombre de persona debe ser igual a la real");
		//De la forma de las lineas anteriores aunque no falle se genera el string
		
		assertEquals(esperado, real, ()->"El nombre de la cuenta no es el que se esperaba");
		assertTrue(real.equals("Persona1"), ()-> "Nombre de cuenta debe ser igual a la real");
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
		//System.out.println(cuenta);
		assertEquals(cuenta, cuenta2, ()-> "Los objetos son distintos pero los valores son iguales");
		// Ver método equals de Cuenta
		// assertEquals(cuenta,cuenta2);
	}

	

}
