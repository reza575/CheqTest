package com.moeiny.reza.kotlincheqtest.fragment

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.moeiny.reza.cheqtest.data.Cheq
import com.moeiny.reza.kotlincheqtest.customview.BarChartView
import com.moeiny.reza.kotlincheqtest.customview.ExpenseView
import com.moeiny.reza.kotlincheqtest.customview.PieChartView
import com.moeiny.reza.kotlincheqtest.R
import com.moeiny.reza.kotlincheqtest.retrofit.ApiClient
import com.moeiny.reza.kotlincheqtest.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

class ExpenseFragment : Fragment() {
    //Var's
    lateinit var myView: View
    lateinit var carousel: LinearLayout
    lateinit var layoutTopLeft: LinearLayout
    lateinit var layoutTopRight: LinearLayout
    lateinit var layoutDownLeft: LinearLayout
    lateinit var layoutDownRight: LinearLayout
    lateinit var layoutParams: LinearLayout.LayoutParams
    lateinit var monthSpinner: Spinner


    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {

        myView = inflater.inflate(R.layout.fragment_expense, container, false)
        monthSpinner = myView.findViewById(R.id.spn_expensefragment_month)
        layoutTopLeft = myView.findViewById(R.id.ll_expensefragment_topleft)
        layoutTopRight = myView.findViewById(R.id.ll_expensefragment_topright)
        layoutDownLeft = myView.findViewById(R.id.ll_expensefragment_lowleft)
        layoutDownRight = myView.findViewById(R.id.ll_expensefragment_lowright)
        carousel = myView.findViewById(R.id.ll_expensefragment_carousel)

        getCheqInfo()

        val months = arrayOf("January","February","March","April","May","June","July","August",
                             "September","October","November","December")

        val adapter = ArrayAdapter(context!!, android.R.layout.simple_spinner_item, months)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        monthSpinner.adapter = adapter

        return myView
    }

    fun getCheqInfo(){
        var apiClient= ApiClient()
        var call: Call<Cheq> =apiClient.getClient().create(ApiService::class.java).getCheq()
        call.enqueue(object : Callback<Cheq> {
            override fun onFailure(call: Call<Cheq>, t: Throwable) {
                Toast.makeText(context,t.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Cheq>, response: Response<Cheq>) {
                 var cheq:Cheq?=response!!.body()
                showData(cheq)
            }
        })
    }

    fun showData(cheq: Cheq?){
        var categoryList=cheq!!.cheq.category
        val houseHoldView= ExpenseView(context)
        val bmphousehold = BitmapFactory.decodeResource(resources, R.drawable.house1)
        houseHoldView.setBmp(bmphousehold)
        houseHoldView.setTitle("Household")
        houseHoldView.setCost(categoryList[0].amount.toInt())
        houseHoldView.setPercent(categoryList[0].percentage.toInt())

        layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        layoutTopLeft.addView(houseHoldView, layoutParams)

        val groceryView= ExpenseView(context)
        val bmpgrocery = BitmapFactory.decodeResource(resources, R.drawable.grocery1)
        groceryView.setBmp(bmpgrocery)
        groceryView.setTitle("Groceries")
        groceryView.setCost(categoryList[1].amount.toInt())
        groceryView.setPercent(categoryList[1].percentage.toInt())

        layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        layoutTopRight.addView(groceryView, layoutParams)

        val transportView= ExpenseView(context)
        val bmptransport = BitmapFactory.decodeResource(resources, R.drawable.transport1)
        transportView.setBmp(bmptransport)
        transportView.setTitle("Transport")
        transportView.setCost(categoryList[2].amount.toInt())
        transportView.setPercent(categoryList[2].percentage.toInt())

        layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        layoutDownLeft.addView(transportView, layoutParams)

        val foodView= ExpenseView(context)
        val bmpfood = BitmapFactory.decodeResource(resources, R.drawable.food1)
        foodView.setBmp(bmpfood)
        foodView.setTitle("Food & Drink")
        foodView.setCost(categoryList[3].amount.toInt())
        foodView.setPercent(categoryList[3].percentage.toInt())

        layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        layoutDownRight.addView(foodView, layoutParams)

        var providerList=cheq!!.cheq.providers

        val barChart1 = BarChartView(context)
        barChart1.setTitle(providerList[2].name)
        barChart1.setItems(object : HashMap<String, Int>() {
            init {
                put(providerList[2].months[0].name, providerList[2].months[0].amount.toInt())
                put(providerList[2].months[1].name, providerList[2].months[1].amount.toInt())
                put(providerList[2].months[2].name, providerList[2].months[2].amount.toInt())
                put(providerList[2].months[3].name, providerList[2].months[3].amount.toInt())
            }
        })

        val pieChart1 = PieChartView(context)
        pieChart1.setTitle(providerList[0].name)
        pieChart1.setItems(object : HashMap<String, Int>() {
            init {
                put(providerList[0].months[0].name, providerList[0].months[0].percentage.toInt())
                put(providerList[0].months[1].name, providerList[0].months[1].percentage.toInt())
                put(providerList[0].months[2].name, providerList[0].months[2].percentage.toInt())
                put(providerList[0].months[3].name, providerList[0].months[3].percentage.toInt())
            }
        })

        val barChart2 = BarChartView(context)
        barChart2.setTitle(providerList[1].name)
        barChart2.setItems(object : HashMap<String, Int>() {
            init {
                put(providerList[1].months[0].name, providerList[1].months[0].amount.toInt())
                put(providerList[1].months[1].name, providerList[1].months[1].amount.toInt())
                put(providerList[1].months[2].name, providerList[1].months[2].amount.toInt())
                put(providerList[1].months[3].name, providerList[1].months[3].amount.toInt())
            }
        })
        layoutParams = LinearLayout.LayoutParams(1000, LinearLayout.LayoutParams.MATCH_PARENT)
        layoutParams.setMargins(40,15,40,15)
        carousel.addView(barChart1, layoutParams)

        layoutParams = LinearLayout.LayoutParams(1000, LinearLayout.LayoutParams.MATCH_PARENT)
        layoutParams.setMargins(40,15,40,15)
        carousel.addView(pieChart1, layoutParams)

        layoutParams = LinearLayout.LayoutParams(1000, LinearLayout.LayoutParams.MATCH_PARENT)
        layoutParams.setMargins(40,15,40,15)
        carousel.addView(barChart2, layoutParams)
    }

}


