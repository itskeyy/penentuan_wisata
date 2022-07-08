package travel;

import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import travel.travel_places.TravelPlacesService;
import travel.travel_places.TravelPlacesVar;
import travel.utils.CLITable;

public class App 
{
    private static void print(String text) {
        System.out.print(text);
    }

    private static void println(String text) {
        System.out.println(text);
    }

    private static void separator() {
        println("=================================================================");
    }

    public static void main( String[] args ) { 
        TravelPlacesService tps = new TravelPlacesService();
        TravelPlacesVar var = new TravelPlacesVar();
        Scanner scan = new Scanner(System.in);
        CLITable wisataBuatanTable = new CLITable(), wisataPSRBTable = new CLITable();

        Thread newThread = new Thread(() -> {
            var.setWisataBuatanObject(tps.getTravelPlaces("http://data.bandung.go.id/beta/index.php/portal/api/a7ab403e-1c83-40ec-9070-3c16a755da52"));
            var.setWisataPSRBObject(tps.getTravelPlaces("http://data.bandung.go.id/beta/index.php/portal/api/b2698882-7aac-493d-9525-47808bc6c079"));       
    
            //=============================[INIT WISATA TABLE]
            wisataBuatanTable.setHeaders("No", "Nama", "Alamat");
            wisataBuatanTable.setShowVerticalLines(true);
            wisataBuatanTable.setRightAlign(false);

            JSONArray wisataBuatanArray = (JSONArray) var.getWisataBuatanObject().get("data");
            //Get the required data using its key
            System.out.println(wisataBuatanArray.size());
            for (int i = 0; i < wisataBuatanArray.size(); i++) {
                JSONObject TravelData = (JSONObject) wisataBuatanArray.get(i);
                wisataBuatanTable.addRow(String.valueOf((i+1)), TravelData.get("nama").toString(), TravelData.get("alamat").toString());
            }

            wisataPSRBTable.setHeaders("No", "Nama", "Alamat");
            wisataPSRBTable.setShowVerticalLines(true);
            wisataPSRBTable.setRightAlign(false);

            JSONArray wisataPSRBArray = (JSONArray) var.getWisataPSRBObject().get("data");
            //Get the required data using its key
            System.out.println(wisataPSRBArray.size());
            for (int i = 0; i < wisataPSRBArray.size(); i++) {
                JSONObject TravelData = (JSONObject) wisataPSRBArray.get(i);
                wisataPSRBTable.addRow(String.valueOf((i+1)), TravelData.get("nama_tempat_wisata").toString(), TravelData.get("alamat").toString());
            }
            //================================[END]
            int travelChoice;
            do {
                separator();
                println("=                   APLIKASI PENENTUAN WISATA                   =");
                println("=                        DI KOTA BANDUNG                        =");
                separator();
                println("= 1 -- " + var.getWisataBuatanObjectName().replace(" di Kota Bandung", "" + "                                      ="));
                println("= 2 -- " + var.getWisataPSRBObjectName().replace(" di Kota Bandung", "" + "      ="));
                println("= 3 -- Keluar Aplikasi" + "                                          =");
                separator();
                print("Pilihan anda: ");
                travelChoice = scan.nextInt();
                
                if(travelChoice == 1) {
                    wisataBuatanTable.print();
                } else if(travelChoice == 2) {
                    wisataPSRBTable.print();
                } else if(travelChoice == 3) {
                    System.exit(0);
                }else {
                    println("");
                    separator();
                    println("\033[0;33m[ERROR] \033[0mPilihan salah, silahkan pilih kembali");
                    separator();
                    println("");
                }
            } while(travelChoice != 1 && travelChoice !=2 && travelChoice !=3);
            scan.close();
        });
        newThread.start();
        
    }
}
