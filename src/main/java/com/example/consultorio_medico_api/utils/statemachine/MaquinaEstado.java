package com.example.consultorio_medico_api.utils.statemachine;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@SuperBuilder
@NoArgsConstructor
public class MaquinaEstado<T extends EstadoSM> {

    protected String id;
    protected String name;
    protected List<Action> actions;
    protected List<T> states;
    @Setter
    protected Map<Integer, T> mapEstadoSM;

    public T getEstadoSM(Integer id) {
        return mapEstadoSM.get(id);
    }

    public static <T extends EstadoSM> void copyValues(MaquinaEstado<T> maquinaEstadoSource, MaquinaEstado<T> maquinaEstadoTarget) {
        maquinaEstadoTarget.id = maquinaEstadoSource.id;
        maquinaEstadoTarget.name = maquinaEstadoSource.name;
        maquinaEstadoTarget.actions = maquinaEstadoSource.actions;
        maquinaEstadoTarget.states = maquinaEstadoSource.states;
    }

    @Override
    public String toString() {
        return states.stream()
                .map(state -> state.getId() + " \t" + state.getName() + " \t" + state.getTransitions() + " \t" + state.getActions())
                .collect(Collectors.joining("\n"));
    }

}
