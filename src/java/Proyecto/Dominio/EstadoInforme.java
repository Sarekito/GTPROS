package Proyecto.Dominio;

/**
 *
 * @author Juan Francisco Pascual Palacin
 */
public enum EstadoInforme {

    noEnviado,
    pendiente,
    aprobado,
    rechazado;

    public static EstadoInforme get(String value) {
        for (EstadoInforme e : values()) {
            if (e.name().equals(value)) {
                return e;
            }
        }
        return null;
    }
}
