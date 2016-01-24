package Trabajador.Dominio;

/**
 *
 * @author antonio
 */
public enum Categoria {

    Uno(1),
    Dos(2),
    Tres(3),
    Cuatro(4);

    private final int categoria;

    private Categoria(int i) {
        categoria = i;
    }

    public int getCategoria() {
        return categoria;
    }

    public static Categoria get(int categoria) {
        for (Categoria c : values()) {
            if (c.categoria == categoria) {
                return c;
            }
        }

        return null;
    }
}
