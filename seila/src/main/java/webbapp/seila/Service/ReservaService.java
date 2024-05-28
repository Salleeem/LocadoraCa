package webbapp.seila.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import webbapp.seila.Model.Reserva;

public class ReservaService {

    public void salvarReserva(Reserva reserva) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/locadoracarro", "postgres", "postgres");
            String sql = "INSERT INTO reservas (nome_completo, modelo_carro, telefone, cpf, email, local_retirada, data_retirada, data_devolucao) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, reserva.getNomeCompleto());
            statement.setString(2, reserva.getModeloCarro());
            statement.setString(3, reserva.getTelefone());
            statement.setString(4, reserva.getCpf());
            statement.setString(5, reserva.getEmail());
            statement.setString(6, reserva.getLocalRetirada());
            statement.setDate(7, new java.sql.Date(reserva.getDataRetirada().getTime()));
            statement.setDate(8, new java.sql.Date(reserva.getDataDevolucao().getTime()));
            statement.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
