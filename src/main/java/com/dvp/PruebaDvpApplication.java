package com.dvp;

import com.dvp.model.dao.ITicketDao;
import com.dvp.model.dao.IUsuarioDao;
import com.dvp.model.entity.Ticket;
import com.dvp.model.entity.Usuario;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class PruebaDvpApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaDvpApplication.class, args);
	}

	@Bean
	CommandLineRunner lineRunner(ITicketDao ticketDao, IUsuarioDao usrDao){
		Random random = new Random();
		return args -> {
			List.of("Usuario_1","Usuario_2","Usuario_3").forEach(usr -> {
				Usuario usuario = Usuario.builder().nombre(usr).build();
				usrDao.save(usuario);
			});
			usrDao.findAll().forEach( usuario -> {
				for (int i=0; i<20; i++){
					Ticket ticket = Ticket.builder()
							.id(UUID.randomUUID().toString())
							.usuario(usuario)
							.creacion(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
							.estado((int) Math.round( Math.random() ) )
							.build();
					ticketDao.save(ticket);
				}
			});

			//SE CREA UN USUARIO NO ALEATORIO PARA PRUEBAS
		//	Ticket ticket = Ticket.builder().id("ID1").usuario("Usuario Inicial").creacion(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
		//			.estado((int) Math.round( Math.random() ) )
		//			.build();
		//	ticketDao.save(ticket);
		};
	}

}
