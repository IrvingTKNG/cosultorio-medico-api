package com.example.consultorio_medico_api.utils.statemachine;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@SuperBuilder
@NoArgsConstructor
public abstract class EstadoSM {

    protected Integer id;
    protected String name;
    protected Set<Integer> actions;
    protected Set<Integer> transitions;

    public boolean validAction(Integer action) {
        return actions.contains(action);
    }

    public boolean validTransition(Integer transition) {
        return transitions.contains(transition);
    }
}
