package uz.behzod.eightytwenty

import org.junit.Assert
import org.junit.Test
import uz.behzod.eightytwenty.data.local.entities.*
import uz.behzod.eightytwenty.domain.model.CategoryAndNotesDomainModel

class MapperTest {

    @Test
    fun `map note entity to note domain model`() {
        val noteEntity = createNoteEntity()
        val noteDomain = noteEntity.asDomain()

        Assert.assertEquals(noteDomain.title,noteEntity.title)
    }

    @Test
    fun `map note domain model note entity`() {
        val noteDomain = createNoteDomainModel()
        val noteEntity = noteDomain.asEntity()

        Assert.assertEquals(noteEntity.title,noteDomain.title)
    }

    @Test
    fun `map to note category entity to category domain model`() {
        val categoryEntity = createNoteCategoryEntity()
        val categoryDomain = categoryEntity.asDomain()

        Assert.assertEquals(categoryDomain.uid,categoryEntity.id)
    }

    @Test
    fun `map to category domain model to category entity`() {
        val categoryDomain = createNoteCategoryDomainModel()
        val categoryEntity = categoryDomain.asEntity()

        Assert.assertEquals(categoryEntity.id, categoryDomain.uid)
    }

    @Test
    fun `map to categories and notes entities to domain model`() {
        val note = createNoteEntity()
        val category = createNoteCategoryEntity()
        val listEntity = CategoryAndNotes(category, listOf(note))

        val listDomain = listEntity.asDomain()

        Assert.assertEquals(listDomain.category.uid,listEntity.noteCategoryEntity.id)
    }

    @Test
    fun `map to categories and notes domain model to category entity`() {
        val note = createNoteDomainModel()
        val category = createNoteCategoryDomainModel()

        val listDomain = CategoryAndNotesDomainModel(category, listOf(note))

        val listEntity = listDomain.asEntity()

        Assert.assertEquals(listEntity.noteCategoryEntity.id,listDomain.category.uid)
    }

    @Test
    fun `assign map to list entities to domain list models`() {
        val listEntity = listOf(createNoteEntity(), createNoteEntity())
        val listDomain = listEntity.map { it.asDomain() }

        Assert.assertEquals(listDomain.size,listEntity.size)
    }
}