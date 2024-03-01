package es.iessoterohernandez.daw.endes.cuenta_tarjeta;

import java.util.Vector;
import java.util.Date;

public class Credito extends Tarjeta
{
	protected double mCredito;
	protected Vector<Movimiento> mMovimientos;

	public Credito(String numero, String titular, Date fechaCaducidad, double credito)
	{
		super(numero, titular, fechaCaducidad);
		mCredito=credito;
		mMovimientos=new Vector<Movimiento>();
	}
	
	public void retirar(double x) throws Exception 
	{
		Movimiento m=new Movimiento();
		m.setConcepto("Retirada en cajero autom�tico");
		double comission=(x*0.05<3.0 ? 3 : x*0.05);  // A�adimos una comisi�n de un 5%, m�nimo de 3 euros.
		m.setImporte(x+comission);
		if (x+comission>getCreditoDisponible()) {
			throw new Exception("Cr�dito insuficiente");
		} else {
			mMovimientos.addElement(m);
		}
	}
//	Con el código original solo se restaba la comisión. Lo he actualizado para que reste todo
	
	public void ingresar(double x) throws Exception 
	{
		Movimiento m=new Movimiento();
		m.setConcepto("Ingreso en cuenta asociada (cajero autom�tico)");
		m.setImporte(x);
		mCredito+=x;
//		mMovimientos.addElement(m);
		mCuentaAsociada.ingresar(x);
	}
//	Con el código original no sumaba al credito lo ingresado. He actualizado para que lo haga
	
	public void pagoEnEstablecimiento(String datos, double x) throws Exception 
	{
		Movimiento m=new Movimiento();
		m.setConcepto("Compra a cr�dito en: " + datos);
		m.setImporte(x);
		mMovimientos.addElement(m);
	}
	
	public double getSaldo() 
	{
		double r=0.0;
		for (int i=0; i<this.mMovimientos.size(); i++) 
		{
			Movimiento m=(Movimiento) mMovimientos.elementAt(i);
			r+=m.getImporte();
		}
		return r;
	}
	
	public double getCreditoDisponible() 
	{
		return mCredito-getSaldo();
	}
	
	@SuppressWarnings("deprecation")
	public void liquidar(int mes, int anyo) 
	{
		Movimiento liq=new Movimiento();
		liq.setConcepto("Liquidaci�n de operaciones tarj. cr�dito, " + (mes+1) + " de " + (anyo+1900));
		double r=0.0;
		for (int i=0; i<this.mMovimientos.size(); i++) 
		{
			Movimiento m=(Movimiento) mMovimientos.elementAt(i);
			if (m.getFecha().getMonth()+1==mes && m.getFecha().getYear()+1900==anyo)
				r+=m.getImporte();
		}
		liq.setImporte(-r);
		if (r!=0) {
			this.mMovimientos.addElement(liq);
			mCuentaAsociada.addMovimiento(liq);
		}
	}
//	He actualizado para que también se añada movimiento liq a this.mMovimientos, así aparece correctamente el saldo
}