package vallegrande.edu.pe.sistematransporte.model;

public class Vehiculo {
    // Corrección de validaciones del modelo Vehiculo
    private String tipo;
    private String marca;
    private String placa;

    public Vehiculo(String tipo, String marca, String placa) {
        this.tipo = tipo;
        this.marca = marca;
        this.placa = placa;
    }

    public String getTipo() {
        return tipo;
    }

    public String getMarca() {
        return marca;
    }

    public String getPlaca() {
        return placa;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}