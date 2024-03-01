package es.iessoterohernandez.daw.endes.cuenta_tarjeta;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import java.util.Date;


public class TestCredito {
    private Credito credito;
    private Cuenta cuenta;

    @BeforeEach
    public void init() {
        credito = new Credito("123456789", "Paco García", new Date(), 1000.0);
        cuenta = new Cuenta("12345", "Paco García");
        credito.setCuenta(cuenta);
    }
    

    @Test
    public void testRetirar() throws Exception {
        credito.retirar(500.0);
        assertEquals(475.0, credito.getCreditoDisponible());
    }


    @Test
    public void testIngresar() throws Exception {
        credito.ingresar(300.0);
        assertEquals(1300.0, credito.getCreditoDisponible());
    }

    @Test
    public void testPagoEnEstablecimiento() throws Exception {
        credito.pagoEnEstablecimiento("Tienda", 200.0);
        assertEquals(800.0, credito.getCreditoDisponible());
    }

    @Test
    public void testGetSaldo() throws Exception {
        credito.ingresar(300.0);
        credito.pagoEnEstablecimiento("Tienda", 200.0);
        assertEquals(200.0, credito.getSaldo());
    }

    @Test
    public void testLiquidar() throws Exception {
        credito.ingresar(300.0);
        credito.pagoEnEstablecimiento("Tienda", 200.0);
        credito.liquidar(3, 2024);
        assertEquals(0.0, credito.getSaldo());
    }
}
