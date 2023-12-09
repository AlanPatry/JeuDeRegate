package fr.ensicaen.regatta.model;

import fr.ensicaen.regatta.mvp.model.BoatModel;
import fr.ensicaen.regatta.mvp.model.PlayerModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    @ParameterizedTest
    @MethodSource("usernames")
    void should_get_nickname_when_set_nickname( String name ) {
        // TODO This test is of no interest. It only shows the use of a parameterized test
        PlayerModel loginModel = new PlayerModel();
        loginModel.setNickname(name);
        assertEquals(name, loginModel.getNickname());
    }

    @ParameterizedTest
    @MethodSource("models")
    void should_get_model_when_set_model( String model ) {
        BoatModel boatModel = new BoatModel();
        boatModel.setBoatModel(model);
        assertEquals(model, boatModel.getModel());
    }

    @ParameterizedTest
    @MethodSource("sails")
    void should_get_sailSize_when_set_sailSize( String sailSize ) {
        BoatModel boatModel = new BoatModel();
        boatModel.setSailSize(sailSize);
        if (sailSize.equals("Grande")) {
            assertEquals(1, boatModel.getSailSize());
        } else if (sailSize.equals("Petite")) {
            assertEquals(2, boatModel.getSailSize());
        }
    }

    @ParameterizedTest
    @MethodSource("passengers")
    void should_get_nbPassengers_when_set_nbPassengers( String nbPassengers ) {
        BoatModel boatModel = new BoatModel();
        boatModel.setNbPassengers(nbPassengers);
        assertEquals(Integer.parseInt(nbPassengers), boatModel.getNbPassengers());
    }

    public static Stream<Arguments> usernames() {
        return Stream.of(
                Arguments.of("Toto"),
                Arguments.of("Un nom avec espace")
        );
    }

    public static Stream<Arguments> models() {
        return Stream.of(
                Arguments.of("figaro"),
                Arguments.of("oceanis-37")
        );
    }

    public static Stream<Arguments> sails() {
        return Stream.of(
                Arguments.of("Grande"),
                Arguments.of("Petite")
        );
    }

    public static Stream<Arguments> passengers() {
        return Stream.of(
                Arguments.of("2"),
                Arguments.of("4")
        );
    }
}