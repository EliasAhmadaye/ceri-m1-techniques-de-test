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
    private IPokedex pokedexMock;
    private IPokemonFactory pokemonFactoryMock;
    private IPokemonMetadataProvider metadataProviderMock;

    // Pokémon utilisés dans plusieurs tests
    private Pokemon bulbizarre, aquali;

    @Before
    public void setUp() {
        // Initialisation des mocks avec Mockito
        pokedexMock = mock(IPokedex.class);
        pokemonFactoryMock = mock(IPokemonFactory.class);
        metadataProviderMock = mock(IPokemonMetadataProvider.class);

        // Initialisation des objets Pokémon
        bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
        aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100);
    }

    // Test pour vérifier la taille du Pokedex
    @Test
    public void testSize() {
        when(pokedexMock.size()).thenReturn(2); // Simuler la taille du Pokédex
        assertEquals(2, pokedexMock.size());    // Vérifier que la taille est correcte
    }

    // Test pour l'ajout de Pokémon au Pokédex
    @Test
    public void testAddPokemon() {
        // Simuler l'ajout de Pokémon et vérifier les indices retournés
        when(pokedexMock.addPokemon(bulbizarre)).thenReturn(bulbizarre.getIndex());
        when(pokedexMock.addPokemon(aquali)).thenReturn(aquali.getIndex());

        assertEquals(0, pokedexMock.addPokemon(bulbizarre));
        assertEquals(133, pokedexMock.addPokemon(aquali));
    }

    // Test pour vérifier la récupération d'un Pokémon à partir de l'index
    @Test
    public void testGetPokemon() throws PokedexException {
        when(pokedexMock.getPokemon(0)).thenReturn(bulbizarre);  // Simuler la récupération d'un Pokémon

        Pokemon result = pokedexMock.getPokemon(0);  // Vérifier la récupération
        assertNotNull(result);
        assertEquals(bulbizarre, result);
    }

    // Test pour récupérer tous les Pokémon
    @Test
    public void testGetPokemons() {
        List<Pokemon> pokemonList = new ArrayList<>();
        pokemonList.add(bulbizarre);
        pokemonList.add(aquali);

        when(pokedexMock.getPokemons()).thenReturn(pokemonList); // Simuler la liste de Pokémon

        List<Pokemon> result = pokedexMock.getPokemons();  // Vérifier la récupération
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(pokemonList, result);
    }

    // Test pour la création d'un Pokémon via IPokemonFactory
    @Test
    public void testCreatePokemon() {
        // Simuler la création d'un Pokémon
        when(pokemonFactoryMock.createPokemon(0, 613, 64, 4000, 4)).thenReturn(bulbizarre);

        Pokemon createdPokemon = pokemonFactoryMock.createPokemon(0, 613, 64, 4000, 4);  // Vérifier la création
        assertNotNull(createdPokemon);
        assertEquals("Bulbizarre", createdPokemon.getName());
        assertEquals(613, createdPokemon.getCp());
        assertEquals(64, createdPokemon.getHp());
    }

    // Test pour récupérer les métadonnées d'un Pokémon via IPokemonMetadataProvider
    @Test
    public void testGetPokemonMetadata() throws PokedexException {
        PokemonMetadata bulbizarreMetadata = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);

        // Simuler la récupération des métadonnées
        when(metadataProviderMock.getPokemonMetadata(0)).thenReturn(bulbizarreMetadata);

        PokemonMetadata metadata = metadataProviderMock.getPokemonMetadata(0);  // Vérifier les métadonnées
        assertNotNull(metadata);
        assertEquals("Bulbizarre", metadata.getName());
        assertEquals(126, metadata.getAttack());
        assertEquals(126, metadata.getDefense());
        assertEquals(90, metadata.getStamina());
    }

    // Test pour vérifier les Pokémon triés par comparateurs (exemple d'utilisation avec INDEX)
    @Test
    public void testGetPokemonsWithComparator() {
        List<Pokemon> sortedByIndex = List.of(bulbizarre, aquali);
        when(pokedexMock.getPokemons(PokemonComparators.INDEX)).thenReturn(sortedByIndex);

        List<Pokemon> result = pokedexMock.getPokemons(PokemonComparators.INDEX);  // Vérifier le tri par index
        assertNotNull(result);
        assertEquals(sortedByIndex, result);
    }
}
