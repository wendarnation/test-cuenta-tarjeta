package es.iessoterohernandez.daw.endes.cuenta_tarjeta;

import java.util.Vector;

public class Cuenta 
{
	protected String mNumero;
	protected String mTitular;
	protected Vector mMovimientos;	

	public Cuenta(String numero, String titular)
	{
		mNumero=numero;
		mTitular=titular;
		mMovimientos=new Vector();
	}
	
	public void ingresar(double x) throws Exception
	{
		if (x<=0)
			throw new Exception("No se puede ingresar una cantidad negativa");
		Movimiento m=new Movimiento();
		m.setConcepto("Ingreso en efectivo");
		m.setImporte(x);
		this.mMovimientos.addElement(m);
	}
	
	public void retirar(double x) throws Exception 
	{
		if (x<=0)
			throw new Exception("No se puede retirar una cantidad negativa");	
		if (getSaldo()<x)
			throw new Exception("Saldo insuficiente");
		Movimiento m=new Movimiento();
		m.setConcepto("Retirada de efectivo");
		m.setImporte(-x);
		this.mMovimientos.addElement(m);
	
	}
	
	public void ingresar(String concepto, double x) throws Exception
	{
		if (x<=0)
			throw new Exception("No se puede ingresar una cantidad negativa");
		Movimiento m=new Movimiento();
		m.setConcepto(concepto);
		m.setImporte(x);
		this.mMovimientos.addElement(m);
	}
	
	public void retirar(String concepto, double x) throws Exception 
	{
		if (x<=0)
			throw new Exception("No se puede retirar una cantidad negativa");	
		if (getSaldo()<x)
			throw new Exception("Saldo insuficiente");
		Movimiento m=new Movimiento();
		m.setConcepto(concepto);
		m.setImporte(-x);
		this.mMovimientos.addElement(m);
	
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
	
	public void addMovimiento(Movimiento m) 
	{
		mMovimientos.addElement(m);
	}
}