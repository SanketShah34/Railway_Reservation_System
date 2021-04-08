package com.project.ticketprint;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.project.database.DatabaseAbstactFactory;
import com.project.database.IDatabaseUtilities;
import com.project.reservation.IPassengerInformation;
import com.project.reservation.ReservationAbstractFactory;

public class TicketPrintDAO implements ITicketPrintDAO{

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
	
	@Override
	public ITicketPrint ticketPrint(int reservationId) {
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(System.getProperty("user.home") + "\\Desktop\\Ticket.pdf"));
		
			ITicketPrint ticketPrint = getTicketInformation(reservationId);
			ticketPrint.setPassengerInformation(getPassengerInformation(reservationId));
			document.open();
			Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			
			document.add(new Phrase("\n"));
			Chunk ticketTitle = new Chunk("Train Ticket", font);
			document.add(ticketTitle);
			document.add(new Phrase("\n"));
			document.add(new Phrase("\n"));
			Chunk trainCodeAndNamechunk = new Chunk("Train Code and Train Name: "+ticketPrint.getTrainCode()+" - "+ticketPrint.getTrainName(), font);
			document.add(trainCodeAndNamechunk);
			document.add(new Phrase("\n"));
			document.add(new Phrase("\n"));
			Chunk trainTypeChunk = new Chunk("Train Type: "+ticketPrint.getTrainType(), font);
			document.add(trainTypeChunk);
			document.add(new Phrase("\n"));
			document.add(new Phrase("\n"));
			Chunk stationsChunk = new Chunk("Source Station: "+ticketPrint.getSourceStation()
			+" - "+" Destination Station: "+ticketPrint.getDestinationStation(), font);
			document.add(stationsChunk);
			for(int i=0; i<ticketPrint.getPassengerInformation().size(); i++) {
				document.add(new Phrase("\n"));
				document.add(new Phrase("\n"));
				Chunk passenger = new Chunk("Passenger "+String.valueOf(i+1), font);
				document.add(passenger);
				document.add(new Phrase("\n"));
				document.add(new Phrase("\n"));
				Chunk name = new Chunk("Name: "+ticketPrint.getPassengerInformation().get(i).getFirstName()+" "+ticketPrint.getPassengerInformation().get(i).getLastName(), font);
				document.add(name);
				document.add(new Phrase("\n"));
				document.add(new Phrase("\n"));
				Chunk age = new Chunk("Age: "+String.valueOf(ticketPrint.getPassengerInformation().get(i).getAge()), font);
				document.add(age);
				document.add(new Phrase("\n"));
				document.add(new Phrase("\n"));
				Chunk coachNumber = new Chunk("Coach Number: "+ticketPrint.getPassengerInformation().get(i).getCoachNumber(), font);
				document.add(coachNumber);
				document.add(new Phrase("\n"));
				document.add(new Phrase("\n"));
				Chunk seatNumber = new Chunk("Seat Number: "+String.valueOf(ticketPrint.getPassengerInformation().get(i).getPassengerInformationId()), font);
				document.add(seatNumber);
			}
			document.add(new Phrase("\n"));
			document.add(new Phrase("\n"));
			document.add(new Phrase("\n"));
			Chunk amountPaidChunk = new Chunk("Amount Paid: "+String.valueOf(ticketPrint.getAmountPaid()), font);
			document.add(amountPaidChunk);
			document.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	private ITicketPrint getTicketInformation(int reservationId) {
		ITicketPrint ticketPrint = new TicketPrint();
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
				ticketPrint.setReservationId(reservationId);
				ticketPrint.setTrainCode(resultSet.getInt(trainCodeColumnName));
				ticketPrint.setTrainName(resultSet.getString(trainNameColumnName));
				ticketPrint.setSourceStation(resultSet.getString(sourceStationColumnName));
				ticketPrint.setDestinationStation(resultSet.getString(destinationStationColumnName));
				ticketPrint.setTrainType(resultSet.getString(trainTypeColumnName));
				ticketPrint.setAmountPaid(resultSet.getDouble(amountPaidColumnName));
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeStatement(statement);
			databaseUtilities.closeConnection(connection);
		}
		return ticketPrint;
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
