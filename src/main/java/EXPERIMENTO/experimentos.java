package EXPERIMENTO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class experimentos implements Serializable {
    private List<Cultivo> cultivos;

    public experimentos () {
        this.cultivos = new ArrayList<> ();
    }

    public List<String> getNombresDeCultivosDeBacterias () {
        List<String> nombres = new ArrayList<> ();
        for (Cultivo cultivo : cultivos) {
            nombres.add ( cultivo.getNombre () );
        }
        return nombres;
    }

    public Cultivo getCultivo ( String nombre ) {
        for (Cultivo cultivo : cultivos) {
            if (cultivo.getNombre ().equals ( nombre )) {
                return cultivo;
            }
        }
        return null;
    }

    public boolean eliminarCultivo ( String nombre ) {
        Cultivo cultivo = getCultivo ( nombre );
        if (cultivo != null) {
            cultivos.remove ( cultivo );
            return true;
        }
        return false;
    }

    public Optional<Cultivo> agregarCultivo ( Cultivo cultivo ) {
        if (getCultivo ( cultivo.getNombre () ) == null) {
            cultivos.add ( cultivo );
            return Optional.of ( cultivo );
        }
        return Optional.empty ();
    }

    public static experimentos abrirExperimento ( String ruta ) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream ( new FileInputStream ( ruta ) )) {
            return (experimentos) ois.readObject ();
        }
    }

    public void guardarExperimento ( File file ) {
        String ruta = file.getAbsolutePath ();
        try (ObjectOutputStream oos = new ObjectOutputStream ( new FileOutputStream ( ruta ) )) {
            oos.writeObject ( this );
        } catch (IOException e) {
            throw new RuntimeException ( e );
        }
    }
}
