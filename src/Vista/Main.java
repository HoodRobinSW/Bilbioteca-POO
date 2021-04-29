package Vista;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import Controlador.BibliotecaController;
import Excepciones.CampoObligatorioException;
import Excepciones.IsbnException;
import Modelo.Libro;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Libro libro;
//		String isbn="978-84-8130-252-3",titulo="El capitan Alatriste",autor="Benancio Perez Reverte",
//				editorial="Alfaguara",fechaRegistro="2015-03-15",precio="20.5",prestado="true";
		BibliotecaController bc=null;

		try {
//			libro=new Libro(isbn, titulo, autor, editorial, fechaRegistro, precio, prestado);
//			System.out.println("Libro: "+libro.toString());
			bc=new BibliotecaController();
		} catch (NumberFormatException | ParseException | CampoObligatorioException | IsbnException | IOException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
		
		List<Libro> libros=bc.mostrarLibros();
		
		Menu menu=new Menu(libros);
	}

}
