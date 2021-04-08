package com.project.ticketemail;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.project.database.DatabaseAbstactFactory;
import com.project.database.IDatabaseUtilities;
import com.project.reservation.IPassengerInformation;
import com.project.reservation.ReservationAbstractFactory;

public class TicketEmailDAO implements ITicketEmailDAO{

	public final String trainCodeColumnName = "trainCode";
	public final String trainNameColumnName = "trainName";
	public final String sourceStationColumnName = "sourceStation";
	public final String destinationStationColumnName = "destinationStation";
	public final String reservationDateColumnName = "reservationDate";
	public final String amountPaidColumnName = "amountPaid";
	public final String trainTypeColumnName = "trainType";
	public final String firstNameColumnName = "firstName";
	public final String lastNameColumnName = "lastName";
	public final String ageColumnName = "age";
	public final String coachNumberColumnName = "coachNumber";
	public final String seatNumberColumnName = "seatNumber";

	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	    
	    mailSender.setUsername("sshahsanket31@gmail.com");
	    mailSender.setPassword("Sanket@31");
	    
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    
	    return mailSender;
	}
	
	@Override
	public ITicketEmail ticketEmail(int reservationId) {
		
		ITicketEmail ticketEmail = getTicketInformation(reservationId);
		ticketEmail.setPassengerInformation(getPassengerInformation(reservationId));
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = authentication.getName();
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("sshahsanket31@gmail.com");
		message.setTo(userEmail);
		message.setSubject("Train Ticket");
		
		String messageForEmail = "Hi, \n\n Greetings From Railway Reservation System.\n\n Please find below ticket information:";
		messageForEmail+="\n\nTrain Code and Train Name: "+ticketEmail.getTrainCode()+" - "+ticketEmail.getTrainName();
		messageForEmail+="\nTrain Type: "+ticketEmail.getTrainType();
		messageForEmail+="\nSource Station: "+ticketEmail.getSourceStation()
		+" - "+" Destination Station: "+ticketEmail.getDestinationStation();
		
		for(int i=0; i<ticketEmail.getPassengerInformation().size(); i++) {
			messageForEmail+="\n\nPassenger "+String.valueOf(i+1);
			messageForEmail+="\n\nName: "+ticketEmail.getPassengerInformation().get(i).getFirstName()+" "+ticketEmail.getPassengerInformation().get(i).getLastName();
			messageForEmail+="\nAge: "+String.valueOf(ticketEmail.getPassengerInformation().get(i).getAge());
			messageForEmail+="\nCoach Number: "+ticketEmail.getPassengerInformation().get(i).getCoachNumber();
			messageForEmail+="\nSeat Number: "+String.valueOf(ticketEmail.getPassengerInformation().get(i).getPassengerInformationId());
		}
		
		messageForEmail+="\n\nAmount Paid: "+String.valueOf(ticketEmail.getAmountPaid());
		message.setText(messageForEmail);
		getJavaMailSender().send(message);
		return ticketEmail;
	}
	
	private ITicketEmail getTicketInformation(int reservationId) {
		ITicketEmail ticketEmail = new TicketEmail();
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities =  databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareCall("{call getTicketPrintInformation(?)}");
			statement.setInt(1, reservationId);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				ticketEmail.setReservationId(reservationId);
				ticketEmail.setTrainCode(resultSet.getInt(trainCodeColumnName));
				ticketEmail.setTrainName(resultSet.getString(trainNameColumnName));
				ticketEmail.setSourceStation(resultSet.getString(sourceStationColumnName));
				ticketEmail.setDestinationStation(resultSet.getString(destinationStationColumnName));
				ticketEmail.setTrainType(resultSet.getString(trainTypeColumnName));
				ticketEmail.setAmountPaid(resultSet.getDouble(amountPaidColumnName));
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeStatement(statement);
			databaseUtilities.closeConnection(connection);
		}
		return ticketEmail;
	}
	
	private List<IPassengerInformation> getPassengerInformation(int reservationId) {
		List<IPassengerInformation> passengerInformation = new ArrayList<>();
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities =  databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statement = null;
		ResultSet resultSet = null;
		ReservationAbstractFactory reservationAbstractFactory = ReservationAbstractFactory.instance();
		try {
			statement = connection.prepareCall("{call getPassengerInfo(?)}");
			statement.setInt(1, reservationId);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				IPassengerInformation passengerInfo = reservationAbstractFactory.createNewPassengerInformation();
				passengerInfo.setFirstName(resultSet.getString(firstNameColumnName));
				passengerInfo.setLastName(resultSet.getString(lastNameColumnName));
				passengerInfo.setAge(resultSet.getInt(ageColumnName));
				passengerInfo.setCoachNumber(resultSet.getString(coachNumberColumnName));
				int seatNumber = resultSet.getInt(seatNumberColumnName);
				passengerInfo.setPassengerInformationId(seatNumber);
				passengerInformation.add(passengerInfo);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeStatement(statement);
			databaseUtilities.closeConnection(connection);
		}
		return passengerInformation;
	}
}
