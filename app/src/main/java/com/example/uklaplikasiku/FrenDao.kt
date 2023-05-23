package com.example.uklaplikasiku

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FrenDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun tambahTeman(fren: Fren)
    @Query("SELECT * FROM Fren")
    fun ambilSemuaTeman(): LiveData<List<Fren>>
}