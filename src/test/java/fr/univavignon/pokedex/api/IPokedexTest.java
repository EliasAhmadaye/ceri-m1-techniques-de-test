package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IPokedexTest {

    // Mocks pour IPokedex et ses dépendances
    IPokedex iPokedex;
    IPokemonFactory iPokemonFactory;
    IPokemonMetadataProvider iPokemonMetadataProvider;

    @Before
    public void setUp() {
        // Initialisation des mocks avec Mockito
        iPokedex = mock(IPokedex.class);
        iPokemonFactory = mock(IPokemonFactory.class);
        iPokemonMetadataProvider = mock(IPokemonMetadataProvider.class);
    }

    // Test pour vérifier la taille du Pokedex
    @Test
    public void testSize() {
        when(iPokedex.size()).thenReturn(2); // Simule la taille du Pokédex
        assertEquals(2, iPokedex.size());    // Vérifie que la taille est correcte
    }

    // Test pour l'ajout de Pokémon au Pokédex
    @Test
    public void testAddPokemon() {
        Pokemon pok1 = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
        Pokemon pok2 = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100);

        // Simuler l'ajout de Pokémon et vérifier les indices retournés
        when(iPokedex.addPokemon(pok1)).thenReturn(pok1.getIndex());
        when(iPokedex.addPokemon(pok2)).thenReturn(pok2.getIndex());

        assertEquals(0, iPokedex.addPokemon(pok1));
        assertEquals(133, iPokedex.addPokemon(pok2));
    }

    // Test pour vérifier la récupération d'un Pokémon à partir de l'index
    @Test
    public void testGetPokemon() throws PokedexException {
        Pokemon pok1 = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);

        when(iPokedex.getPokemon(0)).thenReturn(pok1);  // Simuler la récupération d'un Pokémon

        Pokemon result = iPokedex.getPokemon(0);  // Vérifier la récupération
        assertNotNull(result);
        assertEquals(pok1, result);
    }

    // Test pour récupérer tous les Pokémon
    @Test
    public void testGetPokemons() {
        Pokemon pok1 = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
        Pokemon pok2 = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100);
        List<Pokemon> pokemonList = new ArrayList<>();
        pokemonList.add(pok1);
        pokemonList.add(pok2);

        when(iPokedex.getPokemons()).thenReturn(pokemonList); // Simuler la liste de Pokémon

        List<Pokemon> result = iPokedex.getPokemons();  // Vérifier la récupération
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(pokemonList, result);
    }

    // Test pour la création d'un Pokémon via iPokemonFactory
    @Test
    public void testCreatePokemon() {
        Pokemon pok1 = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);

        // Simuler la création d'un Pokémon
        when(iPokemonFactory.createPokemon(0, 613, 64, 4000, 4))
            .thenReturn(pok1);

        Pokemon createdPokemon = iPokemonFactory.createPokemon(0, 613, 64, 4000, 4);  // Vérifier la création
        assertEquals("Bulbizarre", createdPokemon.getName());
        assertEquals(613, createdPokemon.getCp());
        assertEquals(64, createdPokemon.getHp());
    }

    // Test pour récupérer les métadonnées d'un Pokémon via iPokemonMetadataProvider
    @Test
    public void testGetPokemonMetadata() throws PokedexException {
        PokemonMetadata bulbizarreMetadata = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);

        // Simuler la récupération des métadonnées
        when(iPokemonMetadataProvider.getPokemonMetadata(0))
            .thenReturn(bulbizarreMetadata);

        PokemonMetadata metadata = iPokemonMetadataProvider.getPokemonMetadata(0);  // Vérifier les métadonnées
        assertEquals("Bulbizarre", metadata.getName());
        assertEquals(126, metadata.getAttack());
        assertEquals(126, metadata.getDefense());
    }
}
