package com.bensdevelops.myGOT.network.repository

import androidx.compose.foundation.layout.add
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class FlashCardRepositoryImplTest {


    private lateinit var cr: CollectionReference
    private lateinit var flashCardRepository: FlashCardRepositoryImpl

    @Before
    fun setup() {
        cr = mock()
        flashCardRepository = FlashCardRepositoryImpl(cr)
    }

    @Test
    fun getFlashCards_success_shouldPopulateFlashDatabase() = runTest {
        // Mock Firestore data
        val mockDocumentSnapshot: DocumentSnapshot = mock()
        val mockQuerySnapshot: QuerySnapshot = mock()
        val mockTask: Task<QuerySnapshot> = mock()

        val flashCardData = mapOf(
            "question" to "Question 1",
            "answer" to "Answer 1",
            "tags" to listOf("tag1", "tag2")
        )
        val firestoreData = mapOf("flashCardList" to listOf(flashCardData))

        // Stubbing the Firestore calls
        whenever(cr.get()).thenReturn(mockTask)
        whenever(mockTask.isComplete).thenReturn(true)
        whenever(mockTask.isSuccessful).thenReturn(true)
        whenever(mockTask.result).thenReturn(mockQuerySnapshot)
        whenever(mockQuerySnapshot.documents).thenReturn(listOf(mockDocumentSnapshot))
        whenever(mockDocumentSnapshot.data).thenReturn(firestoreData)

        // Call the function
        val result = flashCardRepository.getFlashCards()

        // Assertions
        assertEquals(1, result.size)
        assertEquals("Question 1", result[0].question)
        assertEquals("Answer 1", result[0].answer)
        assertEquals(listOf("tag1", "tag2"), result[0].tags)
    }

    @Test
    fun uploadFlashCards_success_shouldCallAddAndAwait() = runTest {
        // Mock the Task and stub await() to return null (success)
        val mockTask: Task<DocumentReference> = mock()
        doAnswer { null }.whenever(mockTask).await()
        whenever(mockTask.isComplete).thenReturn(true) // Mock isComplete to return true
        whenever(mockTask.isSuccessful).thenReturn(true) // Mock isSuccessful to return true
        whenever(mockTask.result).thenReturn(null) // Mock result to return null (success)
        whenever(cr.add(any())).thenReturn(mockTask)

        val data = mutableMapOf<String, Any>("key" to "value")
        flashCardRepository.uploadFlashCards(data)

        // Verify that add() was called with the data
        verify(cr).add(data)
        // Verify that await() was called on the Task
        verify(mockTask).await()
    }

    @Test
    fun `uploadFlashCards_failure_shouldCatchException`() = runTest {
        // Mock the Task and stub await() to throw an exception
        val mockTask: Task<DocumentReference> = mock()
        doThrow(RuntimeException("Test exception")).whenever(mockTask).await()
        whenever(cr.add(any())).thenReturn(mockTask)

        val data = mutableMapOf<String, Any>("key" to "value")
        flashCardRepository.uploadFlashCards(data)

        // Verify that add() was called with the data
        verify(cr).add(data)
        // Verify that await() was called on the Task (even though it throws)
        verify(mockTask).await()
        // No assertions for exceptions, as they are caught silently
    }

}