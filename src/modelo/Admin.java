package modelo;

import sisbiblio.LibroPrestado;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin extends Usuario {
    public Admin(String nombre, String apellidoPaterno, String apellidoMaterno,
                 String nombreUsuario, String contrasenia){
        super(nombre,apellidoPaterno,apellidoMaterno,nombreUsuario,contrasenia);
    }

    //Metodos

    //Login
    @Override
    public boolean iniciarSesion(String user, String pass) {
        return this.getNombreUsuario().equals(user) && this.getContrasenia().equals(pass);
    }

    Scanner sc = new Scanner(System.in);
    //METODO PARA AGREGAR USUARIOS
    public void añadirUsuario(ArrayList<Usuario> listaUsuario) {
        String nombreUsuario;
        String contrasenia;
        do{
            System.out.print("Nombre:");
            nombre = sc.nextLine().trim();
            if (nombre.isEmpty()){
                System.out.println("Ingrese un titulo valido");
            }
        }while(nombre.isEmpty());

        do{
            System.out.print("Apellido paterno: ");
            apellidoPaterno = sc.nextLine().trim();
            if (apellidoPaterno.isEmpty()){
                System.out.println("Ingrese un titulo valido");
            }
        }while(apellidoPaterno.isEmpty());

        do{
            System.out.print("Apellido materno:");
            apellidoMaterno = sc.nextLine().trim();
            if (apellidoMaterno.isEmpty()){
                System.out.println("Ingrese un titulo valido");
            }
        }while(apellidoMaterno.isEmpty());

        do{
            System.out.print("Nombre de Usuario: ");
            nombreUsuario = sc.nextLine().trim();

            boolean existe = false;

            if(nombreUsuario.isEmpty()){
                System.out.println("No puede estar vacío");
            } else {
                for(Usuario u : listaUsuario){
                    if(u.getNombreUsuario().equalsIgnoreCase(nombreUsuario)){
                        existe = true;
                        break;
                    }
                }
                if(existe){
                    System.out.println("Ese nombre de usuario ya existe");
                    nombreUsuario = ""; // fuerza  la repeticion del do-while
                }
            }

        }while(nombreUsuario.isEmpty());

        do{
            System.out.print("Contraseña: ");
            contrasenia = sc.nextLine().trim();
            if (contrasenia.isEmpty()){
                System.out.println("Ingrese un titulo valido");
            }
        }while(contrasenia.isEmpty());


        Usuario nuevo = new Usuario(nombre, apellidoPaterno, apellidoMaterno,nombreUsuario,contrasenia);
        listaUsuario.add(nuevo);
        System.out.println("\u001B[32mUsuario agregado correctamente\u001B[0m");
    }

    //METODO PARA ELMINAR USUARIOS
    public void eliminarUsuario(ArrayList<Usuario> listaUsuarios){
        if(listaUsuarios.isEmpty()){
            System.out.println("\u001B[33mNo hay usuarios para eliminar.\u001B[0m");
            return;
        }

        int opcion;
        System.out.print("Seleccione el numero del usuario a eliminar: ");
        try{
            opcion = sc.nextInt();
        }catch(InputMismatchException e){
            System.out.println("\u001B[31mDebes ingresar un numero valido...\u001B[0m");
            sc.nextLine();
            return;
        }
        sc.nextLine();

        if(opcion > 0 && opcion <= listaUsuarios.size()){
            Usuario usuario = listaUsuarios.get(opcion - 1);

            if(!usuario.getLibrosPrestados().isEmpty()){
                System.out.println("\u001B[31mNo se puede eliminar al usuario, tiene libros prestados.\u001B[0m");
                return;
            }

            Usuario eliminado = listaUsuarios.remove(opcion - 1);
            System.out.println("\u001B[32mUsuario " + eliminado.getNombreUsuario() + " eliminado correctamente.\u001B[0m");
        } else {
            System.out.println("\u001B[31mOpcion invalida\u001B[0m");
        }
    }

    //Metodo que muestra los usuarios registrados
    public void mostrarUsuarios(ArrayList<Usuario> listaUsuarios){
        if(listaUsuarios.isEmpty()){
            System.out.println("\u001B[33mNO HAY USUARIOS REGISTRADOS\u001B[0m");
            return;
        }
        System.out.println("Lista de usuarios:");
        for(int i = 0; i < listaUsuarios.size(); i++){
            System.out.println((i+1) + ". " + listaUsuarios.get(i).getNombreUsuario());
        }
    }

}