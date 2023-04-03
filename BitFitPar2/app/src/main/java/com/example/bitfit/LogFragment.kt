package com.example.bitfit

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [LogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LogFragment : Fragment() {
    private lateinit var nutritionRecyclerView: RecyclerView
    private lateinit var nutritionAdapter: NutritionAdapter
    private val savedNutritionData = mutableListOf<NutritionData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        Inflate a new view hierarchy from the specified xml resource
        val view = inflater.inflate(R.layout.fragment_log, container, false)

        val addNewFoodBtn = requireActivity().findViewById<Button>(R.id.addNewFoodBtn)

        addNewFoodBtn.setOnClickListener {
            (requireActivity() as MainActivity).onAddNewFoodClicked()
        }

        // Add these configurations for the recyclerView and to configure the adapter
        val layoutMan = LinearLayoutManager(context)
        nutritionRecyclerView = view.findViewById(R.id.food_list_recy_view)
        nutritionRecyclerView.layoutManager = layoutMan
        nutritionRecyclerView.setHasFixedSize(true)
        nutritionAdapter = NutritionAdapter(savedNutritionData)
        nutritionRecyclerView.adapter = nutritionAdapter

        lifecycleScope.launch() {
            (requireActivity().application as NutritionApplication).db.nutritionDao().getAll()
                .collect { dataBaseList ->
                    dataBaseList.map { entity ->
                        NutritionData(entity.typeOfFood, entity.amountOfCalories)
                    }.also { mappedList ->
                        savedNutritionData.clear()
                        savedNutritionData.addAll(mappedList)
                        nutritionAdapter.notifyDataSetChanged()
                    }
                }

        }

        return view // Inflate the layout for this fragment
    }

    companion object {
        /**
         * creates a new instance of this fragment
         */
        fun newInstance(): LogFragment {
            return LogFragment()
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}