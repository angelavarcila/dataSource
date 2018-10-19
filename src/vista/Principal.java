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
        do {
            System.out.println("Elija una opción:");
            System.out.println("1. funciones por host");
            System.out.println("5. salir");
            opc = scanner.nextInt();
            switch (opc) {
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
    public static void getFunctionsPorHost(String host) {
        List<Functions> lista = functionsJpaController.getFunctionsByHostName(host);
        List<Events> listaE = new ArrayList<>();

        gestionArchivo.escrbir("PONER EL ENCABEZADO: event_id,event_type,....,ns_recovery_time", false);

        for (Functions f : lista) {
            listaE.addAll(eventsJpaController.getEventsByTriggers(f.getTriggerid().getTriggerid()));
            for (int i = 0; i < listaE.size(); i++) {
                //escriba.toString() = listaE.get(i).getEventid() + "," + listaE.get(i).getObjectid();
                gestionArchivo.escrbir(getLineInstance(listaE.get(i), f), true);
            }
            listaE.clear();//para no repetir lineas
        }

        //gestionArchivo.escrbir(getLineInstance(listaE.get(0)), false);
        // for (int i = 0; i < listaE.size(); i++) {
        //escriba.toString() = listaE.get(i).getEventid() + "," + listaE.get(i).getObjectid();
        //   gestionArchivo.escrbir(getLineInstance(listaE.get(i)), true);
        //}
    }

    public static String getLineInstance(Events event, Functions function) {
        StringBuilder escriba = new StringBuilder();

        //INFORMACIÓN DEL EVENTO
        escriba.append(event.getEventid() + ","); //id del evento
        escriba.append(event.getSource() + ","); //tipo de evento
        escriba.append(event.getObjectid() + ","); //id del trigger
        escriba.append(event.getClock() + ","); // momento en el que el evento fue creado (timestamp)
        escriba.append(event.getNs() + ","); // momento en el que el evento fue creado (en nanosegundos)
        escriba.append(event.getValue() + ","); // estado (indica si es un problema o no)
       
        //INFORMACIÓN DE RECUPERACIÓN
        if (event.getEventRecovery() != null) {
            escriba.append("1,"); // 1 indica que es un evento que se recuperó
            escriba.append(event.getEventRecovery().getREventid().getClock() + ","); // momento en el que el evento fue recuperado (timestamp)
            escriba.append(event.getEventRecovery().getREventid().getNs() + ","); // momento en el que el evento fue recuperado (en nanosegundos)

            //podría calcular de una vez el delta de t, para indicar la duración del evento
            //escriba.append("--calcular duración del evento--");
        } else {
            escriba.append("0,0,0,"); // 0 indica que el evento no se recuperó, el tiempo de recuperación es 0
        }

        //INFORMACIÓN DE LA FUNCIÓN
        escriba.append(function.getFunction()+","); //nombre de la función
        escriba.append(function.getParameter()+","); //parámetro de la función
        
        //INFORMACIÓN DEL TRIGGER
        escriba.append(function.getTriggerid().getDescription()+","); // descripción del trigger
        escriba.append(function.getTriggerid().getExpression()+","); // expresión del trigger
        escriba.append(function.getTriggerid().getFlags()+","); //indica el origen del trigger
        escriba.append(function.getTriggerid().getPriority()+","); // veveridad del trigger
        escriba.append(function.getTriggerid().getType()+","); // indica si el trigger puede generar o no múltiples problemas
        escriba.append(function.getTriggerid().getRecoveryMode()+","); //modo de generación del evento OK
        escriba.append(function.getTriggerid().getRecoveryExpression()+","); //expresión de recuperación
               
        //INFORMACIÓN DEL ITEM
        escriba.append(function.getItemid().getDelay()+","); // intervalo de tiempo de actualización
        escriba.append(function.getItemid().getName()+","); //nombre del ítem
        escriba.append(function.getItemid().getType()+","); //tipo de ítem
        escriba.append(function.getItemid().getValueType()+","); //tipo de información del ítem
        escriba.append(function.getItemid().getDescription()+","); // descripción del ítem
        escriba.append(function.getItemid().getFlags()+","); //origen del ítem
        escriba.append(function.getItemid().getPort()+","); //puerto monitorizado por el ítem
        escriba.append(function.getItemid().getSnmpCommunity()+","); //comunidad snmp
        escriba.append(function.getItemid().getSnmpOid()+","); //oid snmp
        escriba.append(function.getItemid().getUnits()+","); //unidades del item
        escriba.append(function.getItemid().getValueType()); //tipo de valores???
        
        
        return escriba.toString();
    }

    /**
     * Obtiene los datos del archivo para su lectura de acuerdo a los parámetros
     *
     * @param lectura es el dato que se obtiene del método leerArchivo de la
     * clase GestionArchivo
     * @param numeroColumnas el número de columnas que tendrá el documento a
     * escribir
     *
     * @return la lista de cadenas de texto con el contenido del archivo que se
     * lee
     */
    public static List<String> obtenerDatosArchivo(String[] lectura, int numeroColumnas) {
        String textoArchivo = lectura[0];
        Integer tamanioArchivo = Integer.parseInt(lectura[1]);
        String[] datosPorLinea = textoArchivo.split("\n");

        List<String> lista = new ArrayList<>();
        for (int i = 0; i < tamanioArchivo; i++) {
            String[] texto = datosPorLinea[i].split(",");
            for (int j = 0; j < numeroColumnas; j++) {
                lista.add(texto[j]);
            }
        }
        return lista;
    }

}
