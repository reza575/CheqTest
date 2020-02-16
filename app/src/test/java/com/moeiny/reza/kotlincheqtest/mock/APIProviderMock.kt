package com.moeiny.reza.kotlincheqtest.mock

import com.google.gson.Gson
import com.moeiny.reza.cheqtest.data.Cheq

object CheqAPISearchMock {
   private val cheqPackMockString: String ="{\"cheq\":{\"providers\" : [{\"name\": \"CommomBank\", \"months\" :[{\"name\" : \"June\", \"amount\" : \"200\",\"percentage\":\"25\"},{\"name\" : \"July\", \"amount\" : \"250\",\"percentage\":\"15\"},{\"name\" : \"Auguest\", \"amount\" : \"500\",\"percentage\":\"20\"},{\"name\" : \"September\", \"amount\" : \"800\",\"percentage\":\"40\"}]},{\"name\": \"Nab\", \"months\" :[{\"name\" : \"June\", \"amount\" : \"500\",\"percentage\":\"25\"},{\"name\" : \"July\", \"amount\" : \"600\",\"percentage\":\"15\"},{\"name\" : \"Auguest\", \"amount\" : \"300\",\"percentage\":\"20\"},{\"name\" : \"September\", \"amount\" : \"750\",\"percentage\":\"40\"}]},{\"name\": \"St Gorge\", \"months\" :[{\"name\" : \"June\", \"amount\" : \"300\",\"percentage\":\"25\"},{\"name\" : \"July\", \"amount\" : \"350\",\"percentage\":\"15\"},{\"name\" : \"Auguest\", \"amount\" : \"650\",\"percentage\":\"20\"},{\"name\" : \"September\", \"amount\" : \"480\",\"percentage\":\"40\"}]}],\"category\": [{\"name\" : \"household\", \"amount\":\"500\", \"percentage\" : \"20\"},{\"name\" : \"grocery\", \"amount\":\"600\", \"percentage\" : \"40\"},{\"name\" : \"transport\", \"amount\":\"700\", \"percentage\" : \"60\"},{\"name\" : \"Food\", \"amount\":\"300\", \"percentage\" : \"80\"}]}}"

    val cheqMock =  Gson().fromJson(cheqPackMockString, Cheq::class.java)

}