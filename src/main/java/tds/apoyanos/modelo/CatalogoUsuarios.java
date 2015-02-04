package tds.apoyanos.modelo;

import java.util.HashMap;
import java.util.Map;

public class CatalogoUsuarios {
    private static CatalogoUsuarios ourInstance = new CatalogoUsuarios();

    private Map<String, Usuario> catalogo;

    public static CatalogoUsuarios getInstance() {
        return ourInstance;
    }

    private CatalogoUsuarios() {
        catalogo = new HashMap<String, Usuario>();
    }

    public Usuario getUsuario(String email) {
        return catalogo.get(email);
    }

    public void addUsuario(Usuario user){
        catalogo.put(user.getEmail(), user);
    }
}
