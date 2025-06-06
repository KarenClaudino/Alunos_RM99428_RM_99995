package karenclaudino.com.github.alunos_rm99428_rm_99995.data

import androidx.room.Database
import androidx.room.RoomDatabase
import karenclaudino.com.github.alunos_rm99428_rm_99995.model.ItemModel

@Database(entities = [ItemModel::class], version = 1)
abstract class ItemDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao
}