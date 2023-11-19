package com.stepa0751.newjulylearn3class

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
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
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.etCard1Answer.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {
                    trueOrFalse1()
                    true
                }
                else -> false
            }
        }

//        binding.etCard2Answer.setOnEditorActionListener { v, actionId, event ->
//            return@setOnEditorActionListener when (actionId) {
//                EditorInfo.IME_ACTION_SEND -> {
//                    trueOrFalse2()
//                    true
//                }
//                else -> false
//            }
//        }

        binding.etCard3Answer.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {
                    trueOrFalse3()
                    true
                }
                else -> false
            }
        }

        setOnClicks()
        changePrimerPlusMinus1()
        changePrimerMultiplyDivide3()
        changePrimerMultiplyDividePlusMinus4()
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
            tvTrueAnswer3 = it.card3RightAnswers
            tvFalseAnswer3 = it.card3NotRightAnswers
            tvTrueAnswer4 = it.card4RightAnswers
            tvFalseAnswer4 = it.card4NotRightAnswers
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
            val true4: String = it.card4RightAnswers.toString()
            binding.tvCard4Right.text = "${getString(R.string.pravilno)} $true4"
            val false4: String = it.card4NotRightAnswers.toString()
            binding.tvCard4NotRight.text = "${getString(R.string.nepravilno)} $false4"
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
                R.id.im_card4_more -> trueOrFalse4("<")
                R.id.im_card4_equals -> trueOrFalse4("=")
                R.id.im_card4_less -> trueOrFalse4(">")

            }
        }
    }

    override fun onPause() {
        super.onPause()
        val id1 = tvTrueAnswer
        val id2 = tvFalseAnswer
//        val id3 = binding.tvCard2Right.text.toString().toInt()
//        val id4 = binding.tvCard2NotRight.text.toString().toInt()
        val id5 = tvTrueAnswer3
        val id6 = tvFalseAnswer3
        val id7 = tvTrueAnswer4
        val id8 = tvFalseAnswer4


        val items = DataModel(id1, id2, id5, id6, id7, id8)//, id3, id4,
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
                binding.tvCard1RightTv.text = ""
                binding.tvCard1RightTv.text = "Правильно: $tvTrueAnswer"


            } else {
                blink(0)
                tvFalseAnswer += 1
                binding.tvCard1NotRight.text = ""
                binding.tvCard1NotRight.text = "Неправильно: $tvFalseAnswer"

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
                composePrimer3 = "$chislo1$znak1$chislo2=$otvet"
                binding.tvCard3Example.text = "$chislo1$znak1$chislo2"
                binding.etCard3Answer.text.clear()
                break
            }
            if (otvet > 0 && chislo1 % chislo2 == 0) {
                composePrimer3 = "$chislo1$znak1$chislo2=$otvet"
                binding.tvCard3Example.text = "$chislo1$znak1$chislo2"
                binding.etCard3Answer.text.clear()
                break

            }

        }
    }

    @SuppressLint("SetTextI18n")
    private fun trueOrFalse3() {
        if (binding.etCard3Answer.text?.toString()?.trim()?.equals("")!!) {
            Toast.makeText(this, "Введите число", Toast.LENGTH_LONG).show()
        } else {
            val answer: String = binding.etCard3Answer.text.toString()
            val trueAnswer = composePrimer3.substringAfter("=", "0")
            if (answer == trueAnswer) {
                blink(1)
                tvTrueAnswer3 += 1
                val tr1 = tvTrueAnswer3
                binding.tvCard3Right.text = ""
                binding.tvCard3Right.text = "Правильно: $tr1"

            } else {
                blink(0)
                tvFalseAnswer3 += 1
                val fa1 = tvFalseAnswer3
                binding.tvCard3NotRight.text = ""
                binding.tvCard3NotRight.text = "Неправильно: $fa1"
                Log.d("Mylog", "countAnswer: $fa1")
            }
            changePrimerMultiplyDivide3()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun changePrimerMultiplyDividePlusMinus4() {
        while (true) {

            val random = Random()
            val chislo1: Int = (1..20).random()//random.nextInt(100)
            val chislo2: Int = (1..9).random()
            val chislo3: Int = (1..20).random()
            val chislo4: Int = (1..20).random()
            var znak1 = ""
            var znak2 = ""
            val variant: Int = random.nextInt(4)
            val variant2: Int = random.nextInt(4)
            var otvet = 0
            var otvet2 = 0
            if (variant == 0) {
                otvet = chislo1 * chislo2; znak1 = "*"
            }
            if (variant == 1) {
                otvet = chislo1 / chislo2; znak1 = ":"
            }
            if (variant == 2) {
                otvet = chislo1 - chislo2; znak1 = "-"
            }
            if (variant == 3) {
                otvet = chislo1 + chislo2; znak1 = "+"
            }
            if (variant2 == 0) {
                otvet2 = chislo3 * chislo4; znak2 = "*"
            }
            if (variant2 == 1) {
                otvet2 = chislo3 / chislo4; znak2 = ":"
            }
            if (variant2 == 2) {
                otvet2 = chislo3 - chislo4; znak2 = "-"
            }
            if (variant2 == 3) {
                otvet2 = chislo3 + chislo4; znak2 = "+"
            }
            if (otvet > 0 && otvet2 > 0) {                                   // Если результат примера больше нуля
                if((variant == 1 && chislo1 % chislo2 == 0) && variant2 != 1){ //Если первый вариант - деление, а второй - нет.
                    composePrimer4 = when {
                        otvet > otvet2 -> ">"
                        otvet < otvet2 -> "<"
                        else -> "="
                    }
                    binding.tvCard4Example.text = "$chislo1$znak1$chislo2 ? $chislo3$znak2$chislo4"
                    break
                }
                if((variant != 1)&&(variant2 == 1 && chislo3 % chislo4 == 0)){ // Если первый не деление а второй вариант деление...
                    composePrimer4 = when {
                        otvet > otvet2 -> ">"
                        otvet < otvet2 -> "<"
                        else -> "="
                    }
                    binding.tvCard4Example.text = "$chislo1$znak1$chislo2 ? $chislo3$znak2$chislo4"
                    break
                }
                if (( variant == 1 && chislo1 % chislo2 == 0) &&  //  ЕСли оба варианта деление
                    (variant2 == 1 && chislo3 % chislo4 == 0)
                ) {
                    composePrimer4 = when {
                        otvet > otvet2 -> ">"
                        otvet < otvet2 -> "<"
                        else -> "="
                    }
                    binding.tvCard4Example.text = "$chislo1$znak1$chislo2 ? $chislo3$znak2$chislo4"
                    break
                }
                if(variant != 1 && variant2 != 1){
                    composePrimer4 = when {         // Все остальные варианты
                        otvet > otvet2 -> ">"
                        otvet < otvet2 -> "<"
                        else -> "="
                    }
                    binding.tvCard4Example.text = "$chislo1$znak1$chislo2 ? $chislo3$znak2$chislo4"
                    break
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun trueOrFalse4(answer1: String) {

        val answer: String = answer1
        val realAnswer = composePrimer4

        if (answer == realAnswer) {
            blink(1)
            tvTrueAnswer4 += 1
            val tr1 = tvTrueAnswer4
            binding.tvCard4Right.text = ""
            binding.tvCard4Right.text = "Правильно: $tr1"

        } else {
            blink(0)
            tvFalseAnswer4 += 1
            val fa1 = tvFalseAnswer4
            binding.tvCard4NotRight.text = ""
            binding.tvCard4NotRight.text = "Неправильно: $fa1"

        }
        changePrimerMultiplyDividePlusMinus4()

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