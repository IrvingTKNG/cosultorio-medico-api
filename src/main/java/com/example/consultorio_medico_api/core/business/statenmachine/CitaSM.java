package com.example.consultorio_medico_api.core.business.statenmachine;


import com.example.consultorio_medico_api.utils.statemachine.EstadoSM;
import com.example.consultorio_medico_api.utils.statemachine.MaquinaEstado;
import com.example.consultorio_medico_api.utils.statemachine.StateMachineLoader;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
public class CitaSM extends MaquinaEstado<CitaSM.Estado> {

    public static final int ST_AGENDADA = 1;
    public static final int ST_REAGENDADA = 2;
    public static final int ST_CANCELADA = 3;
    public static final int ST_CONFIRMADA = 4;
    public static final int ST_TERMINADA = 5;

    public static final int ACT_REAGENDAR = 1;
    public static final int ACT_CANCELAR = 2;
    public static final int ACT_CONFIRMAR = 3;
    public static final int ACT_FINALIZAR = 4;


    @SuperBuilder
    @Getter
    @Accessors(fluent = true)
    @Setter(AccessLevel.PRIVATE)
    public static final class Estado extends EstadoSM {
        private boolean reagendar;
        private boolean cancelar;
        private boolean confirmar;
        private boolean finalizar;
    }

    @PostConstruct
    public void init() {
        StateMachineLoader.load("jsonmachines/cita-sm.json", this,
                estado -> {
                    estado.reagendar(estado.validAction(ACT_REAGENDAR));
                    estado.cancelar(estado.validAction(ACT_CANCELAR));
                    estado.confirmar(estado.validAction(ACT_CONFIRMAR));
                    estado.finalizar(estado.validAction(ACT_FINALIZAR));
                    return estado;
                });
    }


}
