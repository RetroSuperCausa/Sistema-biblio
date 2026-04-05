# Sistema-biblio
Español:

Sistema de biblioteca en Java con gestión de usuarios, libros y préstamos usando POO.

# Sistema de Biblioteca en Java
Descripción

Sistema de biblioteca hecho en Java usando POO, para gestionar usuarios, bibliotecarios y administradores.
Permite:

Crear, eliminar y mostrar usuarios 

Agregar, editar y eliminar libros 

Prestar y devolver libros 

Menús interactivos según rol (Usuario, Bibliotecario, Admin) 

## Arquitectura
Main.java → Controla el flujo principal y los menús.

modelo/ → Clases de Usuario, Admin, Bibliotecario, Libro y LibroPrestado.

sisbiblio/ → Clase Biblioteca para manejar listas de usuarios y libros.

## Manejo de datos
Uso de ArrayList para usuarios y libros.

Métodos para agregar, eliminar, prestar y devolver libros.

Validaciones para evitar campos vacíos al crear usuarios o libros.

## Flujo del programa
Inicio con menú principal 

Selección de rol (Usuario, Bibliotecario, Admin) 

Inicio de sesión según el rol

Acceso a las funcionalidades correspondientes 

Opción de cerrar sesión o salir del programa 

## Control de errores
Validación de inputs numéricos con try/catch (InputMismatchException)

Prevención de entradas vacías en usuarios y libros

No permite borrar usuarios que tengan libros prestados 

## Avances, dificultades y recomendaciones
Avances: Proyecto funcional con gestión completa de usuarios y libros 

Dificultades: Manejo de ArrayList y validación de menús 

Recomendaciones: Mantener un flujo de menús claro y validar entradas para evitar errores


Inglés:

Java OOP project: Library system with user, book, and loan management.

# Library System in Java
Description

A Java OOP project to manage a library with users, librarians, and admins.
Features include:

Create, delete, and view users 

Add, edit, and remove books 

Borrow and return books 

Interactive menus based on role (User, Librarian, Admin)


## Architecture
Main.java → Controls the main program flow and menus.

modelo/ → Classes: Usuario, Admin, Bibliotecario, Libro, LibroPrestado.

sisbiblio/ → Biblioteca class manages lists of users and books.

## Data Management
Uses ArrayList for users and books.

Methods to add, delete, borrow, and return books.

Validation to avoid empty fields when creating users or books.

## Program Flow

Start with the main menu 

Select role (User, Librarian, Admin) 

Log in according to the selected role

Access role-specific functionalities 

Option to log out or exit the program  
## Error Handling
Numeric input validation using try/catch (InputMismatchException)

Prevents empty input for users and books

Users with borrowed books cannot be deleted 

## Progress, Challenges, and Recommendations

Progress: Fully functional project with complete user and book management 

Challenges: Handling ArrayList and menu validations 

Recommendations: Keep menus clear and validate input to avoid errors
