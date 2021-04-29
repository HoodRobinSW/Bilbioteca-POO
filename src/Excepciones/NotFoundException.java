package Excepciones;

public class NotFoundException extends Exception{

	public NotFoundException() {
		// TODO Auto-generated constructor stub
	}
	
	public String getMessage() {
		return "No se encontró el libro";
	}

}
