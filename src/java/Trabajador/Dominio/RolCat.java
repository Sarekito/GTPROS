package Trabajador.Dominio;

/**
 *
 * @author antonio
 */
public class RolCat {

    public static Categoria dameCat(Rol r) {
        switch (r) {
            case JefeProyecto:
                return Categoria.Uno;
            case Analista:
                return Categoria.Dos;
            case AnalistaProgramador:
                return Categoria.Tres;
            case Disenador:
                return Categoria.Tres;
            case ResponsablePruebas:
                return Categoria.Tres;
            case Programador:
                return Categoria.Cuatro;
            case Probador:
                return Categoria.Cuatro;
            default:
                return null;
        }
    }
}
