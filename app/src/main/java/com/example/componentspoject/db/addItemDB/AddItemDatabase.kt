package com.example.componentspoject.db.addItemDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.componentspoject.model.ListItem

@Database(entities = [ListItem::class], version = 1)
abstract class AddItemDatabase : RoomDatabase() {
    abstract fun addItemDao(): AddItemDao

    companion object {
        @Volatile
        private var INSTANCE: AddItemDatabase? = null

        fun getDatabase(context: Context): AddItemDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AddItemDatabase::class.java,
                    "item_database"
                ).build()
                INSTANCE = instance
                instance              // return instance
            }
        }
    }
}