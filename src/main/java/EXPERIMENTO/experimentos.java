package EXPERIMENTO;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class experimentos {

    private List<CultivoDeBacterias> cultivos = new ArrayList<> ();

    public void agregarCultivoDeBacterias(CultivoDeBacterias cultivo) {
        cultivos.add(cultivo);
    }

    public void eliminarCultivoDeBacterias(String nombre) {
        cultivos.removeIf(c -> c.getNombre().equals(nombre));
    }

    public Optional<CultivoDeBacterias> obtenerDetallesCultivo( String nombre) {
        return cultivos.stream()
                .filter(c -> c.getNombre().equals(nombre))
                .findFirst();
    }

    public void mostrarCultivosDeBacterias() {
        if (cultivos.isEmpty()) {
            System.out.println("No hay cultivos de bacterias registrados.");
        } else {
            System.out.println("Cultivos de bacterias registrados:");
            for (CultivoDeBacterias cultivo : cultivos) {
                System.out.println("- " + cultivo.getNombre());
            }
        }
    }

    public static experimentos abrirExperimento(String ruta) throws IOException, ClassNotFoundException {
        // Implementación de abrir experimento
        return new experimentos();
    }

    public void guardarExperimento(String ruta) throws IOException {
        // Implementación de guardar experimento
    }

    public void verInformacionDetalladaDeCultivoDeBacterias(String nombre, StringWriter detallesArea) {
        if (nombre != null && !nombre.isEmpty()) {
            Optional<CultivoDeBacterias> cultivoOptional = obtenerDetallesCultivo(nombre);
            if (cultivoOptional.isPresent()) {
                CultivoDeBacterias cultivo = cultivoOptional.get();
                detallesArea.append("Nombre: " + cultivo.getNombre() + "\n");
                detallesArea.append("Fechas: " + cultivo.getFechaInicio() + " - " + cultivo.getFechaFin() + "\n");
                detallesArea.append("Cantidad inicial: " + cultivo.getCantidad() + "\n");
                detallesArea.append("Temperatura: " + cultivo.getTemperatura() + "\n");
                detallesArea.append("Luminosidad: " + cultivo.getLuminosidad() + "\n");
                detallesArea.append("Dosis de comida:\n");
                detallesArea.append(" - Inicial: " + cultivo.getDosisComida().getComidaInicial() + "\n");
                detallesArea.append(" - Incremento en día " + cultivo.getDosisComida().getDiaIncremento() + ": " +
                        cultivo.getDosisComida().getComidaIncremento() + "\n");
                detallesArea.append(" - Final: " + cultivo.getDosisComida().getComidaFinal() + "\n");
            } else {
                detallesArea.append("No se encontró el cultivo de bacterias: " + nombre + "\n");
            }
        } else {
            detallesArea.append("Debe especificar un nombre de cultivo para ver detalles.\n");
        }
    }
}
