package com.project.ticketprint;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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

public class TicketPrintDAO implements ITicketPrintDAO {
	public final String TRAIN_CODE = "trainCode";
	public final String TRAIN_NAME = "trainName";
	public final String SOURCE_STATION = "sourceStation";
	public final String DESTINATION_STATION = "destinationStation";
	public final String RESERVATION_DATE = "reservationDate";
	public final String AMOUNT_PAID = "amountPaid";
	public final String TRAIN_TYPE = "trainType";

	@Override
	public ITicketPrint ticketPrint(int reservationId) {
		Document document = new Document();

		try {
			PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\sshah\\Desktop\\Dal\\Ticket1.pdf"));
			ITicketPrint ticketPrint = getTicketInformation(reservationId);

			document.open();
			Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

			document.add(new Phrase("\n"));
			document.addTitle("Train Ticket");
			Chunk trainCodeAndNamechunk = new Chunk(ticketPrint.getTrainCode() + " - " + ticketPrint.getTrainName(),
					font);

			document.add(trainCodeAndNamechunk);
			document.add(new Phrase("\n"));
			document.add(Chunk.NEWLINE);
			Chunk trainTypeChunk = new Chunk(ticketPrint.getTrainType(), font);

			document.add(trainTypeChunk);
			document.add(Chunk.NEWLINE);
			document.add(new Phrase("\n"));
			Chunk stationsChunk = new Chunk("Source Station: " + ticketPrint.getSourceStation()

					+ " - " + " Destination Station: " + ticketPrint.getDestinationStation(), font);
			document.add(stationsChunk);
			document.add(new Phrase("\n"));
			document.add(Chunk.NEWLINE);
			Chunk amountPaidChunk = new Chunk(String.valueOf(ticketPrint.getAmountPaid()), font);

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
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statement = null;
		ResultSet resultSet = null;

		try {
			statement = connection.prepareCall("{call getTicketPrintInformation(?)}");
			statement.setInt(1, reservationId);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				ticketPrint.setReservationId(reservationId);
				ticketPrint.setTrainCode(resultSet.getInt(TRAIN_CODE));
				ticketPrint.setTrainName(resultSet.getString(TRAIN_NAME));
				ticketPrint.setSourceStation(resultSet.getString(SOURCE_STATION));
				ticketPrint.setDestinationStation(resultSet.getString(DESTINATION_STATION));
				ticketPrint.setTrainType(resultSet.getString(TRAIN_TYPE));
				ticketPrint.setAmountPaid(resultSet.getDouble(AMOUNT_PAID));
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

}