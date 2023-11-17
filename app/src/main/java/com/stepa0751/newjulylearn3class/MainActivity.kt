package com.stepa0751.newjulylearn3class

import android.annotation.SuppressLint

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.stepa0751.newjulylearn3class.databinding.ActivityMainBinding
import com.stepa0751.newjulylearn3class.models.DataModel
import com.stepa0751.newjulylearn3class.models.MainViewModel
import java.util.Random
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mViewModel: MainViewModel


    var composePrimer1: Int = 0
    var composePrimer2: String = ""
    var composePrimer3: String = ""
    var composePrimer4: String = ""
    var tvTrueAnswer : Int = 0
    var tvFalseAnswer : Int = 0
    var tvTrueAnswer2 : Int = 0
    var tvFalseAnswer2 : Int = 0
    var tvTrueAnswer3 : Int = 0
    var tvFalseAnswer3 : Int = 0
    var tvTrueAnswer4 : Int = 0
    var tvFalseAnswer4 : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setOnClicks()
        changePrimerPlusMinus()

    }





    override fun onStop() {
        super.onStop()
        Log.d("MyLog", "OnStop")
    }

    override fun onStart() {
        super.onStart()
        Log.d("MyLog", "OnStart")
        setView()
    }



    @SuppressLint("SetTextI18n")
    private fun setView()  {
        mViewModel.dataLiveData.observe(this, Observer {


            tvTrueAnswer = it.card1RightAnswers
            tvFalseAnswer = it.card1NotRightAnswers
//            binding.tvCard2Right.text = it.card2RightAnswers.toString()
//            binding.tvCard2NotRight.text = it.card2NotRightAnswers.toString()
//            binding.tvCard3Right.text = it.card3RightAnswers.toString()
//            binding.tvCard3NotRight.text = it.card3NotRightAnswers.toString()
//            binding.tvCard4Right.text = it.card4RightAnswers.toString()
//            binding.tvCard4NotRight.text = it.card4NotRightAnswers.toString()
            val zzz:String = it.card1RightAnswers.toString()
            binding.tvCard1RightTv.text = "${getString(R.string.pravilno)} $zzz"
            val sss: String =  it.card1NotRightAnswers.toString()
            binding.tvCard1NotRight.text = "${getString(R.string.nepravilno)} $sss"
        })
//        val sstr1 = StringBuilder(R.string.pravilno).append(tvTrueAnswer).toString()
//        binding.tvCard1RightTv.text = sstr1
//        val sstr2 = StringBuilder(R.string.nepravilno).append(tvFalseAnswer).toString()
//        binding.tvCard1NotRight.text = sstr2
    }


    private fun setOnClicks() = with(binding) {
        val listener = onClicks()
        btCard1Ok.setOnClickListener(listener)
        btCard2Ok.setOnClickListener(listener)
        btCard3Ok.setOnClickListener(listener)
        imCard4More.setOnClickListener(listener)
        imCard4Equals.setOnClickListener(listener)
        imCard4Less.setOnClickListener(listener)
    }

    private fun onClicks(): View.OnClickListener {
        return View.OnClickListener {
            when (it.id) {
                R.id.bt_card1_ok -> trueOrFalse()
//                R.id.bt_card2_ok ->
//                R.id.bt_card3_ok ->
//                R.id.im_card4_more ->
//                R.id.im_card4_equals ->
//                R.id.im_card4_less ->

            }
        }
    }

    override fun onPause() {
        super.onPause()
        val id1 = tvTrueAnswer
        val id2 = tvFalseAnswer
//        val id3 = binding.tvCard2Right.text.toString().toInt()
//        val id4 = binding.tvCard2NotRight.text.toString().toInt()
//        val id5 = binding.tvCard3Right.text.toString().toInt()
//        val id6 = binding.tvCard3NotRight.text.toString().toInt()
//        val id7 = binding.tvCard4Right.text.toString().toInt()
//        val id8 = binding.tvCard4NotRight.text.toString().toInt()


        val items = DataModel( id1, id2)//, id3, id4, id5, id6, id7,  id8
        mViewModel.dataLiveData.value = items

    }




    @SuppressLint("SetTextI18n")
    fun changePrimerPlusMinus() {

        while (true) {

            val random = Random()
            val chislo1: Int = (1..100).random()//random.nextInt(100)
            val chislo2: Int = (1..100).random()
            val chislo3: Int = (1..100).random()
            var znak1 = ""
            var znak2 = ""
            val variant: Int = random.nextInt(4)
            var otvet = 0
            if (variant == 0) {
                otvet = chislo1 + chislo2 + chislo3; znak1 = "+"; znak2 = "+"
            }
            if (variant == 1) {
                otvet = chislo1 + chislo2 - chislo3; znak1 = "+"; znak2 = "-"
            }
            if (variant == 2) {
                otvet = chislo1 - chislo2 + chislo3; znak1 = "-"; znak2 = "+"
            }
            if (variant == 3) {
                otvet = chislo1 - chislo2 - chislo3; znak1 = "-"; znak2 = "-"
            }
            if (otvet > 0) {

                composePrimer1 = otvet
//              composePrimer = "$chislo1$znak1$chislo2$znak2$chislo3=$otvet"
                binding.tvCard1Example.text = "$chislo1$znak1$chislo2$znak2$chislo3"
                binding.etCard1Answer.text.clear()
                break

            }

        }

    }

    @SuppressLint("SetTextI18n")
    fun trueOrFalse() {

        if (binding.etCard1Answer.text?.toString()?.trim()?.equals("")!!) {
            Toast.makeText(this, "Введите число", Toast.LENGTH_LONG).show()
        } else {
            val answer: String = binding.etCard1Answer.text?.toString()!!
            val trueAnswer = composePrimer1
            if (answer.toInt() == trueAnswer) {
//            blinkgGreen()
//            binding.tvCard1RightTv += 1
                tvTrueAnswer += 1
                binding.tvCard1RightTv.text ="${getString(R.string.pravilno)} $tvTrueAnswer"
//            bindingClass.tvTrue.text = "Правильно: $tr"

            } else {
//            blinkgRed()
                tvFalseAnswer += 1
                binding.tvCard1NotRight.text = ""
                binding.tvCard1NotRight.text = "${getString(R.string.nepravilno)} $tvFalseAnswer"
//            bindingClass.tvFalse.text = "Неправильно: $fa"
            }
//            Log.d("Mylog", "answer: $answer")
//            Log.d("Mylog", "trueAnswer: $trueAnswer")
//        howManyErrors()
            changePrimerPlusMinus()
        }
    }



}