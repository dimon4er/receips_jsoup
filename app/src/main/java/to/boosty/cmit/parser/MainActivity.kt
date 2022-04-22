package to.boosty.cmit.parser

import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import to.boosty.cmit.parser.lists.Receipts
import to.boosty.cmit.parser.databinding.ActivityMainBinding

open class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var listReceipts: MutableList<Receipts>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listReceipts = mutableListOf()

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = linearLayoutManager

        GlobalScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                GetData().jsoupScan()
            }.onSuccess { result ->
                withContext(Dispatchers.Main) {
                    val receiptsAdapter = ReceiptsAdapter(
                        result,
                        this@MainActivity)
                    recyclerView.adapter = receiptsAdapter
                }
            }.onFailure { t->
                Toast.makeText(
                    this@MainActivity,
                    "При работе возникла ошибка $t",
                    LENGTH_LONG).show()
            }
        }
    }
}

