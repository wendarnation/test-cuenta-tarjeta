package es.iessoterohernandez.daw.endes.cuenta_tarjeta;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;


public class TestCuenta {
    private Cuenta cuenta;

    @BeforeEach
    public void setUp() {
        cuenta = new Cuenta("123456789", "Juan Pérez");
    }

    @Test
    public void testIngresar() throws Exception {
        cuenta.ingresar(100.0);
        assertEquals(100.0, cuenta.getSaldo());
    }

    @Test
    public void testRetirar() throws Exception {
        cuenta.ingresar(200.0);
        cuenta.retirar(50.0);
        assertEquals(150.0, cuenta.getSaldo());
    }

    @Test
    public void testIngresarConcepto() throws Exception {
        cuenta.ingresar("Venta zapatillas", 300.0);
        assertEquals(300.0, cuenta.getSaldo());
    }

    @Test
    public void testRetirarConcepto() throws Exception {
        cuenta.ingresar(500.0);
        cuenta.retirar("Compra Mercadona", 200.0);
        assertEquals(300.0, cuenta.getSaldo());
    }

    @Test
    public void testSaldoInicial() {
        assertEquals(0.0, cuenta.getSaldo());
    }

    @Test
    public void testSaldoDespuesAgregarMovimiento() {
        Movimiento movimiento = new Movimiento();
        movimiento.setConcepto("Depósito");
        movimiento.setImporte(500.0);
        cuenta.addMovimiento(movimiento);
        assertEquals(500.0, cuenta.getSaldo());
    }

   
}
