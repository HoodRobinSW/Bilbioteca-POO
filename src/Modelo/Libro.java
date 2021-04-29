package Modelo;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import Excepciones.CampoObligatorioException;
import Excepciones.IsbnException;

public class Libro {

	private String isbn,titulo,autor,editorial;
	private java.sql.Date fechaRegistro;
	private double precio;
	private boolean prestado;
	
	public Libro() {
		// TODO Auto-generated constructor stub
		this.isbn=null;
		this.titulo=null;
		this.autor=null;
		this.editorial=null;
		this.fechaRegistro=null;
		this.precio=0.0;
		this.prestado=false;
	}

	
	public Libro(String isbn, String titulo, String autor, String editorial, String fechaRegistro, String precio,
			String prestado) throws ParseException,NumberFormatException, CampoObligatorioException, IsbnException {
		super();
		setIsbn(isbn);
		setTitulo(titulo);
		setAutor(autor);
		setEditorial(editorial);
		setFechaRegistro(fechaRegistro);
		setPrecio(precio);
		setPrestado(prestado);
	}


	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) throws CampoObligatorioException,IsbnException{
		if (isbn.isEmpty())
			throw new CampoObligatorioException();
		
		isbn=isbn.replace("-", "");
		
		if (isbn.length()!=13)
			throw new IsbnException();
		
		int dc=Integer.parseInt(Character.toString(isbn.charAt(12)));
		int suma=0;
		for (int x=0;x<isbn.length()-1;x++) {
			if (x%2==0)
				suma+=Integer.parseInt(Character.toString(isbn.charAt(x)));
			else suma+=Integer.parseInt(Character.toString(isbn.charAt(x)))*3;
		}
		int comprobante=10-(suma%10);
		if (comprobante==10)
			comprobante=0;
		if (comprobante!=dc)
			throw new IsbnException();
		
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) throws CampoObligatorioException{
		if (titulo.isEmpty())
			throw new CampoObligatorioException();
		
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) throws CampoObligatorioException{
		if (autor.isEmpty())
			throw new CampoObligatorioException();
			
		this.autor = autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) throws CampoObligatorioException{
		if (editorial.isEmpty())
			throw new CampoObligatorioException();
			
		this.editorial = editorial;
	}

	public java.sql.Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) throws ParseException,CampoObligatorioException {
		if (fechaRegistro.isEmpty() || fechaRegistro.equals("null"))
			throw new CampoObligatorioException();
		
		java.util.Date fecha;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(prestado);
		fecha=sdf.parse(fechaRegistro);
		
		this.fechaRegistro = new java.sql.Date(fecha.getTime());
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) throws NumberFormatException,CampoObligatorioException{
		if (precio.isEmpty())
			throw new CampoObligatorioException();
		
		this.precio = Double.parseDouble(precio);
	}

	public boolean isPrestado() {
		return prestado;
	}

	public void setPrestado(String prestado) throws CampoObligatorioException{
		if (prestado.isEmpty())
			throw new CampoObligatorioException();
		
		this.prestado = Boolean.parseBoolean(prestado);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return isbn+","+titulo+","+autor+","+editorial+","+fechaRegistro+","+precio+","+prestado;
	}

}
