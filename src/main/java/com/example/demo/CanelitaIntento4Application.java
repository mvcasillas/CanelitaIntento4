package com.example.demo;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.message.MessageAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class CanelitaIntento4Application {


	@Autowired
	private Environment env;
	public static final int pistolabasicdmg=10;
	public static final int espadabasicdmg=10;
	public static ArrayList<Personaje> listapersonajes = new ArrayList<>();

	public static boolean isRegistrado(MessageAuthor usuario){
		for(int i=0;i<listapersonajes.size();i++){
			if(listapersonajes.get(i).getUsuario()!=null && listapersonajes.get(i).getUsuario().equals(usuario)){
				return true;
			}
		}
		return false;
	}

	public static boolean isRegistradoN(MessageAuthor usuario){
		for(int i=0;i<listapersonajes.size();i++){
			if(listapersonajes.get(i).getUsuario()==null && listapersonajes.get(i).getNombre()!=null
					&& listapersonajes.get(i).getNombre().matches(usuario.getDisplayName())){
				return true;
			}
		}
		return false;
	}

	public static int indexRegistrado(MessageAuthor usuario){
		for(int i=0;i<listapersonajes.size();i++){
			if(listapersonajes.get(i).getUsuario().equals(usuario)){
				return i;
			}
		}
		return -1;
	}

	public static int indexPersonaje(MessageAuthor usuario){
		for(int i=0;i<listapersonajes.size();i++){
			if(listapersonajes.get(i).getNombre().equals(usuario.getDisplayName())){
				return i;
			}
		}
		return -1;
	}

	//---------------------------- aquí comienza el main -----------------------------------------

	public static void main(String[] args) {
		SpringApplication.run(CanelitaIntento4Application.class, args);
	}

	@Bean
	@ConfigurationProperties(value = "discord-api")
	public DiscordApi discordApi(){
		String token = env.getProperty("TOKEN");
		DiscordApi api = new DiscordApiBuilder().setToken(token)
				.login()
				.join();

		/*
		//Sistema de balas viejito
		AtomicInteger balas= new AtomicInteger();

		api.addMessageCreateListener(event -> {
			if (event.getMessageContent().equalsIgnoreCase("!cargar")) {
				balas.set(10);
				event.getChannel().sendMessage("Cargado. Tienes "+balas+" balas");
			}
		});

		api.addMessageCreateListener(event -> {
			if (event.getMessageContent().equalsIgnoreCase("!pium")&& balas.get() >0) {
				balas.set(balas.get() - 1);
				event.getChannel().sendMessage("💥BOOM!💥 Te quedan "+balas+" balas");
			}else if(event.getMessageContent().equalsIgnoreCase("!pium")&& balas.get() ==0){
				event.getChannel().sendMessage("¡No te queda munición!");
			}
		});

		*/

		//ASIGNACIÓN DE STATS AUTOMÁTICA
		Personaje lf = new Personaje("Lan Feng", 28, 32, 47, 28, 35, 19);
		Personaje faust = new Personaje ("Faust", 42, 47, 34, 24, 30, 16);
		Personaje douglas = new Personaje("Douglas C..G.", 40, 24, 46, 35, 18, 16);
		Personaje luriel = new Personaje("Luriel", 38, 18, 48, 40, 25, 20);
		Personaje sybil = new Personaje ("Sybil", 40, 37, 28, 28, 40, 23);
		Personaje minerva = new Personaje ("Minerva", 35, 28, 41, 31, 31, 22);
		Personaje kafka = new Personaje ("Kafka", 40, 35, 50, 25, 15, 24);
		Personaje lizeth = new Personaje ("Lizeth", 30, 20, 35, 55, 25, 30);
		Personaje shura = new Personaje("Shura", 40, 12, 28, 58, 30, 30);
		Personaje edan = new Personaje("Edán", 50, 28, 30, 40, 10, 20);
		Personaje vvelial = new Personaje("Vvelial", 30, 40, 50, 50, 10, 14);

		listapersonajes.add(lf);
		listapersonajes.add(faust);
		listapersonajes.add(douglas);
		listapersonajes.add(luriel);
		listapersonajes.add(sybil);
		listapersonajes.add(minerva);
		listapersonajes.add(kafka);
		listapersonajes.add(lizeth);
		listapersonajes.add(shura);
		listapersonajes.add(edan);
		listapersonajes.add(vvelial);




		//SISTEMA DE REGISTRO DE STATS--------------------------------------------------
		api.addMessageCreateListener(event -> {
			if (event.getMessageContent().matches("!registrar "+"[0-9]{2}\\s[0-9]{2}\\s[0-9]{2}\\s[0-9]{2}\\s[0-9]{2}\\s[0-9]{2}")) {
				MessageAuthor usuario= event.getMessageAuthor();
				boolean registrado=false;
				for(int i=0;i<listapersonajes.size() && registrado==false;i++){
					if(listapersonajes.get(i).getNombre().equals(usuario.getDisplayName())){
						registrado=true;
						event.getChannel().sendMessage("¡Tus stats ya están registradas!");
					}
				}

				if(registrado==false){
					int pdv=Integer.parseInt(event.getMessageContent().substring(11,13));
					int fue=Integer.parseInt(event.getMessageContent().substring(14,16));
					int agi=Integer.parseInt(event.getMessageContent().substring(17,19));
					int des=Integer.parseInt(event.getMessageContent().substring(20,22));
					int car=Integer.parseInt(event.getMessageContent().substring(23,25));
					int vol=Integer.parseInt(event.getMessageContent().substring(26,28));

					Personaje nuevopj = new Personaje(usuario.getDisplayName(), pdv, fue, agi, des, car, vol);
					listapersonajes.add(nuevopj);
					event.getChannel().sendMessage("Stats registradas con éxito.");
				}
			}
		});

		//Mostrar las stats
		api.addMessageCreateListener(event -> {
			if (event.getMessageContent().matches("!verstats")) {
				MessageAuthor usuario= event.getMessageAuthor();
				boolean registrado=false;
				for(int i=0;i<listapersonajes.size() && registrado==false;i++){
					if(listapersonajes.get(i).getNombre().equals(usuario.getDisplayName())){
						registrado=true;
						event.getChannel().sendMessage(listapersonajes.get(i).toString());
					}
				}

				if(registrado==false){
					event.getChannel().sendMessage("No tienes stats registradas.");
				}
			}
		});


		//SISTEMA DE ARMAS Y MUNICIÓN -----------------------------------------------------------
		ArrayList<Municion> propietariosarmas = new ArrayList<>();

		//Recargar y registrar propietario de arma
		api.addMessageCreateListener(event -> {
			if (event.getMessageContent().matches("!recargar")) {
				MessageAuthor usuario= event.getMessageAuthor();
				boolean registrado=false;
				for(int i=0;i<propietariosarmas.size() && registrado==false;i++){
					if(propietariosarmas.get(i).getPropietarioNombre().equals(usuario.getDisplayName())){
						registrado=true;
						propietariosarmas.get(i).set(10);
						event.getChannel().sendMessage(propietariosarmas.get(i).getPropietarioNombre()+", has recargado tu munición. Tienes "+ propietariosarmas.get(i).get()+" balas.");
					}
				}

				if(registrado==false){
					Municion nuevopropietario = new Municion(10,usuario);
					propietariosarmas.add(nuevopropietario);
					event.getChannel().sendMessage(nuevopropietario.getPropietarioNombre()+", has recargado tu munición. Tienes "+ nuevopropietario.get()+" balas.");
				}
			}
		});

		//Disparar con número
		api.addMessageCreateListener(event -> {
			if (event.getMessageContent().matches("!dispara "+"[0-9]{2}")&& !isRegistradoN(event.getMessageAuthor())) {
				//event.getChannel().sendMessage("Recibido.");
				MessageAuthor usuario= event.getMessageAuthor();
				int municionactual=0;
				int propietarioindex=-1;
				for(int i=0;i<propietariosarmas.size();i++){
					if(propietariosarmas.get(i).getPropietarioNombre().equals(usuario.getDisplayName())){
						municionactual=propietariosarmas.get(i).get();
						propietarioindex=i;
					}
				}

				if (municionactual>0){
					int recibeDestreza=Integer.parseInt(event.getMessageContent().substring(9));

					//Calcula éxito
					if((recibeDestreza*3)>=(Math.random()*100)){
						//Si tiene éxito, calcula daño
						int atk = recibeDestreza/4;
						int pluscritico= (int)(Math.random()*10);
						int dmg= pistolabasicdmg+atk+pluscritico;

						//Resta uno a munición
						propietariosarmas.get(propietarioindex).set(propietariosarmas.get(propietarioindex).get() -1);

						event.getChannel().sendMessage("💥BOOM!💥 Has hecho " +dmg+" puntos de daño.\nTe quedan "
								+propietariosarmas.get(propietarioindex).get()+" balas");

					}else{
						//Si fallas resta uno a munición también
						propietariosarmas.get(propietarioindex).set(propietariosarmas.get(propietarioindex).get() -1);

						event.getChannel().sendMessage("¡Has fallado!\n" +
								"Te quedan "+propietariosarmas.get(propietarioindex).get()+" balas");
					}


				}else{
					event.getChannel().sendMessage("¡No tienes munición!");
				}

			}
		});

		//Disparar con stats registradas
		api.addMessageCreateListener(event -> {
			if (event.getMessageContent().matches("!dispara")) {
				//event.getChannel().sendMessage("Recibido.");
				MessageAuthor usuario= event.getMessageAuthor();
				int municionactual=0;
				int propietarioindex=-1;

				//Comprueba que has recargado (estás en la lista de propietariosarmas)
				for(int i=0;i<propietariosarmas.size();i++){
					if(propietariosarmas.get(i).getPropietarioNombre().equals(usuario.getDisplayName())){
						municionactual=propietariosarmas.get(i).get();
						propietarioindex=i;
					}
				}

				//Comprueba que las stats están registradas
				int personajeindex=-1;

				if(isRegistradoN(usuario)){
					personajeindex=indexPersonaje(usuario);
					int destreza=listapersonajes.get(personajeindex).getDestreza();

					if (municionactual>0){
						//Calcula éxito
						if((destreza*3)>=(Math.random()*100)){
							//Si tiene éxito, calcula daño
							int atk = destreza/4;
							int pluscritico= (int)(Math.random()*10);
							int dmg= pistolabasicdmg+atk+pluscritico;

							//Resta uno a munición
							propietariosarmas.get(propietarioindex).set(propietariosarmas.get(propietarioindex).get() -1);

							event.getChannel().sendMessage("💥BOOM!💥 Has hecho " +dmg+" puntos de daño.\nTe quedan "
									+propietariosarmas.get(propietarioindex).get()+" balas");

						}else{
							//Si fallas resta uno a munición también
							propietariosarmas.get(propietarioindex).set(propietariosarmas.get(propietarioindex).get() -1);

							event.getChannel().sendMessage("¡Has fallado!\n" +
									"Te quedan "+propietariosarmas.get(propietarioindex).get()+" balas");
						}

					}else{
						event.getChannel().sendMessage("¡No tienes munición!");
					}

				}
				else{
					event.getChannel().sendMessage("Error. Registra tus stats o introduce tu destreza en el comando");
				}

			}
		});

		//SISTEMA DE ARMAS DE CAC ----------------------------------------------------
		//Atacar con stats sin registrar
		api.addMessageCreateListener(event -> {
			if (event.getMessageContent().matches("!ataca "+"[0-9]{2}\\s[0-9]{2}")&& !isRegistradoN(event.getMessageAuthor())) {
				int recibeFuerza=Integer.parseInt(event.getMessageContent().substring(7,9));
				int recibeDestreza=Integer.parseInt(event.getMessageContent().substring(10));

				if((recibeDestreza*3)>=(Math.random()*100)){
					//Si tiene éxito, calcula daño
					int atk = recibeFuerza;
					int pluscritico= (int)(Math.random()*10);
					int dmg= espadabasicdmg+atk+pluscritico;

					event.getChannel().sendMessage(":crossed_swords: ¡Has hecho "+dmg+" puntos de daño!");

				}else{
					event.getChannel().sendMessage("¡Has fallado!");
				}

			}
		});

		//Atacar con stats registradas
		api.addMessageCreateListener(event -> {
			if (event.getMessageContent().matches("!ataca")) {
				MessageAuthor usuario= event.getMessageAuthor();
				//Comprueba que las stats están registradas
				int personajeindex=-1;
				int fuerza;
				int destreza;

				if(isRegistradoN(usuario)){
					personajeindex=indexPersonaje(usuario);
					fuerza=listapersonajes.get(personajeindex).getFuerza();
					destreza=listapersonajes.get(personajeindex).getDestreza();

					if((destreza*3)>=(Math.random()*100)){
						//Si tiene éxito, calcula daño
						int atk = fuerza;
						int pluscritico= (int)(Math.random()*10);
						int dmg= espadabasicdmg+atk+pluscritico;

						event.getChannel().sendMessage(":crossed_swords: ¡Has hecho "+dmg+" puntos de daño!");

					}else{
						event.getChannel().sendMessage("¡Has fallado!");
					}

				}else{
					event.getChannel().sendMessage("Error. Registra tus stats o introduce fuerza y destreza en el comando");
				}

			}
		});

		return api;
	}
}
