package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class HomeControllerTest {

    @InjectMocks
    HomeController homePageController;

    @Test
    void homePageReturnString() {
        String result = homePageController.homepage();
        assertEquals("HomePage", result);
    }
}

