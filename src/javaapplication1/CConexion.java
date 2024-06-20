/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author Lina Pao
 */
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Lina Pao
 */
public class CConexion {
    
    Connection conectar = null;
    
    String usuario = "root";
    String contrasenia = "1234";
    String bd = "taller_sena";
    String ip = "localhost";
    String puerto = "3306";
    
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    public Connection estableceConexion(){
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conectar = DriverManager.getConnection(cadena,usuario,contrasenia);
        //JOptionPane.showMessageDialog(null, "La conexion se ha realizado con exito");
        
    } catch (Exception e){
    JOptionPane.showMessageDialog(null, "Error al conectarse a la bd"+ e.toString());
    }
    return conectar;
    }
    }
    
