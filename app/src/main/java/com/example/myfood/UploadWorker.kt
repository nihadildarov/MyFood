package com.example.myfood

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay


class UploadWorker(appContext: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(appContext, workerParameters) {
    override suspend fun doWork(): Result {
        Log.e("MyTag", "Do work started")
        uploadImages()
        Log.e("MyTag", "Do work ended")

        return Result.success()
    }

    suspend fun uploadImages(): Result {
        Log.e("MyTag", "Upload Images started")
        delay(2000)
        Log.e("MyTag", "Upload Images ended")

        return Result.success()
    }
}