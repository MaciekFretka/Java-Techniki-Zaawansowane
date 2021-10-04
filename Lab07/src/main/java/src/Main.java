package src;

import src.DbConnector;

import java.io.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
//        QueryExecutor.executeQuery("INSERT INTO public.clients (\n" +
//                "\"USER_ID\", \"NAME\", \"LAST_NAME\") VALUES (\n" +
//                "'3'::integer, 'julisz'::text, 'slowacki'::text)");

//        String imie = "maciek";
//        String UPDATE = "UPDATE public.clients SET\n" +
//                "\"NAME\" = ?::text WHERE\n" +
//                "\"USER_ID\" = ?;";
//
//        QueryExecutor.executeQuery(UPDATE);
//        ResultSet result = QueryExecutor.executeSelect("SELECT * FROM public.clients");
//        while(result.next()){
//
//            String userName = result.getString("NAME");
//            System.out.println(userName);
//        }

        ClientDaoImpl cd=new ClientDaoImpl();
        InstallationDaoImpl id = new InstallationDaoImpl();
        DueDaoImpl dd = new DueDaoImpl();
        PaymentDaoImpl pd = new PaymentDaoImpl();
        Boolean flag = true;
        Scanner keyboard = new Scanner(System.in);
        while(flag){
            int cont;
            System.out.println("1. - Operacje klienta");
            System.out.println("2. - Operacje instalacji");
            System.out.println("3. - Operacje należności");
            System.out.println("4. - Operacje na dokonanycy wpłatach");
            System.out.println("5. - Operacje na cenniku");
            System.out.println("6. - Wygeneruj plik z zaległymi płatnościami");

            cont = keyboard.nextInt();
            switch (cont){
                case 1:
                    System.out.println("Interfejs klienta");
                    System.out.println("1. - Dodaj nowego klienta");
                    System.out.println("2. Wyświetl wszystich klientów");
                    System.out.println("3. Wyświetl dane konkretnego klienta");
                    System.out.println("4. Zaktualizuj dane klienta");
                    System.out.println("5. Usuń danego klienta");
                    int contt2=keyboard.nextInt();
                    switch (contt2){
                        case 1:
                            keyboard=new Scanner(System.in);
                            System.out.println("Imie:");
                            String name = keyboard.nextLine();
                            System.out.println("Nazwisko:");
                            String last_name=keyboard.nextLine();
                            System.out.println("ID:");
                            int idi = keyboard.nextInt();
                            Client c= new Client(idi,name,last_name);
                            cd.addClient(c);
                            break;
                        case 2:
                            List<Client> list = cd.getAllClients();
                            for(Client client : list){
                                System.out.println("Klient: "+ client.getId());
                                System.out.println("Imię: "+client.getName());
                                System.out.println("Nazwisko: "+client.getLastname());
                            }
                            break;
                        case 3:
                            keyboard=new Scanner(System.in);
                            System.out.println("ID:");
                            int idc = keyboard.nextInt();
                            Client client=cd.getClient(idc);
                            System.out.println("Klient: "+ client.getId());
                            System.out.println("Imię: "+client.getName());
                            System.out.println("Nazwisko: "+client.getLastname());
                            break;
                        case 4:
                            keyboard=new Scanner(System.in);
                            System.out.println("ID:");
                            int idu = keyboard.nextInt();
                            System.out.println("Imie:");
                            String nameu = keyboard.nextLine();
                            System.out.println("Nazwisko:");
                            String last_nameu=keyboard.nextLine();
                            Client cu= new Client(idu,nameu,last_nameu);
                            cd.updateClient(cu);
                            break;
                        case 5:
                            keyboard=new Scanner(System.in);
                            System.out.println("ID:");
                            int idd = keyboard.nextInt();
                            cd.deleteClient(idd);
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Interfejs instalacji");
                    System.out.println("1. - Dodaj nową instalacje");
                    System.out.println("2. Wyświetl wszystkie instalacje");
                    System.out.println("3. Wyświetl dane konkretnej instalacji");
                    System.out.println("4. Zaktualizuj dane instalacji");
                    System.out.println("5. Usuń daną instalacje");
                    int contt3=keyboard.nextInt();
                    switch (contt3){
                        case 1:
                            keyboard=new Scanner(System.in);
                            System.out.println("Adres: ");
                            String address = keyboard.nextLine();
                            System.out.println("Typ usługi:");
                            String type=keyboard.nextLine();
                            System.out.println("ID Klienta:");
                            int uid=keyboard.nextInt();

                            System.out.println("ID:");
                            int idia = keyboard.nextInt();
                            Installation i = new Installation(idia,address, type, uid);
                            id.addInstallation(i);
                            break;
                        case 2:
                            List<Installation> list = id.getAllInstallations();
                            for(Installation installation : list){
                                System.out.println("Instalacja: "+ installation.getROUTERID());
                                System.out.println("Adres: "+ installation.getAddress());
                                System.out.println("Rodzaj usługi: "+installation.getType());
                                System.out.println("ID Klienta: "+installation.getUserID());
                            }
                            break;
                        case 3:
                            keyboard=new Scanner(System.in);
                            System.out.println("ID:");
                            int idi = keyboard.nextInt();
                            Installation installation=id.getInstallation(idi);
                            System.out.println("Instalacja: "+ installation.getROUTERID());
                            System.out.println("Adres: "+ installation.getAddress());
                            System.out.println("Rodzaj usługi: "+installation.getType());
                            System.out.println("ID Klienta: "+installation.getUserID());
                            break;
                        case 4:
                            keyboard=new Scanner(System.in);
                            System.out.println("Adres: ");
                            String addressu = keyboard.nextLine();
                            System.out.println("Typ usługi:");
                            String typeu=keyboard.nextLine();
                            System.out.println("ID Klienta:");
                            int uidu=keyboard.nextInt();


                            System.out.println("ID:");
                            int idiu = keyboard.nextInt();
                            Installation iu = new Installation(idiu,addressu, typeu, uidu);
                            id.updateInstallation(iu);
                            break;
                        case 5:
                            keyboard=new Scanner(System.in);
                            System.out.println("ID:");
                            int idd = keyboard.nextInt();
                            id.deleteInstallation(idd);
                            break;
                    }
                    break;
                case 3:
                    System.out.println("Interfejs należności");
                    System.out.println("1. - Dodaj nową należność");
                    System.out.println("2. Wyświetl wszystkie należności");
                    System.out.println("3. Wyświetl dane konkretnej należności");
                    System.out.println("4. Zaktualizuj dane należności");
                    System.out.println("5. Usuń daną należność");
                    int contt4=keyboard.nextInt();
                    switch(contt4){
                        case 1:
                            keyboard=new Scanner(System.in);
                            System.out.println("Data: ");
                            Date date = Date.valueOf(keyboard.next());
                            System.out.println("Typ kwota:");
                            int amount=keyboard.nextInt();
                            System.out.println("ID Routera:");
                            int uid=keyboard.nextInt();
                            Due d = new Due(date,amount, uid);
                            dd.addDue(d);
                            break;
                        case 2:
                        List<Due> list =dd.getAllDues();
                        for(Due due : list){
                            System.out.println("Należność:");
                            System.out.println("Płatność do: "+due.getDate());
                            System.out.println("Kwota do zapłaty: " +due.getAmount());
                            System.out.println("ID instalacji: "+due.getROUTER_ID());

                        }
                            break;
                        case 3:
                            keyboard=new Scanner(System.in);
                            System.out.println("ID:");
                            int idi = keyboard.nextInt();
                            Due due=dd.getDue(idi);
                            System.out.println("Należność:");
                            System.out.println("Płatność do: "+due.getDate());
                            System.out.println("Kwota do zapłaty: " +due.getAmount());
                            System.out.println("ID instalacji: "+due.getROUTER_ID());
                            break;
                        case 4:
                            keyboard=new Scanner(System.in);
                            System.out.println("Data: ");
                            Date dateu = Date.valueOf(keyboard.nextLine());
                            System.out.println("Kwota do zapłaty:");
                            int amount2=keyboard.nextInt();
                            System.out.println("ID Routera:");
                            int uidu=keyboard.nextInt();

                            Due dueu = new Due(dateu,amount2, uidu);
                            dd.updateDue(dueu);
                            break;
                        case 5:
                            keyboard=new Scanner(System.in);
                            System.out.println("ID:");
                            int idd = keyboard.nextInt();
                            dd.deleteDue(idd);
                            break;
                    }
                    break;
                case 4:
                    System.out.println("Interfejs wpłaty");
                    System.out.println("1. - Dodaj nową wpłatę");
                    System.out.println("2. Wyświetl wszystkie wpłaty");
                    System.out.println("3. Wyświetl dane konkretnej wpłaty");
                    System.out.println("4. Zaktualizuj dane wpłaty");
                    System.out.println("5. Usuń daną wpłatę");
                    int contt5=keyboard.nextInt();
                    switch(contt5){
                        case 1:
                            keyboard=new Scanner(System.in);
                            System.out.println("Data: ");
                            Date date = Date.valueOf(keyboard.next());
                            System.out.println("Kwota:");
                            int amount=keyboard.nextInt();
                            System.out.println("ID Routera:");
                            int uid=keyboard.nextInt();
                            Payment p = new Payment(date,amount, uid);
                            pd.addPayment(p);
                            break;
                        case 2:
                            List<Payment> list =pd.getAllPayments();
                            for(Payment payment : list){
                                System.out.println("Wpłata:");
                                System.out.println("Data wpłaty: "+payment.getDate());
                                System.out.println("Zapłacona kwota: " +payment.getAmount());
                                System.out.println("ID instalacji: "+payment.getROUTER_ID());

                            }
                            break;
                        case 3:
                            keyboard=new Scanner(System.in);
                            System.out.println("ID:");
                            int idi = keyboard.nextInt();
                            Payment payment=pd.getPayment(idi);
                            System.out.println("Wpłata:");
                            System.out.println("Data wpłaty:: "+payment.getDate());
                            System.out.println("Zapłacona kwota: " +payment.getAmount());
                            System.out.println("ID instalacji: "+payment.getROUTER_ID());
                            break;
                        case 4:
                            keyboard=new Scanner(System.in);
                            System.out.println("Data: ");
                            Date dateu = Date.valueOf(keyboard.nextLine());
                            System.out.println("Kwota do zapłaty:");
                            int amount2=keyboard.nextInt();
                            System.out.println("ID Routera:");
                            int uidu=keyboard.nextInt();

                            Payment paymentu = new Payment(dateu,amount2, uidu);
                            pd.updatePayment(paymentu);
                            break;
                        case 5:
                            keyboard=new Scanner(System.in);
                            System.out.println("ID:");
                            int idd = keyboard.nextInt();
                            pd.deletePayment(idd);
                            break;
                    }
                    break;
                case 5:
                    break;
                case 6:
                    List<Due> list =dd.getAllDues();
                    File fout = new File("due.txt");
                    FileOutputStream fos = new FileOutputStream(fout);
                    OutputStreamWriter os = new OutputStreamWriter(fos);
                    BufferedWriter bw = new BufferedWriter(os);
                    for(Due due : list){
                        Date date = new Date(System.currentTimeMillis());
                        if(due.getDate().compareTo(date) < 0){
                            bw.write("Installation: " + due.getROUTER_ID()+", Amount: "+due.getAmount()+", Date: "+due.getDate());
                            bw.newLine();
                        }


                    }
                    bw.close();
                    System.out.println("Wygenerowano plik");
                    break;
            }
        }
    }
}
