package hi.netkaffi.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import hi.netkaffi.databinding.ActivityQrTestBinding
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import hi.netkaffi.R


class QRActivity: AppCompatActivity(){
    private lateinit var binding: ActivityQrTestBinding
    private lateinit var  qrScanner: CodeScanner
    private val  cameraPermission = 1111
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Toast.makeText(this, "Tester_2", Toast.LENGTH_LONG).show()
        binding = ActivityQrTestBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val scannerView = findViewById<CodeScannerView>(R.id.qrScannerView)
        qrScanner = CodeScanner(this, scannerView)
        qrScanner.camera = CodeScanner.CAMERA_BACK
        qrScanner.formats = CodeScanner.ALL_FORMATS
        qrScanner.autoFocusMode = AutoFocusMode.SAFE
        qrScanner.scanMode = ScanMode.SINGLE
        qrScanner.isAutoFocusEnabled =true
        qrScanner.isFlashEnabled = false

        // Callbacks
        qrScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                Toast.makeText(this, "Scan result: ${it.text}", Toast.LENGTH_LONG).show()
            }
        }
        qrScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
            runOnUiThread {
                Toast.makeText(this, "Camera initialization error: ${it.message}",
                    Toast.LENGTH_LONG).show()
            }
        }

        scannerView.setOnClickListener {
            qrScanner.startPreview()
        }
    }
    fun checkPermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),cameraPermission)
        }
        else{
            qrScanner.startPreview()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == cameraPermission&&grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            qrScanner.startPreview()
        }else{
            Toast.makeText(this, "Need camera permission to be able to scan code",Toast.LENGTH_LONG).show()
        }
    }
    override fun onResume() {
        super.onResume()
        qrScanner.startPreview()
    }

    override fun onPause() {
        qrScanner.releaseResources()
        super.onPause()
    }

}

