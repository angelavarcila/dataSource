/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import control.EventsJpaController;
import control.FunctionsJpaController;
import control.ItemsJpaController;
import control.util.GestionArchivo;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Events;
import model.Functions;
import model.Items;

/**
 *
 * @author ove
 */
public class Principal {

    private static Scanner scanner;
    private static final String ARCHIVO = "resultado.txt";
    private static GestionArchivo gestionArchivo;
    private static String[] datos;
    
    private static ItemsJpaController itemsJpaController;
    private static FunctionsJpaController functionsJpaController;
    private static EventsJpaController eventsJpaController;
    
    public static void main(String[] args) {
        itemsJpaController = new ItemsJpaController();
        functionsJpaController = new FunctionsJpaController();
        eventsJpaController = new EventsJpaController();
        gestionArchivo = new GestionArchivo();
        
        scanner = new Scanner(System.in);
        gestionArchivo.leerArchivo(ARCHIVO);
        
        int opc;
        do{
            System.out.println("Elija una opción:");
            System.out.println("1. funciones por host");
            System.out.println("5. salir");
            opc = scanner.nextInt();
            switch(opc){
                case 1:
                    System.out.println("Escrbiba el nombre del host");
                    String host = scanner.next();
                    getFunctionsPorHost(host);
                    break;
                case 5:
                    System.out.println("ADIOS!!!");
                    break;
                default:
                    System.out.println("\n Elija nuevamente \n");
                    break;
            }
        } while (opc != 5);
      
    }
    
    /**
     * Obtiene funciones por el nombre de host que se escribe como parámetro
     * 
     * @param host el nombre del host
     */
    public static void getFunctionsPorHost(String host){
         List<Functions> lista = functionsJpaController.getFunctionsByHostName(host);
        List<Events> listaE = new ArrayList<>();
        for(Functions f : lista){
            listaE.addAll(eventsJpaController.getEventsByTriggers(f.getTriggerid().getTriggerid()));
        }
        
        String escriba = listaE.get(0).getEventid()+","+listaE.get(0).getObjectid();
        gestionArchivo.escrbir(escriba, false);
        
        for(int i = 1; i<listaE.size(); i++){
            escriba = listaE.get(i).getEventid()+","+listaE.get(i).getObjectid();
            gestionArchivo.escrbir(escriba, true);
        }
        
    }
    
    /**
     * Obtiene los datos del archivo para su lectura de acuerdo a los parámetros
     * 
     * @param lectura es el dato que se obtiene del método leerArchivo de la clase GestionArchivo
     * @param numeroColumnas el número de columnas que tendrá el documento a escribir
     * 
     * @return la lista de cadenas de texto con el contenido del archivo que se lee
     */
    public static List<String> obtenerDatosArchivo(String[] lectura, int numeroColumnas){
        String textoArchivo = lectura[0];
        Integer tamanioArchivo = Integer.parseInt(lectura[1]);
        String[] datosPorLinea = textoArchivo.split("\n");
        
        List<String> lista = new ArrayList<>();
        for(int i = 0; i<tamanioArchivo; i++){
            String[] texto = datosPorLinea[i].split(",");
            for(int j = 0; j<numeroColumnas; j++){
                lista.add(texto[j]);
            }
        }
        return lista;
    }
    
}
