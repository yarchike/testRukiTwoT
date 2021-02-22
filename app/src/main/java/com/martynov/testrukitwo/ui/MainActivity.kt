package com.martynov.testrukitwo.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.martynov.testrukitwo.R
import com.martynov.testrukitwo.adapter.CellAdapter
import com.martynov.testrukitwo.databinding.ActivityMainBinding
import com.martynov.testrukitwo.model.Cell
import com.martynov.testrukitwo.model.CellViewModel
import com.martynov.testrukitwo.model.State

class MainActivity : AppCompatActivity() {
    private val cellViewModel by lazy { ViewModelProviders.of(this).get(CellViewModel::class.java) }
    private lateinit var binding: ActivityMainBinding
    var items = ArrayList<Cell>()
    var cellAdapter: CellAdapter? = null
    var position = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cellAdapter = CellAdapter(items)
        with(binding.container) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = cellAdapter
        }
        cellViewModel.getListCell().observe(this, Observer {
            it?.let {
                cellAdapter?.refreshCell(listToArrayList(it))
                position = cellAdapter?.itemCount!!
                Log.d("My", cellAdapter?.getItemCount().toString())
            }
        })
        binding.container.smoothScrollToPosition(position)






        binding.buttonCreate.setOnClickListener {
            val cell = deadAndLive()

            if (items.size > 2 && items.get(items.size - 1).state == State.LIVE && items.get(items.size - 2).state == State.LIVE && items.get(
                    items.size - 3).state == State.LIVE
            ) {
                items.add(Cell(State.LIFE))
            }
            if (items.size > 3 && items.get(items.size - 1).state == State.DEAD && items.get(items.size - 2).state == State.DEAD && items.get(
                    items.size - 3).state == State.DEAD
            ) {
                if (items.get(items.size - 4).state == State.LIFE) {
                    items.removeAt(items.size - 4)
                }
            }


            binding.container.adapter?.notifyDataSetChanged()
            binding.container.smoothScrollToPosition(items.size - 1)


            cellViewModel.updateListCell(items)
        }
    }


    private fun deadAndLive(): Cell {
        val random = (0..1).random()
        val cell =
            when (random) {
                0 -> {
                    Cell(State.DEAD)
                }
                else -> {
                    Cell(State.LIVE)
                }
            }
        items.add(cell)
        return cell
    }

    fun listToArrayList(list: List<Cell>): ArrayList<Cell> {
        val mutableList = ArrayList<Cell>()
        for (i in list) {
            mutableList.add(i)
        }
        return mutableList

    }
}