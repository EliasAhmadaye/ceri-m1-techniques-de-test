
package fr.univavignon.pokedex.api;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IPokemonMetadataProviderTest {

    // Mocks pour le fournisseur de métadonnées et les objets PokemonMetadata
    IPokemonMetadataProvider metaData = mock(IPokemonMetadataProvider.class);
    PokemonMetadata bulbizarre = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
    PokemonMetadata aquali = new PokemonMetadata(133, "Aquali", 186, 168, 260);

    @Before
    public void setUp() throws PokedexException {
        // Configurer le mock pour retourner les métadonnées de Bulbizarre et d'Aquali
        when(metaData.getPokemonMetadata(0)).thenReturn(bulbizarre);
        when(metaData.getPokemonMetadata(133)).thenReturn(aquali);
    }

    @Test
    public void testMetaDataBulbizarre() throws PokedexException {
        // Récupérer les métadonnées de Bulbizarre via le mock
        PokemonMetadata testMetaData = metaData.getPokemonMetadata(0);

        // Vérifier que les métadonnées sont correctes
        assertEquals(0, testMetaData.getIndex());
        assertEquals("Bulbizarre", testMetaData.getName());
        assertEquals(126, testMetaData.getAttack());
        assertEquals(126, testMetaData.getDefense());
        assertEquals(90, testMetaData.getStamina());
    }

    @Test
    public void testMetaDataAquali() throws PokedexException {
        // Récupérer les métadonnées d'Aquali via le mock
        PokemonMetadata testMetaData = metaData.getPokemonMetadata(133);

        // Vérifier que les métadonnées sont correctes
        assertEquals(133, testMetaData.getIndex());
        assertEquals("Aquali", testMetaData.getName());
        assertEquals(186, testMetaData.getAttack());
        assertEquals(168, testMetaData.getDefense());
        assertEquals(260, testMetaData.getStamina());
    }
}