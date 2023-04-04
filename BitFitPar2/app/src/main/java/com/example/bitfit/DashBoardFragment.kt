package com.example.bitfit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.bitfit.AppDatabase.Companion.getInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.max
import kotlin.math.min


class DashBoardFragment : Fragment() {
    private lateinit var nutritionDao: NutritionDao
    private lateinit var averageCal: TextView
    private lateinit var minCal: TextView
    private lateinit var maxCal: TextView
    private lateinit var clearButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize the 'nutritionDao' property in 'onCreate'
        val database = getInstance(requireContext())
        nutritionDao = database.nutritionDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dash_board, container, false)
        averageCal = view.findViewById<TextView>(R.id.averageCaloriesTV)
        minCal = view.findViewById<TextView>(R.id.minCaloriesTV)
        maxCal = view.findViewById<TextView>(R.id.maxCaloriesTV)
        clearButton = view.findViewById<Button>(R.id.clearDataBtn)
        return view
    }

    override fun onResume() {
        super.onResume()
        val nutritionRecords = mutableListOf<NutritionData>()

        lifecycleScope.launch {
            nutritionDao.getAll().collect { entities ->
                nutritionRecords.addAll(entities.map {
                    NutritionData(
                        it.typeOfFood,
                        it.amountOfCalories
                    )
                })
                var totalCalories = 0
                var minCalories = Int.MAX_VALUE
                var maxCalories = Int.MIN_VALUE

                for (record in nutritionRecords) {
                    val calories = record.caloriesCount?.toIntOrNull() ?: 0
                    totalCalories += calories
                    minCalories = min(minCalories, calories)
                    maxCalories = max(maxCalories, calories)
                }

                val recordCount = nutritionRecords.size
                val average = if (recordCount > 0) totalCalories / recordCount else 0

                // Set the values of the TextViews to display the calculated values
                averageCal.text = if (recordCount > 0) average.toString() else ""
                minCal.text = if (minCalories != Int.MAX_VALUE) minCalories.toString() else ""
                maxCal.text = if (maxCalories != Int.MIN_VALUE) maxCalories.toString() else ""
            }
        }

        clearButton.setOnClickListener {
            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    nutritionDao.deleteAll()

                    activity?.runOnUiThread {
                        averageCal.text = ""
                        minCal.text = ""
                        maxCal.text = ""
                    }
                }
            }
        }


        // handle the add new button from the DashBoard fragment
        val addNewFoodBtn = requireActivity().findViewById<Button>(R.id.addNewFoodBtn)
        addNewFoodBtn.setOnClickListener {
            (requireActivity() as MainActivity).onAddNewFoodClicked()
        }
    }


    companion object {
        fun newInstance(): DashBoardFragment {
            return DashBoardFragment()
        }
    }


}


