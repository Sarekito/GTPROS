package Proyecto.Dominio;

/**
 *
 * @author Juan Francisco Pascual Palacin
 */
public enum TipoTarea {

    tratoUsuarios,
    reuniones,
    documentacion,
    elaboracion,
    programas,
    otras;

    public static TipoTarea get(String value) {
        for (TipoTarea e : values()) {
            if (e.name().equals(value)) {
                return e;
            }
        }
        return null;
    }

    public static String getLegible(String value) {
        if(value.equals("tratoUsuarios")){
            return "Trato con usuarios";
        }
        if(value.equals("reuniones")){
            return "Reuniones";
        }
        if(value.equals("documentacion")){
            return "Lectura y revision de documentacion";
        }
        if(value.equals("elaboracion")){
            return "Elaboracion de documentacion";
        }
        if(value.equals("programas")){
            return "Desarrollo y verificacion de programas";
        }
        if(value.equals("otras")){
            return "Otras actividades";
        }
        return null;
    }
}
