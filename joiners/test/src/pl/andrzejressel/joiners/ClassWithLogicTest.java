package pl.andrzejressel.joiners;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClassWithLogicTest {

    @Test
    void getNumber() {
        ClassWithLogic classWithLogic = new ClassWithLogic();
        int result = classWithLogic.getNumber();
        assertEquals(42, result);
    }

}