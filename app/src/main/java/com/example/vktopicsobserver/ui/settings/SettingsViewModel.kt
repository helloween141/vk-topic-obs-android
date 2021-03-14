package com.example.vktopicsobserver.ui.settings

import android.app.Application
import androidx.lifecycle.*
import com.example.vktopicsobserver.constants.SETTINGS_SAVED
import com.example.vktopicsobserver.domain.repository.SettingsRepository
import com.example.vktopicsobserver.settings.SettingsObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingsViewModel(application: Application) : AndroidViewModel(application) {
    private val _settingsData = MutableLiveData<SettingsObject>()
    private val _settingsRepository = SettingsRepository(application)

    val settingsData: LiveData<SettingsObject> = _settingsData
    var resultMessage: MutableLiveData<String> = MutableLiveData()

    fun load() {
        viewModelScope.launch(Dispatchers.Main) {
            val dbSettings = withContext(Dispatchers.IO) {  _settingsRepository.getAll() }

            SettingsObject.apply {
                commentsSort = dbSettings.comments_sort
                topicsSort = dbSettings.topics_sort
                commentsPerTopic = dbSettings.comments_per_topic
            }
            _settingsData.postValue(SettingsObject)
        }
    }

     fun save() {
        viewModelScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {  _settingsRepository.save() }
            _settingsData.postValue(SettingsObject)
            resultMessage.postValue(SETTINGS_SAVED)
        }
    }
}