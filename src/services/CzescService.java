package services;

import infrastructure.ConnectionManager;

import java.sql.ResultSet;

public class CzescService {

    public static ResultSet getCzesci() {
        return ConnectionManager.getStatementResultSet("select ID_CZESCI, NAZWA, CENA_JEDN from CZESC");
    }

    public static ResultSet getCzesciSerwisu(String idSerwisu) {
        return ConnectionManager.getStatementResultSet("select ID_SERWISU, ID_CZESCI, NAZWA, CENA_JEDN from STAN_CZESCI natural join CZESC where ID_SERWISU = '" + idSerwisu + "'");
    }

    public static ResultSet getCzesc(String idCzesci) {
        return ConnectionManager.getStatementResultSet("select ID_CZESCI, NAZWA, CENA_JEDN from CZESC where ID_CZESCI = '" + idCzesci + "'");
    }

    public static void deleteCzesc(String idCzesci) {
        ConnectionManager.executeStatement("delete from CZESC where ID_CZESCI = '" + idCzesci + "'");
    }

    public static void updateCzesc(String idCzesci, String nazwa, String cenaJedn) {
        ConnectionManager.executeStatement("update CZESC set NAZWA = '" + nazwa + "', CENA_JEDN = '" + cenaJedn + "' where ID_CZESCI = '" + idCzesci + "'");
    }

}
