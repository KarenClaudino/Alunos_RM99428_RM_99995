package karenclaudino.com.github.alunos_rm99428_rm_99995;

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import karenclaudino.com.github.alunos_rm99428_rm_99995.R
import karenclaudino.com.github.alunos_rm99428_rm_99995.model.ItemModel
import karenclaudino.com.github.alunos_rm99428_rm_99995.viewmodel.ItemsAdapter
import karenclaudino.com.github.alunos_rm99428_rm_99995.viewmodel.ItemsViewModel
import karenclaudino.com.github.alunos_rm99428_rm_99995.viewmodel.ItemsViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ItemsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Impacto Certo"

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val itemsAdapter = ItemsAdapter { item ->
            viewModel.removeItem(item)
        }
        recyclerView.adapter = itemsAdapter

        val editTextLocalName = findViewById<EditText>(R.id.editTextLocalName)
        val editTextEventType = findViewById<EditText>(R.id.editTextEventType)
        val editTextImpactDegree = findViewById<EditText>(R.id.editTextImpactDegree)
        val editTextEventDate = findViewById<EditText>(R.id.editTextEventDate)
        val editTextAffectedPeople = findViewById<EditText>(R.id.editTextAffectedPeople)
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            val localName = editTextLocalName.text.toString()
            val eventType = editTextEventType.text.toString()
            val impactDegree = editTextImpactDegree.text.toString()
            val eventDate = editTextEventDate.text.toString()
            val affectedPeopleStr = editTextAffectedPeople.text.toString()

            if (localName.isEmpty()) {
                editTextLocalName.error = "Nome do local"
                return@setOnClickListener
            }
            if (eventType.isEmpty()) {
                editTextEventType.error = "Tipo do evento"
                return@setOnClickListener
            }
            if (impactDegree.isEmpty()) {
                editTextImpactDegree.error = "Grau de impacto"
                return@setOnClickListener
            }
            if (eventDate.isEmpty()) {
                editTextEventDate.error = "Data do evento"
                return@setOnClickListener
            }
            if (affectedPeopleStr.isEmpty()) {
                editTextAffectedPeople.error = "Número de pessoas afetadas"
                return@setOnClickListener
            }

            val affectedPeople = affectedPeopleStr.toIntOrNull()
            if (affectedPeople == null || affectedPeople <= 0) {
                editTextAffectedPeople.error = "Número de pessoas afetadas deve ser maior que zero"
                return@setOnClickListener
            }

            val newItem = ItemModel(
                localName = localName,
                eventType = eventType,
                impactDegree = impactDegree,
                eventDate = eventDate,
                affectedPeople = affectedPeople
            )
            viewModel.addItem(newItem)

            editTextLocalName.text.clear()
            editTextEventType.text.clear()
            editTextImpactDegree.text.clear()
            editTextEventDate.text.clear()
            editTextAffectedPeople.text.clear()
        }

        val viewModelFactory = ItemsViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ItemsViewModel::class.java)

        viewModel.itemsLiveData.observe(this) { items ->
            itemsAdapter.updateItems(items)
        }
    }
}

