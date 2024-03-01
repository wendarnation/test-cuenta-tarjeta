package es.iessoterohernandez.daw.endes.cuenta_tarjeta;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.*;


public class TestDebito {
    private Debito tarjetaDebito;
    private Cuenta cuenta;

    @BeforeEach
    public void setUp() {
        cuenta = new Cuenta("123456789", "Paco Pepe");
        tarjetaDebito = new Debito("123456789", "Paco Pepe", new Date());
        tarjetaDebito.setCuenta(cuenta);
    }

    @Test
    public void testRetirar() throws Exception {
        cuenta.ingresar(200.0);
        tarjetaDebito.retirar(50.0);
        assertEquals(150.0, cuenta.getSaldo());
    }

    @Test
    public void testIngresar() throws Exception {
        tarjetaDebito.ingresar(200.0);
        assertEquals(200.0, cuenta.getSaldo());
    }

    @Test
    public void testPagoEnEstablecimiento() throws Exception {
        cuenta.ingresar(500.0);
        tarjetaDebito.pagoEnEstablecimiento("Tienda de zapatillas", 200.0);
        assertEquals(300.0, cuenta.getSaldo());
    }

    @Test
    public void testGetSaldo() throws Exception {
        cuenta.ingresar(1000.0);
        assertEquals(1000.0, tarjetaDebito.getSaldo());
    }


}
