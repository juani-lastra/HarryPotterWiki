package data

import com.mobiletandil.data.mapper.database.transformToElixir
import com.mobiletandil.data.mapper.database.transformToHead
import com.mobiletandil.data.mapper.database.transformToHouse
import com.mobiletandil.data.mapper.database.transformToRoomElixir
import com.mobiletandil.data.mapper.database.transformToRoomHead
import com.mobiletandil.data.mapper.database.transformToRoomHouse
import com.mobiletandil.data.mapper.database.transformToRoomSpells
import com.mobiletandil.data.mapper.database.transformToRoomTrait
import com.mobiletandil.data.mapper.database.transformToRoomWizards
import com.mobiletandil.data.mapper.database.transformToSpell
import com.mobiletandil.data.mapper.database.transformToTrait
import com.mobiletandil.data.mapper.database.transformToWizard
import com.mobiletandil.domain.entity.Elixir
import com.mobiletandil.domain.entity.House
import com.mobiletandil.domain.entity.HouseHead
import com.mobiletandil.domain.entity.Spell
import com.mobiletandil.domain.entity.Traits
import com.mobiletandil.domain.entity.Wizard
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RoomMapperTest {

    @Test
    fun `House mapper test`() {
        val houseRoom = MOCK_HOUSE.transformToRoomHouse()
        assertEquals(MOCK_HOUSE, houseRoom.transformToHouse())
    }

    @Test
    fun `Heads mapper test`() {
        val headRoom = MOCK_HEADS.transformToRoomHead()
        assertEquals(MOCK_HEADS, headRoom.transformToHead())
    }

    @Test
    fun `Elixirs mapper test`() {
        val elixirRoom = MOCK_ELIXIRS.transformToRoomElixir()
        assertEquals(MOCK_ELIXIRS, elixirRoom.transformToElixir())
    }

    @Test
    fun `Traits mapper test`() {
        val traitsRoom = MOCK_TRAITS.transformToRoomTrait()
        assertEquals(MOCK_TRAITS, traitsRoom.transformToTrait())
    }

    @Test
    fun `Spells mapper test`() {
        val spellRoom = MOCK_SPELL.transformToRoomSpells()
        assertEquals(MOCK_SPELL, spellRoom.transformToSpell())
    }

    @Test
    fun `Wizards mapper test`() {
        val wizardRoom = MOCK_WIZARD.transformToRoomWizards()
        assertEquals(MOCK_WIZARD, wizardRoom.transformToWizard())
    }

    companion object {
        val MOCK_HOUSE: House = House(
            id = "",
            name = "ValidName",
            houseColours = "",
            founder = "",
            animal = "",
            element = "",
            ghost = "",
            commonRoom = "",
            heads = emptyList(),
            traits = emptyList()
        )
        val MOCK_HEADS: HouseHead = HouseHead(
            id = "",
            firstName = "ValidName",
            lastName = "",
        )

        val MOCK_ELIXIRS: Elixir = Elixir(
            id = "",
            name = "ValidName",
        )

        val MOCK_TRAITS: Traits = Traits(
            id = "",
            name = "ValidName"
        )

        val MOCK_SPELL: Spell = Spell(
            id = "",
            name = "ValidName",
            incantation = "",
            effect = "",
            canBeVerbal = true,
            type = "",
            light = "",
            creator = "",
        )

        val MOCK_WIZARD: Wizard = Wizard(
            id = "",
            firstName = "ValidName",
            lastName = "",
            elixirs = emptyList(),
        )
    }
}
