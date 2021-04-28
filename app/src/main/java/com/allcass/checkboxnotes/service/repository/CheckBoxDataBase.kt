package com.allcass.checkboxnotes.service.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.allcass.checkboxnotes.service.model.CheckBoxModel


@Database(entities = [CheckBoxModel::class], version = 1)
abstract class CheckBoxDataBase : RoomDatabase() {

    abstract fun checkBoxDAO(): CheckBoxDAO
    abstract fun noteDAO(): NoteDAO

    companion object {
        private lateinit var INSTANCE: CheckBoxDataBase

        fun getDatabase(context: Context): CheckBoxDataBase {
            if (!::INSTANCE.isInitialized) {
                synchronized(CheckBoxDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context,CheckBoxDataBase::class.java, "checkBoxDB")
                        .addMigrations(MIGRATION_1_2)
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }


/**
         * Atualização de versão de banco de dados
         */

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("DELETE FROM Guest")
            }
        }

    }
}
