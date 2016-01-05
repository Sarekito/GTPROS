package Proyecto.Despliegue;

import Proyecto.Dominio.InformeSeguimiento;
import Proyecto.Dominio.Proyecto;
import Proyecto.Dominio.TrabajadoresProyecto;
import Trabajador.Dominio.Trabajador;
import java.util.ArrayList;
import javax.ejb.Local;

/**
 *
 * @author antonio
 */
@Local
public interface despliegueProyectoLocal {

    public ArrayList<Proyecto> getMisProyectos(String jefe);

    public Proyecto getProyecto(String nombreProyecto);

    public void generar(String nombreProyecto, String jefe);

    boolean cerrarProyecto(String nombreProyecto);

    boolean cerrarEtapa(String nombreProyecto, int numero);

    public ArrayList<Proyecto> getMisProyectosActuales(Trabajador get);

    public TrabajadoresProyecto dameTrabajadorProyecto(String user, String nombre);

    java.util.ArrayList<InformeSeguimiento> getInformesProyecto(String nombreProyecto);

    void aprobarInforme(InformeSeguimiento informe);

    void rechazarInforme(InformeSeguimiento informe);

    java.util.ArrayList<InformeSeguimiento> getInformesPendientesProyecto(String nombreProyecto);

    java.util.ArrayList<InformeSeguimiento> getInformesNoEnviadosProyecto(String nombreProyecto);

}
