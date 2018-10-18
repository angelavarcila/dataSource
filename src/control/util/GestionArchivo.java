/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ove
 */
public class GestionArchivo {

    private File file;
    private FileReader reader;
    private FileWriter writer;

    public String[] leerArchivo(String nombre) {
        try {
            file = new File(nombre);
            reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);

            String texto = "";
            String linea = "";
            Integer contador = 0;

            while (true) {
                linea = br.readLine();
                if (linea != null) {
                    texto += linea + "\n";
                    contador++;
                } else {
                    break;
                }
            }
            String[] retorno = new String[2];
            retorno[0] = texto;
            retorno[1] = contador.toString();
            return retorno;
        } catch (IOException ex) {
            Logger.getLogger(GestionArchivo.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void escrbir(String datos, boolean aniadeTexto) {
        try {
            if (aniadeTexto) {
                writer = new FileWriter(file, true);
            } else {
                writer = new FileWriter(file);
            }
            BufferedWriter bw = new BufferedWriter(writer);
            PrintWriter salida = new PrintWriter(bw);
            
            if(aniadeTexto){
                salida.write("\n" + datos);
            } else {
                salida.write(datos);
            }
            salida.close();
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(GestionArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
