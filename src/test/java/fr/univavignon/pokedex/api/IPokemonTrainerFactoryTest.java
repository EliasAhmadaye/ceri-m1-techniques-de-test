package fr.univavignon.pokedex.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IPokemonTrainerFactoryTest {

    // Mocks
    IPokemonTrainerFactory mockPokemonTrainerFactory;
    IPokedexFactory mockPokedexFactory;
    IPokedex mockPokedex;
    PokemonTrainer trainer;

    @Before
    public void setUp() {
        // Initialiser les mocks
        mockPokemonTrainerFactory = mock(IPokemonTrainerFactory.class);
        mockPokedexFactory = mock(IPokedexFactory.class);
        mockPokedex = mock(IPokedex.class);

        // Créer une instance de PokemonTrainer pour le mock
        trainer = new PokemonTrainer("Abdel", Team.INSTINCT, mockPokedex);

        // Configurer le mock pour renvoyer un dresseur (trainer) quand createTrainer est appelé
        when(mockPokemonTrainerFactory.createTrainer("Abdel", Team.INSTINCT, mockPokedexFactory))
            .thenReturn(trainer);
    }

    // Test pour vérifier le nom du dresseur
    @Test
    public void testCreateTrainer() {
        PokemonTrainer createdTrainer = mockPokemonTrainerFactory.createTrainer("Abdel", Team.INSTINCT, mockPokedexFactory);
        assertEquals("Abdel", createdTrainer.getName());
    }

    // Test pour vérifier l'équipe du dresseur
    @Test
    public void testCreateTrainerGetTeam() {
        PokemonTrainer createdTrainer = mockPokemonTrainerFactory.createTrainer("Abdel", Team.INSTINCT, mockPokedexFactory);
        assertEquals(Team.INSTINCT, createdTrainer.getTeam());
    }

    // Test pour vérifier que le Pokédex du dresseur n'est pas nul
    @Test
    public void testCreateTrainerGetPokedex() {
        PokemonTrainer createdTrainer = mockPokemonTrainerFactory.createTrainer("Abdel", Team.INSTINCT, mockPokedexFactory);
        assertNotNull(createdTrainer.getPokedex());
    }
}
