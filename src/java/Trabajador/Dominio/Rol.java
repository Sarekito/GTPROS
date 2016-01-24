package Trabajador.Dominio;

/**
 *
 * @author antonio
 */
public enum Rol {
    JefeProyecto,
    Analista,
    Disenador,
    AnalistaProgramador,
    ResponsablePruebas,
    Programador,
    Probador;
    
    public static Rol get(String rol) {
        for (Rol r: values()) {
            if (r.name().equals(rol)) {
                return r;
            }
        }
        
        return null;
    }
}
