package util;

import implementacionesDAO.FactoryDAO;
import interfacesDAO.UsuarioDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Administrador;
import model.Usuario;
import model.Viajero;

public class Test {
	public static void main(String[] args) throws ParseException {
		
		UsuarioDAO udao = FactoryDAO.getUsuarioDAO();
		
		//Registrar Usuarios
		/*SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdt = new SimpleDateFormat("HH:mm");*/
		
		/*Date fechanac1 = sdf.parse("1994-01-01");
		Usuario usuario1 = new Viajero("ns","ns","Nicolas","Schwab","2211234567","nicoschwab@hotmail.com",fechanac1,"Escuchar musica, tomar mate");
		udao.registrar(usuario1);*/
		
		Administrador usuario2 = new Administrador("admin","admin");
		udao.registrar(usuario2);
		
		/*Date fechanac3 = sdf.parse("1987-01-01");
		Usuario usuario3 = new Viajero("jpq","jpq","Juan Pablo","Quiñones","2216081502","jpq1987@gmail.com",fechanac3,"Tomar mate y hablar");
		udao.registrar(usuario3);*/
		
		/*
		//Listar Usuarios		 
		List<Usuario> usuarios = udao.listar();
		System.out.println("");
		System.out.println("Listado de usuarios registrados");
		System.out.println("");
		for (Usuario usr : usuarios) {
			System.out.println("Usuario: " + usr.getUsuario() + " - Clave: " + usr.getClave());
		}
		
		//Modificar Usuario
		Usuario prueba=udao.encontrar(1);
		prueba.setClave("ClaveVieja");		
		udao.modificar(prueba);
		
		//Listar Usuarios
		usuarios = udao.listar();
		System.out.println("");
		System.out.println("Listado de usuarios ya modificados");
		System.out.println("");
		for (Usuario usr : usuarios) {
			System.out.println("Usuario: " + usr.getUsuario() + " - Clave: " + usr.getClave());
		}
		
		Usuario usr = udao.existe("jpq1987", "jpq2014");
		System.out.println(usr);
		
		//Borrar Usuario						
		udao.eliminar(1);
		
		//Listar Usuarios
		usuarios = udao.listar();
		System.out.println("");
		System.out.println("Listado de usuarios despues del borrado");
		System.out.println("");
		for (Usuario usr1 : usuarios) {
			System.out.println("Usuario: " + usr1.getUsuario() + " - Clave: " + usr1.getClave());
		}
				
		EventoDAO edao = FactoryDAO.getEventoDAO();
		//Registrar Evento
		Date fecha = sdft.parse("2014-10-10 10:25");
		Evento evento = new Evento("EkoParty",fecha,"www.ekoparty.com","Buenos Aires");
		edao.registrar(evento);		
		
		//Listar Eventos
		List<Evento> eventos = edao.listar();
		System.out.println("");
		System.out.println("Listado de eventos");
		System.out.println("");
		for (Evento eve : eventos) {
			System.out.println("Evento: " + eve.getNombre() + " - Ubicacion: " + eve.getUbicacion());
		}
		
		//Modificar Evento
		evento = edao.encontrar(1);
		evento.setNombre("GDGFest");		
		edao.modificar(evento);
		
		//Listar Eventos
		eventos = edao.listar();
		System.out.println("");
		System.out.println("Listado de eventos modificados");
		System.out.println("");
		for (Evento eve : eventos) {
			System.out.println("Evento: " + eve.getNombre() + " - Ubicacion: " + eve.getUbicacion());
		}	
		
		ViajeDAO vdao = FactoryDAO.getViajeDAO();
		//Registrar Viaje
		Date fechainicio = sdf.parse("2014-10-10");
		Date fechafin = sdf.parse("2015-09-10");
		Date horaPartida = sdt.parse("10:30");
		Date horaLlegada = sdt.parse("18:30");
		List<DiasSemana> diasSem = new ArrayList<DiasSemana>();
		diasSem.add(DiasSemana.LUNES);
		diasSem.add(DiasSemana.MIERCOLES);
		Viajero conductor = udao.encontrar(3);
		evento = edao.encontrar(1);
		
		Viaje viaje1 = new Viaje(fechainicio,fechafin,diasSem,horaPartida,horaLlegada,4,"Plaza Moreno","Facultad de Informatica",null,conductor);
		vdao.registrar(viaje1);
		
		Viaje viaje2 = new Viaje(fechainicio,fechafin,diasSem,horaPartida,horaLlegada,4,"Plaza Italia",null,evento,conductor);
		vdao.registrar(viaje2);
		
		
		//Listar Viajes
		List<Viaje> viajes = vdao.listar();
		System.out.println("");
		System.out.println("Listado de viajes registrados");
		System.out.println("");
		for (Viaje viaje : viajes) {
			System.out.println("Viaje: " + viaje.getDireccionOrigen() + " - " + viaje.getDireccionDestino()+ "  Estado: "+ viaje.getActivo());
		}
		
		//Modificar Viaje
		viaje1 = vdao.encontrar(1);
		viaje1.setDireccionDestino("Facultad de Medicina");		
		vdao.modificar(viaje1);

		//Listar Viajes
		viajes = vdao.listar();
		System.out.println("");
		System.out.println("Listado de viajes modificados");
		System.out.println("");
		for (Viaje viaje : viajes) {
			System.out.println("Viaje: " + viaje.getDireccionOrigen() + " - " + viaje.getDireccionDestino() + "  Estado: "+ viaje.getActivo());
		}
		
		//Baja logica
		viaje1 = vdao.encontrar(1);
		viaje1.setActivo(false);	
		vdao.modificar(viaje1);

		//Listar Viajes
		viajes = vdao.listar();
		System.out.println("");
		System.out.println("Listado de viajes despues del borrado");
		System.out.println("");
		for (Viaje viaje : viajes) {
			System.out.println("Viaje: " + viaje.getDireccionOrigen() + " - " + viaje.getDireccionDestino() + "  Estado: "+ viaje.getActivo());
		}
		
		Viajero usr = udao.encontrar(3);
		System.out.println("");
		System.out.println("Listado de usuarios registrados");
		System.out.println("");
		for (Viaje v : ((Viajero)usr).getMisViajesConductor()) {
			System.out.println("Viaje: " + v.getDireccionOrigen() + " - " + v.getDireccionDestino());			
		}
		
		*/
		
	}
}