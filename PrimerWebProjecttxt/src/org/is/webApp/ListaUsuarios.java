package org.is.webApp;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class ListaUsuarios {
	
	private ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
	
	//private String rutaArchivo = "../workspace/PrimerWebProject/WebContent/datos/usuarios.txt";
	// dev on Eclipse
	// private String rutaArchivo = "PrimerWebProjecttxt/WebContent/datos/usuarios.txt";

	// deploy on Ubuntu
	private String rutaArchivo = "$CATALINA_HOME/webapps/PrimerWebProject-0.0.1-joaquin/datos/usuarios.txt";
	
	/* TIP: Edit tomcat defaul directory:
	*  and I edit the tomcat argument in Eclipse IDE. 
	*  (Run -> Run Config... -> Apache Tomcat -> [Click] Tomcat vX Server -> 
	*  at the right screen, click "Argument" -> Working directory section -> 
	*  I change to Other and specify my actual working directory.)
	*/
	
	/**
	 * @return the listaUsuarios
	 */
	public ArrayList<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	/**
	 * @param listaUsuarios the listaUsuarios to set
	 */
	public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public ListaUsuarios (){
		// cargamos la lista de usuarios del archivo de datos
		// setListaUsuarios(cargarUsuariosDelArchivo(this.rutaArchivo));
		this.listaUsuarios = cargarUsuariosDelArchivo();
		
		for (int i=0; i<getListaUsuarios().size(); i++){
			Usuario u = getListaUsuarios().get(i);
			System.out.println(u.toString());
		}

	}
	
	public ArrayList<Usuario> cargarUsuariosDelArchivo (){
		System.out.println("Cargando Usuarios de: "+ rutaArchivo);
		ArrayList<Usuario> usuariosLeidos = new ArrayList<Usuario>();
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(rutaArchivo));
			String cadena;
			int i = 0;
			while ((cadena = in.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(cadena, "|");
				String username = null, password = null; 
				String fecha=null;

				while (tokenizer.hasMoreTokens()) {
					username = tokenizer.nextToken();
					password= tokenizer.nextToken();
					fecha = tokenizer.nextToken();
				}

				Usuario usuario= new Usuario(username, password, fecha);
				// A�adir usuario a la lista
				usuariosLeidos.add(usuario);
				i++;
			}
			in.close();
			System.out.println("Numero Usuarios leidos:" + i );
		} catch (IOException e) {
			System.out.println("Error en cargarUsuariosDelArchivo :" + e.getMessage());
			System.out.println(System.getProperty("user.dir"));
		}

		return usuariosLeidos; // ArrayList<Usuario> 
	}
	
	public void guardarUsuariosEnArchivo (){
		
		//this.rutaArchivo.concat("salida.txt");
		System.out.println("Guardando Usuarios en: "+ this.rutaArchivo);

		try {
			PrintWriter out = new PrintWriter(new FileWriter(new File(
					this.rutaArchivo)));

			for (int i = 0; i < this.listaUsuarios.size(); i++) {
				
				String linea = 
						this.listaUsuarios.get(i).getUsername() 
						+ "|"
						+ this.listaUsuarios.get(i).getPassword()
						+ "|"
						+ this.listaUsuarios.get(i).getStringFechaCreacion();
				out.println(linea);
			}

			out.close();
		} catch (IOException e) {
			System.out.println("Excepci�n al guardar archivo de usuarios");
		}

	}
	
	public int insertarUsuario(Usuario u){
		// Devuelve 1 si el usuario se inserta correctamente
		// Devuelve 0 en caso contrario
		
		/*
		 * @pre El nuevo nombre de usuario no debe existir en la lista de usuarios
		 */
		int encontrado = 0;
		for (int i=0; i<listaUsuarios.size() && encontrado==0; i++ ){
			if (listaUsuarios.get(i).getUsername().equals(u.getUsername()))
				encontrado = 1;
		}
		
		if (encontrado==0) {
			listaUsuarios.add(u);
			guardarUsuariosEnArchivo();
			return 1;
		}
		else return 0;
	}

}
