package EXPERIMENTO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Optional;

public class experimentoss {
    private ArrayList<Cultivo> cultivos;

    public experimentoss() {
        this.cultivos = new ArrayList<>();
    }

    public ArrayList<String> getNombresDeCultivosDeBacterias() {
        ArrayList<String> nombres = new ArrayList<>();
        for (Cultivo cultivo : cultivos) {
            nombres.add(cultivo.getNombre());
        }
        return nombres;
    }

    public Cultivo getCultivo(String nombre) {
        for (Cultivo cultivo : cultivos) {
            if (cultivo.getNombre().equals(nombre)) {
                return cultivo;
            }
        }
        return null;
    }

    public boolean eliminarCultivo(String nombre) {
        for (int i = 0; i < cultivos.size(); i++) {
            if (cultivos.get(i).getNombre().equals(nombre)) {
                cultivos.remove(i);
                return true;
            }
        }
        return false;
    }

    public Optional<Cultivo> agregarCultivo( Cultivo cultivo) {
        cultivos.add(cultivo);
        return Optional.of(cultivo);
    }

    public void guardarExperimento( File file) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream (file))) {
            oos.writeObject(this);
        }
    }
}
