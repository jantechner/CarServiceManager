package services;

import infrastructure.ConnectionManager;

import java.sql.ResultSet;

public class KlientService {

    public static void addKlient(boolean czyIndywidualny, String imieNazwa, String nazwiskoNip, String adres) {
        String typ = czyIndywidualny ? "IND" : "FIRMA";
        ConnectionManager.executeProcedure("{call WSTAW_KLIENTA('" + typ + "', '" + imieNazwa + "', '" + nazwiskoNip + "', '" + adres + "')}");
    }

    public static ResultSet getKlienci(boolean czyIndywidualny) {
        return czyIndywidualny ? ConnectionManager.getStatementResultSet("select ID_KLIENTA, IMIE, NAZWISKO, ADRES from KLIENT natural join KLIENT_INDYWIDUALNY") :
                ConnectionManager.getStatementResultSet("select ID_KLIENTA, NAZWA, NIP, ADRES from KLIENT natural join FIRMA");
    }

    public static ResultSet getKlient(boolean czyIndywidualny, String ID) {
        return czyIndywidualny ? ConnectionManager.getStatementResultSet("select ID_KLIENTA, IMIE, NAZWISKO, ADRES from KLIENT natural join KLIENT_INDYWIDUALNY where ID_KLIENTA='" + ID + "'") :
                ConnectionManager.getStatementResultSet("select ID_KLIENTA, NAZWA, NIP, ADRES from KLIENT natural join FIRMA where ID_KLIENTA='" + ID + "'");
    }

    public static void deleteKlient(boolean czyIndywidualny, String ID) {
        if (czyIndywidualny)
            ConnectionManager.executeStatement("delete from KLIENT_INDYWIDUALNY where ID_KLIENTA='" + ID + "'");
        else
            ConnectionManager.executeStatement("delete from FIRMA where ID_KLIENTA='" + ID + "'");
        ConnectionManager.executeStatement("delete from KLIENT where ID_KLIENTA='" + ID + "'");
    }

    public static void updateKlient(String ID, boolean czyIndywidualny, String imieNazwa, String nazwiskoNip, String adres) {
        if (czyIndywidualny)
            ConnectionManager.executeStatement("update KLIENT_INDYWIDUALNY set IMIE = '" + imieNazwa + "', NAZWISKO = '" + nazwiskoNip + "' where ID_KLIENTA='" + ID + "'");
        else
            ConnectionManager.executeStatement("update FIRMA set NAZWA = '" + imieNazwa + "', NIP = '" + nazwiskoNip + "' where ID_KLIENTA='" + ID + "'");
        ConnectionManager.executeStatement("update KLIENT set ADRES = '" + adres + "' where ID_KLIENTA='" + ID + "'");
    }
}
