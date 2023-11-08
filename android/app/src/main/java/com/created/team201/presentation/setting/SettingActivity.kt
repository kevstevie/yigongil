package com.created.team201.presentation.setting

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsIntent.SHARE_STATE_OFF
import androidx.core.content.ContextCompat
import androidx.fragment.app.commit
import androidx.recyclerview.widget.DividerItemDecoration
import com.created.team201.BuildConfig
import com.created.team201.R
import com.created.team201.databinding.ActivitySettingBinding
import com.created.team201.presentation.accountSetting.AccountSettingActivity
import com.created.team201.presentation.common.BindingActivity
import com.created.team201.presentation.login.LoginActivity
import com.created.team201.presentation.setting.adapter.SettingAdapter
import com.created.team201.presentation.setting.model.SettingType
import com.created.team201.presentation.setting.model.SettingUiModel
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.UpdateAvailability
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingActivity : BindingActivity<ActivitySettingBinding>(R.layout.activity_setting) {
    private val viewModel: SettingViewModel by viewModels()
    private val settingItems: List<SettingUiModel> by lazy {
        resources.getStringArray(R.array.settingItems).mapIndexed { index, setting ->
            SettingUiModel(index.toLong(), setting)
        }
    }

    private val onSettingItemClick: SettingClickListener by lazy {
        object : SettingClickListener {
            override fun onClick(itemId: Long) {
                when (SettingType.valueOf(itemId)) {
                    SettingType.NOTIFICATION -> Unit
                    SettingType.ACCOUNT -> navigateToAccountSetting()
                    SettingType.POLICY -> navigateToPolicy()
                    SettingType.LOGOUT -> {
                        removeDialog()
                        showDialog(
                            getString(R.string.setting_dialog_logout_title),
                            getString(R.string.setting_dialog_logout_content),
                            object : SettingDialogClickListener {
                                override fun onCancelClick() {
                                }

                                override fun onOkClick() {
                                    viewModel.logout()
                                    navigateToLogin()
                                }
                            },
                        )
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActionBar()
        initVersionInformation()
        initSettingRecyclerView()
    }

    private fun initActionBar() {
        setSupportActionBar(binding.tbSetting)
        supportActionBar?.setHomeActionContentDescription(R.string.toolbar_back_text)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initVersionInformation() {
        binding.tvSettingVersion.text = BuildConfig.VERSION_NAME

        val appUpdateManager = AppUpdateManagerFactory.create(this)
        appUpdateManager.appUpdateInfo.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE) {
                binding.tvSettingVersionUpdate.visibility = View.VISIBLE
                binding.clSettingVersion.setOnClickListener {
                    navigateToPlayStore()
                }
            } else {
                binding.clSettingVersion.setOnClickListener { }
                binding.tvSettingVersionUpdate.visibility = View.GONE
            }
        }
    }

    private fun initSettingRecyclerView() {
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(this, R.drawable.divider_recyclerview_line)?.let {
            decoration.setDrawable(it)
        }

        binding.rvSetting.addItemDecoration(decoration)
        binding.rvSetting.adapter =
            SettingAdapter(onSettingItemClick).also { it.submitList(settingItems) }
    }

    private fun navigateToAccountSetting() {
        startActivity(AccountSettingActivity.getIntent(this))
    }

    private fun showDialog(
        title: String,
        content: String,
        settingDialogClickListener: SettingDialogClickListener,
    ) {
        SettingDialog(title, content, settingDialogClickListener).show(
            supportFragmentManager,
            TAG_DIALOG_LOGOUT,
        )
    }

    private fun removeDialog() {
        supportFragmentManager.findFragmentByTag(TAG_DIALOG_LOGOUT)?.let {
            supportFragmentManager.commit {
                remove(it)
            }
        }
    }

    private fun navigateToLogin() {
        startActivity(
            LoginActivity.getIntent(this).also { it.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP },
        )
        finishAffinity()
    }

    private fun navigateToPolicy() {
        CustomTabsIntent.Builder()
            .setUrlBarHidingEnabled(true)
            .setShowTitle(true)
            .setShareState(SHARE_STATE_OFF)
            .build()
            .launchUrl(this, Uri.parse(BuildConfig.TEAM201_POLICY))
    }

    private fun navigateToPlayStore() {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(URI_MARKET_FORMAT.format(packageName))
            )
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> false
        }

    companion object {
        private const val TAG_DIALOG_LOGOUT = "TAG_DIALOG_LOGOUT"
        private const val URI_MARKET_FORMAT = "market://details?id=%s"

        fun getIntent(context: Context): Intent = Intent(context, SettingActivity::class.java)
    }
}
