package Excepciones;

public class CampoObligatorioException extends Exception{

	public CampoObligatorioException() {
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return "El campo no puede estar vacio";
	}
}
