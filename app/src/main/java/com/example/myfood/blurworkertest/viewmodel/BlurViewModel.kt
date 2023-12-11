package com.example.myfood.blurworkertest.viewmodel

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.myfood.R
import com.example.myfood.blurworkertest.worker.BlurWorker
import com.example.myfood.blurworkertest.worker.KEY_IMAGE_URI

class BlurViewModel(application: Application):ViewModel() {
    internal var imageUri: Uri? = null
    internal var outputUri: Uri? = null
    init {
        // This transformation makes sure that whenever the current work Id changes the WorkInfo
        // the UI is listening to changes
        imageUri = getImageUri(application.applicationContext)
    }

    //Workmanagerin instanceini yaratmaq uchun
    private val workManager = WorkManager.getInstance(application)

    //Go buttonuna clickledikde cagirilacaq method. Burada onetimeworker istifade etmisem.
    internal fun applyBlur(blurLevel: Int) {
        //workManager.enqueue(OneTimeWorkRequest.from(BlurWorker::class.java))

            val blurRequest = OneTimeWorkRequestBuilder<BlurWorker>()
                .setInputData(createInputDataForUri())
                .build()

            workManager.enqueue(blurRequest)

    }

    private fun createInputDataForUri(): Data {
        val builder = Data.Builder()
        imageUri?.let {
            builder.putString(KEY_IMAGE_URI, imageUri.toString())
        }
        return builder.build()
    }

    private fun uriOrNull(uriString: String?): Uri? {
        return if (!uriString.isNullOrEmpty()) {
            Uri.parse(uriString)
        } else {
            null
        }
    }

    private fun getImageUri(context: Context): Uri {
        val resources = context.resources

        val imageUri = Uri.Builder()
            .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
            .authority(resources.getResourcePackageName(R.drawable.my_ss))
            .appendPath(resources.getResourceTypeName(R.drawable.my_ss))
            .appendPath(resources.getResourceEntryName(R.drawable.my_ss))
            .build()

        return imageUri
    }

    internal fun setOutputUri(outputImageUri: String?) {
        outputUri = uriOrNull(outputImageUri)
    }

}