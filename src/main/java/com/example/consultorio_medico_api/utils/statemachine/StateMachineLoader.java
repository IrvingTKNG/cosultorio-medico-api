package com.example.consultorio_medico_api.utils.statemachine;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public final class StateMachineLoader {
    private static final Gson GSON = new Gson();

    private StateMachineLoader() {
    }

    public static <T extends EstadoSM> void load(final String jsonFile, final MaquinaEstado<T> maquinaEstado, UnaryOperator<T> asignarEstadoSM) {
        try (InputStream jsonStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(jsonFile)) {
            assert jsonStream != null;
            try (InputStreamReader reader = new InputStreamReader(jsonStream, StandardCharsets.UTF_8)) {
                //A partir del json crear una instancia de MaquinaEstado<T>
                final Type type = maquinaEstado.getClass().getGenericSuperclass();
                final ParameterizedType pType = (ParameterizedType) type;
                final Class<T> estadoSMClass = (Class<T>) pType.getActualTypeArguments()[0];

                final var maquinaEstadosJson = GSON.<MaquinaEstado<T>>fromJson(reader, TypeToken.getParameterized(
                                MaquinaEstado.class, estadoSMClass)
                        .getType());
                MaquinaEstado.copyValues(maquinaEstadosJson, maquinaEstado);
                //Crea un Map<idEstado, Estado> inmutable
                final var entries = maquinaEstadosJson.getStates().stream()
                        .collect(Collectors.toUnmodifiableMap(EstadoSM::getId, asignarEstadoSM));
                maquinaEstado.setMapEstadoSM(entries);
            }

        } catch (Exception e) {
            throw new StateMachineLoaderException(e.getMessage());
        }
    }
    //Todo: agregar multiRol

    private static class StateMachineLoaderException extends RuntimeException {
        public StateMachineLoaderException(String message) {
            super("Error al leer el JSON de maquina de estados: " + message);
        }
    }

}
