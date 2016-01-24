package Trabajador.Dominio;

/**
 *
 * @author antonio
 */
public enum Rol {
    JefeProyecto(Categoria.Uno),
    Analista(Categoria.Dos),
    Disenador(Categoria.Tres),
    AnalistaProgramador(Categoria.Tres),
    ResponsablePruebas(Categoria.Tres),
    Programador(Categoria.Cuatro),
    Probador(Categoria.Cuatro);
    
    private final Categoria categoria;
    
    public Categoria getCategoria() {
        return categoria;
    }
    
    private Rol(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public static Rol get(String rol) {
        for (Rol r: values()) {
            if (r.name().equals(rol)) {
                return r;
            }
        }
        
        return null;
    }
}
