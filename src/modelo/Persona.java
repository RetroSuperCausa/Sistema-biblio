package modelo;

public abstract class Persona {
    String nombre;
    String apellidoPaterno;
    String apellidoMaterno;

    //Constructor
    public Persona(String nombre, String apellidoPaterno, String apellidoMaterno) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    public void mostrarDatos(){
        System.out.println("Nombre: "+nombre + " " + apellidoPaterno + " " + apellidoMaterno);
    }
}
