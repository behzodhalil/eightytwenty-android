package uz.behzod.eightytwenty

import android.content.Context
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import uz.behzod.eightytwenty.data.local.dao.BillDao
import uz.behzod.eightytwenty.data.local.db.EightyTwentyDatabase

@RunWith(AndroidJUnit4::class)
@SmallTest
class BillDaoTest {

    private lateinit var db: EightyTwentyDatabase
    private lateinit var context: Context
    private lateinit var billDao: BillDao

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()

        db= Room.inMemoryDatabaseBuilder(
            context,
            EightyTwentyDatabase::class.java
        ).build()

        billDao = db.getBillDao()
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun check_insert_bill_element() = runBlocking{
        val bill = createBill()
        billDao.insert(bill)

        val bills = billDao.fetchBills().asLiveData().getOrAwaitValue()

        1 shouldBeEqualTo bills.size

    }
}