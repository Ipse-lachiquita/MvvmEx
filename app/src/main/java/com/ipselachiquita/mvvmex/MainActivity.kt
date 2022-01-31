package com.ipselachiquita.mvvmex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ipselachiquita.mvvmex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG: String = "로그"
    }

    //전역 변수로 바인딩 객체 선언
    private var mBinding: ActivityMainBinding? = null
    //매번 null 체크를 할 필요 없이 편의성을 위해 바인딩 변수 재 선언
    private val binding get() = mBinding!!

    //나중에 값이 설정 될 거 라고 lateinit 으로 설정
    lateinit var myNumberViewModel: MyNumberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //자동 생성된 뷰 바인딩 클래스에서의 inflate라는 메서드를 활용해서
        //액티비티에서 사용할 바인딩 클래스의 인스턴스 생성
        mBinding = ActivityMainBinding.inflate(layoutInflater)

        //getRoot 메서드로 레이아웃 내부의 최상위 위치 뷰의
        //인스턴스를 활용하여 생성도니 뷰를 액티비티에 표시 합니다.
        setContentView(binding.root)

        //뷰 모델 프로바이더를 통해 뷰모델 가져오기
        //라이프사이클을 가지고 있는 녀석을 넣어줌 즉 자기 자신
        // 우리가 가져오고 싶은 뷰모델 클래스를 넣어 뷰모델을 가져오기
        myNumberViewModel = ViewModelProvider(this)[MyNumberViewModel::class.java]

        //뷰모델이 가지고 있는 값의 변경사항을 관찰할 수 있는 라이브 데이터를 옵저빙한다.
        myNumberViewModel.currentValue.observe(this, Observer {
            Log.d(TAG, "MainActivity - MyNumberViewMode - currentValue 라이브 데이터 값 변경 : $it")
            binding.numberText.text = it.toString()
        })

        binding.plusBtn.setOnClickListener(this)
        binding.minusBtn.setOnClickListener(this)
    }

    //click
    override fun onClick(view: View?) {
        val userInput: Int = binding.userinputEdittext.text.toString().toInt()

        //뷰모델에 라이브데이터 값을 변경하는 메소드 실행
        when(view) {
            binding.plusBtn -> {
                myNumberViewModel.updateValue(actionType = ActionType.PLUS, userInput)
            }
            binding.minusBtn -> {
                myNumberViewModel.updateValue(actionType = ActionType.MINUS, userInput)
            }
        }
    }

    //액티비티가 파괴될 때..
    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }
}