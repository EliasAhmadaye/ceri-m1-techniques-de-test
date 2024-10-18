package fr.univavignon.pokedex.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IPokedexFactoryTest {

    // Mocks pour les interfaces
    IPokedexFactory mockPokedexFactory;
    IPokemonMetadataProvider mockMetadataProvider;
    IPokemonFactory mockPokemonFactory;
    IPokedex mockPokedex;

    @Before
    public void init() {
        // Initialisation des mocks
        mockPokedexFactory = mock(IPokedexFactory.class);
        mockMetadataProvider = mock(IPokemonMetadataProvider.class);
        mockPokemonFactory = mock(IPokemonFactory.class);
        mockPokedex = mock(IPokedex.class);

        // Définir le comportement de createPokedex
        when(mockPokedexFactory.createPokedex(mockMetadataProvider, mockPokemonFactory)).thenReturn(mockPokedex);
    }

    @Test
    public void testPokedexIsNotNull() {
        // Vérifier que createPokedex renvoie un Pokédex non nul
        IPokedex resultPokedex = mockPokedexFactory.createPokedex(mockMetadataProvider, mockPokemonFactory);
        assertNotNull(resultPokedex);  // Vérifie que le Pokedex n'est pas nul
    }

    @Test
    public void testPokedexFactoryReturnsCorrectPokedex() {
        // Vérifier que le Pokédex renvoyé est bien celui mocké (mockPokedex)
        IPokedex resultPokedex = mockPokedexFactory.createPokedex(mockMetadataProvider, mockPokemonFactory);
        assertEquals(mockPokedex, resultPokedex);  // Vérifie que le bon Pokedex est renvoyé
    }

    @Test
    public void testPokedexFactoryUsesCorrectMetadataAndFactory() {
        // Vérifier que la méthode createPokedex est bien appelée avec les bons arguments
        mockPokedexFactory.createPokedex(mockMetadataProvider, mockPokemonFactory);
        verify(mockPokedexFactory).createPokedex(mockMetadataProvider, mockPokemonFactory);
    }
}
