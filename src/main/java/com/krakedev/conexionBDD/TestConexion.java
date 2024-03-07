package com.krakedev.conexionBDD;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TestConexion {

	public static void main(String[] args) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {

			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/prueba", "postgres", "admin");
			System.out.println("Conexion Exitosa");
			/*
			 * preparedStatement = connection.
			 * prepareStatement("INSERT INTO clientes (id,nombre,apellido,email,telefono) VALUES (?,?,?,?,?)"
			 * ); preparedStatement.setInt(1, 3); preparedStatement.setString(2, "Diego");
			 * preparedStatement.setString(3, "Rodriguez"); preparedStatement.setString(4,
			 * "diegol12@gmail.com"); preparedStatement.setString(5, "0987652122");
			 * preparedStatement.executeUpdate();
			 * 
			 * 
			 * 
			 */

			preparedStatement = connection.prepareStatement(
					"INSERT INTO productos (id,nombre,precio,fecha_produccion,hora_produccion) VALUES (?,?,?,?,?)");
			preparedStatement.setInt(1, 4);
			preparedStatement.setString(2, "Audifonos");
			preparedStatement.setBigDecimal(3, new BigDecimal(5550.12));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			String fechasStr = "2024/01/23 11:09:10";
			java.util.Date fecha;
			try {
				fecha = sdf.parse(fechasStr);
				long fechaMilis = fecha.getTime();
				java.sql.Date fechaSQL = new Date(fechaMilis);
				Time timeSQL = new Time(fechaMilis);
				preparedStatement.setDate(4, fechaSQL);
				preparedStatement.setTime(5, timeSQL);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			preparedStatement.executeUpdate();
			System.out.println("ejecuta insert");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
