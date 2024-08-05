package com.dvp;

import com.dvp.model.dao.ITicketDao;
import com.dvp.model.entity.Ticket;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class PruebaDvpApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaDvpApplication.class, args);
	}

	@Bean
	CommandLineRunner lineRunner(ITicketDao ticketDao){
		return args -> {
			for (int i=0; i<20; i++){
				Ticket ticket = Ticket.builder()
						.id(UUID.randomUUID().toString())
						.usuario("Usuario_" + i)
						.creacion(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
						.estado((int) Math.round( Math.random() ) )
						.build();
				ticketDao.save(ticket);
			}
			//SE CREA UN USUARIO NO ALEATORIO PARA PRUEBAS
			Ticket ticket = Ticket.builder().id("ID1").usuario("Usuario Inicial").creacion(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
					.estado((int) Math.round( Math.random() ) )
					.build();
			ticketDao.save(ticket);
		};
	}

}
