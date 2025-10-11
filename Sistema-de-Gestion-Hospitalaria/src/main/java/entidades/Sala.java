package entidades;

import enumeraciones.EstadoCita;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED,force = true)

public class Sala {
    //Identificación única por número
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,length = 20)
    @Getter
    private String numero;

    //Clasificación por tipo
    @Column(length = 100)
    private String tipoSala;

    //Pertenece a un departamento específico
    @ManyToOne
    @JoinColumn(name = "id-Departamento")
    private Departamento departamento;

    //Gestiona citas programadas
    @OneToMany(mappedBy = "sala",cascade = CascadeType.ALL)
    private List<Cita> citas;

    public Sala(String numero,String tipoSala,Departamento departamento){
        validarNumeroSala(numero,departamento);
        this.numero = numero;
        this.tipoSala = tipoSala;
        this.departamento = departamento;
        citas = new ArrayList<>();
    }

    private void validarNumeroSala(String numero,Departamento departamento){
        for (Sala s: departamento.getSalasEspecializadas()){
            if (s.getNumero() == numero){
                throw new IllegalArgumentException("La sala con el número '"+numero+"' ya existe en el hospital");
            }
        }
    }

    public List<Cita> getCitasProgramadas(){
        List<Cita> citasProgramadas=null;
        for (Cita c: citas){
            if(c.getEstado() == EstadoCita.PROGRAMADA){
                citasProgramadas.add(c);
            }
        }
        return Collections.unmodifiableList(citasProgramadas);
    }

    public void mostrarHorarios(LocalDate dia){
        System.out.println("----------Disponibilidad de sala----------");
        LocalTime horaInicio = LocalTime.of(0, 0);
        LocalTime horaFin = LocalTime.of(23, 0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime horaPropuesta = dia.atTime(horaInicio);
        while (horaPropuesta.toLocalTime().isBefore(horaFin) && horaPropuesta.toLocalDate().equals(dia) || horaPropuesta.toLocalTime().equals(horaFin) && horaPropuesta.toLocalDate().equals(dia)) {
            String estadoSala = "DISPONIBLE";
            if (!esSalaDisponible(horaPropuesta)) {
                estadoSala = "OCUPADA";
            }
            System.out.println(horaPropuesta.format(formatter) + " -> " + estadoSala);
            horaPropuesta = horaPropuesta.plusHours(1);
        }
        System.out.println("----------------------------------------------");
    }

    private boolean esSalaDisponible(LocalDateTime fechaHora) {
        for (Cita citaExistente : citas) {
            long horasDiferencia = java.time.Duration.between(citaExistente.getFechaHora(), fechaHora).abs().toHours();
            if (horasDiferencia < 2) {
                return false;
            }
        }
        return true;
    }

}
