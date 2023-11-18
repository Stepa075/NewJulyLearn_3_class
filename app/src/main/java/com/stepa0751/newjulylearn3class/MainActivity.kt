package com.stepa0751.newjulylearn3class

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.stepa0751.newjulylearn3class.databinding.ActivityMainBinding
import com.stepa0751.newjulylearn3class.models.DataModel
import com.stepa0751.newjulylearn3class.models.MainViewModel
import java.util.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mViewModel: MainViewModel
    private var timer: CountDownTimer? = null


    var composePrimer1: Int = 0
    var composePrimer2: String = ""
    var composePrimer3: String = ""
    var composePrimer4: String = ""
    var tvTrueAnswer: Int = 0
    var tvFalseAnswer: Int = 0
    var tvTrueAnswer2: Int = 0
    var tvFalseAnswer2: Int = 0
    var tvTrueAnswer3: Int = 0
    var tvFalseAnswer3: Int = 0
    var tvTrueAnswer4: Int = 0
    var tvFalseAnswer4: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setOnClicks()
        changePrimerPlusMinus1()
        changePrimerMultiplyDivide3()
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
    private fun setView() {
        mViewModel.dataLiveData.observe(this, Observer {
            tvTrueAnswer = it.card1RightAnswers
            tvFalseAnswer = it.card1NotRightAnswers
//            tvTrueAnswer2 = it.card2RightAnswers
//            tvFalseAnswer2 = it.card2NotRightAnswers
//            tvTrueAnswer3 = it.card3RightAnswers
//            tvFalseAnswer3 = it.card3NotRightAnswers
//            tvTrueAnswer4 = it.card4RightAnswers
//            tvFalseAnswer4 = it.card4NotRightAnswers
            val true1: String = it.card1RightAnswers.toString()
            binding.tvCard1RightTv.text = "${getString(R.string.pravilno)} $true1"
            val false1: String = it.card1NotRightAnswers.toString()
            binding.tvCard1NotRight.text = "${getString(R.string.nepravilno)} $false1"
//            val true2: String = it.card2RightAnswers.toString()
//            binding.tvCard2Right.text = "${getString(R.string.pravilno)} $true2"
//            val false2: String = it.card2NotRightAnswers.toString()
//            binding.tvCard2NotRight.text = "${getString(R.string.nepravilno)} $false2"
            val true3: String = it.card3RightAnswers.toString()
            binding.tvCard3Right.text = "${getString(R.string.pravilno)} $true3"
            val false3: String = it.card3NotRightAnswers.toString()
            binding.tvCard3NotRight.text = "${getString(R.string.nepravilno)} $false3"
//            val true4: String = it.card4RightAnswers.toString()
//            binding.tvCard4Right.text = "${getString(R.string.pravilno)} $true4"
//            val false4: String = it.card4NotRightAnswers.toString()
//            binding.tvCard4NotRight.text = "${getString(R.string.nepravilno)} $false4"
        })
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
                R.id.bt_card1_ok -> trueOrFalse1()
//                R.id.bt_card2_ok ->
                R.id.bt_card3_ok -> trueOrFalse3()
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
        val id5 = binding.tvCard3Right.text.toString().toInt()
        val id6 = binding.tvCard3NotRight.text.toString().toInt()
//        val id7 = binding.tvCard4Right.text.toString().toInt()
//        val id8 = binding.tvCard4NotRight.text.toString().toInt()


        val items = DataModel(id1, id2, id5, id6)//, id3, id4,, id7, id8
        mViewModel.dataLiveData.value = items
        Log.d("MyLog", "onPause")
    }


    @SuppressLint("SetTextI18n")
    fun changePrimerPlusMinus1() {

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
    fun trueOrFalse1() {

        if (binding.etCard1Answer.text?.toString()?.trim()?.equals("")!!) {
            Toast.makeText(this, "Введите число", Toast.LENGTH_LONG).show()
        } else {
            val answer: String = binding.etCard1Answer.text?.toString()!!
            val trueAnswer = composePrimer1
            if (answer.toInt() == trueAnswer) {
                blink(1)
                tvTrueAnswer += 1
                binding.tvCard1RightTv.text = "${getString(R.string.pravilno)} $tvTrueAnswer"


            } else {
                blink(0)
                tvFalseAnswer += 1
                binding.tvCard1NotRight.text = ""
                binding.tvCard1NotRight.text = "${getString(R.string.nepravilno)} $tvFalseAnswer"

            }

            changePrimerPlusMinus1()
        }
    }




    @SuppressLint("SetTextI18n")
    private fun changePrimerMultiplyDivide3() {
        while (true) {

            val random = Random()
            val chislo1: Int = (1..20).random()//random.nextInt(100)
            val chislo2: Int = (1..9).random()
            var znak1 = ""
            val variant: Int = random.nextInt(2)
            var otvet = 0

            if (variant == 0) {
                otvet = chislo1 * chislo2; znak1 = "*"
            }
            if (variant == 1) {
                otvet = chislo1 / chislo2; znak1 = ":"
            }
            if (otvet > 0 && variant == 0) {
                composePrimer2 = "$chislo1$znak1$chislo2=$otvet"
                bindingClass.cv2tvPrimer.text = "$chislo1$znak1$chislo2"
                bindingClass.cv2etOtvet.text.clear()
                break
            }
            if (otvet > 0 && chislo1 % chislo2 == 0) {
                composePrimer2 = "$chislo1$znak1$chislo2=$otvet"
                bindingClass.cv2tvPrimer.text = "$chislo1$znak1$chislo2"
                bindingClass.cv2etOtvet.text.clear()
                break

            }

        }
    }

    @SuppressLint("SetTextI18n")
    private fun trueOrFalse3() {
        if (bindingClass.cv2etOtvet.text?.toString()?.trim()?.equals("")!!) {
            Toast.makeText(this, "Введите число", Toast.LENGTH_LONG).show()
        } else {
            val answer: String = bindingClass.cv2etOtvet.text.toString()
            val trueAnswer = composePrimer2.substringAfter("=", "0")
            if (answer == trueAnswer) {
                blinkgGreen()
                tvTrueAnswer2 += 1
                val tr1 = tvTrueAnswer2
                bindingClass.cv2tvTrue.text = "Правильно: $tr1"

            } else {
                blinkgRed()
                tvFalseAnswer2 += 1
                val fa1 = tvFalseAnswer2
                bindingClass.cv2tvFalse.text = "Неправильно: $fa1"
                Log.d("Mylog", "countAnswer: $fa1")
            }
            Log.d("Mylog", "answer: $answer")
            Log.d("Mylog", "trueAnswer: $trueAnswer")
            howManyErrors()
            changePrimerMultiplyDivide3()
        }
    }

    private fun blink(number: Int) {
        timer?.cancel()
        var stateBackground = 0
        timer = object : CountDownTimer(1500, 500) {

            @SuppressLint("ResourceAsColor")
            override fun onTick(p0: Long) {
                if (number == 0) {
                    stateBackground = if (stateBackground == 0) {binding.cl1.setBackgroundColor(Color.RED); 1}
                    else {binding.cl1.setBackgroundColor(Color.BLACK); 0}
                }
                else if(number ==1){
                     stateBackground = if (stateBackground == 0){binding.cl1.setBackgroundColor(Color.GREEN); 1}
                     else {binding.cl1.setBackgroundColor(Color.WHITE);0}
                }
            }

            @SuppressLint("ResourceAsColor")
            override fun onFinish() {
                binding.cl1.background = null
                stateBackground = 0
            }
        }.start()
    }
}