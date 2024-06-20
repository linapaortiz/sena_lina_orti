/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;


import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.CallableStatement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Lina Pao
 */
public class CAlumnos {
    
     int codigo;
   String nombreAlumnos;
   String apellidosAlumnos;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreAlumnos() {
        return nombreAlumnos;
    }

    public void setNombreAlumnos(String nombreAlumnos) {
        this.nombreAlumnos = nombreAlumnos;
    }

    public String getApellidosAlumnos() {
        return apellidosAlumnos;
    }

    public void setApellidosAlumnos(String apellidosAlumnos) {
        this.apellidosAlumnos = apellidosAlumnos;
    }
  public void InsertarAlumno (JTextField paramNombres, JTextField paramApellidos){
      
      setNombreAlumnos(paramNombres.getText());
      setApellidosAlumnos(paramApellidos.getText());
      
      CConexion objetoConexion = new CConexion();
      
      String consulta = "insert into taller_sena.alumnos (nombres,apellidos) values (?,?);";
      
      try {
        
          CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
          
          cs.setString(1, getNombreAlumnos());
          cs.setString(2, getApellidosAlumnos());
          
          cs.execute();
          
          JOptionPane.showMessageDialog(null,"Se inserto correctamente el alumno jaja");
    } catch (Exception e){
    
        JOptionPane.showMessageDialog(null,"No se inserto correctamente el alumno jaja, error: "+e.toString());
    }
  }
  
  public void MostrarAlumnos(JTable paramTablaTotalAlumnos){
  
      CConexion objetoConexion= new CConexion();
      
      DefaultTableModel modelo = new DefaultTableModel();
      
      TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
      paramTablaTotalAlumnos.setRowSorter(OrdenarTabla);
      
      String sql ="";
      
      modelo.addColumn("id");
      modelo.addColumn("Nombres");
      modelo.addColumn("Apellidos");
      
      paramTablaTotalAlumnos.setModel(modelo);
      
      sql = "SELECT * FROM taller_sena.alumnos;";
      
      String[] datos = new String[3];
      
      Statement st;
      
     try {
        
         st = objetoConexion.estableceConexion().createStatement();
         ResultSet rs = st.executeQuery(sql);
         
         while(rs.next()){
         
             datos[0]=rs.getString(1);
             datos[1]=rs.getString(2);
             datos[2]=rs.getString(3);
             
             modelo.addRow(datos);
         }
         
         paramTablaTotalAlumnos.setModel(modelo);
    } catch (Exception e){
    
        JOptionPane.showMessageDialog(null,"No se consulto correctamente el alumno jaja, error: "+e.toString());
    }
      
      
  }
  
  public void SeleccionarAlumno (JTable paramTablaTotalAlumnos, JTextField paramId, JTextField paramNombres, JTextField paramApellidos){
  
      try{
      
          int fila = paramTablaTotalAlumnos.getSelectedRow();
          
          if(fila >=0){
          
             paramId.setText((paramTablaTotalAlumnos.getValueAt(fila, 0).toString()));
             paramNombres.setText((paramTablaTotalAlumnos.getValueAt(fila, 1).toString()));
             paramApellidos.setText((paramTablaTotalAlumnos.getValueAt(fila, 2).toString()));
             
          }
          else{
          
              JOptionPane.showMessageDialog(null,"Fila no seleccionada");
          }
          
      } catch(Exception e){
      
          JOptionPane.showMessageDialog(null,"Error al seleccionar: "+e.toString());
      }
  
  }
  
    public void ModificarAlumnos (JTextField paramCodigo, JTextField paramNombres, JTextField paramApellidos){
  
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        setNombreAlumnos(paramNombres.getText());
        setApellidosAlumnos(paramApellidos.getText());
        
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "UPDATE taller_sena.alumnos SET alumnos.nombres =?, alumnos.apellidos =? WHERE alumnos.id=?;";
        
        try{
            
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setString(1, getNombreAlumnos());
            cs.setString(2, getApellidosAlumnos());
            cs.setInt(3, getCodigo());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Modificacion Exitosa");
        
        } catch (SQLException e){
        
            JOptionPane.showMessageDialog(null, "No se modifico, Error;"+e.toString());
        }
  }
    public void EliminarAlumnos(JTextField paramCodigo){
        
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        CConexion objetoConexion = new CConexion();
        String consulta ="DELETE FROM taller_sena.alumnos WHERE alumnos.id=?;";
        
        try{
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            cs.setInt(1, getCodigo());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Se elimino correctamente");
        
        } catch (SQLException e) {
        
            JOptionPane.showMessageDialog(null, "No se pudo eliminar, Error;"+e.toString());
        
        }
    
        
    }
 
  }
