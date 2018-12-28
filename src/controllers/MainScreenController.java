package controllers;

import infrastructure.ConnectionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MainScreenController {
    @FXML
    private ListView<String> klienciIndywidualniList, firmyList;

    // Można to robić za pomocą TableView, ale jest dużo roboty z CellValueFactory (albo PropertyValueFactory) i trzeba by pewnie porobić klasy dla każdej encji
    // Dodatkowo nazwy kolumn trzeba by wyciągać z tabel systemowych (nie indeksowalne, okropnie długie przeszukiwanie) albo hardkodować
    public void showKlienci() throws SQLException {
        ObservableList<String> klienciIndywidualni = FXCollections.observableArrayList();
        ResultSet resultSet = ConnectionManager.getStatementResultSet("select ID_KLIENTA, IMIE, NAZWISKO, ADRES from KLIENT natural join KLIENT_INDYWIDUALNY");
        while (resultSet.next()) {
            klienciIndywidualni.add(resultSet.getString(1) + ", " + resultSet.getString(2) + ", " + resultSet.getString(3) + ", " + resultSet.getString(4));
        }
        klienciIndywidualniList.setItems(klienciIndywidualni);
        ObservableList<String> klienciBiznesowi = FXCollections.observableArrayList();  // Nie można reużyć poprzedniej listy, bo jest Observable, czyli asynchronicznie się aktualizuje
        resultSet = ConnectionManager.getStatementResultSet("select ID_KLIENTA, NAZWA, NIP, ADRES from KLIENT natural join FIRMA");
        while (resultSet.next()) {
            klienciBiznesowi.add(resultSet.getString(1) + ", " + resultSet.getString(2) + ", " + resultSet.getString(3) + ", " + resultSet.getString(4));
        }
        firmyList.setItems(klienciBiznesowi);
    }

}
