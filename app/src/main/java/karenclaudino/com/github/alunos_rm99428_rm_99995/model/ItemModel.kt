package karenclaudino.com.github.alunos_rm99428_rm_99995.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val localName: String,
    val eventType: String,
    val impactDegree: String,
    val eventDate: String,
    val affectedPeople: Int
)


