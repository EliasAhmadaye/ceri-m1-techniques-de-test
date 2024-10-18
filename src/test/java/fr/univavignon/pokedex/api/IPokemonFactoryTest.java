package fr.univavignon.pokedex.api;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

// Test de IPokemonFactory
public class IPokemonFactoryTest {

    // Mock de IPokemonFactory
    IPokemonFactory mockPokemonFactory = mock(IPokemonFactory.class);

    // Création d'instances de Pokémon pour Bulbizarre et Aquali
    Pokemon bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
    Pokemon aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100);

    @Before
    public void init() {
        // Définir le comportement du mock pour la création de Bulbizarre et d'Aquali
        when(mockPokemonFactory.createPokemon(0, 613, 64, 4000, 4)).thenReturn(bulbizarre);
        when(mockPokemonFactory.createPokemon(133, 2729, 202, 5000, 4)).thenReturn(aquali);
    }

    @Test
    public void testCreatePokemonBulbizarre() {
        // Récupérer le Pokémon Bulbizarre via le mock
        Pokemon p = mockPokemonFactory.createPokemon(0, 613, 64, 4000, 4);

        // Vérifier les caractéristiques de Bulbizarre
        assertEquals(0, p.getIndex());
        assertEquals("Bulbizarre", p.getName());
        assertEquals(126, p.getAttack());
        assertEquals(126, p.getDefense());
        assertEquals(90, p.getStamina());
        assertEquals(613, p.getCp());
        assertEquals(64, p.getHp());
        assertEquals(4000, p.getDust());
        assertEquals(4, p.getCandy());
        assertEquals(56, p.getIv(), 0.01);  // Tolérance pour les valeurs flottantes
    }

    @Test
    public void testCreatePokemonAquali() {
        // Récupérer le Pokémon Aquali via le mock
        Pokemon p = mockPokemonFactory.createPokemon(133, 2729, 202, 5000, 4);

        // Vérifier les caractéristiques d'Aquali
        assertEquals(133, p.getIndex());
        assertEquals("Aquali", p.getName());
        assertEquals(186, p.getAttack());
        assertEquals(168, p.getDefense());
        assertEquals(260, p.getStamina());
        assertEquals(2729, p.getCp());
        assertEquals(202, p.getHp());
        assertEquals(5000, p.getDust());
        assertEquals(4, p.getCandy());
        assertEquals(100, p.getIv(), 0.01);  // Tolérance pour les valeurs flottantes
    }
}
