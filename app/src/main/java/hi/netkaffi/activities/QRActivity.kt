package hi.netkaffi.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.PersistableBundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
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
import hi.netkaffi.databinding.ActivityQrCameraBinding


class QRActivity: AppCompatActivity() {
    private lateinit var binding: ActivityQrCameraBinding

    private lateinit var qrScanner: CodeScanner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Toast.makeText(this, "Tester_2", Toast.LENGTH_LONG).show()
        binding = ActivityQrCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 123)
        } else {
            startScanning()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this,"Im going back", Toast.LENGTH_LONG).show()
    }
    private fun startScanning() {
        val scannerView: CodeScannerView = findViewById(R.id.qrScannerView)
        qrScanner = CodeScanner(this, scannerView)
        qrScanner.camera = CodeScanner.CAMERA_BACK
        qrScanner.formats = CodeScanner.ALL_FORMATS
        qrScanner.autoFocusMode = AutoFocusMode.SAFE
        qrScanner.scanMode = ScanMode.SINGLE
        qrScanner.isAutoFocusEnabled = true
        qrScanner.isFlashEnabled = false
        qrScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                Toast.makeText(this, "${it.text}", Toast.LENGTH_SHORT).show()
                //val textView: TextView = findViewById(R.id.qrResults)
                //textView.movementMethod = LinkMovementMethod.getInstance()
                //textView.text = Html.fromHtml("${it.text}")

                val intentBooking= Intent(this, BookingActivity::class.java)
                intentBooking.putExtra("product","${it.text}")
                startActivity(intentBooking)

            }
        }
        qrScanner.errorCallback = ErrorCallback {
            runOnUiThread {
                Toast.makeText(this, "Camera initilization error ${it.message}", Toast.LENGTH_LONG)
                    .show()
            }
        }
        scannerView.setOnClickListener {
            qrScanner.startPreview()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 123 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Camera permission granted", Toast.LENGTH_LONG).show()
            startScanning()
        } else {
            Toast.makeText(
                this,
                "Need camera permission to be able to scan code",
                Toast.LENGTH_LONG
            ).show()
        }

    }
    override fun onResume() {
        super.onResume()
        if (::qrScanner.isInitialized){
            qrScanner.startPreview()
        }
    }

    override fun onPause() {
        if (::qrScanner.isInitialized){
            qrScanner.releaseResources()
        }
        super.onPause()
    }
}

