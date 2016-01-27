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
    otros;

    public static TipoTarea get(String value) {
        for (TipoTarea e : values()) {
            if (e.name().equals(value)) {
                return e;
            }
        }
        return null;
    }
}
