package Controlador;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import Excepciones.CampoObligatorioException;
import Excepciones.IsbnException;
import Excepciones.NotFoundException;
import Excepciones.containsException;
import Modelo.Libro;

public class BibliotecaController {
	
	private List<Libro> biblioteca;
	
	public BibliotecaController() throws IOException, NumberFormatException, ParseException, CampoObligatorioException, IsbnException {
		// TODO Auto-generated constructor stub
		this.biblioteca=leerFichero();
	}

	private List<Libro> leerFichero() throws IOException, NumberFormatException, ParseException, CampoObligatorioException, IsbnException {
		List<Libro> biblioteca=new ArrayList<>();
		Libro libro;
		String isbn="",titulo="",autor="",editorial="",fechaRegistro="",precio="",prestado="";
		FileReader fr=new FileReader("libros.txt");
		BufferedReader br=new BufferedReader(fr);
		String linea=br.readLine();String lineaSplit[];
		while (linea!=null) {
			lineaSplit=linea.split(",");
			isbn=lineaSplit[0];titulo=lineaSplit[1];autor=lineaSplit[2];editorial=lineaSplit[3];
			fechaRegistro=lineaSplit[4];precio=lineaSplit[5];prestado=lineaSplit[6];
			libro=new Libro(isbn, titulo, autor, editorial, fechaRegistro, precio, prestado);
			biblioteca.add(libro);
			libro=null;
			linea=br.readLine();
		}
		
		
		return biblioteca;
	}
	
	public List<Libro> mostrarLibros(){
		return this.biblioteca;
	}
	
	public boolean agregar(String isbn,String titulo,String autor,String editorial,String fechaRegistro,
			String precio,String prestado) throws NumberFormatException, ParseException, CampoObligatorioException, IsbnException, containsException {
		boolean agregado=false;
		Libro libro=new Libro(isbn, titulo, autor, editorial, fechaRegistro, precio, prestado);
		if (!biblioteca.contains(libro)) {
			biblioteca.add(libro);
			agregado=true;
		}
		else throw new containsException();
		return agregado;
	}
	
	public Libro buscar(String isbn) {
		Libro libro=null;
		for (Libro lib:biblioteca) {
			if (isbn.equals(lib.getIsbn()))
				libro=lib;
		}
		return libro;
	}
	
	public boolean eliminar(String isbn) throws NotFoundException{
		boolean eliminado=false;
		Libro libro=buscar(isbn);
		if (libro!=null) {
			biblioteca.remove(libro);
			eliminado=true;
		} else throw new NotFoundException();
			
		return eliminado;
	}
	
	public boolean editar(String isbn,String titulo,String autor,String editorial,String fechaRegistro,
			String precio,String prestado) throws NumberFormatException, CampoObligatorioException, ParseException{
		boolean editado=false;
		Libro libro=buscar(isbn);
		if (libro!=null) {
			libro.setPrecio(precio);libro.setTitulo(titulo);libro.setAutor(autor);libro.setEditorial(editorial);
			libro.setFechaRegistro(fechaRegistro);libro.setPrestado(prestado);
			editado=true;
		}
		
		return editado;
	}
	
	public List<Libro> filtradoAutor(String autor){
		List<Libro> filtrado=new ArrayList<>();
		for (Libro lib:biblioteca) {
			if (autor.equalsIgnoreCase(lib.getAutor()))
				filtrado.add(lib);	
		}
		
		return filtrado;
	}
	
	public List<Libro> filtradoEditorial(String editorial){
		List<Libro> filtrado=new ArrayList<>();
		for (Libro lib:biblioteca) {
			if (editorial.equalsIgnoreCase(lib.getEditorial()))
				filtrado.add(lib);	
		}
		
		return filtrado;
	}
	
	public List<Libro> filtradoFecha(String fecha){
		List<Libro> filtrado=new ArrayList<>();
		for (Libro lib:biblioteca) {
			if (fecha.equalsIgnoreCase(lib.getFechaRegistro().toString()))
				filtrado.add(lib);	
		}
		
		return filtrado;
	}
	
	public List<Libro> filtradoPrecio(String precio){
		List<Libro> filtrado=new ArrayList<>();
		for (Libro lib:biblioteca) {
			if (precio.equalsIgnoreCase(lib.getPrecio()+""))
				filtrado.add(lib);	
			}
		return filtrado;
	}
	
	public List<Libro> filtradoPrestado(String prestado){
		List<Libro> filtrado=new ArrayList<>();
		for (Libro lib:biblioteca) {
			if (prestado.equalsIgnoreCase(lib.isPrestado()+""))
				filtrado.add(lib);	
		}
		
		return filtrado;
	}
	
	public boolean prestar(String isbn) throws CampoObligatorioException,NotFoundException {
		boolean prestado=false;
		Libro libro=buscar(isbn);
		if (libro==null)
			throw new NotFoundException();
		if (libro.isPrestado()==false) {
			libro.setPrestado("true");
			prestado=true;
		}
		
		return prestado;
	}
}
