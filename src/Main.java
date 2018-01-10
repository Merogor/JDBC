import java.sql.*;
import java.util.Scanner;

public class Main {
    private static Connection connection;
    private static String host = "slo.swe.fh-luebeck.de";
    private static String schema = "Gruppe6";
    private static String user = "Gruppe6";
    private static String password = "9K5G3oSX6ppr";

    public static void main(String[] args) {
        try {
            //JDBC-Treiber laden
            Class.forName("org.mariadb.jdbc.Driver");
            //Verbindung herstellen
            connection = DriverManager.getConnection("jdbc:mysql://" + host + "/" + schema + "?user=" + user
                    + "&password=" + password + "&allowMultiQueries=true");

            System.out.println("\n---------------------------------------------------------------------------");
            System.out.println("-----------------Datenbankzugriff aus einer Java-Anwendung-----------------");
            System.out.println("---------------------------------------------------------------------------\n\n");
            System.out.println("Welche Aufgabenstellung möchten sie starten? Bitte wählen sie durch eingabe von 1, 2, 3 oder 4 aus folgenden Möglichkeiten: \n");
            System.out.println("Auswahl 1 --- Erfassung eines Javaobjekts");
            System.out.println("Auswahl 2 --- Abfrage von Datensätzen");
            System.out.println("Auswahl 3 --- Stored Procedure");
            System.out.println("Auswahl 4 --- Programm beenden\n");
            System.out.print("Ihre Eingabe: ");

            Scanner in = new Scanner(System.in);
            String choice = in.nextLine();
            boolean finished = false;

            while(!finished) {

                switch (choice) {
                    case "1":
                        erfassungObjekt();
                        break;

                    case "2":
                        abfrage();
                        break;

                    case "3":
                        storedProcedure();
                        break;

                    case "4":
                        System.out.println("Programm wird beendet");
                        finished = true;
                        break;
                    default:
                        System.out.println("Falsche Eingabe -> Programm wird beendet");
                        finished = true;

                }
                if(!finished) {
                    System.out.println("\n---------------------------------------------------------------------------");
                    System.out.println("-----------------Datenbankzugriff aus einer Java-Anwendung-----------------");
                    System.out.println("---------------------------------------------------------------------------\n\n");
                    System.out.println("Welche Aufgabenstellung möchten sie starten? Bitte wählen sie durch eingabe von 1, 2, 3 oder 4 aus folgenden Möglichkeiten: \n");
                    System.out.println("Auswahl 1 --- Erfassung eines Javaobjekts");
                    System.out.println("Auswahl 2 --- Abfrage von Datensätzen");
                    System.out.println("Auswahl 3 --- Stored Procedure");
                    System.out.println("Auswahl 4 --- Programm beenden\n");
                    System.out.print("Ihre Eingabe: ");
                    choice = in.nextLine();
                }
            }//while
            in.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Erzeugt Objekte vom Typ DJ, Adresse und Kontaktdaten und legt in den entsprechenden
     * Tabellen der Datenbank neue Einträge an
     */
    private static void erfassungObjekt() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet result = null;
            ResultSet resultDJid = null;
            ResultSet resultAdressid = null;
            ResultSet resultKontaktid = null;


            result = stmt.executeQuery("SELECT * FROM DJ;");
            resultDJid = stmt.executeQuery("SELECT idDJ FROM DJ");
            resultAdressid = stmt.executeQuery("SELECT idAdresse FROM Adresse");
            resultKontaktid = stmt.executeQuery("SELECT idKontaktdaten FROM Kontaktdaten");

            String sidDJ = "idDJ";
            String sAdressid = "idAdresse";
            String sKontaktid = "idKontaktdaten";


            System.out.println("\n\n\nBereits vorhandene DJs:");

            while (result.next()) {
                System.out.println(
                        "DJID :" + result.getString("idDJ") + "   Vor- und Nachname:  " + result.getString("vorname") +
                                " " + result.getString("nachname") + "   Verfügbarkeit:  " +
                                result.getString("verfuegbarkeit") + "   AdressID:  " + result.getString("Adresse_idAdresse") +
                                "   KontaktdatenID:  " + result.getString("Kontaktdaten_idKontaktdaten")
                                + "   Künstlername:  " + result.getString("kuenstlername"));
            }

            System.out.println("Neues Objekt vom Typ 'DJ' wird angelegt ...");

            Scanner in = new Scanner(System.in);
            Scanner in2 = new Scanner(System.in);

            //Automatische Festlegung der DJid
            int iddj = generateID(resultDJid, sidDJ);

            System.out.println("Bitte Vornamen eingeben: ");
            String vorname = in2.nextLine();
            while (vorname.length() == 0 || vorname.length() > 20) {
                System.out.println("Dieser Vorname ist ungültig, bitte gültigen Vornamen eingeben: ");
                vorname = in2.nextLine();
            }

            System.out.println("Bitte Nachnamen eingeben: ");
            String nachname = in2.nextLine();
            while (nachname.length() == 0 || nachname.length() > 20) {
                System.out.println("Dieser Nachname ist ungültig, bitte gültigen Vornamen eingeben: ");
                vorname = in2.nextLine();
            }

            System.out.println("Bitte Verfügbarkeit eingeben - verfügbar (0) oder (1): ");
            int verfuegbar = in.nextInt();
            while (verfuegbar != 1 && verfuegbar != 0) {
                System.out.println("Verfügbarkeit ist ungültig, bitte nur 0 oder 1 eingeben! Erneute Eingabe: ");
                verfuegbar = in.nextInt();
            }


            System.out.println("Bitte Künstlernamen eingeben: ");
            String kname = in2.nextLine();
            while (kname.length() == 0 || kname.length() > 40) {
                System.out.println("Dieser Künstlername ist ungültig, bitte gültigen Künstlernamen eingeben: ");
                kname = in2.nextLine();
            }
            System.out.println("\n--------------------------------------");
            System.out.println("-----------Adresseingabe---------------");
            System.out.println("---------------------------------------");
            //Automatische Festlegung der AdressID
            int idAdresse = generateID(resultAdressid, sAdressid);


            System.out.println("Bitte Straße eingeben: ");
            String strasse = in2.nextLine();
            while (strasse.length() == 0 || strasse.length() > 50) {
                System.out.println("Dieser Strassenname ist ungültig, bitte gültigen Strassennamen eingeben: ");
                strasse = in2.nextLine();
            }

            System.out.println("Bitte Hausnummer eingeben: ");
            int hausnummer = in.nextInt();
            while (hausnummer <= 0 && hausnummer > 1000) {
                System.out.println("Diese Eingabe Hausnummer ist ungültig. Bitte erneut eingeben: ");
                hausnummer = in.nextInt();
            }

            System.out.println("Bitte Postleitzahl eingeben: ");
            int plz = in.nextInt();
            while (plz < 10000 && plz >= 99999) {
                System.out.println("Diese Postleitzahl ist ungültig. Bitte erneut eingeben: ");
                plz = in.nextInt();
            }

            System.out.println("Bitte Stadt eingeben: ");
            String stadt = in2.nextLine();
            while (stadt.length() == 0 || stadt.length() > 30) {
                System.out.println("Dieser Stadtnname ist ungültig, bitte gültigen Stadtnamen eingeben: ");
                stadt = in2.nextLine();
            }

            System.out.println("Bitte Land eingeben: ");
            String land = in2.nextLine();
            while (land.length() == 0 || land.length() > 30) {
                System.out.println("Dieser Landname ist ungültig, bitte gültigen Landnnamen eingeben: ");
                land = in2.nextLine();
            }

            System.out.println("\n--------------------------------------");
            System.out.println("---------Kontaktdateneingabe-----------");
            System.out.println("---------------------------------------");

            //Automatische Festlegung von KontaktdatenID
            int idKontakt = generateID(resultKontaktid, sKontaktid);

            //TODO Nummer als String
            System.out.println("Bitte Festnetznummer eingeben: ");
            int festnr = in.nextInt();
            while (festnr <= 0 && festnr > 999999999) {
                System.out.println("Diese Festnetznummer ist ungültig. Bitte erneut eingeben: ");
                festnr = in.nextInt();
            }
            //TODO Nummer als String
            System.out.println("Bitte Mobilnummer eingeben: ");
            int mobilnr = in.nextInt();
            while (mobilnr >= 0 && mobilnr > 999999999) {
                System.out.println("Diese Eingabe ist ungültig - IDs können von 1 - 100 vergeben werden. Bitte erneut eingeben: ");
                mobilnr = in.nextInt();
            }

            System.out.println("Bitte email eingeben: ");
            String mail = in2.nextLine();
            while (mail.length() == 0 || mail.length() > 30) {
                System.out.println("Diese Email ist ungültig, bitte gültige Email eingeben: ");
                mail = in2.nextLine();
            }

            //Erzeugung von Adress, DJ und Kontaktdaten Objekt
            Adresse newAdr = new Adresse(idAdresse, strasse, hausnummer, plz, stadt, land);
            DJ newDJ = new DJ(iddj, vorname, nachname, verfuegbar, idAdresse, idKontakt, kname);
            Kontaktdaten newKon = new Kontaktdaten(idKontakt, festnr, mobilnr, mail);

            //Ausgabe des neuen DJ-Objekts auf der Konsole
            System.out.println("\n\n--- Sie haben folgendes DJ-Objekt angelegt und an die Datenbank übemittelt ---");
            System.out.println(newDJ.toString() + "\n");
            System.out.println("Mit folgender Adresse: ");
            System.out.println(newAdr.toString() + "\n");
            System.out.println("Mit folgenden Kontaktdaten: ");
            System.out.println(newKon.toString() + "\n");

            //SQL-Statements um DJ + Adresse + Kontaktdaten in entsprechende Tabellen in der DB einzufügen
            PreparedStatement adrin = connection.prepareStatement(
                    "INSERT INTO `Adresse` (`idAdresse`, `strasse`, `hausnummer`, `postleitzahl`, `stadt`, `land`) VALUES (?, ?, ?, ?, ?, ?)");
            adrin.setInt(1, newAdr.getIdAdresse());
            adrin.setString(2, newAdr.getStrasse());
            adrin.setInt(3, newAdr.getHausnummer());
            adrin.setInt(4, newAdr.getPostleitzahl());
            adrin.setString(5, newAdr.getStadt());
            adrin.setString(6, newAdr.getLand());
            adrin.addBatch();
            adrin.executeBatch();

            PreparedStatement kontin = connection.prepareStatement(
                    "INSERT INTO `Kontaktdaten` (`idKontaktdaten`, `festnetznummer`, `mobilnummer`, `emailadresse`) VALUES (?, ?, ?, ?)");
            kontin.setInt(1, newKon.getIdKontaktdaten());
            kontin.setInt(2, newKon.getFestnetznummer());
            kontin.setInt(3, newKon.getMobilnummer());
            kontin.setString(4, newKon.getEmailadresse());
            kontin.addBatch();
            kontin.executeBatch();


            PreparedStatement kundein = connection.prepareStatement(
                    "INSERT INTO `DJ` (`idDJ`, `vorname`, `nachname`, `verfuegbarkeit`, `Adresse_idAdresse`, `Kontaktdaten_idKontaktdaten`, `kuenstlerName`)  VALUES (?, ?, ?, ?, ?, ?, ?)");
            kundein.setInt(1, newDJ.getIdDJ());
            kundein.setString(2, newDJ.getVorname());
            kundein.setString(3, newDJ.getNachname());
            kundein.setInt(4, newDJ.getVerfuegbarkeit());
            kundein.setInt(5, newDJ.getAdresse_idAdresse());
            kundein.setInt(6, newDJ.getKontaktdaten_idKontaktdaten());
            kundein.setString(7, newDJ.getKuenstlerName());
            kundein.addBatch();
            kundein.executeBatch();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Gibt Getränke bis zu einem vom Nutzer definierten Preislimit auf der Konsole aus - sortiert nach Kategorie & Name
     */
    private static void abfrage() {
        try {

            Scanner border = new Scanner(System.in);
            System.out.println("\n\nBitte wählen sie aus bis zu welchem Verkaufspreis Getränke angezeigt werden sollen.");
            System.out.print("Ihre Eingabe: ");

            //Preislimit
            int threshholdPrice = border.nextInt();

            Statement stmt = connection.createStatement();
            ResultSet result = stmt
                    .executeQuery("SELECT * FROM Getraenk WHERE preisVerkauf < " + threshholdPrice + " GROUP BY kategorie, name");

            int check = 0;
            while (result.next()) {
                System.out.println("\nGetränkename: " + result.getString("name") + "  Preis: " + result.getString("preisverkauf") + "  Kategorie: " + result.getString("kategorie"));
                check = 1;
            }
            if (check == 0) {
                System.out.println("Es gibt keine Getränke, welche den Auswahlkriterien entsprechen.");
            }
            //border.close();
            stmt.close();
            result.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ruft die Stored Procedure 'updatePreis' mit vom Nutzer eingegebenen Parametern auf
     */
    private static void storedProcedure() {
        try {
            Scanner priceUpdate = new Scanner(System.in);
            System.out.println("Um welchen Faktor sollen die Preise erhöht werden: ");

            //Vom Nutzer eingegebener DECIMAL, welcher die Preiserhöhung bestimmt
            double k = priceUpdate.nextDouble();

            Statement stmt = connection.createStatement();
            //Ausgabe der alten Preisliste
            System.out.println("------------ ALTE PREISE --------------");
            ResultSet priceBefore = stmt.executeQuery("SELECT name, preisVerkauf FROM Getraenk");
            while (priceBefore.next()) {
                System.out.println("\nGetränkename: " + priceBefore.getString("name") + "  | alter Preis: " + priceBefore.getDouble("preisVerkauf"));
            }

            //Ausführung der Preiserhöhung mittels Stored Procedure
            ResultSet result = stmt.executeQuery("CALL updatePreis(" + k + ")");

            //Ausgabe der neuen Preisliste
            System.out.println("\n\n------------ NEUE PREISE --------------");
            ResultSet priceAfter = stmt.executeQuery("SELECT name, preisVerkauf FROM Getraenk");
            while (priceAfter.next()) {
                System.out.println("\nGetränkename: " + priceAfter.getString("name") + " | neuer Preis: " + priceAfter.getDouble("preisVerkauf"));
            }

            stmt.close();
            result.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Generiert eine neue ID, welche noch nicht existiert und um eins höher ist als die höchste bisherige ID
     *
     * @param res Resultset mit existierenden IDs
     * @param s   Tabellenname der zu erstellenden ID
     * @return neue ID
     */
    private static int generateID(ResultSet res, String s) {
        int tempid = 0;
        try {
            while (res.next()) {
                if (res.getInt(s) >= tempid) {
                    tempid = res.getInt(s);
                }
            }
            tempid++;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tempid;
    }
}

