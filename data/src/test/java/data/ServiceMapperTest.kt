package data

import com.mobiletandil.data.mapper.transformToElixirs
import com.mobiletandil.data.mapper.transformToHead
import com.mobiletandil.data.mapper.transformToHouse
import com.mobiletandil.data.mapper.transformToSpells
import com.mobiletandil.data.mapper.transformToTrait
import com.mobiletandil.data.mapper.transformToWizards
import com.mobiletandil.data.service.response.ElixirsResponse
import com.mobiletandil.data.service.response.HouseHeadResponse
import com.mobiletandil.data.service.response.HouseResultResponse
import com.mobiletandil.data.service.response.SpellsResponse
import com.mobiletandil.data.service.response.TraitsResponse
import com.mobiletandil.data.service.response.WizardsResponse
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ServiceMapperTest {

    @Test
    fun `House mapper test`() {
        val house = MOCK_HOUSE.transformToHouse()
        assertEquals(MOCK_HOUSE.name, house.name)
    }

    @Test
    fun `Heads mapper test`() {
        val head = MOCK_HEADS.transformToHead()
        assertEquals(MOCK_HEADS.firstName, head.firstName)
    }

    @Test
    fun `Elixirs mapper test`() {
        val elixir = MOCK_ELIXIRS.transformToElixirs()
        assertEquals(MOCK_ELIXIRS.name, elixir.name)
    }

    @Test
    fun `Traits mapper test`() {
        val traits = MOCK_TRAITS.transformToTrait()
        assertEquals(MOCK_TRAITS.name, traits.name)
    }

    @Test
    fun `Spells mapper test`() {
        val spell = MOCK_SPELL.transformToSpells()
        assertEquals(MOCK_SPELL.name, spell.name)
    }

    @Test
    fun `Wizards mapper test`() {
        val wizard = MOCK_WIZARD.transformToWizards()
        assertEquals(MOCK_WIZARD.firstName, wizard.firstName)
    }

    companion object {
        private const val DEFAULT_NAME = "default"
        val MOCK_HOUSE: HouseResultResponse = HouseResultResponse(
            id = "",
            name = DEFAULT_NAME,
            houseColours = "",
            founder = "",
            animal = "",
            element = "",
            ghost = "",
            commonRoom = "",
            heads = emptyList(),
            traits = emptyList()
        )
        val MOCK_HEADS: HouseHeadResponse = HouseHeadResponse(
            id = "",
            firstName = DEFAULT_NAME,
            lastName = "",
        )

        val MOCK_ELIXIRS: ElixirsResponse = ElixirsResponse(
            id = "",
            name = DEFAULT_NAME,
        )

        val MOCK_TRAITS: TraitsResponse = TraitsResponse(
            id = "",
            name = DEFAULT_NAME
        )

        val MOCK_SPELL: SpellsResponse = SpellsResponse(
            id = "",
            name = DEFAULT_NAME,
            incantation = "",
            effect = "",
            canBeVerbal = true,
            type = "",
            light = "",
            creator = "",
        )

        val MOCK_WIZARD: WizardsResponse = WizardsResponse(
            id = "",
            firstName = DEFAULT_NAME,
            lastName = "",
            elixirs = emptyList(),
        )
    }
}
