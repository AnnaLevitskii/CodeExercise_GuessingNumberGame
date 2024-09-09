package com.tests;

import com.core.models.Game;
import com.core.models.User;
import com.core.providers.CleanUpProvider;
import com.core.providers.SetUpProvider;
import org.mockito.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static com.core.providers.MethodProvider.secretNumberOneWrong;

import static com.core.providers.MethodProvider.secretNumberWrongPlace;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

public class GameTest {
    User user1 = new User("Jane", 15);
    User user2 = new User("Ron", 25);
    @BeforeSuite
    public void bs(){
        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        SetUpProvider.setUpStorage(list);
    }

    @Test
    public void testStart_posetive() throws IOException {
        BufferedWriter bw = mock(BufferedWriter.class);
        BufferedReader br = mock(BufferedReader.class);
        Game game = new Game();
        game.setBw(bw);
        game.setBr(br);
        when(br.readLine()).thenReturn("Larisa");
        game.start();

        ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);

        verify(bw, times(1)).write(stringCaptor.capture());

        String firstCall = stringCaptor.getAllValues().get(0);
        assertEquals("Enter name:  ", firstCall);
    }
    @Test
    public void testStart_negative() throws IOException {
        BufferedWriter bw = mock(BufferedWriter.class);
        BufferedReader br = mock(BufferedReader.class);
        Game game = new Game();
        game.setBw(bw);
        game.setBr(br);
        when(br.readLine()).thenReturn("1Larisa", "Larisa");
        game.start();


        ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);

        verify(bw, times(3)).write(stringCaptor.capture());


        assertEquals("Enter name:  ", stringCaptor.getAllValues().get(0));
        assertEquals("Invalid name. Please try again.\n", stringCaptor.getAllValues().get(1));
        assertEquals("Enter name:  ", stringCaptor.getAllValues().get(2));
    }

    @Test
    public void testBody() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        BufferedWriter bw = mock(BufferedWriter.class);
        BufferedReader br = mock(BufferedReader.class);
        Game game = new Game();
        String secretNumber = game.getSecretNumber();

        game.setBw(bw);
        game.setBr(br);
        String secretNumberOneWrong = secretNumberOneWrong(secretNumber);

        Method numberCheck = Game.class.getDeclaredMethod("numberCheck", String.class);
        numberCheck.setAccessible(true);
        String secretNumberOneWrong_output = (String) numberCheck.invoke(game, secretNumberOneWrong);

        when(br.readLine()).thenReturn(secretNumber+"12", secretNumberOneWrong, secretNumber);
        game.body();


        ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);

        verify(bw, times(6)).write(stringCaptor.capture());


        assertEquals("Try to guess the four-digit number, no duplicate digits.", stringCaptor.getAllValues().get(0));
        assertEquals("Enter number:  ", stringCaptor.getAllValues().get(1));
        assertEquals("Incorrect Input 4 digits \n", stringCaptor.getAllValues().get(2));
        assertEquals("Enter number:  ", stringCaptor.getAllValues().get(3));
        assertEquals(secretNumberOneWrong_output + " \n", stringCaptor.getAllValues().get(4));
        assertEquals("Enter number:  ", stringCaptor.getAllValues().get(5));
    }

    @Test
    public void testHappyEnd() {

    }
    @Test
    public void testNumberCheck_positive() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Game game = new Game();
        String secretNumber = game.getSecretNumber();
        Method numberCheck = Game.class.getDeclaredMethod("numberCheck", String.class);
        numberCheck.setAccessible(true);
        String output = (String) numberCheck.invoke(game, secretNumber);
        assertEquals(output,"++++");
    }
    @Test
    public void testNumberCheck_negative_oneWrong() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Game game = new Game();
        String secretNumber = game.getSecretNumber();
        Method numberCheck = Game.class.getDeclaredMethod("numberCheck", String.class);
        numberCheck.setAccessible(true);
        String secretNumberOneWrong = secretNumberOneWrong(secretNumber);
        String secretNumberOneWrong_output = (String) numberCheck.invoke(game, secretNumberOneWrong);
        assertTrue(secretNumberOneWrong_output.contains("_"));
    }
    @Test
    public void testNumberCheck_negative_wrongPlace() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Game game = new Game();
        String secretNumber = game.getSecretNumber();
        Method numberCheck = Game.class.getDeclaredMethod("numberCheck", String.class);
        numberCheck.setAccessible(true);
        String secretNumberWrongPlace = secretNumberWrongPlace(secretNumber);
        String secretNumberWrongPlace_output = (String) numberCheck.invoke(game, secretNumberWrongPlace);
        assertEquals(secretNumberWrongPlace_output,"--++");
    }

    @AfterSuite
    public void as(){
        CleanUpProvider.cleanUpStorage();
    }
}