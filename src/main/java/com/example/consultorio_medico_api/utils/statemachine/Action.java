package com.example.consultorio_medico_api.utils.statemachine;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.StringJoiner;

@Getter
@Setter
public class Action {

    private Integer id;
    private String name;

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Action action = (Action)o;
            return Objects.equals(this.id, action.id);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(this.id);
    }

    public String toString() {
        return (new StringJoiner(", ", Action.class.getSimpleName() + "[", "]")).add("id=" + this.id).add("name='" + this.name + "'").toString();
    }
}
