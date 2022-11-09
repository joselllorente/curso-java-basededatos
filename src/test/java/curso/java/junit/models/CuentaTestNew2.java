package curso.java.junit.models;

import static org.junit.jupiter.api.Assertions.assertAll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;

import curso.java.junit.excepciones.DineroInsuficienteException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CuentaTestNew2 {
	Cuenta cuenta;

	@BeforeAll
	void iniciaConexion(){
		System.out.println("Conectando con la base de datos");
	}
	
	@BeforeEach
	void initMetodoTest(TestInfo testInfo, TestReporter testReporter) {
		System.out.println("Antes de la prueba");
		this.cuenta = new Cuenta("Andres", new BigDecimal("1000.12345"));
	}

	@Test
	@DisplayName("probando debito de la cuenta.")
	void testDebitoCuenta() {
		System.out.println("testDebitoCuenta");
		cuenta.debito(new BigDecimal(100));
		assertNotNull(cuenta.getSaldo());
		assertEquals(900, cuenta.getSaldo().intValue());
		assertEquals("900.12345", cuenta.getSaldo().toPlainString());
	}

	@Test
	@Tag("cuenta")
	@DisplayName("probando credito de la cuenta.")
	void testCreditoCuenta() {
		System.out.println("testCreditoCuenta");
		cuenta.credito(new BigDecimal(100));
		assertNotNull(cuenta.getSaldo());
		assertEquals(1100, cuenta.getSaldo().intValue());
		assertEquals("1100.12345", cuenta.getSaldo().toPlainString());
	}

	@Test
	@Tag("cuenta")
	@Tag("error")
	// @Disabled
	@DisplayName("probando excepcion dinero insuficiente.")
	void testDineroInsuficienteExceptionCuenta() {
		System.out.println("testDineroInsuficienteExceptionCuenta");
		Exception exception = assertThrows(DineroInsuficienteException.class, () -> {
			cuenta.debito(new BigDecimal(1500));
		});
		String actual = exception.getMessage();
		String esperado = "Dinero Insuficiente";
		assertEquals(esperado, actual);
	}

	@Test
	@Tag("cuenta")
	@Tag("banco")
	// @Disabled
	@DisplayName("probando relaciones entre las cuentas y el banco con assertAll.")
	void testRelacionBancoCuentas() {
		// fail();
		Cuenta cuenta1 = new Cuenta("Jhon Doe", new BigDecimal("2500"));
		Cuenta cuenta2 = new Cuenta("Andres", new BigDecimal("1500.8989"));

		Banco banco = new Banco();
		banco.addCuenta(cuenta1);
		banco.addCuenta(cuenta2);

		banco.setNombre("Banco del Estado");
		banco.transferir(cuenta2, cuenta1, new BigDecimal(500));
		// assertAll( () -> {} , () -> {} );
		assertAll(
				() -> assertEquals("1000.8989", cuenta2.getSaldo().toPlainString(),
						() -> "el valor del saldo de la cuenta2 no es el esperado."),
				() -> assertEquals("3000", cuenta1.getSaldo().toPlainString(),
						() -> "el valor del saldo de la cuenta1 no es el esperado."),
				() -> assertEquals(2, banco.getCuentas().size(), () -> "el banco no tienes las cuentas esperadas"),
				() -> assertEquals("Banco del Estado", cuenta1.getBanco().getNombre()),
				() -> assertEquals("Andres",banco.getCuentas().stream().filter(cuenta -> cuenta.getPersona().equals("Andres")).findFirst().get().getPersona()),
				() -> assertTrue(banco.getCuentas().stream().anyMatch(c -> c.getPersona().equals("Jhon Doe"))));
	}

	@AfterEach
	void tearDown() {
		System.out.println("finalizando el metodo de prueba.");
	}


	@AfterAll
	static void afterAll() {
		System.out.println("finalizando el test");
	}
}
