package com.created.team201.presentation.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import com.created.team201.R
import com.created.team201.databinding.ActivitySplashBinding
import com.created.team201.presentation.MainActivity
import com.created.team201.presentation.common.BindingActivity
import com.created.team201.presentation.login.LoginActivity
import com.created.team201.presentation.onBoarding.model.OnBoardingDoneState
import com.created.team201.presentation.splash.SplashViewModel.State.FAIL
import com.created.team201.presentation.splash.SplashViewModel.State.IDLE
import com.created.team201.presentation.splash.SplashViewModel.State.SUCCESS

@SuppressLint("CustomSplashScreen")
class SplashActivity : BindingActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    private val splashViewModel by viewModels<SplashViewModel> { SplashViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeLoginState()
        observeOnBoardingDoneState()
    }

    private fun observeLoginState() {
        splashViewModel.loginState.observe(this@SplashActivity) { loginState ->
            when (loginState) {
                SUCCESS -> {
                    splashViewModel.getIsOnboardingDone()
                }

                FAIL -> navigateToLogin()
                IDLE -> throw IllegalStateException()
            }
        }
    }

    private fun observeOnBoardingDoneState() {
        splashViewModel.onBoardingDoneState.observe(this@SplashActivity) { onBoardingState ->
            when (onBoardingState) {
                is OnBoardingDoneState.Success -> {
                    if (onBoardingState.isDone) {
                        navigateToMain()
                        return@observe
                    }
                    navigateToLogin()
                }

                OnBoardingDoneState.FAIL -> navigateToLogin()
                OnBoardingDoneState.IDLE -> throw IllegalStateException()
            }
        }
    }

    private fun navigateToMain() {
        startActivity(MainActivity.getIntent(this))
        finish()
    }

    private fun navigateToLogin() {
        startActivity(LoginActivity.getIntent(this))
        finish()
    }
}